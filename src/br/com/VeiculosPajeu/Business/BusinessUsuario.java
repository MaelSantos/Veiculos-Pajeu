package br.com.VeiculosPajeu.Business;

import br.com.VeiculosPajeu.Business.Interface.IBusinessUsuario;
import br.com.VeiculosPajeu.Dao.DaoUsuario;
import br.com.VeiculosPajeu.Dao.Interface.IDaoUsuario;
import br.com.VeiculosPajeu.Entidade.SuperUsuario;
import br.com.VeiculosPajeu.Entidade.Usuario;
import br.com.VeiculosPajeu.Exception.BusinessException;
import br.com.VeiculosPajeu.Exception.DaoException;
import br.com.VeiculosPajeu.Exception.ValidationException;
import br.com.VeiculosPajeu.Util.CriptografiaUtil;

public class BusinessUsuario extends Business<Usuario> implements IBusinessUsuario {

	private IDaoUsuario daoUsuario;

	public BusinessUsuario() {

		daoUsuario = new DaoUsuario();
		init(daoUsuario);
	}

	@Override
	public void validation(Usuario entidade) throws ValidationException {

		if (entidade.getSenha().length() < 6 || entidade.getSenha().length() > 11)
			throw new ValidationException("A SENHA TEM QUE TER NO MINIMO 6 E NO MAXIMO 11 CARACTERES");
		else if (!CriptografiaUtil.isCriptografado(entidade.getSenha()))
			entidade.setSenha(CriptografiaUtil.criptografar(entidade.getSenha().getBytes()));

		if (entidade instanceof SuperUsuario) {
			SuperUsuario superUsuario = (SuperUsuario) entidade;

			if (superUsuario.getSenha_padrao().length() < 6 || superUsuario.getSenha_padrao().length() > 11)
				throw new ValidationException("A SENHA PADRÃO TEM QUE TER NO MINIMO 6 E NO MAXIMO 11 CARACTERES");
			else if (!CriptografiaUtil.isCriptografado(superUsuario.getSenha_padrao()))
				superUsuario.setSenha_padrao(CriptografiaUtil.criptografar(superUsuario.getSenha_padrao().getBytes()));
		}
	}

	@Override
	public Usuario searchUser(String login, String senha) throws BusinessException {
		try {

			senha = CriptografiaUtil.criptografar(senha.getBytes());
			Usuario usuario = daoUsuario.searchUser(login, senha);

			return usuario;

		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}
}
