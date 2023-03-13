package test.unidade;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.util.Money;

@DisplayName("Classe de Testes Unitários 2: Money")
public class MoneyTest {
	@DisplayName("Deve arredondar lista de valores corretamente")
	@ParameterizedTest(name="Valor original={0}, Valor esperado após arredondamento={1}")
	@CsvSource({
		"0.0000f,0.00f",
		"0.9950f,1.00f",
		"1.0040f,1.00f",
		"1.0050f,1.01f",
		"1.0090f,1.01f",
		"1.0440f,1.04f",
		"1.0450f,1.05f",
		"1.4940f,1.49f",
		"1.4950f,1.50f",
		"1.4949f,1.49f",
		"1.4959f,1.50f",
	})
	public void mustReturnRoundedValue(float original, float expected) {
		float result = Money.round(original);
		
		assertEquals(expected, result, 0, "Erro de arrendondamento");
	}
}
