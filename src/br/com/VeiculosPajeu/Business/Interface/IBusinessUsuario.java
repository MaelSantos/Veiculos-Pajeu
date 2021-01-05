package br.com.VeiculosPajeu.Business.Interface;

import br.com.VeiculosPajeu.Entidade.Usuario;
import br.com.VeiculosPajeu.Exception.BusinessException;

public interface IBusinessUsuario extends IBusiness<Usuario>{

	public Usuario searchUser(String login, String senha) throws BusinessException;
	
}
