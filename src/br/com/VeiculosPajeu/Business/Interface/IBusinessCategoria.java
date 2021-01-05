package br.com.VeiculosPajeu.Business.Interface;

import br.com.VeiculosPajeu.Entidade.Categoria;
import br.com.VeiculosPajeu.Exception.BusinessException;

public interface IBusinessCategoria extends IBusiness<Categoria>{

	public Categoria nextCategoria(Categoria categoria) throws BusinessException; 
	
}
