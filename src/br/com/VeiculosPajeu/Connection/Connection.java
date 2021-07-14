package br.com.VeiculosPajeu.Connection;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import br.com.VeiculosPajeu.Entidade.Enum.TipoBanco;

public class Connection {

	private static Connection instance;
	private IConnectionFactory connectionFactory;
	private Map<String, String> propriedades;

	private Connection(TipoBanco tipoBanco) {

		switch (tipoBanco) {
		case MYSQL:
			connectionFactory = new ConnectionMySql();
			break;
		case POSTGRESQL:
			connectionFactory = new ConnectionPostgree();
			break;
		}

		propriedades = new HashMap<>();
	}

	public static synchronized Connection getInstance(TipoBanco tipoBanco) {
		if (instance == null)
			instance = new Connection(tipoBanco);
		return instance;
	}

	public EntityManager getEntityManager() {
		return connectionFactory.createEntityManager(propriedades);
	}

	public void alterarPropriedades(boolean init) {
		connectionFactory.alterarPropriedades(propriedades, init);
	}

}
