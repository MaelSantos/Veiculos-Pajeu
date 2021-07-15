package br.com.VeiculosPajeu.Business;

import java.time.LocalDate;
import java.util.List;

import br.com.VeiculosPajeu.Business.Interface.IBusinessUtil;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Log;
import br.com.VeiculosPajeu.Entidade.Enum.TipoHistorico;
import br.com.VeiculosPajeu.Exception.BusinessException;

public class BusinessUtilProxy implements IBusinessUtil {

	private IBusinessUtil businessUtil;

	@Override
	public Long searchCont(Class<? extends Entidade> classe) throws BusinessException {
		if (businessUtil == null)
			businessUtil = new BusinessUtil();
		return businessUtil.searchCont(classe);
	}

	@Override
	public List<Log> searchLog(LocalDate date, TipoHistorico tipoHistorico) throws BusinessException {
		if (businessUtil == null)
			businessUtil = new BusinessUtil();
		return businessUtil.searchLog(date, tipoHistorico);
	}

	@Override
	public Long searchContSelect(Class<? extends Entidade> classe, String sql) throws BusinessException {
		if (businessUtil == null)
			businessUtil = new BusinessUtil();
		return businessUtil.searchContSelect(classe, sql);
	}
}
