package br.com.VeiculosPajeu.Util;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {

	private static ConnectionFactory instance;
	private Map<String, String> propriedades;

	private ConnectionFactory() {
		propriedades = new HashMap<>();
	}

	public static synchronized ConnectionFactory getInstance() {
		if (instance == null)
			instance = new ConnectionFactory();
		return instance;
	}

	public EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Veiculos-PostgreSQL", propriedades);
		return factory.createEntityManager();
	}

	public void alterarPropriedades(boolean init) {
		if (init) {
			propriedades.put("hibernate.hbm2ddl.import_files", "/META-INF/sql/init.sql");
			propriedades.put("hibernate.hbm2ddl.auto", "create");
//			System.out.println(propriedades);
		} else {
			propriedades.remove("hibernate.hbm2ddl.import_files");
			propriedades.put("hibernate.hbm2ddl.auto", "update");
//			System.out.println(propriedades);
		}

	}

}
