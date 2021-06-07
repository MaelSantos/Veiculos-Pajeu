package br.com.VeiculosPajeu.Business;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import br.com.VeiculosPajeu.Business.Interface.IBusinessReserva;
import br.com.VeiculosPajeu.Dao.DaoReserva;
import br.com.VeiculosPajeu.Dao.Interface.IDao;
import br.com.VeiculosPajeu.Dao.Interface.IDaoReserva;
import br.com.VeiculosPajeu.Entidade.Fisica;
import br.com.VeiculosPajeu.Entidade.Reserva;
import br.com.VeiculosPajeu.Entidade.View.ReservaView;
import br.com.VeiculosPajeu.Exception.BusinessException;
import br.com.VeiculosPajeu.Exception.DaoException;
import br.com.VeiculosPajeu.Exception.ValidationException;

public class BusinessReserva extends Business<Reserva> implements IBusinessReserva {

	private IDaoReserva daoReserva;

	@Override
	public List<Reserva> searchAllAtivo(String search) throws BusinessException {
		try {
			return daoReserva.searchAllAtivo(search);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<ReservaView> searchAllVencidos() throws BusinessException {
		try {
			return daoReserva.searchAllVencidos();
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Reserva> searchAllPeriodo(LocalDate inicio, LocalDate fim) throws BusinessException {
		try {
			return daoReserva.searchAllPeriodo(inicio, fim);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public BigInteger verificarAtrasados() throws BusinessException {
		try {
			return daoReserva.verificarAtrasados();
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void validation(Reserva entidade) throws ValidationException {

		if (entidade.getCategoria() == null)
			throw new ValidationException("INFORME UMA CATEGORIA");
		if (entidade.getCliente() == null)
			throw new ValidationException("INFORME UM CLIENTE");
		if (entidade.getData_devolucao() == null)
			throw new ValidationException("INFORME UMA DATA DE DEVOLUÇÃO");
		if (entidade.getData_locacao() == null)
			throw new ValidationException("INFORME UMA DATA DE LOCAÇÃO");
		if (entidade.getTipo_locacao() == null)
			throw new ValidationException("INFORME UM TIPO DE LOCAÇÃO");
		if (entidade.getHora_reserva() == null)
			throw new ValidationException("INFORME A HORA DE RESERVA");
		if (entidade.getData_locacao().isAfter(entidade.getData_devolucao()))
			throw new ValidationException("DATA DE LOCAÇÃO MAIOR QUE DEVOLUÇÃO");
		if (entidade.getData_reserva().isAfter(entidade.getData_locacao()))
			throw new ValidationException("DATA DE RESERVA MAIOR QUE LOCAÇÃO");
		if (entidade.getData_reserva().isAfter(entidade.getData_devolucao()))
			throw new ValidationException("DATA DE RESERVA MAIOR QUE DEVOLUÇÃO");
		if (entidade.getCliente() instanceof Fisica) {
			Fisica fisica = (Fisica) entidade.getCliente();
			if (fisica.getVencimento_habilitacao().isBefore(entidade.getData_devolucao())) {
				throw new ValidationException("Habilitação Vencerá Dentro do Prazo de Locação");
			}
		}
	}

	@Override
	public IDao<Reserva> createDao() throws ValidationException {
		if (daoReserva == null)
			daoReserva = new DaoReserva();
		return daoReserva;
	}

}
