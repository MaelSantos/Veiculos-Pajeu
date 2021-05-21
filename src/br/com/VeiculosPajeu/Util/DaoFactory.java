package br.com.VeiculosPajeu.Util;

import br.com.VeiculosPajeu.Dao.DaoAutomovel;
import br.com.VeiculosPajeu.Dao.DaoCarga;
import br.com.VeiculosPajeu.Dao.DaoCategoria;
import br.com.VeiculosPajeu.Dao.DaoCliente;
import br.com.VeiculosPajeu.Dao.DaoConfiguracao;
import br.com.VeiculosPajeu.Dao.DaoEndereco;
import br.com.VeiculosPajeu.Dao.DaoFilial;
import br.com.VeiculosPajeu.Dao.DaoFinanceiro;
import br.com.VeiculosPajeu.Dao.DaoFisica;
import br.com.VeiculosPajeu.Dao.DaoFuncionario;
import br.com.VeiculosPajeu.Dao.DaoJuridica;
import br.com.VeiculosPajeu.Dao.DaoLocacao;
import br.com.VeiculosPajeu.Dao.DaoPassageiro;
import br.com.VeiculosPajeu.Dao.DaoReserva;
import br.com.VeiculosPajeu.Dao.DaoSuperUsuario;
import br.com.VeiculosPajeu.Dao.DaoUsuario;
import br.com.VeiculosPajeu.Dao.DaoVeiculo;
import br.com.VeiculosPajeu.Dao.Interface.IDao;
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
import br.com.VeiculosPajeu.Entidade.Passageiro;
import br.com.VeiculosPajeu.Entidade.Reserva;
import br.com.VeiculosPajeu.Entidade.SuperUsuario;
import br.com.VeiculosPajeu.Entidade.Usuario;
import br.com.VeiculosPajeu.Entidade.Veiculo;

public class DaoFactory {

	private static DaoFactory instance;

	private DaoFactory() {
	}

	public static DaoFactory getInstance() {
		if (instance == null)
			instance = new DaoFactory();
		return instance;
	}

	public IDao<? extends Entidade> getDao(Class<? extends Entidade> classe) {

		if (classe == Veiculo.class)
			return new DaoVeiculo();
		else if (classe == Usuario.class)
			return new DaoUsuario();
		else if (classe == SuperUsuario.class)
			return new DaoSuperUsuario();
		else if (classe == Reserva.class)
			return new DaoReserva();
		else if (classe == Passageiro.class)
			return new DaoPassageiro();
		else if (classe == Locacao.class)
			return new DaoLocacao();
		else if (classe == Juridica.class)
			return new DaoJuridica();
		else if (classe == Funcionario.class)
			return new DaoFuncionario();
		else if (classe == Fisica.class)
			return new DaoFisica();
		else if (classe == Financeiro.class)
			return new DaoFinanceiro();
		else if (classe == Filial.class)
			return new DaoFilial();
		else if (classe == Endereco.class)
			return new DaoEndereco();
		else if (classe == Configuracao.class)
			return new DaoConfiguracao();
		else if (classe == Cliente.class)
			return new DaoCliente();
		else if (classe == Categoria.class)
			return new DaoCategoria();
		else if (classe == Carga.class)
			return new DaoCarga();
		else if (classe == Automovel.class)
			return new DaoAutomovel();

		return null;
	}

}
