package br.com.VeiculosPajeu.Business;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import br.com.VeiculosPajeu.Business.Interface.IBusinessFinanceiro;
import br.com.VeiculosPajeu.Dao.DaoFinanceiro;
import br.com.VeiculosPajeu.Dao.Interface.IDaoFinanceiro;
import br.com.VeiculosPajeu.Entidade.Financeiro;
import br.com.VeiculosPajeu.Entidade.Enum.EstadoFinanceiro;
import br.com.VeiculosPajeu.Exception.BusinessException;
import br.com.VeiculosPajeu.Exception.DaoException;
import br.com.VeiculosPajeu.Exception.ValidationException;

public class BusinessFinanceiro extends Business<Financeiro> implements IBusinessFinanceiro {

	private IDaoFinanceiro daoFinanceiro;
	
	public BusinessFinanceiro() {
		
		daoFinanceiro = new DaoFinanceiro();
		init(daoFinanceiro);
	}
	
	@Override
	public void validation(Financeiro entidade) throws ValidationException {
		
		if(entidade.getData_aberta() == null)
			throw new ValidationException("INFORME A DATA QUE FOI GERADO");
		if(entidade.getEstado() == null)
			throw new ValidationException("INFORME O ESTADO FINANCEIRO");
		if(entidade.getValor_total() == null)
			throw new ValidationException("INFORME O VALOR");
	}

	@Override
	public List<Financeiro> searchAllEstado(LocalDate de, LocalDate ate, EstadoFinanceiro estado) throws BusinessException {
		try {
			return daoFinanceiro.searchAllEstado(de, ate, estado);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public BigInteger verificarAtrasados() throws BusinessException {
		
		try {
			return daoFinanceiro.verificarAtrasados();
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
		
	}
	
}
