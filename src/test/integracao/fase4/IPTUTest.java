package test.integracao.fase4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import main.dao.ImovelDAO;
import main.db.DBConnection;
import main.model.IPTU;
import main.model.Imovel;
import main.model.ImovelDTO;
import main.model.ValorIPTU;

@DisplayName("Classe de Testes Integrados 4: IPTU-ImovelDAO com consultas diretas ao BD")
public class IPTUTest {
	private static final String DRIVER = "org.sqlite.JDBC";
    private static final String CONEXAO = "jdbc:sqlite:.\\src\\iptu.db";
    
    @BeforeEach
    public void beforeEachTest() {
    	DBConnection.set(DRIVER, CONEXAO);
    }
    
    @AfterEach
    public void afterEachTest() {
    	if (DBConnection.isAlive()) {
    		DBConnection.close();
    	}
    }
    
	@DisplayName("Calcula IPTU de imóvel registrado em BD via Imovel::getById")
	@ParameterizedTest(name="ID={0}, Inscrição={1}, Data de liberação={2}, Valor do imóvel={3}, "
			+ "Área={4}, Categoria={5}, Valor do IPTU={6}, Valor do IPTU à vista={7}, Número de parcelas={8}, "
			+ "Valor da parcela={9}, Número de parcelas={10}, Total do parcelamento={11}, "
			+ "Desconto (pagamento à vista)={12}, Juros (parcelamento)={13}")
	@CsvFileSource(resources="..\\..\\files\\iptu-detalhado.csv", delimiter=',')
	public void mustCalculateIPTU_GetImovelByID(int id, int inscricao, String dateString, float valorImovel, int area,
			char categoria, float valorIPTU, float valorIPTUAVista, float valorParcelaIPTU, int numeroParcelas, 
			float valorTotalParcelamentoIPTU, float percDescontoPagamentoAVista, float percJurosParcelamento) {
		ImovelDTO imovelDTO = ImovelDAO.getByID(id);
		Imovel imovel = new Imovel(imovelDTO);
		
		IPTU iptu = new IPTU(imovel);
		
		ValorIPTU dadosValorIPTU = iptu.calculaValor(percDescontoPagamentoAVista, percJurosParcelamento);
			
		assertEquals(valorIPTU, dadosValorIPTU.valor, 0f, 
				"Valor do IPTU diverge do esperado");
		assertEquals(valorIPTUAVista, dadosValorIPTU.valorAVista, 0f, 
				"Valor do IPTU diverge do esperado");
		assertEquals(valorParcelaIPTU, dadosValorIPTU.parcelamento.getValorParcela(), 0f, 
				"Valor da parcela de parcelamento do IPTU diverge do esperado");
		assertEquals(numeroParcelas, dadosValorIPTU.parcelamento.getNumParcelas(), 0f, 
				"Número de parcelas de pagamento do IPTU diverge do esperado");
		assertEquals(valorTotalParcelamentoIPTU, dadosValorIPTU.parcelamento.getValorTotal(), 0f, 
				"Valor total do parcelamento do IPTU diverge do esperado");
	}
	
	@DisplayName("Calcula IPTU de imóvel registrado em BD via Imovel::getByInscricao")
	@ParameterizedTest(name="ID={0}, Inscrição={1}, Data de liberação={2}, Valor do imóvel={3}, "
			+ "Área={4}, Categoria={5}, Valor do IPTU={6}, Valor do IPTU à vista={7}, Número de parcelas={8}, "
			+ "Valor da parcela={9}, Número de parcelas={10}, Total do parcelamento={11}, "
			+ "Desconto (pagamento à vista)={12}, Juros (parcelamento)={13}")
	@CsvFileSource(resources="..\\..\\files\\iptu-detalhado.csv", delimiter=',')
	public void mustCalculateIPTU_GetImovelByInscricao(int id, int inscricao, String dateString, float valorImovel, 
			int area, char categoria, float valorIPTU, float valorIPTUAVista, float valorParcelaIPTU, int numeroParcelas, 
			float valorTotalParcelamentoIPTU, float percDescontoPagamentoAVista, float percJurosParcelamento) {
		ImovelDTO imovelDTO = ImovelDAO.getByInscricao(inscricao);
		Imovel imovel = new Imovel(imovelDTO);
		
		IPTU iptu = new IPTU(imovel);
		
		ValorIPTU dadosValorIPTU = iptu.calculaValor(percDescontoPagamentoAVista, percJurosParcelamento);
			
		assertEquals(valorIPTU, dadosValorIPTU.valor, 0f, 
				"Valor do IPTU diverge do esperado");
		assertEquals(valorIPTUAVista, dadosValorIPTU.valorAVista, 0f, 
				"Valor do IPTU diverge do esperado");
		assertEquals(valorParcelaIPTU, dadosValorIPTU.parcelamento.getValorParcela(), 0f, 
				"Valor da parcela de parcelamento do IPTU diverge do esperado");
		assertEquals(numeroParcelas, dadosValorIPTU.parcelamento.getNumParcelas(), 0f, 
				"Número de parcelas de pagamento do IPTU diverge do esperado");
		assertEquals(valorTotalParcelamentoIPTU, dadosValorIPTU.parcelamento.getValorTotal(), 0f, 
				"Valor total do parcelamento do IPTU diverge do esperado");
	}
}
