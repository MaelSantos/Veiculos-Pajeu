package br.com.VeiculosPajeu.Dao;

import br.com.VeiculosPajeu.Dao.Interface.IDaoJuridica;
import br.com.VeiculosPajeu.Entidade.Juridica;

public class DaoJuridica extends Dao<Juridica> implements IDaoJuridica{

	public DaoJuridica() {
		super(Juridica.class);
		
	}
	
}
