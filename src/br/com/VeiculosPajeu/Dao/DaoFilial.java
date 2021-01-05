package br.com.VeiculosPajeu.Dao;

import br.com.VeiculosPajeu.Dao.Interface.IDaoFilial;
import br.com.VeiculosPajeu.Entidade.Filial;

public class DaoFilial extends Dao<Filial> implements IDaoFilial{

	public DaoFilial() {
		super(Filial.class);
	}
	
}
