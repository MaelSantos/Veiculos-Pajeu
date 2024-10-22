package br.com.VeiculosPajeu.Business;

import br.com.VeiculosPajeu.Business.Interface.IBusinessJuridica;
import br.com.VeiculosPajeu.Dao.DaoJuridica;
import br.com.VeiculosPajeu.Dao.Interface.IDao;
import br.com.VeiculosPajeu.Entidade.Juridica;
import br.com.VeiculosPajeu.Exception.ValidationException;
import br.com.VeiculosPajeu.Util.CriptografiaUtil;

public class BusinessJuridica extends Business<Juridica> implements IBusinessJuridica {

	private DaoJuridica daoJuridica;
	private CriptografiaUtil criptografiaUtil;

	public BusinessJuridica() {
		super();
		criptografiaUtil = CriptografiaUtil.getInstance();
	}

	@Override
	public void validation(Juridica entidade) throws ValidationException {

		if (entidade.getCnpj().trim().isEmpty())
			throw new ValidationException("INFORME O CNPJ");
		if (entidade.getCodigo().trim().isEmpty())
			throw new ValidationException("INFORME O CODIGO DO CLIENTE");
		if (entidade.getInscricao_estadual().trim().isEmpty())
			throw new ValidationException("INFORME A INSCRIÇÃO ESTADUAL");
		if (entidade.getNome().trim().isEmpty())
			throw new ValidationException("INFORME UM NOME");

		// usuário
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
	public IDao<Juridica> createDao() throws ValidationException {
		if (daoJuridica == null)
			daoJuridica = new DaoJuridica();
		return daoJuridica;
	}

}
