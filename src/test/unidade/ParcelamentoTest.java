package test.unidade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import main.model.Parcelamento;

@DisplayName("Classe de Testes Unitários 4: Parcelamento")
public class ParcelamentoTest {
	@DisplayName("Valor de IPTU inválido provoca exceção")
	@ParameterizedTest(name="Valor do IPTU (inválido)={0}")
	@ValueSource(floats = {-1f, 0f})
	public void mustThrowExceptionInvalidValorIPTU(float valorIPTU) {
		assertThrows(IllegalArgumentException.class, () -> new Parcelamento(valorIPTU, 0),
				"Valor de IPTU inválido não provocou exceção");
	}

	@DisplayName("Percentagem de juros inválida provoca exceção")
	@Test
	public void mustThrowExceptionInvalidPercJuros() {
		assertThrows(IllegalArgumentException.class, () -> new Parcelamento(300f, -1f),
				"Percentual de juros inválido não provocou exceção");
	}

	@DisplayName("Calcula número de parcelas corretamente")
	@ParameterizedTest(name="Valor do IPTU={0}, Juros={1}, Número de parcelas={2}, Valor da parcela={3}, "
			+ "Valor total do parcelamento={4}")
	@CsvSource({
		"300f,0f,3,100f,300f",
		"300f,2f,3,100.50f,301.5f",
		"300.01f,0f,5,60f,300f",
		"300.01f,1f,5,60.25f,301.25f",
		"1000f,0f,5,200f,1000f",
		"1000f,2.5f,5,202.07f,1010.35f",
		"1000.01f,0f,10,100f,1000f",
		"1000.01f,3f,10,102.49f,1024.9f",
	})
	public void mustCalculateParcelamento(float valorIPTU, float percJuros, int numParcelas, float valorParcela, 
			float valorTotalParcelamento) {
		Parcelamento parcelamento = new Parcelamento(valorIPTU, percJuros);
		
		assertEquals(numParcelas, parcelamento.getNumParcelas(), 0f, "Número de parcelas diverge do esperado");
		assertEquals(valorParcela, parcelamento.getValorParcela(), 0f, "Valor de parcela diverge do esperado");
		assertEquals(valorTotalParcelamento, parcelamento.getValorTotal(), 0f, 
				"Valor total de parcelamento diverge do esperado");
	}
}
