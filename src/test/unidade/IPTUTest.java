package test.unidade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import main.model.IPTU;
import main.model.Imovel;
import main.model.ValorIPTU;

@ExtendWith(MockitoExtension.class)
@DisplayName("Classe de Testes Unitários 6: IPTU")
public class IPTUTest {
	@DisplayName("Argumento percDescontoVista inválido deve provocar exceção")
	@ParameterizedTest(name="Percentual de desconto={0}")
	@ValueSource(floats = {-0.01f, 100.01f})
	public void mustThrowExceptionInvalidPercDesconto(float percDescontoVista) {
		assertThrows(IllegalArgumentException.class, 
				() -> new IPTU(new Imovel(21110520, LocalDate.parse("1868-07-17"), 16780000f, 90, 'A'))
				.calculaValor(percDescontoVista, 10f), 
				"Tentativa de cálculo de IPTU com percDescontoVista inválido não provocou exceção");
	}

	@DisplayName("Argumento percJurosParcelamento inválido deve provocar exceção")
	@Test
    public void mustThrowExceptionInvalidPercJuros() {
		assertThrows(IllegalArgumentException.class, 
				() -> new IPTU(new Imovel(21110520, LocalDate.parse("1868-07-17"), 16780000f, 90, 'A'))
				.calculaValor(2.5f, -0.01f), 
				"Tentativa de cálculo de IPTU com percJurosParcelamento inválido não provocou exceção");
    }
	
	@DisplayName("Calcula IPTU de imóvel")
	@ParameterizedTest(name="Data de liberação (yyyy-MM-dd)={0}, Valor do imóvel={1}, Área={2}, Categoria={3}, "
			+ "Valor do IPTU={4}, Valor do IPTU à vista={5}, Número de parcelas={6}, Total do parcelamento={7}, "
			+ "Desconto (pagamento à vista)={8}, Juros (parcelamento)={9}")
	@CsvSource({
		"1868-07-17,16780000f,90,A,3230.15f,2842.53f,10,324.9f,3249f,12f,0.7f"
	})
	public void mustCalculateIPTU_1(String dateString, float valorImovel, int area, char categoria, 
			float valorIPTU, float valorAVista, int numParcelas, float valorParcela, float valorTotalParcelas, 
			float percDesconto, float percJuros) {
		LocalDate dataLiberacao = LocalDate.parse(dateString);
		
		Imovel imovel = Mockito.mock(Imovel.class);
		
		when(imovel.getDataLiberacao()).thenReturn(dataLiberacao);
		when(imovel.getValor()).thenReturn(valorImovel);
		when(imovel.getArea()).thenReturn(area);
		when(imovel.getCategoria()).thenReturn(categoria);
		
		IPTU iptu = new IPTU(imovel);
		
		ValorIPTU objectValorIPTU = iptu.calculaValor(percDesconto, percJuros);

		verify(imovel).getDataLiberacao();
		verify(imovel, times(2)).getArea();
		verify(imovel).getValor();
		verify(imovel, atLeast(1)).getCategoria();
		
		assertEquals(valorIPTU, objectValorIPTU.valor, 0f, 
				"Valor de IPTU diverge do esperado");
		assertEquals(valorAVista, objectValorIPTU.valorAVista, 0f, 
				"Valor de pagamento à vista diverge do esperado");
		assertEquals(numParcelas, objectValorIPTU.parcelamento.getNumParcelas(), 0, 
				"Número de parcelas diverge do esperado");
		assertEquals(valorParcela, objectValorIPTU.parcelamento.getValorParcela(), 0f, 
				"Valor de parcela diverge do esperado");
		assertEquals(valorTotalParcelas, objectValorIPTU.parcelamento.getValorTotal(), 0f, 
				"Valor total diverge do esperado");
	}
}
