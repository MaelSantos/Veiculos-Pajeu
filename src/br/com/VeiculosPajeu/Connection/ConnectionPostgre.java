package br.com.VeiculosPajeu.Connection;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionPostgre implements IConnectionFactory {

	@Override
	public EntityManager createEntityManager(Map<String, String> propriedades) {
		return createEntityManagerFactory(propriedades).createEntityManager();
	}

	@Override
	public EntityManagerFactory createEntityManagerFactory(Map<String, String> propriedades) {
		return Persistence.createEntityManagerFactory("Veiculos-PostgreSQL", propriedades);
	}

	@Override
	public void alterarPropriedades(Map<String, String> propriedades, boolean init) {
		if (init) {
			propriedades.put("hibernate.hbm2ddl.import_files", "/META-INF/sql/init_postgre.sql");
			propriedades.put("hibernate.hbm2ddl.auto", "create");
		} else {
			propriedades.remove("hibernate.hbm2ddl.import_files");
			propriedades.put("hibernate.hbm2ddl.auto", "update");
		}

	}

}
