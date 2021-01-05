package br.com.VeiculosPajeu.Dao;

import br.com.VeiculosPajeu.Dao.Interface.IDaoFuncionario;
import br.com.VeiculosPajeu.Entidade.Funcionario;

public class DaoFuncionario extends Dao<Funcionario> implements IDaoFuncionario {

	public DaoFuncionario() {
		super(Funcionario.class);

	}
}
