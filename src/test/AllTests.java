package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	test.unidade.DBConnectionTest.class,
	test.unidade.MoneyTest.class,
	test.unidade.ParcelamentoTest.class,
	test.unidade.ImovelTest.class,
	test.unidade.ImovelFactoryTest.class,
	test.unidade.IPTUTest.class,
	test.integracao.fase1.ImovelDAOTest.class,
	test.integracao.fase2.IPTUTest.class,
	test.integracao.fase3.IPTUTest.class,
	test.integracao.fase4.IPTUTest.class,
})
public class AllTests {
	
}
