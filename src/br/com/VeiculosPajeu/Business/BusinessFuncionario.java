package br.com.VeiculosPajeu.Business;

import br.com.VeiculosPajeu.Business.Interface.IBusinessFuncionario;
import br.com.VeiculosPajeu.Dao.DaoFuncionario;
import br.com.VeiculosPajeu.Dao.Interface.IDao;
import br.com.VeiculosPajeu.Dao.Interface.IDaoFuncionario;
import br.com.VeiculosPajeu.Entidade.Funcionario;
import br.com.VeiculosPajeu.Exception.ValidationException;
import br.com.VeiculosPajeu.Util.CriptografiaUtil;

public class BusinessFuncionario extends Business<Funcionario> implements IBusinessFuncionario {

	private IDaoFuncionario daoFuncionario;
	private CriptografiaUtil criptografiaUtil;

	public BusinessFuncionario() {
		super();
		criptografiaUtil = CriptografiaUtil.getInstance();
	}

	@Override
	public void validation(Funcionario entidade) throws ValidationException {

		if (entidade.getCargo().trim().isEmpty())
			throw new ValidationException("INFORME UM CARGO");
		if (entidade.getTipoFuncionario() == null)
			throw new ValidationException("INFORME O TIPO DE FUNCIONÁRIO");
		if (entidade.getLogin().trim().isEmpty())
			throw new ValidationException("INFORME UM LOGIN");
		if (entidade.getNome().trim().isEmpty())
			throw new ValidationException("INFORME UM NOME");
		if (entidade.getSenha().trim().isEmpty())
			throw new ValidationException("INFORME UMA SENHA");

		if (entidade.getSenha().length() < 6 || entidade.getSenha().length() > 11)
			throw new ValidationException("A SENHA TEM QUE TER NO MINIMO 6 E NO MAXIMO 11 CARACTERES");
		else if (!criptografiaUtil.isCriptografado(entidade.getSenha()))
			entidade.setSenha(criptografiaUtil.criptografar(entidade.getSenha().getBytes()));

	}

	@Override
	public IDao<Funcionario> createDao() throws ValidationException {
		if (daoFuncionario == null)
			daoFuncionario = new DaoFuncionario();
		return daoFuncionario;
	}
}
