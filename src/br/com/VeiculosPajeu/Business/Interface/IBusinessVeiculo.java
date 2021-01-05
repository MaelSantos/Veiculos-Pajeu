package br.com.VeiculosPajeu.Business.Interface;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import br.com.VeiculosPajeu.Entidade.Categoria;
import br.com.VeiculosPajeu.Entidade.Filial;
import br.com.VeiculosPajeu.Entidade.Veiculo;
import br.com.VeiculosPajeu.Entidade.View.VeiculoView;
import br.com.VeiculosPajeu.Exception.BusinessException;

public interface IBusinessVeiculo extends IBusiness<Veiculo>{

	public List<Veiculo> searchAllPorCategoria(Categoria categoria) throws BusinessException;
	public List<Veiculo> searchDisponivel(String search, boolean reservado, boolean alugado) throws BusinessException;
	public List<VeiculoView> searchAllManutencao() throws BusinessException;
	public List<Veiculo> searchAllDataFurtura(Filial filial, LocalDate date) throws BusinessException;
	public List<Veiculo> searchAllDiponivel(Filial filial) throws BusinessException;
	public BigInteger verificarManutencao() throws BusinessException;
}
