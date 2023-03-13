package test.unidade;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import main.dao.ImovelDAO;
import main.model.Imovel;
import main.model.ImovelDTO;

@DisplayName("Classe de Testes Unitários 3: Imovel")
public class ImovelTest {
	@DisplayName("Número de inscrição inválido deve provocar exceção")
	@ParameterizedTest
	@ValueSource(ints = {1234567, 123456789, 01234567})
	public void mustThrowExceptionInvalidInscricao(int inscricao) {
		assertThrows(IllegalArgumentException.class, () -> new Imovel(inscricao, LocalDate.of(1974, 11, 24),
				345000f, 95, 'D'), "Tentativa de criação de objeto com inscrição inválida não provocou exceção");
	}

	@DisplayName("Data de liberação inválida deve provocar exceção")
    @Test
    public void mustThrowExceptionInvalidDataLiberacao() {
    	assertThrows(IllegalArgumentException.class, () -> new Imovel(20030717, LocalDate.now().plusDays(1),
    			345000f, 95, 'D'), "Tentativa de criação de objeto com data de liberação não provocou exceção");
    }

	@DisplayName("Valor inválido deve provocar exceção")
    @ParameterizedTest
    @ValueSource(floats = {0f, -1f})
    public void mustThrowExceptionInvalidValor(float valor) {
    	assertThrows(IllegalArgumentException.class, () -> new Imovel(20030717, LocalDate.of(1974, 11, 24),
				valor, 95, 'D'), "Tentativa de criação de objeto com valor inválido não provocou exceção");
    }

	@DisplayName("Área inválida deve provocar exceção")
    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    public void mustThrowExceptionInvalidArea(int area) {
    	assertThrows(IllegalArgumentException.class, () -> new Imovel(20030717, LocalDate.of(1974, 11, 24),
				345000f, area, 'D'), "Tentativa de criação de objeto com área inválida não provocou exceção");
    }

	@DisplayName("Categoria inválida deve provocar exceção")
    @ParameterizedTest
    @ValueSource(chars = {(char) 'A' - 1, (char) 'Z' + 1})
    public void mustThrowExceptionInvalidCategoria(char categoria) {
    	assertThrows(IllegalArgumentException.class, () -> new Imovel(20030717, LocalDate.of(1974, 11, 24),
				345000f, 95, categoria), "Tentativa de criação de objeto com categoria inválida não provocou exceção");
    }

	@DisplayName("Criação de objeto do tipo imóvel com dado inválido deve provocar exceção")
    @Test
    public void mustThrowExceptionInvalidData() {
    	ImovelDTO imovelDTO = new ImovelDTO();
    	
    	imovelDTO.id = 11;
    	imovelDTO.inscricao = 2003071; // invalid value
    	imovelDTO.dataLiberacao = LocalDate.of(1974, 11, 24);
    	imovelDTO.valor = 345000f;
    	imovelDTO.area = 95;
    	imovelDTO.categoria = 'D';
    	
    	assertThrows(IllegalArgumentException.class, () -> new Imovel(imovelDTO), 
    			"Tentativa de criação de objeto com dado inválido não provocou exceção");
    }

	@DisplayName("Ação de salvar registro inexistente retorna ID diferente de 0")
    @Test
    public void mustNotSaveInexistentRegister() {
    	try (MockedStatic<ImovelDAO> imovelDAO = mockStatic(ImovelDAO.class)) {
    		imovelDAO.when(() -> ImovelDAO.insert(any())).thenReturn(11);
    		
    		Imovel imovel = new Imovel(20030717, LocalDate.of(1974, 11, 24), 345000f, 95, 'D');
    		imovel.save();
    		
    		imovelDAO.verify(() -> ImovelDAO.insert(imovel));
    		assertTrue(imovel.getID() == 11, "ID diverge do esperado");
    	}
    }
	
	@DisplayName("Ação de salvar registro existente provoca chamada do método ImovelDAO.insert()")
    @Test
    public void mustSaveExistentRegister() {
    	try (MockedStatic<ImovelDAO> imovelDAO = mockStatic(ImovelDAO.class)) {
    		imovelDAO.when(() -> ImovelDAO.update(any())).thenReturn(false);
    		
    		Imovel imovel = new Imovel(20030717, LocalDate.of(1974, 11, 24), 345000f, 95, 'D');
    		imovel.setID(11);
    		imovel.save();
    		
    		imovelDAO.verify(() -> ImovelDAO.update(any()));
    	}
    }
    
	@DisplayName("Ação de salvar registro existente provoca alteração do ID do objeto para 0")
    @Test
    public void mustDeleteExistentRegister() {
    	try (MockedStatic<ImovelDAO> imovelDAO = mockStatic(ImovelDAO.class)) {
    		imovelDAO.when(() -> ImovelDAO.delete(any())).thenReturn(true);
    		
    		Imovel imovel = new Imovel(20030717, LocalDate.of(1974, 11, 24), 345000f, 95, 'D');
    		imovel.setID(11);
    		imovel.delete();
    		
    		imovelDAO.verify(() -> ImovelDAO.delete(any()));
    		assertTrue(imovel.getID() == 0, "ID permaneceu inalterado após exclusão de registro");
    	}
    }
    
	@DisplayName("Ação de salvar registro existente não modifica pilha de chamadas do sistema")
    @Test
    public void mustNotDeleteInexistentRegister() {
    	try (MockedStatic<ImovelDAO> imovelDAO = mockStatic(ImovelDAO.class)) {
    		imovelDAO.when(() -> ImovelDAO.delete(any())).thenReturn(false);
    		
    		Imovel imovel = new Imovel(20030717, LocalDate.of(1974, 11, 24), 345000f, 95, 'D');
    		imovel.delete();
    		
    		imovelDAO.verifyNoInteractions();
    	}
    }
}
