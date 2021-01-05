package br.com.VeiculosPajeu.Dao.Interface;

import java.time.LocalDate;
import java.util.List;

import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Log;
import br.com.VeiculosPajeu.Entidade.Enum.TipoHistorico;
import br.com.VeiculosPajeu.Exception.DaoException;

public interface IDaoUtil {

	public Long searchCont(Class<? extends Entidade> classe) throws DaoException;
	
	public Long searchContSelect(Class<? extends Entidade> classe, String sql) throws DaoException;
	
	public List<Log> searchLog(LocalDate date, TipoHistorico tipoHistorico) throws DaoException;
}
