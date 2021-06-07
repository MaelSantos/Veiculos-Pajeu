package br.com.VeiculosPajeu.Business;

import java.time.LocalDate;
import java.util.List;

import br.com.VeiculosPajeu.Business.Interface.IBusinessUtil;
import br.com.VeiculosPajeu.Dao.DaoUtil;
import br.com.VeiculosPajeu.Dao.Interface.IDaoUtil;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Log;
import br.com.VeiculosPajeu.Entidade.Enum.TipoHistorico;
import br.com.VeiculosPajeu.Exception.BusinessException;
import br.com.VeiculosPajeu.Exception.DaoException;

public class BusinessUtil implements IBusinessUtil {

	private IDaoUtil daoUtil;

	public BusinessUtil() {
		daoUtil = new DaoUtil();
	}

	@Override
	public Long searchCont(Class<? extends Entidade> classe) throws BusinessException {
		try {
			return daoUtil.searchCont(classe);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Log> searchLog(LocalDate date, TipoHistorico tipoHistorico) throws BusinessException {
		try {
			return daoUtil.searchLog(date, tipoHistorico);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public Long searchContSelect(Class<? extends Entidade> classe, String sql) throws BusinessException {
		try {
			return daoUtil.searchContSelect(classe, sql);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}
}
