package br.com.VeiculosPajeu.Business;

import java.time.LocalDate;
import java.util.List;

import br.com.VeiculosPajeu.Business.Interface.IBusinessLocacao;
import br.com.VeiculosPajeu.Dao.DaoLocacao;
import br.com.VeiculosPajeu.Dao.Interface.IDaoLocacao;
import br.com.VeiculosPajeu.Entidade.Cliente;
import br.com.VeiculosPajeu.Entidade.Fisica;
import br.com.VeiculosPajeu.Entidade.Locacao;
import br.com.VeiculosPajeu.Entidade.View.LocacaoView;
import br.com.VeiculosPajeu.Exception.BusinessException;
import br.com.VeiculosPajeu.Exception.DaoException;
import br.com.VeiculosPajeu.Exception.ValidationException;

public class BusinessLocacao extends Business<Locacao> implements IBusinessLocacao{

	private IDaoLocacao daoLocacao;
	
	public BusinessLocacao() {
		daoLocacao = new DaoLocacao();
		init(daoLocacao);
	}
	
	@Override
	public void validation(Locacao entidade) throws ValidationException {
		
		if(entidade.getCliente() == null)
			throw new ValidationException("INFORME UM CLIENTE");
		if(entidade.getData_devolucao() == null)
			throw new ValidationException("INFORME UMA DATA DE DEVOLUÇÃO");
		if(entidade.getData_locacao() == null)
			throw new ValidationException("INFORME UMA DATA DE LOCAÇÃO");
		if(entidade.getDiaria() == null)
			throw new ValidationException("INFORME A DIÁRIA");
		if(entidade.getFilial_locacao() == null)
			throw new ValidationException("INFORME EM QUAL SERÁ A FILIAL FEITA A LOCAÇÃO");
		if(entidade.getTipoLocacao() == null)
			throw new ValidationException("INFORME O TIPO DE LOCAÇÃO");
		if(entidade.getValor_total() == null)
			throw new ValidationException("INFORME O VALOR TOTAL");
		if(entidade.getVeiculo() == null)
			throw new ValidationException("INFORME O VEÍCULO");
		if(entidade.getHora_devolucao() == null)
			throw new ValidationException("INFORME A HORA DE DEVOLUÇÃO");
		if(entidade.getData_locacao().isAfter(entidade.getData_devolucao()))
			throw new ValidationException("DATA DE LOCAÇÃO MAIOR QUE DEVOLUÇÃO");
		if(entidade.getValor_total() < 0)
			throw new ValidationException("VALOR NEGATIVO - CONTATAR ADM");
		if (entidade.getCliente() instanceof Fisica) {
			Fisica fisica = (Fisica) entidade.getCliente();
			if (fisica.getVencimento_habilitacao().isBefore(entidade.getData_devolucao())) {
				throw new ValidationException("Habilitação Vencerá Dentro do Prazo de Locação");
			}
		}
	}

	@Override
	public List<Locacao> searchAllAtivo(String search) throws BusinessException {
		try {
			return daoLocacao.searchAllAtivo(search);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<LocacaoView> searchAllVencidos() throws BusinessException {
		try {
			return daoLocacao.searchAllVencidos();
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Locacao> searchAllPeriodo(LocalDate inicio, LocalDate fim) throws BusinessException {
		try {
			return daoLocacao.searchAllPeriodo(inicio, fim);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Locacao> searchAllPorCliente(Cliente cliente) throws BusinessException {
		try {
			return daoLocacao.searchAllPorCliente(cliente);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}

}
