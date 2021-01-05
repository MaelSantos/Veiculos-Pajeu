package br.com.VeiculosPajeu.Dao;

import br.com.VeiculosPajeu.Dao.Interface.IDaoCategoria;
import br.com.VeiculosPajeu.Entidade.Categoria;

public class DaoCategoria extends Dao<Categoria> implements IDaoCategoria {

	public DaoCategoria() {
		super(Categoria.class);
	}

}
