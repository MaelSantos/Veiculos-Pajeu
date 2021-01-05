package br.com.VeiculosPajeu.Dao.Interface;

import java.time.LocalDate;
import java.util.List;

import br.com.VeiculosPajeu.Entidade.Cliente;
import br.com.VeiculosPajeu.Entidade.Locacao;
import br.com.VeiculosPajeu.Entidade.View.LocacaoView;
import br.com.VeiculosPajeu.Exception.DaoException;

public interface IDaoLocacao extends IDao<Locacao> {

	public List<Locacao> searchAllAtivo(String search) throws DaoException;
	
	public List<LocacaoView> searchAllVencidos() throws DaoException;
	
	public List<Locacao> searchAllPeriodo(LocalDate inicio, LocalDate fim) throws DaoException;

	List<Locacao> searchAllPorCliente(Cliente cliente) throws DaoException;
}
