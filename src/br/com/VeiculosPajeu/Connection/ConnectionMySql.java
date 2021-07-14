package br.com.VeiculosPajeu.Connection;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionMySql implements IConnectionFactory {

	@Override
	public EntityManager createEntityManager(Map<String, String> propriedades) {
		return createEntityManagerFactory(propriedades).createEntityManager();
	}

	@Override
	public EntityManagerFactory createEntityManagerFactory(Map<String, String> propriedades) {
		return Persistence.createEntityManagerFactory("Veiculos-MySQL", propriedades);
	}

	@Override
	public void alterarPropriedades(Map<String, String> propriedades, boolean init) {
		if (init) {
			propriedades.put("hibernate.hbm2ddl.import_files", "/META-INF/sql/init_mysql.sql");
			propriedades.put("hibernate.hbm2ddl.auto", "create");
		} else {
			propriedades.remove("hibernate.hbm2ddl.import_files");
			propriedades.put("hibernate.hbm2ddl.auto", "update");
		}
		
	}

}
