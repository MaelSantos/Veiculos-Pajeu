package br.com.VeiculosPajeu.Dao;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.VeiculosPajeu.Dao.Interface.IDaoVeiculo;
import br.com.VeiculosPajeu.Entidade.Categoria;
import br.com.VeiculosPajeu.Entidade.Filial;
import br.com.VeiculosPajeu.Entidade.Veiculo;
import br.com.VeiculosPajeu.Entidade.View.VeiculoView;
import br.com.VeiculosPajeu.Exception.DaoException;
import br.com.VeiculosPajeu.Util.SQLUtil;

public class DaoVeiculo extends Dao<Veiculo> implements IDaoVeiculo {

	public DaoVeiculo() {
		super(Veiculo.class);
	}

	@Override
	public List<Veiculo> searchAllPorCategoria(Categoria categoria) throws DaoException {
		try {

			TypedQuery<Veiculo> query = entityManager.createQuery(SQLUtil.VEICULO_POR_CATEGORIA + "", classe);
			query.setParameter("trocar", categoria);
			query.setParameter("manutencao", false);
			query.setParameter("alugado", false);

			return query.getResultList();

		} catch (NoResultException n) {
			n.printStackTrace();
			throw new DaoException("Nenhum Veículo Encontrado");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
		}
	}

	@Override
	public List<Veiculo> searchDisponivel(String search, boolean manutencao, boolean alugado) throws DaoException {
		try {

			TypedQuery<Veiculo> query = entityManager.createQuery(SQLUtil.VEICULO_DISPONIVEL + "", classe);
			query.setParameter("trocar", "%" + search.toLowerCase() + "%");
			query.setParameter("manutencao", manutencao);
			query.setParameter("alugado", alugado);

			return query.getResultList();

		} catch (NoResultException n) {
			n.printStackTrace();
			throw new DaoException("Nenhum Veículo Encontrado");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
		}
	}

	@Override
	public List<VeiculoView> searchAllManutencao() throws DaoException {
		try {

			TypedQuery<VeiculoView> query = null;
			List<VeiculoView> veiculoViews = new ArrayList<>();

			query = entityManager.createQuery(SQLUtil.AUTOMOVEL_MANUTENCAO + "", VeiculoView.class);
			veiculoViews.addAll(query.getResultList());

			query = entityManager.createQuery(SQLUtil.CARGA_MANUTENCAO + "", VeiculoView.class);
			veiculoViews.addAll(query.getResultList());

			query = entityManager.createQuery(SQLUtil.PASSAGEIROS_MANUTENCAO + "", VeiculoView.class);
			veiculoViews.addAll(query.getResultList());

			return veiculoViews;

		} catch (NoResultException n) {
			n.printStackTrace();
			throw new DaoException("Nenhum Veículo Encontrado");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
		}

	}

	@Override
	public List<Veiculo> searchAllDataFurtura(Filial filial, LocalDate date) throws DaoException {
		try {

			TypedQuery<Veiculo> query = entityManager.createQuery(SQLUtil.VEICULO_DATA_FUTURA + "", classe);
			query.setParameter("data", date);
			query.setParameter("filial", filial);

			return query.getResultList();

		} catch (NoResultException n) {
			n.printStackTrace();
			throw new DaoException("Nenhum Veículo Encontrado");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
		}

	}

	@Override
	public List<Veiculo> searchAllDiponivel(Filial filial) throws DaoException {
		try {

			TypedQuery<Veiculo> query = entityManager.createQuery(SQLUtil.VEICULO_DATA_CORRENTE + "", classe);
			query.setParameter("filial", filial);
			
			return query.getResultList();

		} catch (NoResultException n) {
			n.printStackTrace();
			throw new DaoException("Nenhum Veículo Encontrado");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Erro ao Procurar " + classe.getSimpleName() + " - " + e.getMessage());
		}
	}

	@Override
	public BigInteger verificarManutencao() throws DaoException {
		try {
			Query query = entityManager.createNativeQuery("SELECT count(*) FROM verificar_manutencao()");
			BigInteger result = (BigInteger) query.getSingleResult();

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Erro Ao Veirifcar Veículos na Manutenção");
		}

	}

}
