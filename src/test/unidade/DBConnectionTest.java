package test.unidade;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.db.DBConnection;
import java.sql.Connection;

@DisplayName("Classe de Testes Unitários 1: DBConnection")
public class DBConnectionTest {
	private static final String DRIVER = "org.sqlite.JDBC";
    private static final String CONEXAO = "jdbc:sqlite:.\\src\\iptu.db";
    
    @BeforeEach
    public void beforeEachTest() {
    	DBConnection.set(null, null);
    }
    
    @AfterEach
    public void afterEachTest() {
    	if (DBConnection.isAlive()) {
    		DBConnection.close();
    	}
    }
    
    @DisplayName("Driver indefinido provoca exceção")
    @Test
    public void mustThrowExceptionDriverUndefined() {
    	DBConnection.set(null, CONEXAO);
    	
    	assertThrows(IllegalArgumentException.class, DBConnection::get, 
    			"Driver indefinido não provocou exceção");
    }
    
    @DisplayName("String de conexão indefinida provoca exceção")
    @Test
    public void mustThrowExceptionConnectionStringUndefined() {
    	DBConnection.set(DRIVER, null);
    	
    	assertThrows(IllegalArgumentException.class, DBConnection::get, 
    			"String indefinida de conexão não provocou exceção");
    }
    
    @DisplayName("Driver inválido provoca exceção")
    @Test
    public void mustThrowExceptionInvalidDriver() {
    	DBConnection.set(" ", CONEXAO);
    	
    	assertThrows(IllegalArgumentException.class, DBConnection::get, 
    			"Driver inválido não provocou exceção");
	}
    
    @DisplayName("String de conexão inválida provoca exceção")
    @Test
    public void mustThrowExceptionInvalidConnectionString() {
    	DBConnection.set(DRIVER, " ");
    	
    	assertThrows(IllegalArgumentException.class, DBConnection::get, 
    			"String inválida de conexão não provocou exceção");
    }
    
    @DisplayName("Encerramento de conexão inexistente provoca exceção")
    @Test
    public void mustThrowExceptionCloseInexistentConnection() {
    	DBConnection.set(DRIVER, CONEXAO);
    	
    	assertThrows(IllegalArgumentException.class, DBConnection::close, 
    			"Tentativa de finalização de conexão inexistente não provocou exceção");
    }
    
    @DisplayName("Abertura de conexão bem sucedida")
    @Test
    public void mustOpenConnection() {
    	DBConnection.set(DRIVER, CONEXAO);
    	Connection dbconnection = DBConnection.get();
    	
    	assertTrue(dbconnection != null && DBConnection.isAlive(), 
    			"Tentativa de inicialização de conexão após passagem de argumentos válidos resulta em êxito");
    }
}
