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

public interface IFachada {

//	Automovel	businessAutomovel = new BusinessAutomovel();
	public void saveAutomovel(Automovel entidade) throws BusinessException;
    public Automovel searchAutomovel(int id) throws BusinessException;
    public void removeAutomovel(int id) throws BusinessException;
    public List<Automovel> searchAllAutomovel() throws BusinessException;
    public List<Automovel> searchAllAutomovel(String search) throws BusinessException;

//	Carga	businessCarga = new BusinessCarga();
	public void saveCarga(Carga entidade) throws BusinessException;
    public Carga searchCarga(int id) throws BusinessException;
    public void removeCarga(int id) throws BusinessException;
    public List<Carga> searchAllCarga() throws BusinessException;
    public List<Carga> searchAllCarga(String search) throws BusinessException;
    public Categoria nextCategoria(Categoria categoria) throws BusinessException;

//	Categoria	businessCategoria = new BusinessCategoria();
	public void saveCategoria(Categoria entidade) throws BusinessException;
    public Categoria searchCategoria(int id) throws BusinessException;
    public void removeCategoria(int id) throws BusinessException;
    public List<Categoria> searchAllCategoria() throws BusinessException;    
    public List<Categoria> searchAllCategoria(String search) throws BusinessException;

//	Configuracao	businessConfiguracao = new BusinessConfiguracao();
	public void saveConfiguracao(Configuracao entidade) throws BusinessException;
    public Configuracao searchConfiguracao(int id) throws BusinessException;
    public void removeConfiguracao(int id) throws BusinessException;
    public List<Configuracao> searchAllConfiguracao() throws BusinessException;
    public List<Configuracao> searchAllConfiguracao(String search) throws BusinessException;

//    Endereco	businessEndereco = new BusinessEndereco();
	public void saveEndereco(Endereco entidade) throws BusinessException;
    public Endereco searchEndereco(int id) throws BusinessException;
    public void removeEndereco(int id) throws BusinessException;
    public List<Endereco> searchAllEndereco() throws BusinessException;
    public List<Endereco> searchAllEndereco(String search) throws BusinessException;

    //Filial 	businessFilial = new BusinessFilial();
    public void saveFilial(Filial entidade) throws BusinessException;
    public Filial searchFilial(int id) throws BusinessException;
    public void removeFilial(int id) throws BusinessException;
    public List<Filial> searchAllFilial() throws BusinessException;
    public List<Filial> searchAllFilial(String search) throws BusinessException;

	//Pessoa Fisica 	businessFisica = new BusinessFisica();
	public void saveFisica(Fisica entidade) throws BusinessException;
    public Fisica searchFisica(int id) throws BusinessException;
    public void removeFisica(int id) throws BusinessException;
    public List<Fisica> searchAllFisica() throws BusinessException;
    public List<Fisica> searchAllFisica(String search) throws BusinessException;

//    Funcionario	businessFuncionario = new BusinessFuncionario();
	public void saveFuncionario(Funcionario entidade) throws BusinessException;
    public Funcionario searchFuncionario(int id) throws BusinessException;
    public void removeFuncionario(int id) throws BusinessException;
    public List<Funcionario> searchAllFuncionario() throws BusinessException;    
    public List<Funcionario> searchAllFuncionario(String search) throws BusinessException;

    //Pessoa Juridica	businessJuridica = new BusinessJuridica();
    public void saveJuridica(Juridica entidade) throws BusinessException;
    public Juridica searchJuridica(int id) throws BusinessException;
    public void removeJuridica(int id) throws BusinessException;
    public List<Juridica> searchAllJuridica() throws BusinessException;
    public List<Juridica> searchAllJuridica(String search) throws BusinessException;

//    Locacao	businessLocacao = new BusinessLocacao();
	public void saveLocacao(Locacao entidade) throws BusinessException;
    public Locacao searchLocacao(int id) throws BusinessException;
    public void removeLocacao(int id) throws BusinessException;
    public List<Locacao> searchAllLocacao() throws BusinessException;
    public List<Locacao> searchAllLocacao(String search) throws BusinessException;
    public List<Locacao> searchAllLocacaoAtivo(String search) throws BusinessException;
	public List<LocacaoView> searchAllLocacaoVencidos() throws BusinessException;
	public List<Locacao> searchAllLocacaoPeriodo(LocalDate inicio, LocalDate fim) throws BusinessException;
	public List<Locacao> searchAllLocacaoPorCliente(Cliente cliente) throws BusinessException;

//    Passageiro	businessPassageiro = new BusinessPassageiro();
	public void savePassageiro(Passageiro entidade) throws BusinessException;
    public Passageiro searchPassageiro(int id) throws BusinessException;
    public void removePassageiro(int id) throws BusinessException;
    public List<Passageiro> searchAllPassageiro() throws BusinessException;
    public List<Passageiro> searchAllPassageiro(String search) throws BusinessException;

//	Reserva	businessReserva = new BusinessReserva();
	public void saveReserva(Reserva entidade) throws BusinessException;
    public Reserva searchReserva(int id) throws BusinessException;
    public void removeReserva(int id) throws BusinessException;
    public List<Reserva> searchAllReserva() throws BusinessException;
    public List<Reserva> searchAllReserva(String search) throws BusinessException;
    public List<Reserva> searchAllReservaAtivo(String search) throws BusinessException;
    public List<ReservaView> searchAllReservaVencidos() throws BusinessException;
    public List<Reserva> searchAllReservaPeriodo(LocalDate inicio, LocalDate fim) throws BusinessException;
    
//    SuperUsuario	businessSuperUsuario = new BusinessSuperUsuario();
	public void saveSuperUsuario(SuperUsuario entidade) throws BusinessException;
    public SuperUsuario searchSuperUsuario(int id) throws BusinessException;
    public void removeSuperUsuario(int id) throws BusinessException;
    public List<SuperUsuario> searchAllSuperUsuario() throws BusinessException;    
    public List<SuperUsuario> searchAllSuperUsuario(String search) throws BusinessException;

    //Usuario
    public Usuario searchUser(String login, String senha) throws BusinessException;
 
    //Cliente
    public List<Cliente> searchAllCliente() throws BusinessException;
    public List<Cliente> searchAllCliente(String search) throws BusinessException;
    
    //Veiculo
    public List<Veiculo> searchVeiculoDisponivel(String search, boolean reservado, boolean alugado) throws BusinessException;
    public List<Veiculo> searchAllVeiculoPorCategoria(Categoria categoria) throws BusinessException;
    public List<VeiculoView> searchAllManutencao() throws BusinessException;
    public List<Veiculo> searchAllVeiculoDataFurtura(Filial filial, LocalDate date) throws BusinessException;
	public List<Veiculo> searchAllVeiculoDiponivel(Filial filial) throws BusinessException;
    
	//Financeiro
	public void saveFinanceiro(Financeiro entidade) throws BusinessException;
    public Financeiro searchFinanceiro(int id) throws BusinessException;
    public void remove(int id) throws BusinessException;
    public List<Financeiro> searchAllFinanceiro() throws BusinessException;
    public List<Financeiro> searchAllFinanceiro(String search) throws BusinessException;
    public List<Financeiro> searchAllFinanceiroEstado(LocalDate de, LocalDate ate, EstadoFinanceiro estado) throws BusinessException;
	
    //Util
    public Long searchCont(Class<? extends Entidade> classe) throws BusinessException;
    public List<Log> searchLog(LocalDate date, TipoHistorico tipoHistorico) throws BusinessException;
    public Long searchContSelect(Class<? extends Entidade> classe, String sql) throws BusinessException;
    
    //Store Produce
    public BigInteger verificarAtrasadosFinanceiro() throws BusinessException;
	public BigInteger verificarAtrasadosReserva() throws BusinessException;
	public BigInteger verificarManutencaoVeiculo() throws BusinessException;
}
