package br.com.VeiculosPajeu.Dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.VeiculosPajeu.Dao.Interface.IDaoUsuario;
import br.com.VeiculosPajeu.Entidade.Usuario;
import br.com.VeiculosPajeu.Exception.DaoException;
import br.com.VeiculosPajeu.Util.SQLUtil;

public class DaoUsuario extends Dao<Usuario> implements IDaoUsuario {

	public DaoUsuario() {
		super(Usuario.class);
	}

	@Override
	public Usuario searchUser(String login, String senha) throws DaoException {
		try {

			TypedQuery<Usuario> query = entityManager.createQuery(SQLUtil.USUARIO_LOGIN + "", classe);
			query.setParameter("login", login);
			query.setParameter("senha", senha);

			return query.getSingleResult();

		} catch (NoResultException n) {
			n.printStackTrace();
			throw new DaoException("Nenhum Usuário Encontrado");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
		}
	}

}
