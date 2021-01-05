package br.com.VeiculosPajeu.Dao;

import br.com.VeiculosPajeu.Dao.Interface.IDaoFisica;
import br.com.VeiculosPajeu.Entidade.Fisica;

public class DaoFisica extends Dao<Fisica> implements IDaoFisica {

	public DaoFisica() {
		super(Fisica.class);
	}

}
