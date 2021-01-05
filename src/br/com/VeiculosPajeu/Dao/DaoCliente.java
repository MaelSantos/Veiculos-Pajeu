package br.com.VeiculosPajeu.Dao;

import br.com.VeiculosPajeu.Dao.Interface.IDaoCliente;
import br.com.VeiculosPajeu.Entidade.Cliente;

public class DaoCliente extends Dao<Cliente> implements IDaoCliente{

	public DaoCliente() {
		super(Cliente.class);
	}

}
