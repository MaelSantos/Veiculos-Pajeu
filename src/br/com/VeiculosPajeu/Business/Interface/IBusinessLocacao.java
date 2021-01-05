package br.com.VeiculosPajeu.Business.Interface;

import java.time.LocalDate;
import java.util.List;

import br.com.VeiculosPajeu.Entidade.Cliente;
import br.com.VeiculosPajeu.Entidade.Locacao;
import br.com.VeiculosPajeu.Entidade.View.LocacaoView;
import br.com.VeiculosPajeu.Exception.BusinessException;

public interface IBusinessLocacao extends IBusiness<Locacao> {

	public List<Locacao> searchAllAtivo(String search) throws BusinessException;

	public List<LocacaoView> searchAllVencidos() throws BusinessException;

	public List<Locacao> searchAllPeriodo(LocalDate inicio, LocalDate fim) throws BusinessException;

	public List<Locacao> searchAllPorCliente(Cliente cliente) throws BusinessException;
	
}
