package br.com.VeiculosPajeu.Dao.Interface;

import java.util.List;

import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Exception.DaoException;

public interface IDao<T extends Entidade> {
		
	public void create(T entidade) throws DaoException;
	  
    public T search(int id) throws DaoException;

    public void remove(int id) throws DaoException;

    public void update(T entidade) throws DaoException;

    public List<T> searchAll() throws DaoException;
    
    public List<T> searchAll(String search) throws DaoException;
    
}
