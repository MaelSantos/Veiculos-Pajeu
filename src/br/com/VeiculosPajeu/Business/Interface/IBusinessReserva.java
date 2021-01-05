package br.com.VeiculosPajeu.Business.Interface;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import br.com.VeiculosPajeu.Entidade.Reserva;
import br.com.VeiculosPajeu.Entidade.View.ReservaView;
import br.com.VeiculosPajeu.Exception.BusinessException;

public interface IBusinessReserva extends IBusiness<Reserva>{

	public List<Reserva> searchAllAtivo(String search) throws BusinessException;
	
	public List<ReservaView> searchAllVencidos() throws BusinessException;
	
	public List<Reserva> searchAllPeriodo(LocalDate inicio, LocalDate fim) throws BusinessException;
	
	public BigInteger verificarAtrasados() throws BusinessException;
}
