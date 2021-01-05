package br.com.VeiculosPajeu.Dao.Interface;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import br.com.VeiculosPajeu.Entidade.Reserva;
import br.com.VeiculosPajeu.Entidade.View.ReservaView;
import br.com.VeiculosPajeu.Exception.DaoException;

public interface IDaoReserva extends IDao<Reserva> {

	public List<Reserva> searchAllAtivo(String search) throws DaoException;

	public List<ReservaView> searchAllVencidos() throws DaoException;
	
	public List<Reserva> searchAllPeriodo(LocalDate inicio, LocalDate fim) throws DaoException;
	
	public BigInteger verificarAtrasados() throws DaoException;
}
