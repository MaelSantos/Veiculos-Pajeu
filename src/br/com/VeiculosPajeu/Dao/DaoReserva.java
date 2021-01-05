package br.com.VeiculosPajeu.Dao;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.VeiculosPajeu.Dao.Interface.IDaoReserva;
import br.com.VeiculosPajeu.Entidade.Reserva;
import br.com.VeiculosPajeu.Entidade.View.ReservaView;
import br.com.VeiculosPajeu.Exception.DaoException;
import br.com.VeiculosPajeu.Util.SQLUtil;

public class DaoReserva extends Dao<Reserva> implements IDaoReserva {

	public DaoReserva() {
		super(Reserva.class);
	}

	@Override
	public List<Reserva> searchAllAtivo(String search) throws DaoException {

		try {
			TypedQuery<Reserva> query = entityManager.createQuery(SQLUtil.RESERVA_ATIVA + "", classe);
			query.setParameter("trocar", "%" + search.toLowerCase() + "%");
			query.setParameter("ativo", true);

			return query.getResultList();

		} catch (NoResultException n) {
			n.printStackTrace();
			throw new DaoException("Nenhuma Reserva Encontrada");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
		}
	}

	@Override
	public List<ReservaView> searchAllVencidos() throws DaoException {

		try {
			TypedQuery<ReservaView> query = entityManager.createQuery(SQLUtil.RESERVA_VENCIDA + "", ReservaView.class);
//			query.setParameter("ativo", true);

			return query.getResultList();

		} catch (NoResultException n) {
			n.printStackTrace();
			throw new DaoException("Nenhuma Reserva Encontrada");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
		}
	}

	@Override
	public List<Reserva> searchAllPeriodo(LocalDate inicio, LocalDate fim) throws DaoException {

		try {
			TypedQuery<Reserva> query = entityManager.createQuery(SQLUtil.RESERVA_PERIODO + "", classe);
			query.setParameter("inicio", inicio);
			query.setParameter("fim", fim);

			return query.getResultList();

		} catch (NoResultException n) {
			n.printStackTrace();
			throw new DaoException("Nenhuma Reserva Encontrada");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
		}
	}

	@Override
	public BigInteger verificarAtrasados() throws DaoException {

		try {
			Query query = entityManager.createNativeQuery("SELECT count(*) FROM verificar_atraso_reserva()");
			BigInteger result = (BigInteger) query.getSingleResult();

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Erro Ao Veirifcar Reservas Atrasados");
		}
	}	
}
