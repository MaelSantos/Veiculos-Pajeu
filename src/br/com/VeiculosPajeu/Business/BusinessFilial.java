package br.com.VeiculosPajeu.Business;

import br.com.VeiculosPajeu.Business.Interface.IBusinessFilial;
import br.com.VeiculosPajeu.Dao.DaoFilial;
import br.com.VeiculosPajeu.Dao.Interface.IDao;
import br.com.VeiculosPajeu.Dao.Interface.IDaoFilial;
import br.com.VeiculosPajeu.Entidade.Filial;
import br.com.VeiculosPajeu.Exception.ValidationException;

public class BusinessFilial extends Business<Filial> implements IBusinessFilial {

	private IDaoFilial daoFilial;

	@Override
	public void validation(Filial entidade) throws ValidationException {

		if (entidade.getNome().trim().isEmpty())
			throw new ValidationException("INFORME O NOME");

	}

	@Override
	public IDao<Filial> createDao() throws ValidationException {
		if (daoFilial == null)
			daoFilial = new DaoFilial();
		return daoFilial;
	}

}
