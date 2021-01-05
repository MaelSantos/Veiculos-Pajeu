package br.com.VeiculosPajeu.Dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.VeiculosPajeu.Dao.Interface.IDaoLocacao;
import br.com.VeiculosPajeu.Entidade.Cliente;
import br.com.VeiculosPajeu.Entidade.Locacao;
import br.com.VeiculosPajeu.Entidade.View.LocacaoView;
import br.com.VeiculosPajeu.Exception.DaoException;
import br.com.VeiculosPajeu.Util.SQLUtil;

public class DaoLocacao extends Dao<Locacao> implements IDaoLocacao {

	public DaoLocacao() {
		super(Locacao.class);
	}

	@Override
	public List<Locacao> searchAllAtivo(String search) throws DaoException {
		try {
			TypedQuery<Locacao> query = entityManager.createQuery(SQLUtil.LOCACAO_ATIVA + "", classe);
			query.setParameter("trocar", "%" + search.toLowerCase() + "%");
			query.setParameter("ativo", true);

			return query.getResultList();

		} catch (NoResultException n) {
			n.printStackTrace();
			throw new DaoException("Nenhuma Locação Encontrada");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
		}

	}

	@Override
	public List<LocacaoView> searchAllVencidos() throws DaoException {

		try {
			TypedQuery<LocacaoView> query = entityManager.createQuery(SQLUtil.LOCACAO_VENCIDA + "", LocacaoView.class);

			return query.getResultList();

		} catch (NoResultException n) {
			n.printStackTrace();
			throw new DaoException("Nenhuma Locação Encontrada");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
		}
	}

	@Override
	public List<Locacao> searchAllPeriodo(LocalDate inicio, LocalDate fim) throws DaoException {

		try {
			TypedQuery<Locacao> query = entityManager.createQuery(SQLUtil.LOCACAO_PERIODO + "", classe);
			query.setParameter("inicio", inicio);
			query.setParameter("fim", fim);

			return query.getResultList();

		} catch (NoResultException n) {
			n.printStackTrace();
			throw new DaoException("Nenhuma Locação Encontrada");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
		}
	}

	@Override
	public List<Locacao> searchAllPorCliente(Cliente cliente) throws DaoException {
		try {
			TypedQuery<Locacao> query = entityManager.createQuery(SQLUtil.LOCACAO_CLIENTE + "", classe);
			query.setParameter("cliente", cliente);
			
			return query.getResultList();

		} catch (NoResultException n) {
			n.printStackTrace();
			throw new DaoException("Nenhuma Locação Encontrada");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
		}

	}

}
