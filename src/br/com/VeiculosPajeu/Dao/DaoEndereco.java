package br.com.VeiculosPajeu.Dao;

import br.com.VeiculosPajeu.Dao.Interface.IDaoEndereco;
import br.com.VeiculosPajeu.Entidade.Endereco;

public class DaoEndereco extends Dao<Endereco> implements IDaoEndereco{

	public DaoEndereco() {
		super(Endereco.class);
	}
	
}
