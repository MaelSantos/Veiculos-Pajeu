package br.com.VeiculosPajeu.Util;

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

public class LoadBusiness {

	private static LoadBusiness instance;

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

	private LoadBusiness() {

	}

	public static synchronized LoadBusiness getInstance() {
		if (instance == null)
			instance = new LoadBusiness();
		return instance;
	}

	public IBusinessAutomovel loadAutomovel() {
		if (businessAutomovel == null)
			businessAutomovel = new BusinessAutomovel();
		return businessAutomovel;
	}

	public IBusinessCarga loadCarga() {
		if (businessCarga == null)
			businessCarga = new BusinessCarga();
		return businessCarga;
	}

	public IBusinessCategoria loadCategoria() {
		if (businessCategoria == null)
			businessCategoria = new BusinessCategoria();
		return businessCategoria;
	}

	public IBusinessConfiguracao loadConfiguracao() {
		if (businessConfiguracao == null)
			businessConfiguracao = new BusinessConfiguracao();
		return businessConfiguracao;
	}

	public IBusinessEndereco loadEndereco() {
		if (businessEndereco == null)
			businessEndereco = new BusinessEndereco();
		return businessEndereco;
	}

	public IBusinessFilial loadFilial() {
		if (businessFilial == null)
			businessFilial = new BusinessFilial();
		return businessFilial;
	}

	public IBusinessFisica loadFisica() {
		if (businessFisica == null)
			businessFisica = new BusinessFisica();
		return businessFisica;
	}

	public IBusinessFuncionario loadFuncionario() {
		if (businessFuncionario == null)
			businessFuncionario = new BusinessFuncionario();
		return businessFuncionario;
	}

	public IBusinessJuridica loadJuridica() {
		if (businessJuridica == null)
			businessJuridica = new BusinessJuridica();
		return businessJuridica;
	}

	public IBusinessLocacao loadLocacao() {
		if (businessLocacao == null)
			businessLocacao = new BusinessLocacao();
		return businessLocacao;
	}

	public IBusinessPassageiro loadPassageiro() {
		if (businessPassageiro == null)
			businessPassageiro = new BusinessPassageiro();
		return businessPassageiro;
	}

	public IBusinessReserva loadReserva() {
		if (businessReserva == null)
			businessReserva = new BusinessReserva();
		return businessReserva;
	}

	public IBusinessSuperUsuario loadSuperUsuario() {
		if (businessSuperUsuario == null)
			businessSuperUsuario = new BusinessSuperUsuario();
		return businessSuperUsuario;
	}

	public IBusinessFinanceiro loadFinanceiro() {
		if (businessFinanceiro == null)
			businessFinanceiro = new BusinessFinanceiro();
		return businessFinanceiro;
	}

	public IBusinessUsuario loadUsuario() {
		if (businessUsuario == null)
			businessUsuario = new BusinessUsuario();
		return businessUsuario;
	}

	public IBusinessVeiculo loadVeiculo() {
		if (businessVeiculo == null)
			businessVeiculo = new BusinessVeiculo();
		return businessVeiculo;
	}

	public IBusinessCliente loadCliente() {
		if (businessCliente == null)
			businessCliente = new BusinessCliente();
		return businessCliente;
	}

	public IBusinessUtil loadUtil() {
		if (businessUtil == null)
			businessUtil = new BusinessUtil();
		return businessUtil;
	}
}
