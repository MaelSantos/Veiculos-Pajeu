package br.com.VeiculosPajeu.Dao;

import br.com.VeiculosPajeu.Dao.Interface.IDaoSuperUsuario;
import br.com.VeiculosPajeu.Entidade.SuperUsuario;

public class DaoSuperUsuario extends Dao<SuperUsuario> implements IDaoSuperUsuario {

	public DaoSuperUsuario() {
		super(SuperUsuario.class);
	}

}
