package br.com.VeiculosPajeu.Dao;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.VeiculosPajeu.Dao.Interface.IDaoFinanceiro;
import br.com.VeiculosPajeu.Entidade.Financeiro;
import br.com.VeiculosPajeu.Entidade.Enum.EstadoFinanceiro;
import br.com.VeiculosPajeu.Exception.DaoException;
import br.com.VeiculosPajeu.Util.SQLUtil;

public class DaoFinanceiro extends Dao<Financeiro> implements IDaoFinanceiro {

	public DaoFinanceiro() {
		super(Financeiro.class);
	}

	@Override
	public List<Financeiro> searchAllEstado(LocalDate de, LocalDate ate, EstadoFinanceiro estado) throws DaoException {

		try {
			TypedQuery<Financeiro> query = entityManager.createQuery(SQLUtil.FINANCEIRO_ESTADO + "", classe);
			query.setParameter("de", de);
			query.setParameter("ate", ate);

			if (estado != null)
				query.setParameter("estado", ("%" + estado + "%").toLowerCase());
			else
				query.setParameter("estado", "%%");

			return query.getResultList();

		} catch (NoResultException n) {
			n.printStackTrace();
			throw new DaoException("Nenhum Financeiro Encontrado");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
		}

	}

	@Override
	public BigInteger verificarAtrasados() throws DaoException {

		try {
			Query query = entityManager.createNativeQuery("SELECT count(*) FROM verificar_atraso_financeiro()");
			BigInteger result = (BigInteger) query.getSingleResult();

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Erro Ao Veirifcar Financeiros Atrasados");
		}
	}
}
