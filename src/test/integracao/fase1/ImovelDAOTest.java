package test.integracao.fase1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;

import main.dao.ImovelDAO;
import main.db.DBConnection;
import main.model.Imovel;
import main.model.ImovelDTO;

@DisplayName("Classe de Testes Integrados 1: ImovelDAO-DBConnection")
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ImovelDAOTest {
	private static final String DRIVER = "org.sqlite.JDBC";
    private static final String CONEXAO = "jdbc:sqlite:.\\src\\iptu.db";

	private static int id;
	private static int inscricao;
	private static LocalDate dataLiberacao;
    
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
	
    @DisplayName("Consulta ao BD por ID existente deve retornar registro de imóvel")
	@Order(2)
	@ParameterizedTest
	@CsvSource({
		"247000f,45,E",
	})
	public void mustReturnRegisterByID(float valorImovel, int area, char categoria) {
		ImovelDTO imovelDTO = ImovelDAO.getByID(ImovelDAOTest.id);
		
		assertEquals(ImovelDAOTest.id, imovelDTO.id, 0, "ID diverge do esperado");
		assertEquals(ImovelDAOTest.inscricao, imovelDTO.inscricao, 0, "Inscrição diverge do esperado");
		assertTrue(imovelDTO.dataLiberacao.getDayOfMonth() == ImovelDAOTest.dataLiberacao.getDayOfMonth() && 
				imovelDTO.dataLiberacao.getMonth() == ImovelDAOTest.dataLiberacao.getMonth() && 
				imovelDTO.dataLiberacao.getYear() == ImovelDAOTest.dataLiberacao.getYear(), 
				"Data de liberação diverge do esperado");
		assertEquals(valorImovel, imovelDTO.valor, 0f, "Valor diverge do esperado");
		assertEquals(area, imovelDTO.area, 0, "Área diverge do esperado");
		assertEquals(categoria, imovelDTO.categoria, "Categoria diverge do esperado");
	}
	
    @DisplayName("Consulta ao BD por inscrição existente deve retornar registro de imóvel")
	@Order(3)
	@ParameterizedTest
	@CsvSource({
		"247000f,45,E",
	})
	public void mustReturnRegisterByInscricao(float valorImovel, int area, char categoria) {
		ImovelDTO imovelDTO = ImovelDAO.getByInscricao(ImovelDAOTest.inscricao);
		
		assertEquals(ImovelDAOTest.id, imovelDTO.id, 0, "ID diverge do esperado");
		assertEquals(ImovelDAOTest.inscricao, imovelDTO.inscricao, 0, "Inscrição diverge do esperado");
		assertTrue(ImovelDAOTest.dataLiberacao.getDayOfMonth() == imovelDTO.dataLiberacao.getDayOfMonth() && 
				ImovelDAOTest.dataLiberacao.getMonth() == imovelDTO.dataLiberacao.getMonth() && 
				ImovelDAOTest.dataLiberacao.getYear() == imovelDTO.dataLiberacao.getYear(), 
				"Data de liberação diverge do esperado");
		assertEquals(valorImovel, imovelDTO.valor, 0, "Valor diverge do esperado");
		assertEquals(area, imovelDTO.area, 0, "Área diverge do esperado");
		assertEquals(categoria, imovelDTO.categoria, 0, "Categoria diverge do esperado");
	}
	
    @DisplayName("Cadastro de novo imóvel deve retornar ID diferente de 0")
	@Order(1)
	@ParameterizedTest
	@CsvSource({
		"78123456,1978-08-14,247000f,45,E",
	})
	public void mustInsertRegister(int inscricao, String data, float valor, int area, char categoria) {
		ImovelDAOTest.inscricao = inscricao;
		ImovelDAOTest.dataLiberacao = LocalDate.parse(data);
		
		Imovel imovel = Mockito.mock(Imovel.class);
		
		when(imovel.getInscricao()).thenReturn(ImovelDAOTest.inscricao);
		when(imovel.getDataLiberacao()).thenReturn(ImovelDAOTest.dataLiberacao);
		when(imovel.getValor()).thenReturn(valor);
		when(imovel.getArea()).thenReturn(area);
		when(imovel.getCategoria()).thenReturn(categoria);
		
		ImovelDAOTest.id = ImovelDAO.insert(imovel);
		
		verify(imovel).getInscricao();
		verify(imovel).getDataLiberacao();
		verify(imovel).getValor();
		verify(imovel).getArea();
		verify(imovel).getCategoria();
		
		assertTrue(ImovelDAOTest.id != 0, "ID retornado é nulo");
	}
	
    @DisplayName("Atualização de registro de imóvel deve modificar dados no BD")
	@Order(4)
	@ParameterizedTest
	@CsvSource({
		"81234567,1978-08-24,350000f,55,B",
	})
	public void mustUpdateRegister(int inscricao, String dateString, float valorImovel, int area, char categoria) {
		LocalDate dataLiberacao = LocalDate.parse(dateString);
		
		Imovel imovel = Mockito.mock(Imovel.class);
		
		when(imovel.getID()).thenReturn(ImovelDAOTest.id);
		when(imovel.getInscricao()).thenReturn(inscricao);
		when(imovel.getDataLiberacao()).thenReturn(dataLiberacao);
		when(imovel.getValor()).thenReturn(valorImovel);
		when(imovel.getArea()).thenReturn(area);
		when(imovel.getCategoria()).thenReturn(categoria);
		
		boolean success = ImovelDAO.update(imovel);

		verify(imovel).getInscricao();
		verify(imovel).getDataLiberacao();
		verify(imovel).getValor();
		verify(imovel).getArea();
		verify(imovel).getCategoria();
		verify(imovel).getID();
		
		ImovelDTO imovelDTO = ImovelDAO.getByID(ImovelDAOTest.id);
		
		assertTrue(success, "Método retornou false quando deveria retornar true");
		assertEquals(ImovelDAOTest.id, imovelDTO.id, 0, "ID foi indevidamente alterado");
		assertEquals(inscricao, imovelDTO.inscricao, 0, "Inscrição foi indevidamente alterada");
		assertTrue(ImovelDAOTest.dataLiberacao.getDayOfMonth() != imovelDTO.dataLiberacao.getDayOfMonth() || 
				ImovelDAOTest.dataLiberacao.getMonth() != imovelDTO.dataLiberacao.getMonth() || 
				ImovelDAOTest.dataLiberacao.getYear() != imovelDTO.dataLiberacao.getYear(), 
				"Data de liberação diverge do esperado");
		assertEquals(valorImovel, imovelDTO.valor, 0f, "Valor foi inalterado");
		assertEquals(area, imovelDTO.area, 0, "Área foi inalterada");
		assertEquals(categoria, imovelDTO.categoria, "Categoria foi inalterada");
	}
	
    @DisplayName("Exclusão de registro de imóvel deve remover dados no BD")
	@Order(5)
	@Test
	public void mustDeleteRegister() {
		Imovel imovel = Mockito.mock(Imovel.class);
		
		when(imovel.getID()).thenReturn(ImovelDAOTest.id);
		boolean success = ImovelDAO.delete(imovel);
		
		assertTrue(success, "Método retornou false quando deveria retornar true");
	}
}
