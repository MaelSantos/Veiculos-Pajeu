package br.com.VeiculosPajeu.Business;

import br.com.VeiculosPajeu.Business.Interface.IBusinessEndereco;
import br.com.VeiculosPajeu.Dao.DaoEndereco;
import br.com.VeiculosPajeu.Dao.Interface.IDaoEndereco;
import br.com.VeiculosPajeu.Entidade.Endereco;
import br.com.VeiculosPajeu.Exception.ValidationException;

public class BusinessEndereco extends Business<Endereco> implements IBusinessEndereco {

	private IDaoEndereco daoEndereco;
	
	public BusinessEndereco() {
		daoEndereco = new DaoEndereco();
		init(daoEndereco);
	}
	
	@Override
	public void validation(Endereco entidade) throws ValidationException {
		
		if(entidade.getBairro().trim().isEmpty())
			throw new ValidationException("INFORME O BAIRRO");
		if(entidade.getCep().trim().isEmpty())
			throw new ValidationException("INFORME O CEP");
		if(entidade.getCidade().trim().isEmpty())
			throw new ValidationException("INFORME A CIDADE");
		if(entidade.getEstado() == null)
			throw new ValidationException("INFORME O ESTADO");
		if(entidade.getNumero().trim().isEmpty())
			throw new ValidationException("INFORME O NUMERO");
		if(entidade.getPais().trim().isEmpty())
			throw new ValidationException("INFORME O PAIS");
		if(entidade.getRua().trim().isEmpty())
			throw new ValidationException("INFORME  A RUA");
	}

	
}
