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

		businessAutomovel = new BusinessAutomovel();
		businessCarga = new BusinessCarga();
		businessCategoria = new BusinessCategoria();
		businessConfiguracao = new BusinessConfiguracao();
		businessEndereco = new BusinessEndereco();
		businessFilial = new BusinessFilial();
		businessFisica = new BusinessFisica();
		businessFuncionario = new BusinessFuncionario();
		businessJuridica = new BusinessJuridica();
		businessLocacao = new BusinessLocacao();
		businessPassageiro = new BusinessPassageiro();
		businessReserva = new BusinessReserva();
		businessSuperUsuario = new BusinessSuperUsuario();
		businessFinanceiro = new BusinessFinanceiro();
		
		businessUsuario = new BusinessUsuario();
		businessVeiculo = new BusinessVeiculo();
		businessCliente = new BusinessCliente();

		businessUtil = new BusinessUtil();
	}

	public static Fachada getInstance() {
		if (instance == null)
			instance = new Fachada();
		return instance;
	}

	@Override
	public void createOrUpdateAutomovel(Automovel entidade) throws BusinessException {
		businessAutomovel.createOrUpdate(entidade);
	}

	@Override
	public Automovel searchAutomovel(int id) throws BusinessException {
		return businessAutomovel.search(id);
	}

	@Override
	public void removeAutomovel(int id) throws BusinessException {
		businessAutomovel.remove(id);

	}

	@Override
	public List<Automovel> searchAllAutomovel() throws BusinessException {
		return businessAutomovel.searchAll();
	}

	@Override
	public List<Automovel> searchAllAutomovel(String search) throws BusinessException {
		return businessAutomovel.searchAll(search);
	}

	@Override
	public void createOrUpdateCarga(Carga entidade) throws BusinessException {
		businessCarga.createOrUpdate(entidade);
	}

	@Override
	public Carga searchCarga(int id) throws BusinessException {
		return businessCarga.search(id);
	}

	@Override
	public void removeCarga(int id) throws BusinessException {
		businessCarga.remove(id);
	}

	@Override
	public List<Carga> searchAllCarga() throws BusinessException {
		return businessCarga.searchAll();
	}

	@Override
	public List<Carga> searchAllCarga(String search) throws BusinessException {
		return businessCarga.searchAll(search);
	}

	@Override
	public void createOrUpdateCategoria(Categoria entidade) throws BusinessException {
		businessCategoria.createOrUpdate(entidade);
	}

	@Override
	public Categoria searchCategoria(int id) throws BusinessException {
		return businessCategoria.search(id);
	}

	@Override
	public void removeCategoria(int id) throws BusinessException {
		businessCategoria.remove(id);
	}

	@Override
	public List<Categoria> searchAllCategoria() throws BusinessException {
		return businessCategoria.searchAll();
	}

	@Override
	public List<Categoria> searchAllCategoria(String search) throws BusinessException {
		return businessCategoria.searchAll(search);
	}

	@Override
	public Categoria nextCategoria(Categoria categoria) throws BusinessException {
		return businessCategoria.nextCategoria(categoria);
	}

	@Override
	public void createOrUpdateConfiguracao(Configuracao entidade) throws BusinessException {
		businessConfiguracao.createOrUpdate(entidade);
	}

	@Override
	public Configuracao searchConfiguracao(int id) throws BusinessException {
		return businessConfiguracao.search(id);
	}

	@Override
	public void removeConfiguracao(int id) throws BusinessException {
		businessConfiguracao.remove(id);
	}

	@Override
	public List<Configuracao> searchAllConfiguracao() throws BusinessException {
		return businessConfiguracao.searchAll();
	}

	@Override
	public List<Configuracao> searchAllConfiguracao(String search) throws BusinessException {
		return businessConfiguracao.searchAll(search);
	}

	@Override
	public void createOrUpdateEndereco(Endereco entidade) throws BusinessException {
		businessEndereco.createOrUpdate(entidade);
	}

	@Override
	public Endereco searchEndereco(int id) throws BusinessException {
		return businessEndereco.search(id);
	}

	@Override
	public void removeEndereco(int id) throws BusinessException {
		businessEndereco.remove(id);
	}

	@Override
	public List<Endereco> searchAllEndereco() throws BusinessException {
		return businessEndereco.searchAll();
	}

	@Override
	public List<Endereco> searchAllEndereco(String search) throws BusinessException {
		return businessEndereco.searchAll(search);
	}

	@Override
	public void createOrUpdateFilial(Filial entidade) throws BusinessException {
		businessFilial.createOrUpdate(entidade);
	}

	@Override
	public Filial searchFilial(int id) throws BusinessException {
		return businessFilial.search(id);
	}

	@Override
	public void removeFilial(int id) throws BusinessException {
		businessFilial.remove(id);
	}

	@Override
	public List<Filial> searchAllFilial() throws BusinessException {
		return businessFilial.searchAll();
	}

	@Override
	public List<Filial> searchAllFilial(String search) throws BusinessException {
		return businessFilial.searchAll(search);
	}

	@Override
	public void createOrUpdateFisica(Fisica entidade) throws BusinessException {
		businessFisica.createOrUpdate(entidade);
	}

	@Override
	public Fisica searchFisica(int id) throws BusinessException {
		return businessFisica.search(id);
	}

	@Override
	public void removeFisica(int id) throws BusinessException {
		businessFisica.remove(id);
	}

	@Override
	public List<Fisica> searchAllFisica() throws BusinessException {
		return businessFisica.searchAll();
	}

	@Override
	public List<Fisica> searchAllFisica(String search) throws BusinessException {
		return businessFisica.searchAll(search);
	}

	@Override
	public void createOrUpdateFuncionario(Funcionario entidade) throws BusinessException {
		businessFuncionario.createOrUpdate(entidade);
	}

	@Override
	public Funcionario searchFuncionario(int id) throws BusinessException {
		return businessFuncionario.search(id);
	}

	@Override
	public void removeFuncionario(int id) throws BusinessException {
		businessFuncionario.remove(id);
	}

	@Override
	public List<Funcionario> searchAllFuncionario() throws BusinessException {
		return businessFuncionario.searchAll();
	}

	@Override
	public List<Funcionario> searchAllFuncionario(String search) throws BusinessException {
		return businessFuncionario.searchAll(search);
	}

	@Override
	public void createOrUpdateJuridica(Juridica entidade) throws BusinessException {
		businessJuridica.createOrUpdate(entidade);
	}

	@Override
	public Juridica searchJuridica(int id) throws BusinessException {
		return businessJuridica.search(id);
	}

	@Override
	public void removeJuridica(int id) throws BusinessException {
		businessJuridica.remove(id);
	}

	@Override
	public List<Juridica> searchAllJuridica() throws BusinessException {
		return businessJuridica.searchAll();
	}

	@Override
	public List<Juridica> searchAllJuridica(String search) throws BusinessException {
		return businessJuridica.searchAll(search);
	}

	@Override
	public void createOrUpdateLocacao(Locacao entidade) throws BusinessException {
		businessLocacao.createOrUpdate(entidade);
	}

	@Override
	public Locacao searchLocacao(int id) throws BusinessException {
		return businessLocacao.search(id);
	}

	@Override
	public void removeLocacao(int id) throws BusinessException {
		businessLocacao.remove(id);
	}

	@Override
	public List<Locacao> searchAllLocacao() throws BusinessException {
		return businessLocacao.searchAll();
	}

	@Override
	public List<Locacao> searchAllLocacao(String search) throws BusinessException {
		return businessLocacao.searchAll(search);
	}

	@Override
	public List<Locacao> searchAllLocacaoAtivo(String search) throws BusinessException {
		return businessLocacao.searchAllAtivo(search);
	}

	@Override
	public List<LocacaoView> searchAllLocacaoVencidos() throws BusinessException {
		return businessLocacao.searchAllVencidos();
	}

	@Override
	public List<Locacao> searchAllLocacaoPeriodo(LocalDate inicio, LocalDate fim) throws BusinessException {
		return businessLocacao.searchAllPeriodo(inicio, fim);
	}

	@Override
	public List<Locacao> searchAllLocacaoPorCliente(Cliente cliente) throws BusinessException {
		return businessLocacao.searchAllPorCliente(cliente);
	}

	@Override
	public void createOrUpdatePassageiro(Passageiro entidade) throws BusinessException {
		businessPassageiro.createOrUpdate(entidade);
	}

	@Override
	public Passageiro searchPassageiro(int id) throws BusinessException {
		return businessPassageiro.search(id);
	}

	@Override
	public void removePassageiro(int id) throws BusinessException {
		businessPassageiro.remove(id);
	}

	@Override
	public List<Passageiro> searchAllPassageiro() throws BusinessException {
		return businessPassageiro.searchAll();
	}

	@Override
	public List<Passageiro> searchAllPassageiro(String search) throws BusinessException {
		return businessPassageiro.searchAll(search);
	}

	@Override
	public void createOrUpdateReserva(Reserva entidade) throws BusinessException {
		businessReserva.createOrUpdate(entidade);
	}

	@Override
	public Reserva searchReserva(int id) throws BusinessException {
		return businessReserva.search(id);
	}

	@Override
	public void removeReserva(int id) throws BusinessException {
		businessReserva.remove(id);
	}

	@Override
	public List<Reserva> searchAllReserva() throws BusinessException {
		return businessReserva.searchAll();
	}

	@Override
	public List<Reserva> searchAllReserva(String search) throws BusinessException {
		return businessReserva.searchAll(search);
	}

	@Override
	public List<Reserva> searchAllReservaAtivo(String search) throws BusinessException {
		return businessReserva.searchAllAtivo(search);
	}

	@Override
	public List<ReservaView> searchAllReservaVencidos() throws BusinessException {
		return businessReserva.searchAllVencidos();
	}

	@Override
	public List<Reserva> searchAllReservaPeriodo(LocalDate inicio, LocalDate fim) throws BusinessException {
		return businessReserva.searchAllPeriodo(inicio, fim);
	}

	@Override
	public void createOrUpdateSuperUsuario(SuperUsuario entidade) throws BusinessException {
		businessSuperUsuario.createOrUpdate(entidade);
	}

	@Override
	public SuperUsuario searchSuperUsuario(int id) throws BusinessException {
		return businessSuperUsuario.search(id);
	}

	@Override
	public void removeSuperUsuario(int id) throws BusinessException {
		businessSuperUsuario.remove(id);
	}

	@Override
	public List<SuperUsuario> searchAllSuperUsuario() throws BusinessException {
		return businessSuperUsuario.searchAll();
	}

	@Override
	public List<SuperUsuario> searchAllSuperUsuario(String search) throws BusinessException {
		return businessSuperUsuario.searchAll(search);
	}

	@Override
	public Usuario searchUser(String login, String senha) throws BusinessException {
		return businessUsuario.searchUser(login, senha);
	}

	@Override
	public List<Cliente> searchAllCliente() throws BusinessException {
		return businessCliente.searchAll();
	}

	@Override
	public List<Cliente> searchAllCliente(String search) throws BusinessException {
		return businessCliente.searchAll(search);
	}

	@Override
	public List<Veiculo> searchVeiculoDisponivel(String search, boolean reservado, boolean alugado)
			throws BusinessException {
		return businessVeiculo.searchDisponivel(search, reservado, alugado);
	}

	@Override
	public List<Veiculo> searchAllVeiculoPorCategoria(Categoria categoria) throws BusinessException {
		return businessVeiculo.searchAllPorCategoria(categoria);
	}

	public List<VeiculoView> searchAllManutencao() throws BusinessException {
		return businessVeiculo.searchAllManutencao();
	}

	@Override
	public List<Log> searchLog(LocalDate date, TipoHistorico tipoHistorico) throws BusinessException {
		return businessUtil.searchLog(date, tipoHistorico);
	}

	@Override
	public Long searchContSelect(Class<? extends Entidade> classe, String sql) throws BusinessException {
		return businessUtil.searchContSelect(classe, sql);
	}

	@Override
	public void createOrUpdateFinanceiro(Financeiro entidade) throws BusinessException {
		businessFinanceiro.createOrUpdate(entidade);
	}

	@Override
	public Financeiro searchFinanceiro(int id) throws BusinessException {
		return businessFinanceiro.search(id);
	}

	@Override
	public void remove(int id) throws BusinessException {
		businessFinanceiro.remove(id);
	}

	@Override
	public List<Financeiro> searchAllFinanceiro() throws BusinessException {
		return businessFinanceiro.searchAll();
	}

	@Override
	public List<Financeiro> searchAllFinanceiro(String search) throws BusinessException {
		return businessFinanceiro.searchAll(search);
	}
	
	@Override
	public List<Financeiro> searchAllFinanceiroEstado(LocalDate de, LocalDate ate, EstadoFinanceiro estado) throws BusinessException {
		return businessFinanceiro.searchAllEstado(de, ate, estado);
	}

	@Override
	public List<Veiculo> searchAllVeiculoDataFurtura(Filial filial, LocalDate date) throws BusinessException {
		return businessVeiculo.searchAllDataFurtura(filial, date);
	}

	@Override
	public List<Veiculo> searchAllVeiculoDiponivel(Filial filial) throws BusinessException {
		return businessVeiculo.searchAllDiponivel(filial);
	}

	@Override
	public Long searchCont(Class<? extends Entidade> classe) throws BusinessException {
		return businessUtil.searchCont(classe);
	}

	@Override
	public BigInteger verificarAtrasadosFinanceiro() throws BusinessException{
		return businessFinanceiro.verificarAtrasados();
	}

	@Override
	public BigInteger verificarAtrasadosReserva() throws BusinessException {
		return businessReserva.verificarAtrasados();
	}

	@Override
	public BigInteger verificarManutencaoVeiculo() throws BusinessException {
		return businessVeiculo.verificarManutencao();
	}
}