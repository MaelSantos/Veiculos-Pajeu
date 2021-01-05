package br.com.VeiculosPajeu.Dao;

import br.com.VeiculosPajeu.Dao.Interface.IDaoConfiguracao;
import br.com.VeiculosPajeu.Entidade.Configuracao;

public class DaoConfiguracao extends Dao<Configuracao> implements IDaoConfiguracao {

	public DaoConfiguracao() {
		super(Configuracao.class);
	}
}
