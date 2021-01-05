package br.com.VeiculosPajeu.Business;

import br.com.VeiculosPajeu.Business.Interface.IBusinessSuperUsuario;
import br.com.VeiculosPajeu.Dao.DaoSuperUsuario;
import br.com.VeiculosPajeu.Dao.Interface.IDaoSuperUsuario;
import br.com.VeiculosPajeu.Entidade.SuperUsuario;
import br.com.VeiculosPajeu.Exception.ValidationException;
import br.com.VeiculosPajeu.Util.CriptografiaUtil;

public class BusinessSuperUsuario extends Business<SuperUsuario> implements IBusinessSuperUsuario {

	private IDaoSuperUsuario daoSuperUsuario;

	public BusinessSuperUsuario() {
		daoSuperUsuario = new DaoSuperUsuario();
		init(daoSuperUsuario);
	}

	@Override
	public void validation(SuperUsuario entidade) throws ValidationException {

		if (entidade.getLogin().trim().isEmpty())
			throw new ValidationException("INFORME UM LOGIN");
		if (entidade.getNome().trim().isEmpty())
			throw new ValidationException("INFORME UM NOME");
		if (entidade.getSenha().trim().isEmpty())
			throw new ValidationException("INFORME UMA SENHA");
		if (entidade.getSenha_padrao().trim().isEmpty())
			throw new ValidationException("INFORME UMA SENHA PADRÃO");

		if (entidade.getSenha().length() < 6 || entidade.getSenha().length() > 11)
			throw new ValidationException("A SENHA TEM QUE TER NO MINIMO 6 E NO MAXIMO 11 CARACTERES");
		else if (!CriptografiaUtil.isCriptografado(entidade.getSenha()))
			entidade.setSenha(CriptografiaUtil.criptografar(entidade.getSenha().getBytes()));
		
		if (entidade.getSenha_padrao().length() < 6 || entidade.getSenha_padrao().length() > 11)
			throw new ValidationException("A SENHA PADRÃO TEM QUE TER NO MINIMO 6 E NO MAXIMO 11 CARACTERES");
		else if (!CriptografiaUtil.isCriptografado(entidade.getSenha_padrao()))
			entidade.setSenha_padrao(CriptografiaUtil.criptografar(entidade.getSenha_padrao().getBytes()));
	}
}
