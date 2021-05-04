package br.com.VeiculosPajeu.Controle;

import java.util.List;

import com.jfoenix.controls.JFXTimePicker;

import br.com.VeiculosPajeu.Entidade.Automovel;
import br.com.VeiculosPajeu.Entidade.Carga;
import br.com.VeiculosPajeu.Entidade.Categoria;
import br.com.VeiculosPajeu.Entidade.Configuracao;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Filial;
import br.com.VeiculosPajeu.Entidade.Locacao;
import br.com.VeiculosPajeu.Entidade.Passageiro;
import br.com.VeiculosPajeu.Entidade.Reserva;
import br.com.VeiculosPajeu.Entidade.Veiculo;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import br.com.VeiculosPajeu.Exception.BusinessException;
import br.com.VeiculosPajeu.Util.DateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ControleRetirarReserva extends Controle {

	@FXML
    private Button btnAlocar;

    @FXML
    private Label lblTitulo;

    @FXML
    private Label lblCliente;

    @FXML
    private Label lblVeiculo;

    @FXML
    private Label lblTipoLocacao;

    @FXML
    private TextField tfdReserva;

    @FXML
    private Button btnBuscarReserva;

    @FXML
    private TextField tfdFilial;

    @FXML
    private Button btnSelecionarVeiculo;

    @FXML
    private TextField tfdDiaria;

    @FXML
    private TextField tfdValorFinal;

    @FXML
    private DatePicker dtpDevolucao;

    @FXML
    private TextField tfdDataLocacao;

    @FXML
    private JFXTimePicker tpcHoraDevolucao;

	private Veiculo veiculo;

	private Reserva reserva;
	private Locacao locacao;
	private Filial filial;

	private Configuracao configuracao;

	private Float valorTotal;
	
	private DateUtil dateUtil;

	@Override
	public void update(Tela tela, Entidade entidade) {

		if (tela == Tela.RETIRAR_RESERVA) {
			limparCampos();
		}
		if (entidade instanceof Reserva) {
			reserva = (Reserva) entidade;
			carregarCampos();
		}

	}

	@Override
	protected void init() {
		
		dateUtil = DateUtil.getInstance();

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
				carregarLocacao();
				fachada.createOrUpdateLocacao(locacao);

				fachada.createOrUpdateReserva(reserva);

				if (veiculo instanceof Automovel) {
					fachada.createOrUpdateAutomovel((Automovel) veiculo);
				} else if (veiculo instanceof Carga) {
					fachada.createOrUpdateCarga((Carga) veiculo);
				} else if (veiculo instanceof Passageiro) {
					fachada.createOrUpdatePassageiro((Passageiro) veiculo);
				}

				notificacao.mensagemSucesso("Locação Salva Com Sucesso");
				limparCampos();
			} catch (BusinessException e) {
				notificacao.mensagemErro("Salvar Locação", e.getMessage());
				e.printStackTrace();
			}

		} else if (obj == btnBuscarReserva) {
			try {
				if (!tfdReserva.getText().isEmpty()) {
					Reserva reserva = notificacao.selecionar(fachada.searchAllReservaAtivo(tfdReserva.getText().trim()));

					if (reserva != null)
					{
						this.reserva = reserva;
						tfdReserva.setText(reserva + "");
						carregarCampos();
					}
				} else
					notificacao.mensagemAtencao();
			} catch (BusinessException e) {
				notificacao.mensagemErro("Buscar Reserva", e.getMessage());
				e.printStackTrace();
			}
		} else if (obj == dtpDevolucao) {

			valorTotal = calcularValorFinal();
			tfdValorFinal.setText(valorTotal + "");

		}else if (obj == btnSelecionarVeiculo) {
			try {

				Categoria categoria = reserva.getCategoria();
				List<Veiculo> veiculos = fachada.searchAllVeiculoPorCategoria(categoria);
				do {
					veiculos = fachada.searchAllVeiculoPorCategoria(categoria);

					if (veiculos.isEmpty()) {
						categoria = fachada.nextCategoria(categoria);
						notificacao.mensagemPersonalisado(AlertType.WARNING, "Indisponivel",
								"Nenhum Veículo Disponinel", "Mudando para categoria superior: " + categoria);
					}

				} while (veiculos.isEmpty());

				Veiculo veiculo = notificacao.selecionar(veiculos);

				if (veiculo != null)
				{
					this.veiculo = veiculo;
					filial = veiculo.getFilial();
					tfdFilial.setText(filial+"");
					lblVeiculo.setText(veiculo + "");
				}
			} catch (BusinessException e) {
				e.printStackTrace();
				notificacao.mensagemErro("Buscar Veículos", e.getMessage());
			}
		}

	}

	private float calcularValorFinal() {
		float valor = 0;
		if (reserva != null) {
			valor = reserva.getCategoria().getValor();
			System.out.println("Valor Categoria: " + valor);
			tfdDiaria.setText(valor + "");
		}

		if (reserva != null && !dtpDevolucao.getEditor().getText().isEmpty()) {
			switch (reserva.getTipo_locacao()) {
			case KM_CONTROLE:
				valor *= dateUtil.DiferencaDias(reserva.getData_locacao(), dtpDevolucao.getValue());
				valor += configuracao.getValorKmControle();
				break;
			case KM_LIVRE:
				valor *= dateUtil.DiferencaDias(reserva.getData_locacao(), dtpDevolucao.getValue());
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
		locacao.setCliente(reserva.getCliente());
		locacao.setVeiculo(veiculo);
		locacao.setFilial_locacao(filial);
		locacao.setDiaria(Float.parseFloat(tfdDiaria.getText().trim()));
		locacao.setValor_total(Float.parseFloat(tfdValorFinal.getText().trim()));
		locacao.setTipoLocacao(reserva.getTipo_locacao());
		locacao.setData_devolucao(dtpDevolucao.getValue());
		locacao.setData_locacao(reserva.getData_locacao());
		locacao.setHora_devolucao(tpcHoraDevolucao.getValue());
		reserva.setAtivo(false);
	}

	private void carregarCampos() {

		lblCliente.setText(reserva.getCliente() + "");
		lblVeiculo.setText(veiculo + "");

		lblTipoLocacao.setText(reserva.getTipo_locacao() + "");
		tfdDataLocacao.setText(getDate(reserva.getData_locacao()));
		
		dtpDevolucao.getEditor().setText(getDate(reserva.getData_devolucao()));
		dtpDevolucao.setValue(reserva.getData_devolucao());

		valorTotal = calcularValorFinal();
		tfdValorFinal.setText(valorTotal + "");
	}

	protected void limparCampos() {

		lblCliente.setText("-");
		lblVeiculo.setText("-");
		lblTipoLocacao.setText("-");

		tfdDiaria.setText("");
		tfdFilial.setText("");
		tfdValorFinal.setText("");
		tfdDataLocacao.setText("");
		dtpDevolucao.getEditor().setText("");

		locacao = null;
		veiculo = null;
		filial = null;

		valorTotal = (float) 0;
	}

}
