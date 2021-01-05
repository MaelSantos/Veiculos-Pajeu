package br.com.VeiculosPajeu.Business.Interface;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import br.com.VeiculosPajeu.Entidade.Financeiro;
import br.com.VeiculosPajeu.Entidade.Enum.EstadoFinanceiro;
import br.com.VeiculosPajeu.Exception.BusinessException;

public interface IBusinessFinanceiro extends IBusiness<Financeiro>{

	 public List<Financeiro> searchAllEstado(LocalDate de, LocalDate ate, EstadoFinanceiro estado) throws BusinessException;
	 
	 public BigInteger verificarAtrasados() throws BusinessException;
}
