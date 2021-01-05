package br.com.VeiculosPajeu.Dao;

import br.com.VeiculosPajeu.Dao.Interface.IDaoAutomovel;
import br.com.VeiculosPajeu.Entidade.Automovel;

public class DaoAutomovel extends Dao<Automovel> implements IDaoAutomovel {

	public DaoAutomovel() {
		super(Automovel.class);
	}

}
