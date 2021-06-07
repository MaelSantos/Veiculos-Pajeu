package br.com.VeiculosPajeu.Business;

import java.util.List;

import br.com.VeiculosPajeu.Business.Interface.IBusiness;
import br.com.VeiculosPajeu.Dao.Interface.IDao;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Exception.BusinessException;
import br.com.VeiculosPajeu.Exception.DaoException;
import br.com.VeiculosPajeu.Exception.ValidationException;

public abstract class Business<T extends Entidade> implements IBusiness<T> {

	protected IDao<T> dao;

	public Business() {
		try {
			this.dao = createDao();
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save(T entidade) throws BusinessException {

		try {
			validation(entidade);
			if (entidade.getId() == null)
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
