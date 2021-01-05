package br.com.VeiculosPajeu.Dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.VeiculosPajeu.Dao.Interface.IDaoUtil;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Log;
import br.com.VeiculosPajeu.Entidade.Enum.TipoHistorico;
import br.com.VeiculosPajeu.Exception.DaoException;
import br.com.VeiculosPajeu.Util.SQLUtil;

public class DaoUtil implements IDaoUtil {

	private EntityManager entityManager;

	public DaoUtil() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		return Dao.getEntityManager();
	}

	public Long searchCont(Class<? extends Entidade> classe) throws DaoException {
		try {
			Query query = entityManager.createQuery("Select count(1) From " + classe.getName() + " a", Long.class);

			return (Long) query.getSingleResult();

		} catch (NoResultException n) {
			n.printStackTrace();
			throw new DaoException("Nenhum " + classe.getSimpleName() + " Encontrado");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
		}
	}

	@Override
	public List<Log> searchLog(LocalDate date, TipoHistorico tipoHistorico) throws DaoException {
		try {
			TypedQuery<Log> query = entityManager.createQuery(SQLUtil.LOG + "", Log.class);
			query.setParameter("data", date);
			query.setParameter("alteracao", "%"+tipoHistorico.getValor()+"%");

			return query.getResultList();

		} catch (NoResultException n) {
			n.printStackTrace();
			throw new DaoException("Nenhum Histórico Encontrado");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Erro ao Procurar Histórico" + e.getMessage());
		}
	}

	@Override
	public Long searchContSelect(Class<? extends Entidade> classe, String sql) throws DaoException {
		try {
			Query query = entityManager.createQuery("select count(1) from "+classe.getName()+" a where "+sql, Long.class);

			return (Long) query.getSingleResult();

		} catch (NoResultException n) {
			n.printStackTrace();
			throw new DaoException("Nenhum Registro Encontrado");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Erro ao Procurar Quantidade de "+classe.getSimpleName() + e.getMessage());
		}

	}
	
}
