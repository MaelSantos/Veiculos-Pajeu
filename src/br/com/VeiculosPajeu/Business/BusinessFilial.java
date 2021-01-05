package br.com.VeiculosPajeu.Business;

import br.com.VeiculosPajeu.Business.Interface.IBusinessFilial;
import br.com.VeiculosPajeu.Dao.DaoFilial;
import br.com.VeiculosPajeu.Dao.Interface.IDaoFilial;
import br.com.VeiculosPajeu.Entidade.Filial;
import br.com.VeiculosPajeu.Exception.ValidationException;

public class BusinessFilial extends Business<Filial> implements IBusinessFilial{

	private IDaoFilial daoFilial;
	
	public BusinessFilial() {
		daoFilial = new DaoFilial();
		init(daoFilial);
	}
	
	@Override
	public void validation(Filial entidade) throws ValidationException {
		
		if(entidade.getNome().trim().isEmpty())
			throw new ValidationException("INFORME O NOME");
		
	}

}
