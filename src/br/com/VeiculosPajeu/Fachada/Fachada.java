package br.com.VeiculosPajeu.Fachada;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import br.com.VeiculosPajeu.Entidade.Automovel;
import br.com.VeiculosPajeu.Entidade.Carga;
import br.com.VeiculosPajeu.Entidade.Categoria;
import br.com.VeiculosPajeu.Entidade.Cliente;
import br.com.VeiculosPajeu.Entidade.Configuracao;
import br.com.VeiculosPajeu.Entidade.Endereco;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Filial;
import br.com.VeiculosPajeu.Entidade.Financeiro;
import br.com.VeiculosPajeu.Entidade.Fisica;
import br.com.VeiculosPajeu.Entidade.Funcionario;
import br.com.VeiculosPajeu.Entidade.Juridica;
import br.com.VeiculosPajeu.Entidade.Locacao;
import br.com.VeiculosPajeu.Entidade.Log;
import br.com.VeiculosPajeu.Entidade.Passageiro;
import br.com.VeiculosPajeu.Entidade.Reserva;
import br.com.VeiculosPajeu.Entidade.SuperUsuario;
import br.com.VeiculosPajeu.Entidade.Usuario;
import br.com.VeiculosPajeu.Entidade.Veiculo;
import br.com.VeiculosPajeu.Entidade.Enum.EstadoFinanceiro;
import br.com.VeiculosPajeu.Entidade.Enum.TipoHistorico;
import br.com.VeiculosPajeu.Entidade.View.LocacaoView;
import br.com.VeiculosPajeu.Entidade.View.ReservaView;
import br.com.VeiculosPajeu.Entidade.View.VeiculoView;
import br.com.VeiculosPajeu.Exception.BusinessException;
import br.com.VeiculosPajeu.Util.LoadBusiness;

public class Fachada implements IFachada {

	private static Fachada instance;

	private LoadBusiness loadBusiness;

	private Fachada() {
		loadBusiness = LoadBusiness.getInstance();
	}

	public static synchronized Fachada getInstance() {
		if (instance == null)
			instance = new Fachada();
		return instance;
	}

	@Override
	public void saveAutomovel(Automovel entidade) throws BusinessException {
		loadBusiness.loadAutomovel().save(entidade);
	}

	@Override
	public Automovel searchAutomovel(int id) throws BusinessException {
		return loadBusiness.loadAutomovel().search(id);
	}

	@Override
	public void removeAutomovel(int id) throws BusinessException {
		loadBusiness.loadAutomovel().remove(id);
	}

	@Override
	public List<Automovel> searchAllAutomovel() throws BusinessException {
		return loadBusiness.loadAutomovel().searchAll();
	}

	@Override
	public List<Automovel> searchAllAutomovel(String search) throws BusinessException {
		return loadBusiness.loadAutomovel().searchAll(search);
	}

	@Override
	public void saveCarga(Carga entidade) throws BusinessException {
		loadBusiness.loadCarga().save(entidade);
	}

	@Override
	public Carga searchCarga(int id) throws BusinessException {
		return loadBusiness.loadCarga().search(id);
	}

	@Override
	public void removeCarga(int id) throws BusinessException {
		loadBusiness.loadCarga().remove(id);
	}

	@Override
	public List<Carga> searchAllCarga() throws BusinessException {
		return loadBusiness.loadCarga().searchAll();
	}

	@Override
	public List<Carga> searchAllCarga(String search) throws BusinessException {
		return loadBusiness.loadCarga().searchAll(search);
	}

	@Override
	public void saveCategoria(Categoria entidade) throws BusinessException {
		loadBusiness.loadCategoria().save(entidade);
	}

	@Override
	public Categoria searchCategoria(int id) throws BusinessException {
		return loadBusiness.loadCategoria().search(id);
	}

	@Override
	public void removeCategoria(int id) throws BusinessException {
		loadBusiness.loadCategoria().remove(id);
	}

	@Override
	public List<Categoria> searchAllCategoria() throws BusinessException {
		return loadBusiness.loadCategoria().searchAll();
	}

	@Override
	public List<Categoria> searchAllCategoria(String search) throws BusinessException {
		return loadBusiness.loadCategoria().searchAll(search);
	}

	@Override
	public Categoria nextCategoria(Categoria categoria) throws BusinessException {
		return loadBusiness.loadCategoria().nextCategoria(categoria);
	}

	@Override
	public void saveConfiguracao(Configuracao entidade) throws BusinessException {
		loadBusiness.loadConfiguracao().save(entidade);
	}

	@Override
	public Configuracao searchConfiguracao(int id) throws BusinessException {
		return loadBusiness.loadConfiguracao().search(id);
	}

	@Override
	public void removeConfiguracao(int id) throws BusinessException {
		loadBusiness.loadConfiguracao().remove(id);
	}

	@Override
	public List<Configuracao> searchAllConfiguracao() throws BusinessException {
		return loadBusiness.loadConfiguracao().searchAll();
	}

	@Override
	public List<Configuracao> searchAllConfiguracao(String search) throws BusinessException {
		return loadBusiness.loadConfiguracao().searchAll(search);
	}

	@Override
	public void saveEndereco(Endereco entidade) throws BusinessException {
		loadBusiness.loadEndereco().save(entidade);
	}

	@Override
	public Endereco searchEndereco(int id) throws BusinessException {
		return loadBusiness.loadEndereco().search(id);
	}

	@Override
	public void removeEndereco(int id) throws BusinessException {
		loadBusiness.loadEndereco().remove(id);
	}

	@Override
	public List<Endereco> searchAllEndereco() throws BusinessException {
		return loadBusiness.loadEndereco().searchAll();
	}

	@Override
	public List<Endereco> searchAllEndereco(String search) throws BusinessException {
		return loadBusiness.loadEndereco().searchAll(search);
	}

	@Override
	public void saveFilial(Filial entidade) throws BusinessException {
		loadBusiness.loadFilial().save(entidade);
	}

	@Override
	public Filial searchFilial(int id) throws BusinessException {
		return loadBusiness.loadFilial().search(id);
	}

	@Override
	public void removeFilial(int id) throws BusinessException {
		loadBusiness.loadFilial().remove(id);
	}

	@Override
	public List<Filial> searchAllFilial() throws BusinessException {
		return loadBusiness.loadFilial().searchAll();
	}

	@Override
	public List<Filial> searchAllFilial(String search) throws BusinessException {
		return loadBusiness.loadFilial().searchAll(search);
	}

	@Override
	public void saveFisica(Fisica entidade) throws BusinessException {
		loadBusiness.loadFisica().save(entidade);
	}

	@Override
	public Fisica searchFisica(int id) throws BusinessException {
		return loadBusiness.loadFisica().search(id);
	}

	@Override
	public void removeFisica(int id) throws BusinessException {
		loadBusiness.loadFisica().remove(id);
	}

	@Override
	public List<Fisica> searchAllFisica() throws BusinessException {
		return loadBusiness.loadFisica().searchAll();
	}

	@Override
	public List<Fisica> searchAllFisica(String search) throws BusinessException {
		return loadBusiness.loadFisica().searchAll(search);
	}

	@Override
	public void saveFuncionario(Funcionario entidade) throws BusinessException {
		loadBusiness.loadFuncionario().save(entidade);
	}

	@Override
	public Funcionario searchFuncionario(int id) throws BusinessException {
		return loadBusiness.loadFuncionario().search(id);
	}

	@Override
	public void removeFuncionario(int id) throws BusinessException {
		loadBusiness.loadFuncionario().remove(id);
	}

	@Override
	public List<Funcionario> searchAllFuncionario() throws BusinessException {
		return loadBusiness.loadFuncionario().searchAll();
	}

	@Override
	public List<Funcionario> searchAllFuncionario(String search) throws BusinessException {
		return loadBusiness.loadFuncionario().searchAll(search);
	}

	@Override
	public void saveJuridica(Juridica entidade) throws BusinessException {
		loadBusiness.loadJuridica().save(entidade);
	}

	@Override
	public Juridica searchJuridica(int id) throws BusinessException {
		return loadBusiness.loadJuridica().search(id);
	}

	@Override
	public void removeJuridica(int id) throws BusinessException {
		loadBusiness.loadJuridica().remove(id);
	}

	@Override
	public List<Juridica> searchAllJuridica() throws BusinessException {
		return loadBusiness.loadJuridica().searchAll();
	}

	@Override
	public List<Juridica> searchAllJuridica(String search) throws BusinessException {
		return loadBusiness.loadJuridica().searchAll(search);
	}

	@Override
	public void saveLocacao(Locacao entidade) throws BusinessException {
		loadBusiness.loadLocacao().save(entidade);
	}

	@Override
	public Locacao searchLocacao(int id) throws BusinessException {
		return loadBusiness.loadLocacao().search(id);
	}

	@Override
	public void removeLocacao(int id) throws BusinessException {
		loadBusiness.loadLocacao().remove(id);
	}

	@Override
	public List<Locacao> searchAllLocacao() throws BusinessException {
		return loadBusiness.loadLocacao().searchAll();
	}

	@Override
	public List<Locacao> searchAllLocacao(String search) throws BusinessException {
		return loadBusiness.loadLocacao().searchAll(search);
	}

	@Override
	public List<Locacao> searchAllLocacaoAtivo(String search) throws BusinessException {
		return loadBusiness.loadLocacao().searchAllAtivo(search);
	}

	@Override
	public List<LocacaoView> searchAllLocacaoVencidos() throws BusinessException {
		return loadBusiness.loadLocacao().searchAllVencidos();
	}

	@Override
	public List<Locacao> searchAllLocacaoPeriodo(LocalDate inicio, LocalDate fim) throws BusinessException {
		return loadBusiness.loadLocacao().searchAllPeriodo(inicio, fim);
	}

	@Override
	public List<Locacao> searchAllLocacaoPorCliente(Cliente cliente) throws BusinessException {
		return loadBusiness.loadLocacao().searchAllPorCliente(cliente);
	}

	@Override
	public void savePassageiro(Passageiro entidade) throws BusinessException {
		loadBusiness.loadPassageiro().save(entidade);
	}

	@Override
	public Passageiro searchPassageiro(int id) throws BusinessException {
		return loadBusiness.loadPassageiro().search(id);
	}

	@Override
	public void removePassageiro(int id) throws BusinessException {
		loadBusiness.loadPassageiro().remove(id);
	}

	@Override
	public List<Passageiro> searchAllPassageiro() throws BusinessException {
		return loadBusiness.loadPassageiro().searchAll();
	}

	@Override
	public List<Passageiro> searchAllPassageiro(String search) throws BusinessException {
		return loadBusiness.loadPassageiro().searchAll(search);
	}

	@Override
	public void saveReserva(Reserva entidade) throws BusinessException {
		loadBusiness.loadReserva().save(entidade);
	}

	@Override
	public Reserva searchReserva(int id) throws BusinessException {
		return loadBusiness.loadReserva().search(id);
	}

	@Override
	public void removeReserva(int id) throws BusinessException {
		loadBusiness.loadReserva().remove(id);
	}

	@Override
	public List<Reserva> searchAllReserva() throws BusinessException {
		return loadBusiness.loadReserva().searchAll();
	}

	@Override
	public List<Reserva> searchAllReserva(String search) throws BusinessException {
		return loadBusiness.loadReserva().searchAll(search);
	}

	@Override
	public List<Reserva> searchAllReservaAtivo(String search) throws BusinessException {
		return loadBusiness.loadReserva().searchAllAtivo(search);
	}

	@Override
	public List<ReservaView> searchAllReservaVencidos() throws BusinessException {
		return loadBusiness.loadReserva().searchAllVencidos();
	}

	@Override
	public List<Reserva> searchAllReservaPeriodo(LocalDate inicio, LocalDate fim) throws BusinessException {
		return loadBusiness.loadReserva().searchAllPeriodo(inicio, fim);
	}

	@Override
	public void saveSuperUsuario(SuperUsuario entidade) throws BusinessException {
		loadBusiness.loadSuperUsuario().save(entidade);
	}

	@Override
	public SuperUsuario searchSuperUsuario(int id) throws BusinessException {
		return loadBusiness.loadSuperUsuario().search(id);
	}

	@Override
	public void removeSuperUsuario(int id) throws BusinessException {
		loadBusiness.loadSuperUsuario().remove(id);
	}

	@Override
	public List<SuperUsuario> searchAllSuperUsuario() throws BusinessException {
		return loadBusiness.loadSuperUsuario().searchAll();
	}

	@Override
	public List<SuperUsuario> searchAllSuperUsuario(String search) throws BusinessException {
		return loadBusiness.loadSuperUsuario().searchAll(search);
	}

	@Override
	public Usuario searchUser(String login, String senha) throws BusinessException {
		return loadBusiness.loadUsuario().searchUser(login, senha);
	}

	@Override
	public List<Cliente> searchAllCliente() throws BusinessException {
		return loadBusiness.loadCliente().searchAll();
	}

	@Override
	public List<Cliente> searchAllCliente(String search) throws BusinessException {
		return loadBusiness.loadCliente().searchAll(search);
	}

	@Override
	public List<Veiculo> searchVeiculoDisponivel(String search, boolean reservado, boolean alugado)
			throws BusinessException {
		return loadBusiness.loadVeiculo().searchDisponivel(search, reservado, alugado);
	}

	@Override
	public List<Veiculo> searchAllVeiculoPorCategoria(Categoria categoria) throws BusinessException {
		return loadBusiness.loadVeiculo().searchAllPorCategoria(categoria);
	}

	public List<VeiculoView> searchAllManutencao() throws BusinessException {
		return loadBusiness.loadVeiculo().searchAllManutencao();
	}

	@Override
	public List<Log> searchLog(LocalDate date, TipoHistorico tipoHistorico) throws BusinessException {
		return loadBusiness.loadUtil().searchLog(date, tipoHistorico);
	}

	@Override
	public Long searchContSelect(Class<? extends Entidade> classe, String sql) throws BusinessException {
		return loadBusiness.loadUtil().searchContSelect(classe, sql);
	}

	@Override
	public void saveFinanceiro(Financeiro entidade) throws BusinessException {
		loadBusiness.loadFinanceiro().save(entidade);
	}

	@Override
	public Financeiro searchFinanceiro(int id) throws BusinessException {
		return loadBusiness.loadFinanceiro().search(id);
	}

	@Override
	public void remove(int id) throws BusinessException {
		loadBusiness.loadFinanceiro().remove(id);
	}

	@Override
	public List<Financeiro> searchAllFinanceiro() throws BusinessException {
		return loadBusiness.loadFinanceiro().searchAll();
	}

	@Override
	public List<Financeiro> searchAllFinanceiro(String search) throws BusinessException {
		return loadBusiness.loadFinanceiro().searchAll(search);
	}

	@Override
	public List<Financeiro> searchAllFinanceiroEstado(LocalDate de, LocalDate ate, EstadoFinanceiro estado)
			throws BusinessException {
		return loadBusiness.loadFinanceiro().searchAllEstado(de, ate, estado);
	}

	@Override
	public List<Veiculo> searchAllVeiculoDataFurtura(Filial filial, LocalDate date) throws BusinessException {
		return loadBusiness.loadVeiculo().searchAllDataFurtura(filial, date);
	}

	@Override
	public List<Veiculo> searchAllVeiculoDiponivel(Filial filial) throws BusinessException {
		return loadBusiness.loadVeiculo().searchAllDiponivel(filial);
	}

	@Override
	public Long searchCont(Class<? extends Entidade> classe) throws BusinessException {
		return loadBusiness.loadUtil().searchCont(classe);
	}

	@Override
	public BigInteger verificarAtrasadosFinanceiro() throws BusinessException {
		return loadBusiness.loadFinanceiro().verificarAtrasados();
	}

	@Override
	public BigInteger verificarAtrasadosReserva() throws BusinessException {
		return loadBusiness.loadReserva().verificarAtrasados();
	}

	@Override
	public BigInteger verificarManutencaoVeiculo() throws BusinessException {
		return loadBusiness.loadVeiculo().verificarManutencao();
	}
}