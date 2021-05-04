package br.com.VeiculosPajeu.Business;

import java.time.LocalDate;

import br.com.VeiculosPajeu.Business.Interface.IBusinessFisica;
import br.com.VeiculosPajeu.Dao.DaoFisica;
import br.com.VeiculosPajeu.Dao.Interface.IDaoFisica;
import br.com.VeiculosPajeu.Entidade.Fisica;
import br.com.VeiculosPajeu.Exception.ValidationException;
import br.com.VeiculosPajeu.Util.CriptografiaUtil;
import br.com.VeiculosPajeu.Util.DateUtil;

public class BusinessFisica extends Business<Fisica> implements IBusinessFisica {

	private IDaoFisica daoFisica;
	private CriptografiaUtil criptografiaUtil;
	private DateUtil dateUtil;

	public BusinessFisica() {
		daoFisica = new DaoFisica();
		criptografiaUtil = CriptografiaUtil.getInstance();
		dateUtil = DateUtil.getInstance();
		init(daoFisica);
	}

	@Override
	public void validation(Fisica entidade) throws ValidationException {

		if (entidade.getCodigo().trim().isEmpty())
			throw new ValidationException("INFORME UM CODIGO");
		if (entidade.getCpf().trim().isEmpty())
			throw new ValidationException("INFORME UM CPF");
		if (entidade.getData_de_nascimento() == null)
			throw new ValidationException("INFORME UMA DATA DE NASCIMENTO");
		if (entidade.getNome().trim().isEmpty())
			throw new ValidationException("INFORME UM NOME");
		if (entidade.getNumero_habilitacao().trim().isEmpty())
			throw new ValidationException("INFORME O NUMERO DA HABILITAÇÃO");
		if (entidade.getSexo() == null)
			throw new ValidationException("INFORME O GÊNERO");
		if (entidade.getVencimento_habilitacao() == null)
			throw new ValidationException("INFORME A DATA DE VENCIMENTO DA CARTEIRA");
		if (dateUtil.DiferencaAnos(entidade.getData_de_nascimento()) < 21)
			throw new ValidationException("É NESCESSARIO TER 21 ANOS OU MAIS PARA RELAIZAR O CADASTRO");
		if (entidade.getVencimento_habilitacao().isBefore(LocalDate.now()))
			throw new ValidationException("HABILITAÇÃO VENCIDA");

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

}
