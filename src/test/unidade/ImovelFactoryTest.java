package test.unidade;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;

import main.dao.ImovelDAO;
import main.factory.ImovelFactory;
import main.model.Imovel;
import main.model.ImovelDTO;

@DisplayName("Classe de Testes Unitários 5: ImovelFactory")
public class ImovelFactoryTest {
	@DisplayName("Consulta ao BD por ID inexistente deve retornar null")
	@Test
	public void mustReturnNullObjectInvalidID() {
    	try (MockedStatic<ImovelDAO> imovelDAO = mockStatic(ImovelDAO.class)) {
    		ImovelDTO imovelDTO = new ImovelDTO();
    		
    		imovelDAO.when(() -> ImovelDAO.getByID(0)).thenReturn(imovelDTO);
    		Imovel imovel = ImovelFactory.getByID(0);
    		
    		assertTrue(imovel == null, "Objeto retornado não é null");
    	}
	}

	@DisplayName("Consulta ao BD por ID existente deve retornar registro de imóvel")
	@ParameterizedTest
	@CsvSource({
		"11,20030717,1974-11-24,345000f,95,D",
	})
	public void mustReturnObjectNotNullValidID(int id, int inscricao, String date, float valor, 
			int area, char categoria) {
    	try (MockedStatic<ImovelDAO> imovelDAO = mockStatic(ImovelDAO.class)) {
    		ImovelDTO imovelDTO = new ImovelDTO();
    		imovelDTO.id = id;
    		imovelDTO.inscricao = inscricao;
    		imovelDTO.dataLiberacao = LocalDate.parse(date);
    		imovelDTO.valor = valor;
    		imovelDTO.area = area;
    		imovelDTO.categoria = categoria;
    		
    		imovelDAO.when(() -> ImovelDAO.getByID(id)).thenReturn(imovelDTO);
    		Imovel imovel = ImovelFactory.getByID(11);
    		
    		assertTrue(imovel != null, "Objeto retornado é null");
    	}
	} 

	@DisplayName("Consulta ao BD por inscrição inexistente deve retornar null")
	@Test
	public void mustReturnNullObjectInvalidInscricao() {
    	try (MockedStatic<ImovelDAO> imovelDAO = mockStatic(ImovelDAO.class)) {
    		ImovelDTO imovelDTO = new ImovelDTO();
    		
    		imovelDAO.when(() -> ImovelDAO.getByInscricao(0)).thenReturn(imovelDTO);
    		Imovel imovel = ImovelFactory.getByInscricao(0);
    		
    		assertTrue(imovel == null, "Objeto retornado não é null");
    	}
	}
	
	@DisplayName("Consulta ao BD por inscrição existente deve retornar registro de imóvel")
	@ParameterizedTest
	@CsvSource({
		"11,20030717,1974-11-24,345000f,95,D",
	})
	public void mustReturnObjectNotNullValidInscricao(int id, int inscricao, String date, float valor, 
			int area, char categoria) {
    	try (MockedStatic<ImovelDAO> imovelDAO = mockStatic(ImovelDAO.class)) {
    		ImovelDTO imovelDTO = new ImovelDTO();
    		imovelDTO.id = id;
    		imovelDTO.inscricao = inscricao;
    		imovelDTO.dataLiberacao = LocalDate.parse(date);
    		imovelDTO.valor = valor;
    		imovelDTO.area = area;
    		imovelDTO.categoria = categoria;
    		
    		imovelDAO.when(() -> ImovelDAO.getByInscricao(inscricao)).thenReturn(imovelDTO);
    		Imovel imovel = ImovelFactory.getByInscricao(inscricao);
    		
    		assertTrue(imovel != null, "Objeto retornado é null");
    	}
	}
}
