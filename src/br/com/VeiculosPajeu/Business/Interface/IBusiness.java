package br.com.VeiculosPajeu.Business.Interface;

import java.util.List;

import br.com.VeiculosPajeu.Dao.Interface.IDao;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Exception.BusinessException;
import br.com.VeiculosPajeu.Exception.ValidationException;

public interface IBusiness<T extends Entidade> {
	
	public void save(T entidade) throws BusinessException;
	  
    public T search(int id) throws BusinessException;

    public void remove(int id) throws BusinessException;

    public List<T> searchAll() throws BusinessException;
    
    public List<T> searchAll(String search) throws BusinessException;

	public void validation(T entidade) throws ValidationException;
	
	public IDao<T> createDao() throws ValidationException;
}
