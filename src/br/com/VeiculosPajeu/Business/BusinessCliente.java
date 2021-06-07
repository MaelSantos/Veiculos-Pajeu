package br.com.VeiculosPajeu.Business;

import br.com.VeiculosPajeu.Business.Interface.IBusinessCliente;
import br.com.VeiculosPajeu.Dao.DaoCliente;
import br.com.VeiculosPajeu.Dao.Interface.IDao;
import br.com.VeiculosPajeu.Dao.Interface.IDaoCliente;
import br.com.VeiculosPajeu.Entidade.Cliente;
import br.com.VeiculosPajeu.Exception.ValidationException;

public class BusinessCliente extends Business<Cliente> implements IBusinessCliente {

	private IDaoCliente daoCliente;

	@Override
	public void validation(Cliente entidade) throws ValidationException {
	}

	@Override
	public IDao<Cliente> createDao() throws ValidationException {
		if (daoCliente == null)
			daoCliente = new DaoCliente();
		return daoCliente;
	}

}
