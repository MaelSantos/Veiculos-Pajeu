package br.com.VeiculosPajeu.Business;

import br.com.VeiculosPajeu.Business.Interface.IBusinessCliente;
import br.com.VeiculosPajeu.Dao.DaoCliente;
import br.com.VeiculosPajeu.Dao.Interface.IDaoCliente;
import br.com.VeiculosPajeu.Entidade.Cliente;
import br.com.VeiculosPajeu.Exception.ValidationException;

public class BusinessCliente extends Business<Cliente> implements IBusinessCliente{

	private IDaoCliente daoCliente;
	
	public BusinessCliente() {
		daoCliente = new DaoCliente();
		init(daoCliente);
	}
	
	@Override
	public void validation(Cliente entidade) throws ValidationException {
	}

}
