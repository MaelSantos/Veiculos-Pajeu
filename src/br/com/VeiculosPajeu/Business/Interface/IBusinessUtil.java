package br.com.VeiculosPajeu.Business.Interface;

import java.time.LocalDate;
import java.util.List;

import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Log;
import br.com.VeiculosPajeu.Entidade.Enum.TipoHistorico;
import br.com.VeiculosPajeu.Exception.BusinessException;

public interface IBusinessUtil {

	public Long searchCont(Class<? extends Entidade> classe) throws BusinessException;
	
	public List<Log> searchLog(LocalDate date, TipoHistorico tipoHistorico) throws BusinessException;
	
	public Long searchContSelect(Class<? extends Entidade> classe, String sql) throws BusinessException;
}
