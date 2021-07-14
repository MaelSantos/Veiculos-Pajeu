package br.com.VeiculosPajeu.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.VeiculosPajeu.Connection.Connection;
import br.com.VeiculosPajeu.Dao.Interface.IDao;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Enum.TipoBanco;
import br.com.VeiculosPajeu.Exception.DaoException;
import br.com.VeiculosPajeu.Util.SQLUtil;

public class Dao<T extends Entidade> implements IDao<T> {

	protected static EntityManager entityManager;
	private static Connection connectionFactory = Connection.getInstance(TipoBanco.POSTGRESQL);
	protected Class<T> classe;

	public static void closeConnection() {
		entityManager.flush();
		entityManager.close();
	}

	public static void resetConnection() {

		connectionFactory.alterarPropriedades(true);
		entityManager.clear();
		entityManager.close();
		entityManager = connectionFactory.getEntityManager();
		entityManager.clear();

	}

	protected static EntityManager getEntityManager() {
		if (entityManager == null) {
			connectionFactory.alterarPropriedades(false);
			entityManager = connectionFactory.getEntityManager();
			entityManager.clear();
		}

		return entityManager;
	}

	public Dao(Class<T> classe) {
		this.classe = classe;
	}

	@Override
	public void create(T entidade) throws DaoException {

		try {
			entityManager.getTransaction().begin();
			entityManager.persist(entidade);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			throw new DaoException("Erro ao Salvar " + classe.getSimpleName() + " - " + e.getMessage());
		}
	}

	@Override
	public void update(T entidade) throws DaoException {

		try {
			entityManager.getTransaction().begin();
			entityManager.merge(entidade);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			throw new DaoException("Erro ao Atualizar " + classe.getSimpleName() + " - " + e.getMessage());
		}
	}

	@Override
	public T search(int id) throws DaoException {
		try {
			return entityManager.find(classe, id);
		} catch (NoResultException n) {
			n.printStackTrace();
			throw new DaoException("Nenhum " + classe.getSimpleName() + " Encontrado");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Erro Ao Procurar Por " + classe.getSimpleName());
		}
	}

	@Override
	public void remove(int id) throws DaoException {

		try {
			entityManager.getTransaction().begin();
			T entidade = entityManager.find(classe, id);
			entityManager.remove(entidade);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			throw new DaoException("Erro ao Remover " + classe.getSimpleName() + " - " + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> searchAll() throws DaoException {
		try {
			return entityManager.createQuery("FROM " + classe.getName()).getResultList();
		} catch (NoResultException n) {
			n.printStackTrace();
			throw new DaoException("Nenhum " + classe.getSimpleName() + " Encontrado");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
		}
	}

	@Override
	public List<T> searchAll(String search) throws DaoException {
		try {
			TypedQuery<T> query = entityManager.createQuery(SQLUtil.buscaPorBusca(classe), classe);
			query.setParameter("trocar", "%" + search.toLowerCase() + "%");

			return query.getResultList();
		} catch (NoResultException n) {
			n.printStackTrace();
			throw new DaoException("Nenhum " + classe.getSimpleName() + " Encontrado");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
		}
	}

}
