package br.com.VeiculosPajeu.Dao;

import br.com.VeiculosPajeu.Dao.Interface.IDaoPassageiro;
import br.com.VeiculosPajeu.Entidade.Passageiro;

public class DaoPassageiro extends Dao<Passageiro> implements IDaoPassageiro {

	public DaoPassageiro() {
		super(Passageiro.class);
	}
}
