package br.com.VeiculosPajeu.Connection;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public interface IConnectionFactory {

	public EntityManager createEntityManager(Map<String, String> propriedades);
	
	public EntityManagerFactory createEntityManagerFactory(Map<String, String> propriedades);
	
	public void alterarPropriedades(Map<String, String> propriedades, boolean init);
	
}
