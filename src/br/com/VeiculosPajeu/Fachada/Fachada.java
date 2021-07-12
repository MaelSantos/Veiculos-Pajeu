package br.com.VeiculosPajeu.Fachada;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import br.com.VeiculosPajeu.Business.BusinessAutomovel;
import br.com.VeiculosPajeu.Business.BusinessCarga;
import br.com.VeiculosPajeu.Business.BusinessCategoria;
import br.com.VeiculosPajeu.Business.BusinessCliente;
import br.com.VeiculosPajeu.Business.BusinessConfiguracao;
import br.com.VeiculosPajeu.Business.BusinessEndereco;
import br.com.VeiculosPajeu.Business.BusinessFilial;
import br.com.VeiculosPajeu.Business.BusinessFinanceiro;
import br.com.VeiculosPajeu.Business.BusinessFisica;
import br.com.VeiculosPajeu.Business.BusinessFuncionario;
import br.com.VeiculosPajeu.Business.BusinessJuridica;
import br.com.VeiculosPajeu.Business.BusinessLocacao;
import br.com.VeiculosPajeu.Business.BusinessPassageiro;
import br.com.VeiculosPajeu.Business.BusinessReserva;
import br.com.VeiculosPajeu.Business.BusinessSuperUsuario;
import br.com.VeiculosPajeu.Business.BusinessUsuario;
import br.com.VeiculosPajeu.Business.BusinessUtil;
import br.com.VeiculosPajeu.Business.BusinessVeiculo;
import br.com.VeiculosPajeu.Business.Interface.IBusinessAutomovel;
import br.com.VeiculosPajeu.Business.Interface.IBusinessCarga;
import br.com.VeiculosPajeu.Business.Interface.IBusinessCategoria;
import br.com.VeiculosPajeu.Business.Interface.IBusinessCliente;
import br.com.VeiculosPajeu.Business.Interface.IBusinessConfiguracao;
import br.com.VeiculosPajeu.Business.Interface.IBusinessEndereco;
import br.com.VeiculosPajeu.Business.Interface.IBusinessFilial;
import br.com.VeiculosPajeu.Business.Interface.IBusinessFinanceiro;
import br.com.VeiculosPajeu.Business.Interface.IBusinessFisica;
import br.com.VeiculosPajeu.Business.Interface.IBusinessFuncionario;
import br.com.VeiculosPajeu.Business.Interface.IBusinessJuridica;
import br.com.VeiculosPajeu.Business.Interface.IBusinessLocacao;
import br.com.VeiculosPajeu.Business.Interface.IBusinessPassageiro;
import br.com.VeiculosPajeu.Business.Interface.IBusinessReserva;
import br.com.VeiculosPajeu.Business.Interface.IBusinessSuperUsuario;
import br.com.VeiculosPajeu.Business.Interface.IBusinessUsuario;
import br.com.VeiculosPajeu.Business.Interface.IBusinessUtil;
import br.com.VeiculosPajeu.Business.Interface.IBusinessVeiculo;
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

public class Fachada implements IFachada {

	private IBusinessAutomovel businessAutomovel;
	private IBusinessCarga businessCarga;
	private IBusinessCategoria businessCategoria;
	private IBusinessConfiguracao businessConfiguracao;
	private IBusinessEndereco businessEndereco;
	private IBusinessFilial businessFilial;
	private IBusinessFisica businessFisica;
	private IBusinessFuncionario businessFuncionario;
	private IBusinessJuridica businessJuridica;
	private IBusinessLocacao businessLocacao;
	private IBusinessPassageiro businessPassageiro;
	private IBusinessReserva businessReserva;
	private IBusinessSuperUsuario businessSuperUsuario;
	private IBusinessFinanceiro businessFinanceiro;

	private IBusinessUsuario businessUsuario;
	private IBusinessVeiculo businessVeiculo;
	private IBusinessCliente businessCliente;

	private IBusinessUtil businessUtil;

	private static Fachada instance;

	private Fachada() {
	}

	public static synchronized Fachada getInstance() {
		if (instance == null)
			instance = new Fachada();
		return instance;
	}

	private void loadAutomovel() {
		if (businessAutomovel == null)
			businessAutomovel = new BusinessAutomovel();
	}

	private void loadCarga() {
		if (businessCarga == null)
			businessCarga = new BusinessCarga();
	}

	private void loadCategoria() {
		if (businessCategoria == null)
			businessCategoria = new BusinessCategoria();
	}

	private void loadConfiguracao() {
		if (businessConfiguracao == null)
			businessConfiguracao = new BusinessConfiguracao();
	}

	private void loadEndereco() {
		if (businessEndereco == null)
			businessEndereco = new BusinessEndereco();
	}

	private void loadFilial() {
		if (businessFilial == null)
			businessFilial = new BusinessFilial();
	}

	private void loadFisica() {
		if (businessFisica == null)
			businessFisica = new BusinessFisica();
	}

	private void loadFuncionario() {
		if (businessFuncionario == null)
			businessFuncionario = new BusinessFuncionario();
	}

	private void loadJuridica() {
		if (businessJuridica == null)
			businessJuridica = new BusinessJuridica();
	}

	private void loadLocacao() {
		if (businessLocacao == null)
			businessLocacao = new BusinessLocacao();
	}

	private void loadPassageiro() {
		if (businessPassageiro == null)
			businessPassageiro = new BusinessPassageiro();
	}

	private void loadReserva() {
		if (businessReserva == null)
			businessReserva = new BusinessReserva();
	}

	private void loadSuperUsuario() {
		if (businessSuperUsuario == null)
			businessSuperUsuario = new BusinessSuperUsuario();
	}

	private void loadFinanceiro() {
		if (businessFinanceiro == null)
			businessFinanceiro = new BusinessFinanceiro();
	}

	private void loadUsuario() {
		if (businessUsuario == null)
			businessUsuario = new BusinessUsuario();
	}

	private void loadVeiculo() {
		if (businessVeiculo == null)
			businessVeiculo = new BusinessVeiculo();
	}

	private void loadCliente() {
		if (businessCliente == null)
			businessCliente = new BusinessCliente();
	}

	private void loadUtil() {
		if (businessUtil == null)
			businessUtil = new BusinessUtil();
	}

	@Override
	public void saveAutomovel(Automovel entidade) throws BusinessException {
		loadAutomovel();
		businessAutomovel.save(entidade);
	}

	@Override
	public Automovel searchAutomovel(int id) throws BusinessException {
		loadAutomovel();
		return businessAutomovel.search(id);
	}

	@Override
	public void removeAutomovel(int id) throws BusinessException {
		loadAutomovel();
		businessAutomovel.remove(id);
	}

	@Override
	public List<Automovel> searchAllAutomovel() throws BusinessException {
		loadAutomovel();
		return businessAutomovel.searchAll();
	}

	@Override
	public List<Automovel> searchAllAutomovel(String search) throws BusinessException {
		loadAutomovel();
		return businessAutomovel.searchAll(search);
	}

	@Override
	public void saveCarga(Carga entidade) throws BusinessException {
		loadCarga();
		businessCarga.save(entidade);
	}

	@Override
	public Carga searchCarga(int id) throws BusinessException {
		loadCarga();
		return businessCarga.search(id);
	}

	@Override
	public void removeCarga(int id) throws BusinessException {
		loadCarga();
		businessCarga.remove(id);
	}

	@Override
	public List<Carga> searchAllCarga() throws BusinessException {
		loadCarga();
		return businessCarga.searchAll();
	}

	@Override
	public List<Carga> searchAllCarga(String search) throws BusinessException {
		loadCarga();
		return businessCarga.searchAll(search);
	}

	@Override
	public void saveCategoria(Categoria entidade) throws BusinessException {
		loadCategoria();
		businessCategoria.save(entidade);
	}

	@Override
	public Categoria searchCategoria(int id) throws BusinessException {
		loadCategoria();
		return businessCategoria.search(id);
	}

	@Override
	public void removeCategoria(int id) throws BusinessException {
		loadCategoria();
		businessCategoria.remove(id);
	}

	@Override
	public List<Categoria> searchAllCategoria() throws BusinessException {
		loadCategoria();
		return businessCategoria.searchAll();
	}

	@Override
	public List<Categoria> searchAllCategoria(String search) throws BusinessException {
		loadCategoria();
		return businessCategoria.searchAll(search);
	}

	@Override
	public Categoria nextCategoria(Categoria categoria) throws BusinessException {
		loadCategoria();
		return businessCategoria.nextCategoria(categoria);
	}

	@Override
	public void saveConfiguracao(Configuracao entidade) throws BusinessException {
		loadConfiguracao();
		businessConfiguracao.save(entidade);
	}

	@Override
	public Configuracao searchConfiguracao(int id) throws BusinessException {
		loadConfiguracao();
		return businessConfiguracao.search(id);
	}

	@Override
	public void removeConfiguracao(int id) throws BusinessException {
		loadConfiguracao();
		businessConfiguracao.remove(id);
	}

	@Override
	public List<Configuracao> searchAllConfiguracao() throws BusinessException {
		loadConfiguracao();
		return businessConfiguracao.searchAll();
	}

	@Override
	public List<Configuracao> searchAllConfiguracao(String search) throws BusinessException {
		loadConfiguracao();
		return businessConfiguracao.searchAll(search);
	}

	@Override
	public void saveEndereco(Endereco entidade) throws BusinessException {
		loadEndereco();
		businessEndereco.save(entidade);
	}

	@Override
	public Endereco searchEndereco(int id) throws BusinessException {
		loadEndereco();
		return businessEndereco.search(id);
	}

	@Override
	public void removeEndereco(int id) throws BusinessException {
		loadEndereco();
		businessEndereco.remove(id);
	}

	@Override
	public List<Endereco> searchAllEndereco() throws BusinessException {
		loadEndereco();
		return businessEndereco.searchAll();
	}

	@Override
	public List<Endereco> searchAllEndereco(String search) throws BusinessException {
		loadEndereco();
		return businessEndereco.searchAll(search);
	}

	@Override
	public void saveFilial(Filial entidade) throws BusinessException {
		loadFilial();
		businessFilial.save(entidade);
	}

	@Override
	public Filial searchFilial(int id) throws BusinessException {
		loadFilial();
		return businessFilial.search(id);
	}

	@Override
	public void removeFilial(int id) throws BusinessException {
		loadFilial();
		businessFilial.remove(id);
	}

	@Override
	public List<Filial> searchAllFilial() throws BusinessException {
		loadFilial();
		return businessFilial.searchAll();
	}

	@Override
	public List<Filial> searchAllFilial(String search) throws BusinessException {
		loadFilial();
		return businessFilial.searchAll(search);
	}

	@Override
	public void saveFisica(Fisica entidade) throws BusinessException {
		loadFisica();
		businessFisica.save(entidade);
	}

	@Override
	public Fisica searchFisica(int id) throws BusinessException {
		loadFisica();
		return businessFisica.search(id);
	}

	@Override
	public void removeFisica(int id) throws BusinessException {
		loadFisica();
		businessFisica.remove(id);
	}

	@Override
	public List<Fisica> searchAllFisica() throws BusinessException {
		loadFisica();
		return businessFisica.searchAll();
	}

	@Override
	public List<Fisica> searchAllFisica(String search) throws BusinessException {
		loadFisica();
		return businessFisica.searchAll(search);
	}

	@Override
	public void saveFuncionario(Funcionario entidade) throws BusinessException {
		loadFuncionario();
		businessFuncionario.save(entidade);
	}

	@Override
	public Funcionario searchFuncionario(int id) throws BusinessException {
		loadFuncionario();
		return businessFuncionario.search(id);
	}

	@Override
	public void removeFuncionario(int id) throws BusinessException {
		loadFuncionario();
		businessFuncionario.remove(id);
	}

	@Override
	public List<Funcionario> searchAllFuncionario() throws BusinessException {
		loadFuncionario();
		return businessFuncionario.searchAll();
	}

	@Override
	public List<Funcionario> searchAllFuncionario(String search) throws BusinessException {
		loadFuncionario();
		return businessFuncionario.searchAll(search);
	}

	@Override
	public void saveJuridica(Juridica entidade) throws BusinessException {
		loadJuridica();
		businessJuridica.save(entidade);
	}

	@Override
	public Juridica searchJuridica(int id) throws BusinessException {
		loadJuridica();
		return businessJuridica.search(id);
	}

	@Override
	public void removeJuridica(int id) throws BusinessException {
		loadJuridica();
		businessJuridica.remove(id);
	}

	@Override
	public List<Juridica> searchAllJuridica() throws BusinessException {
		loadJuridica();
		return businessJuridica.searchAll();
	}

	@Override
	public List<Juridica> searchAllJuridica(String search) throws BusinessException {
		loadJuridica();
		return businessJuridica.searchAll(search);
	}

	@Override
	public void saveLocacao(Locacao entidade) throws BusinessException {
		loadLocacao();
		businessLocacao.save(entidade);
	}

	@Override
	public Locacao searchLocacao(int id) throws BusinessException {
		loadLocacao();
		return businessLocacao.search(id);
	}

	@Override
	public void removeLocacao(int id) throws BusinessException {
		loadLocacao();
		businessLocacao.remove(id);
	}

	@Override
	public List<Locacao> searchAllLocacao() throws BusinessException {
		loadLocacao();
		return businessLocacao.searchAll();
	}

	@Override
	public List<Locacao> searchAllLocacao(String search) throws BusinessException {
		loadLocacao();
		return businessLocacao.searchAll(search);
	}

	@Override
	public List<Locacao> searchAllLocacaoAtivo(String search) throws BusinessException {
		loadLocacao();
		return businessLocacao.searchAllAtivo(search);
	}

	@Override
	public List<LocacaoView> searchAllLocacaoVencidos() throws BusinessException {
		loadLocacao();
		return businessLocacao.searchAllVencidos();
	}

	@Override
	public List<Locacao> searchAllLocacaoPeriodo(LocalDate inicio, LocalDate fim) throws BusinessException {
		loadLocacao();
		return businessLocacao.searchAllPeriodo(inicio, fim);
	}

	@Override
	public List<Locacao> searchAllLocacaoPorCliente(Cliente cliente) throws BusinessException {
		loadLocacao();
		return businessLocacao.searchAllPorCliente(cliente);
	}

	@Override
	public void savePassageiro(Passageiro entidade) throws BusinessException {
		loadPassageiro();
		businessPassageiro.save(entidade);
	}

	@Override
	public Passageiro searchPassageiro(int id) throws BusinessException {
		loadPassageiro();
		return businessPassageiro.search(id);
	}

	@Override
	public void removePassageiro(int id) throws BusinessException {
		loadPassageiro();
		businessPassageiro.remove(id);
	}

	@Override
	public List<Passageiro> searchAllPassageiro() throws BusinessException {
		loadPassageiro();
		return businessPassageiro.searchAll();
	}

	@Override
	public List<Passageiro> searchAllPassageiro(String search) throws BusinessException {
		loadPassageiro();
		return businessPassageiro.searchAll(search);
	}

	@Override
	public void saveReserva(Reserva entidade) throws BusinessException {
		loadReserva();
		businessReserva.save(entidade);
	}

	@Override
	public Reserva searchReserva(int id) throws BusinessException {
		loadReserva();
		return businessReserva.search(id);
	}

	@Override
	public void removeReserva(int id) throws BusinessException {
		loadReserva();
		businessReserva.remove(id);
	}

	@Override
	public List<Reserva> searchAllReserva() throws BusinessException {
		loadReserva();
		return businessReserva.searchAll();
	}

	@Override
	public List<Reserva> searchAllReserva(String search) throws BusinessException {
		loadReserva();
		return businessReserva.searchAll(search);
	}

	@Override
	public List<Reserva> searchAllReservaAtivo(String search) throws BusinessException {
		loadReserva();
		return businessReserva.searchAllAtivo(search);
	}

	@Override
	public List<ReservaView> searchAllReservaVencidos() throws BusinessException {
		loadReserva();
		return businessReserva.searchAllVencidos();
	}

	@Override
	public List<Reserva> searchAllReservaPeriodo(LocalDate inicio, LocalDate fim) throws BusinessException {
		loadReserva();
		return businessReserva.searchAllPeriodo(inicio, fim);
	}

	@Override
	public void saveSuperUsuario(SuperUsuario entidade) throws BusinessException {
		loadSuperUsuario();
		businessSuperUsuario.save(entidade);
	}

	@Override
	public SuperUsuario searchSuperUsuario(int id) throws BusinessException {
		loadSuperUsuario();
		return businessSuperUsuario.search(id);
	}

	@Override
	public void removeSuperUsuario(int id) throws BusinessException {
		loadSuperUsuario();
		businessSuperUsuario.remove(id);
	}

	@Override
	public List<SuperUsuario> searchAllSuperUsuario() throws BusinessException {
		loadSuperUsuario();
		return businessSuperUsuario.searchAll();
	}

	@Override
	public List<SuperUsuario> searchAllSuperUsuario(String search) throws BusinessException {
		loadSuperUsuario();
		return businessSuperUsuario.searchAll(search);
	}

	@Override
	public Usuario searchUser(String login, String senha) throws BusinessException {
		loadUsuario();
		return businessUsuario.searchUser(login, senha);
	}

	@Override
	public List<Cliente> searchAllCliente() throws BusinessException {
		loadCliente();
		return businessCliente.searchAll();
	}

	@Override
	public List<Cliente> searchAllCliente(String search) throws BusinessException {
		loadCliente();
		return businessCliente.searchAll(search);
	}

	@Override
	public List<Veiculo> searchVeiculoDisponivel(String search, boolean reservado, boolean alugado)
			throws BusinessException {
		loadVeiculo();
		return businessVeiculo.searchDisponivel(search, reservado, alugado);
	}

	@Override
	public List<Veiculo> searchAllVeiculoPorCategoria(Categoria categoria) throws BusinessException {
		loadVeiculo();
		return businessVeiculo.searchAllPorCategoria(categoria);
	}

	public List<VeiculoView> searchAllManutencao() throws BusinessException {
		loadVeiculo();
		return businessVeiculo.searchAllManutencao();
	}

	@Override
	public List<Log> searchLog(LocalDate date, TipoHistorico tipoHistorico) throws BusinessException {
		loadUtil();
		return businessUtil.searchLog(date, tipoHistorico);
	}

	@Override
	public Long searchContSelect(Class<? extends Entidade> classe, String sql) throws BusinessException {
		loadUtil();
		return businessUtil.searchContSelect(classe, sql);
	}

	@Override
	public void saveFinanceiro(Financeiro entidade) throws BusinessException {
		loadFinanceiro();
		businessFinanceiro.save(entidade);
	}

	@Override
	public Financeiro searchFinanceiro(int id) throws BusinessException {
		loadFinanceiro();
		return businessFinanceiro.search(id);
	}

	@Override
	public void remove(int id) throws BusinessException {
		loadFinanceiro();
		businessFinanceiro.remove(id);
	}

	@Override
	public List<Financeiro> searchAllFinanceiro() throws BusinessException {
		loadFinanceiro();
		return businessFinanceiro.searchAll();
	}

	@Override
	public List<Financeiro> searchAllFinanceiro(String search) throws BusinessException {
		loadFinanceiro();
		return businessFinanceiro.searchAll(search);
	}

	@Override
	public List<Financeiro> searchAllFinanceiroEstado(LocalDate de, LocalDate ate, EstadoFinanceiro estado)
			throws BusinessException {
		loadFinanceiro();
		return businessFinanceiro.searchAllEstado(de, ate, estado);
	}

	@Override
	public List<Veiculo> searchAllVeiculoDataFurtura(Filial filial, LocalDate date) throws BusinessException {
		loadVeiculo();
		return businessVeiculo.searchAllDataFurtura(filial, date);
	}

	@Override
	public List<Veiculo> searchAllVeiculoDiponivel(Filial filial) throws BusinessException {
		loadVeiculo();
		return businessVeiculo.searchAllDiponivel(filial);
	}

	@Override
	public Long searchCont(Class<? extends Entidade> classe) throws BusinessException {
		loadUtil();
		return businessUtil.searchCont(classe);
	}

	@Override
	public BigInteger verificarAtrasadosFinanceiro() throws BusinessException {
		loadFinanceiro();
		return businessFinanceiro.verificarAtrasados();
	}

	@Override
	public BigInteger verificarAtrasadosReserva() throws BusinessException {
		loadReserva();
		return businessReserva.verificarAtrasados();
	}

	@Override
	public BigInteger verificarManutencaoVeiculo() throws BusinessException {
		loadVeiculo();
		return businessVeiculo.verificarManutencao();
	}
}