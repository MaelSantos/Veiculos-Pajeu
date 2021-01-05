package br.com.VeiculosPajeu.Dao.Interface;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import br.com.VeiculosPajeu.Entidade.Categoria;
import br.com.VeiculosPajeu.Entidade.Filial;
import br.com.VeiculosPajeu.Entidade.Veiculo;
import br.com.VeiculosPajeu.Entidade.View.VeiculoView;
import br.com.VeiculosPajeu.Exception.DaoException;

public interface IDaoVeiculo extends IDao<Veiculo>{

	public List<Veiculo> searchAllPorCategoria(Categoria categoria) throws DaoException;
	public List<Veiculo> searchDisponivel(String search, boolean manutencao, boolean alugado) throws DaoException;
	public List<VeiculoView> searchAllManutencao() throws DaoException;
	public List<Veiculo> searchAllDataFurtura(Filial filial, LocalDate date) throws DaoException;
	public List<Veiculo> searchAllDiponivel(Filial filial) throws DaoException;
	public BigInteger verificarManutencao() throws DaoException;
}
