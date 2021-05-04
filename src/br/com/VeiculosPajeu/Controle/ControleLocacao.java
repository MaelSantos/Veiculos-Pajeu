package br.com.VeiculosPajeu.Controle;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.jfoenix.controls.JFXTimePicker;

import br.com.VeiculosPajeu.App.App;
import br.com.VeiculosPajeu.Entidade.Automovel;
import br.com.VeiculosPajeu.Entidade.Carga;
import br.com.VeiculosPajeu.Entidade.Cliente;
import br.com.VeiculosPajeu.Entidade.Configuracao;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Filial;
import br.com.VeiculosPajeu.Entidade.Funcionario;
import br.com.VeiculosPajeu.Entidade.Locacao;
import br.com.VeiculosPajeu.Entidade.Passageiro;
import br.com.VeiculosPajeu.Entidade.SuperUsuario;
import br.com.VeiculosPajeu.Entidade.Usuario;
import br.com.VeiculosPajeu.Entidade.Veiculo;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import br.com.VeiculosPajeu.Entidade.Enum.TipoFuncionario;
import br.com.VeiculosPajeu.Entidade.Enum.TipoLocacao;
import br.com.VeiculosPajeu.Exception.BusinessException;
import br.com.VeiculosPajeu.Util.DateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;

public class ControleLocacao extends Controle {

	@FXML
	private Button btnAlocar;

	@FXML
	private Label lblTitulo;

	@FXML
	private TextField tfdCliente;

	@FXML
	private Button btnBuscarCliente;

	@FXML
	private TextField tfdFilial;

	@FXML
	private TextField tfdVeiculo;

	@FXML
	private Button btnBuscarVeiculo;

	@FXML
	private ComboBox<TipoLocacao> cbxTipoLocacao;

	@FXML
	private Button btnMaisCliente;

	@FXML
	private Button btnMaisFilial;

	@FXML
	private Button btnMaisVeiculo;

	@FXML
	private TextField tfdDiaria;

	@FXML
	private TextField tfdValorFinal;

	@FXML
	private DatePicker dtpAlocacao;

	@FXML
	private DatePicker dtpDevolucao;

	@FXML
	private JFXTimePicker tpcHoraDevolucao;

	private Locacao locacao;
	private Cliente cliente;
	private Filial filial;
	private Veiculo veiculo;
	private Usuario usuario;

	private Configuracao configuracao;

	private Float valorFinal;

	private DateUtil dateUtil;

	@Override
	protected void init() {
		dateUtil = DateUtil.getInstance();

		valorFinal = (float) 0;

		dtpAlocacao.setValue(LocalDate.now());
		cbxTipoLocacao.getItems().setAll(TipoLocacao.values());
		tpcHoraDevolucao.setValue(LocalTime.now());

		cbxTipoLocacao.setButtonCell(new ListCell<TipoLocacao>() {
			@Override
			protected void updateItem(TipoLocacao item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Tipo de Locação");
				} else {
					setText(item.toString());
				}
			}
		});

		try {
			configuracao = fachada.searchConfiguracao(1);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void action(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnAlocar) {

			try {

				if (veiculo != null && filial != null) {
					carregarLocacao();
					fachada.createOrUpdateLocacao(locacao);

					if (veiculo instanceof Automovel) {
						fachada.createOrUpdateAutomovel((Automovel) veiculo);
					} else if (veiculo instanceof Carga) {
						fachada.createOrUpdateCarga((Carga) veiculo);
					} else if (veiculo instanceof Passageiro) {
						fachada.createOrUpdatePassageiro((Passageiro) veiculo);
					}

					ControlePagamento.setDescricao("Valor De Entrada da Locação", locacao.getDiaria() / 2);
					App.notificarOuvintes(fachada.searchFinanceiro(locacao.getId()));
					notificacao.showDialogo(Tela.PAGAMENTO);

					notificacao.mensagemSucesso("Locação Salva Com Sucesso");

					limparCampos();
				} else
					notificacao.mensagemAtencao();

			} catch (BusinessException e) {
				notificacao.mensagemErro("Salvar Locação", e.getMessage());
				e.printStackTrace();
			}

		} else if (obj == btnBuscarCliente) {

			try {
				if (!tfdCliente.getText().isEmpty()) {
					List<Cliente> clientes = fachada.searchAllCliente(tfdCliente.getText().trim());
					Cliente cliente = notificacao.selecionar(clientes);

					if (cliente != null)
						this.cliente = cliente;
					tfdCliente.setText(cliente + "");

				} else
					notificacao.mensagemAtencao();

			} catch (BusinessException e) {
				notificacao.mensagemErro("Buscar Cliente", e.getMessage());
				e.printStackTrace();
			}
		} else if (obj == btnBuscarVeiculo) {

			try {
				if (!tfdVeiculo.getText().trim().isEmpty()) {
					List<Veiculo> veiculos = fachada.searchVeiculoDisponivel(tfdVeiculo.getText().trim(), false, false);
					Veiculo veiculo = notificacao.selecionar(veiculos);

					if (veiculo != null) {
						this.veiculo = veiculo;

						tfdVeiculo.setText(veiculo + "");
						valorFinal = calcularValorFinal();
						tfdValorFinal.setText(valorFinal + "");

						filial = veiculo.getFilial();
						tfdFilial.setText(filial + "");
					}
				} else
					notificacao.mensagemAtencao();

			} catch (BusinessException e) {
				notificacao.mensagemErro("Buscar Veículo", e.getMessage());
				e.printStackTrace();
			}

		} else if (obj == btnMaisCliente) {
			notificacao.showDialogo(Tela.CADASTRO_CLIENTE);
		} else if (obj == btnMaisFilial) {
			notificacao.showDialogo(Tela.CADASTRO_FILIAL);
		} else if (obj == btnMaisVeiculo) {
			notificacao.showDialogo(Tela.CADASTRO_VEICULO);
		}
	}

	@FXML
	public void atualizar(ActionEvent event) {

		valorFinal = calcularValorFinal();
		tfdValorFinal.setText(valorFinal + "");
	}

	private float calcularValorFinal() {
		float valor = 0;
		if (veiculo != null) {
			valor = veiculo.getCategoria().getValor();
			System.out.println("Valor Categoria: " + valor);
			tfdDiaria.setText(valor + "");
		}

		if (cbxTipoLocacao.getValue() != null && !dtpDevolucao.getEditor().getText().isEmpty()) {
			switch (cbxTipoLocacao.getValue()) {
			case KM_CONTROLE:
				valor *= dateUtil.DiferencaDias(dtpAlocacao.getValue(), dtpDevolucao.getValue());
				valor += configuracao.getValorKmControle();
				break;
			case KM_LIVRE:
				valor *= dateUtil.DiferencaDias(dtpAlocacao.getValue(), dtpDevolucao.getValue());
				valor += configuracao.getValorKmLivre();
				break;
			default:
				break;

			}

		}
		return valor;
	}

	private void carregarLocacao() {

		if (locacao == null) {
			locacao = new Locacao();
			locacao.setAtivo(true);
		}
		veiculo.setAlugado(true);
		locacao.setCliente(cliente);
		locacao.setVeiculo(veiculo);
		locacao.setFilial_locacao(filial);
		locacao.setUsuario(usuario);
		locacao.setHora_devolucao(tpcHoraDevolucao.getValue());
		locacao.setDiaria(Float.parseFloat(tfdDiaria.getText().trim()));
		locacao.setValor_total(Float.parseFloat(tfdValorFinal.getText().trim()));
		locacao.setTipoLocacao(cbxTipoLocacao.getValue());
		locacao.setData_devolucao(dtpDevolucao.getValue());
		locacao.setData_locacao(dtpAlocacao.getValue());
	}

	private void carregarCampos() {

		veiculo = locacao.getVeiculo();
		cliente = locacao.getCliente();
		filial = locacao.getFilial_locacao();
		usuario = locacao.getUsuario();
		tfdCliente.setText(cliente + "");
		tfdVeiculo.setText(veiculo + "");
		tfdFilial.setText(filial + "");

		tpcHoraDevolucao.setValue(locacao.getHora_devolucao());
		tfdDiaria.setText(locacao.getDiaria() + "");
		tfdValorFinal.setText(locacao.getValor_total() + "");
		cbxTipoLocacao.setValue(locacao.getTipoLocacao());
		dtpDevolucao.getEditor().setText(getDate(locacao.getData_devolucao()));
		dtpAlocacao.getEditor().setText(getDate(locacao.getData_locacao()));
		dtpDevolucao.setValue(locacao.getData_devolucao());
		dtpAlocacao.setValue(locacao.getData_locacao());

	}

	protected void limparCampos() {

		locacao = null;
		veiculo = null;
		cliente = null;
		filial = null;

		valorFinal = (float) 0;

		tfdCliente.setText("");
		tfdDiaria.setText("");
		tfdFilial.setText("");
		tfdValorFinal.setText("");
		tfdVeiculo.setText("");
		dtpAlocacao.setValue(LocalDate.now());
		dtpDevolucao.getEditor().setText("");
		dtpDevolucao.setValue(null);

		cbxTipoLocacao.getSelectionModel().clearSelection();
		cbxTipoLocacao.setValue(null);
		tpcHoraDevolucao.setValue(LocalTime.now());

	}

	@Override
	public void update(Tela tela, Entidade entidade) {

		if (tela == Tela.MENU) {
			if (entidade instanceof Usuario) {
				usuario = (Usuario) entidade;
				if (usuario instanceof Funcionario) {
					Funcionario funcionario = (Funcionario) usuario;

					if (funcionario.getTipoFuncionario() == TipoFuncionario.ATENDENTE) {
						btnMaisFilial.setVisible(false);
						btnMaisVeiculo.setVisible(false);
					} else {
						btnMaisFilial.setVisible(true);
						btnMaisVeiculo.setVisible(true);
					}
				}
			}
			if (usuario instanceof SuperUsuario) {
				btnMaisFilial.setVisible(true);
				btnMaisVeiculo.setVisible(true);
			}
		}
		if (entidade instanceof Configuracao) {
			configuracao = (Configuracao) entidade;
		}
		if (tela == Tela.LOCACAO) {

			lblTitulo.setText(tela + "");
			limparCampos();

		} else if (tela == Tela.EDITAR_LOCACAO) {
			if (entidade instanceof Locacao) {
				locacao = (Locacao) entidade;
				carregarCampos();
			}

			lblTitulo.setText(tela + "");
		}

	}
}
