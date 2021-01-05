package br.com.VeiculosPajeu.Dao.Interface;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import br.com.VeiculosPajeu.Entidade.Financeiro;
import br.com.VeiculosPajeu.Entidade.Enum.EstadoFinanceiro;
import br.com.VeiculosPajeu.Exception.DaoException;

public interface IDaoFinanceiro extends IDao<Financeiro>{

    public List<Financeiro> searchAllEstado(LocalDate de, LocalDate ate, EstadoFinanceiro estado) throws DaoException;
	
    public BigInteger verificarAtrasados() throws DaoException;
    
}
