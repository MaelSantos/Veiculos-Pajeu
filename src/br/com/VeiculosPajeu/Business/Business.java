package br.com.VeiculosPajeu.Business;

import java.util.List;

import br.com.VeiculosPajeu.Business.Interface.IBusiness;
import br.com.VeiculosPajeu.Dao.Interface.IDao;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Exception.BusinessException;
import br.com.VeiculosPajeu.Exception.DaoException;
import br.com.VeiculosPajeu.Exception.ValidationException;
import br.com.VeiculosPajeu.Util.DaoFactory;

public abstract class Business<T extends Entidade> implements IBusiness<T>{

	protected IDao<T> dao;
	
//	@SuppressWarnings("unchecked")
//	public Business(Class<T> classe) {
//		this.dao = (IDao<T>) DaoFactory.getInstance().getDao(classe);
//	}
	
	@Override
	public void init(IDao<T> dao) {
		this.dao = dao;
	}
	
	@Override
	public void createOrUpdate(T entidade) throws BusinessException {
		
		try {
			validation(entidade);
			if(entidade.getId() == null)
				dao.create(entidade);
			else
				dao.update(entidade);
		} catch (DaoException | ValidationException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		
	}

	@Override
	public T search(int id) throws BusinessException {
		try {
			return dao.search(id);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void remove(int id) throws BusinessException {
		try {
			dao.remove(id);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<T> searchAll() throws BusinessException {
		
		try {
			return dao.searchAll();
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<T> searchAll(String search) throws BusinessException {
		try {
			return dao.searchAll(search);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
}
