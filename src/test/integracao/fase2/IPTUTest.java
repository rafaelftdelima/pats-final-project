package test.integracao.fase2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.MockedStatic;

import main.dao.ImovelDAO;
import main.model.IPTU;
import main.model.Imovel;
import main.model.ImovelDTO;
import main.model.ValorIPTU;

@DisplayName("Classe de Testes Integrados 2: IPTU-Imovel com criação de mock de ImovelDAO")
public class IPTUTest {
	@DisplayName("Calcula IPTU de imóvel através de Imovel.getByID")
	@ParameterizedTest(name="ID={0}, Inscrição={1}, Data de liberação={2}, Valor do imóvel={3}, "
			+ "Área={4}, Categoria={5}, Valor do IPTU={6}, Valor do IPTU à vista={7}, Número de parcelas={8}, "
			+ "Valor da parcela={9}, Número de parcelas={10}, Total do parcelamento={11}, "
			+ "Desconto (pagamento à vista)={12}, Juros (parcelamento)={13}")
	@CsvFileSource(resources="..\\..\\files\\iptu-detalhado.csv", delimiter=',')
	public void mustCalculateIPTU_GetImovelByID(int id, int inscricao, String dateString, float valorImovel, int area,
			char categoria, float valorIPTU, float valorIPTUAVista, float valorParcelaIPTU, int numeroParcelas, 
			float valorTotalParcelamentoIPTU, float percDescontoPagamentoAVista, float percJurosParcelamento) {
		LocalDate dataLiberacao = LocalDate.parse(dateString);
		
		try(MockedStatic<ImovelDAO> imovelDAO = mockStatic(ImovelDAO.class)) {
			ImovelDTO imovelDTO = new ImovelDTO();
			
			imovelDTO.id = id;
			imovelDTO.inscricao = inscricao;
			imovelDTO.dataLiberacao = dataLiberacao;
			imovelDTO.valor = valorImovel;
			imovelDTO.area = area;
			imovelDTO.categoria = categoria;
			
			imovelDAO.when(() -> ImovelDAO.getByID(id)).thenReturn(imovelDTO);
			
			Imovel imovel = new Imovel(ImovelDAO.getByID(id));
			IPTU iptu = new IPTU(imovel);
			
			ValorIPTU objectValorIPTU = iptu.calculaValor(percDescontoPagamentoAVista, percJurosParcelamento);
			
			assertEquals(valorIPTU, objectValorIPTU.valor, 0f, 
					"Valor do IPTU diverge do esperado");
			assertEquals(valorIPTUAVista, objectValorIPTU.valorAVista, 0f, 
					"Valor do IPTU diverge do esperado");
			assertEquals(valorParcelaIPTU, objectValorIPTU.parcelamento.getValorParcela(), 0f, 
					"Valor da parcela de parcelamento do IPTU diverge do esperado");
			assertEquals(numeroParcelas, objectValorIPTU.parcelamento.getNumParcelas(), 0f, 
					"Número de parcelas de pagamento do IPTU diverge do esperado");
			assertEquals(valorTotalParcelamentoIPTU, objectValorIPTU.parcelamento.getValorTotal(), 0f, 
					"Valor total do parcelamento do IPTU diverge do esperado");
		}
	}
	
	@DisplayName("Calcula IPTU de imóvel através de Imovel.getBy")
	@ParameterizedTest(name="ID={0}, Inscrição={1}, Data de liberação={2}, Valor do imóvel={3}, "
			+ "Área={4}, Categoria={5}, Valor do IPTU={6}, Valor do IPTU à vista={7}, Número de parcelas={8}, "
			+ "Valor da parcela={9}, Número de parcelas={10}, Total do parcelamento={11}, "
			+ "Desconto (pagamento à vista)={12}, Juros (parcelamento)={13}")
	@CsvFileSource(resources="..\\..\\files\\iptu-detalhado.csv", delimiter=',')
	public void mustCalculateIPTU_GetImovelByInscricao(int id, int inscricao, String dateString, float valorImovel, 
			int area, char categoria, float valorIPTU, float valorIPTUAVista, float valorParcelaIPTU, 
			int numeroParcelas, float valorTotalParcelamentoIPTU, float percDescontoPagamentoAVista, 
			float percJurosParcelamento) {
		LocalDate dataLiberacao = LocalDate.parse(dateString);
		
		try(MockedStatic<ImovelDAO> imovelDAO = mockStatic(ImovelDAO.class)) {
			ImovelDTO imovelDTO = new ImovelDTO();
			
			imovelDTO.id = id;
			imovelDTO.inscricao = inscricao;
			imovelDTO.dataLiberacao = dataLiberacao;
			imovelDTO.valor = valorImovel;
			imovelDTO.area = area;
			imovelDTO.categoria = categoria;
			
			imovelDAO.when(() -> ImovelDAO.getByInscricao(inscricao)).thenReturn(imovelDTO);
			
			Imovel imovel = new Imovel(ImovelDAO.getByInscricao(inscricao));
			IPTU iptu = new IPTU(imovel);
			
			ValorIPTU objectValorIPTU = iptu.calculaValor(percDescontoPagamentoAVista, percJurosParcelamento);
			
			assertEquals(valorIPTU, objectValorIPTU.valor, 0f, 
					"Valor do IPTU diverge do esperado");
			assertEquals(valorIPTUAVista, objectValorIPTU.valorAVista, 0f, 
					"Valor do IPTU diverge do esperado");
			assertEquals(valorParcelaIPTU, objectValorIPTU.parcelamento.getValorParcela(), 0f, 
					"Valor da parcela de parcelamento do IPTU diverge do esperado");
			assertEquals(numeroParcelas, objectValorIPTU.parcelamento.getNumParcelas(), 0f, 
					"Número de parcelas de pagamento do IPTU diverge do esperado");
			assertEquals(valorTotalParcelamentoIPTU, objectValorIPTU.parcelamento.getValorTotal(), 0f, 
					"Valor total do parcelamento do IPTU diverge do esperado");
		}
	}
}
