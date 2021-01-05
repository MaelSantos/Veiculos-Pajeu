package br.com.VeiculosPajeu.Business;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import br.com.VeiculosPajeu.Business.Interface.IBusinessVeiculo;
import br.com.VeiculosPajeu.Dao.DaoVeiculo;
import br.com.VeiculosPajeu.Dao.Interface.IDaoVeiculo;
import br.com.VeiculosPajeu.Entidade.Categoria;
import br.com.VeiculosPajeu.Entidade.Filial;
import br.com.VeiculosPajeu.Entidade.Veiculo;
import br.com.VeiculosPajeu.Entidade.View.VeiculoView;
import br.com.VeiculosPajeu.Exception.BusinessException;
import br.com.VeiculosPajeu.Exception.DaoException;
import br.com.VeiculosPajeu.Exception.ValidationException;

public class BusinessVeiculo extends Business<Veiculo> implements IBusinessVeiculo {

	private IDaoVeiculo daoVeiculo;

	public BusinessVeiculo() {

		daoVeiculo = new DaoVeiculo();
		init(daoVeiculo);
	}

	@Override
	public void validation(Veiculo entidade) throws ValidationException {
	}

	@Override
	public List<Veiculo> searchAllPorCategoria(Categoria categoria) throws BusinessException {
		try {
			return daoVeiculo.searchAllPorCategoria(categoria);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Veiculo> searchDisponivel(String search, boolean reservado, boolean alugado) throws BusinessException {
		try {
			return daoVeiculo.searchDisponivel(search, reservado, alugado);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<VeiculoView> searchAllManutencao() throws BusinessException {
		try {
			return daoVeiculo.searchAllManutencao();
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Veiculo> searchAllDataFurtura(Filial filial, LocalDate date) throws BusinessException {
		try {
			return daoVeiculo.searchAllDataFurtura(filial, date);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Veiculo> searchAllDiponivel(Filial filial) throws BusinessException {
		try {
			return daoVeiculo.searchAllDiponivel(filial);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public BigInteger verificarManutencao() throws BusinessException {
		try {
			return daoVeiculo.verificarManutencao();
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}
}
