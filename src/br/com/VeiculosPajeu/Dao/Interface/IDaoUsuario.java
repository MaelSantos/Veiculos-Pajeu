package br.com.VeiculosPajeu.Dao.Interface;

import br.com.VeiculosPajeu.Entidade.Usuario;
import br.com.VeiculosPajeu.Exception.DaoException;

public interface IDaoUsuario extends IDao<Usuario>{

	public Usuario searchUser(String login, String senha) throws DaoException;
	
}
