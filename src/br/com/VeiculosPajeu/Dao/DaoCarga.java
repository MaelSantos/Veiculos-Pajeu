package br.com.VeiculosPajeu.Dao;

import br.com.VeiculosPajeu.Dao.Interface.IDaoCarga;
import br.com.VeiculosPajeu.Entidade.Carga;

public class DaoCarga extends Dao<Carga> implements IDaoCarga {

	public DaoCarga() {
		super(Carga.class);
	}
}
