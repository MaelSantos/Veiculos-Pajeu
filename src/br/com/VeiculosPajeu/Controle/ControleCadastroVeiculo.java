package br.com.VeiculosPajeu.Controle;

import br.com.VeiculosPajeu.Entidade.Automovel;
import br.com.VeiculosPajeu.Entidade.Carga;
import br.com.VeiculosPajeu.Entidade.Categoria;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Filial;
import br.com.VeiculosPajeu.Entidade.Passageiro;
import br.com.VeiculosPajeu.Entidade.Veiculo;
import br.com.VeiculosPajeu.Entidade.Enum.Acionamento;
import br.com.VeiculosPajeu.Entidade.Enum.Tamanho;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import br.com.VeiculosPajeu.Entidade.Enum.TipoAirBag;
import br.com.VeiculosPajeu.Entidade.Enum.TipoCambio;
import br.com.VeiculosPajeu.Entidade.Enum.TipoCombustivel;
import br.com.VeiculosPajeu.Entidade.Enum.TipoVeiculo;
import br.com.VeiculosPajeu.Exception.BusinessException;
import br.com.VeiculosPajeu.Util.MaskFieldUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ControleCadastroVeiculo extends Controle {

	@FXML
	private Button btnAnterior;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnProximo;

	@FXML
	private Label lblTitulo;

	@FXML
	private ComboBox<TipoVeiculo> cbxTipoVeiculo;

	@FXML
	private TextField tfdFilial;

	@FXML
	private Button btnBuscarFilial;

	@FXML
	private Button btnMaisFilial;

	@FXML
	private ComboBox<Categoria> cbxCategoria;

	@FXML
	private Button btnMaisCategoria;

	@FXML
	private Tab tabGeral;

	@FXML
	private TextField tfdFabricante;

	@FXML
	private TextField tfdModelo;

	@FXML
	private Spinner<Integer> spnNumPassageiros;

	@FXML
	private Spinner<Integer> spnNumPortas;

	@FXML
	private TextField tfdAnoFabricacao;

	@FXML
	private TextField tfdAnoModelo;

	@FXML
	private TextField tfdPlaca;

	@FXML
	private TextField tfdNumChassi;

	@FXML
	private TextField tfdNumMotor;

	@FXML
	private TextField tfdCor;

	@FXML
	private TextField tfdTorqueMotor;

	@FXML
	private ComboBox<TipoCombustivel> cbxTipoCombustivel;

	@FXML
	private Tab tabVeiculos;

	@FXML
	private GridPane paneAutomovel;

	@FXML
	private ComboBox<Tamanho> cbxTamanho;

	@FXML
	private ComboBox<TipoCambio> cbxTipoCambio;

	@FXML
	private CheckBox ckbArcondicionado;

	@FXML
	private CheckBox ckbRadio;

	@FXML
	private CheckBox ckbDvd;

	@FXML
	private CheckBox ckbMp3;

	@FXML
	private CheckBox ckbDirecaoHidraulica;

	@FXML
	private CheckBox ckbCameraRe;

	@FXML
	private GridPane panePassageiros;

	@FXML
	private ComboBox<TipoAirBag> cbxAirbag;

	@FXML
	private CheckBox ckbDirecaoAssistida;

	@FXML
	private CheckBox ckbCintosRetrateis;

	@FXML
	private CheckBox ckbRodasLigaLeve;

	@FXML
	private CheckBox ckbControlePoluicao;

	@FXML
	private GridPane paneCarga;

	@FXML
	private TextField tfdCapacidadeCarga;

	@FXML
	private ComboBox<Acionamento> cbxAcionamento;

	@FXML
	private TextField tfdDesempenho;

	@FXML
	private TextField tfdPotencia;

	@FXML
	private TextField tfdVolume;

	@FXML
	private TextField tfdDistancia;

	private Carga carga;
	private Passageiro passageiro;
	private Automovel automovel;

	private Filial filial;

	private MaskFieldUtil maskFieldUtil;

	@Override
	protected void init() {

		maskFieldUtil = MaskFieldUtil.getInstance();

		cbxAirbag.getItems().setAll(TipoAirBag.values());
		cbxTamanho.getItems().setAll(Tamanho.values());
		cbxTipoCambio.getItems().setAll(TipoCambio.values());
		cbxTipoCombustivel.getItems().setAll(TipoCombustivel.values());
		cbxTipoVeiculo.getItems().setAll(TipoVeiculo.values());
		cbxAcionamento.getItems().setAll(Acionamento.values());

		try {
			cbxCategoria.getItems().setAll(fachada.searchAllCategoria());
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		spnNumPassageiros.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
		spnNumPortas.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1));

		cbxTamanho.setButtonCell(new ListCell<Tamanho>() {
			@Override
			protected void updateItem(Tamanho item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Tamanho");
				} else {
					setText(item.toString());
				}
			}
		});

		cbxCategoria.setButtonCell(new ListCell<Categoria>() {
			@Override
			protected void updateItem(Categoria item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Categoria");
				} else {
					setText(item.toString());
				}
			}
		});

		cbxAirbag.setButtonCell(new ListCell<TipoAirBag>() {
			@Override
			protected void updateItem(TipoAirBag item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("AirBag");
				} else {
					setText(item.toString());
				}
			}
		});

		cbxTipoCambio.setButtonCell(new ListCell<TipoCambio>() {
			@Override
			protected void updateItem(TipoCambio item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Tipo de Câmbio");
				} else {
					setText(item.toString());
				}
			}
		});

		cbxTipoCombustivel.setButtonCell(new ListCell<TipoCombustivel>() {
			@Override
			protected void updateItem(TipoCombustivel item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Tipo de Combustível");
				} else {
					setText(item.toString());
				}
			}
		});

		cbxTipoVeiculo.setButtonCell(new ListCell<TipoVeiculo>() {
			@Override
			protected void updateItem(TipoVeiculo item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Tipo de Veículo");
				} else {
					setText(item.toString());
				}
			}
		});

		cbxAcionamento.setButtonCell(new ListCell<Acionamento>() {
			@Override
			protected void updateItem(Acionamento item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Acionamento");
				} else {
					setText(item.toString());
				}
			}
		});

		maskFieldUtil.numericField(tfdAnoFabricacao);
		maskFieldUtil.numericField(tfdAnoModelo);
		maskFieldUtil.numericField(tfdCapacidadeCarga);

	}

	@Override
	public void action(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnAnterior) {
			tabGeral.getTabPane().getSelectionModel().select(0);
			btnProximo.setVisible(true);
			btnAnterior.setVisible(false);
			btnSalvar.setVisible(false);
		} else if (obj == btnProximo) {
			tabGeral.getTabPane().getSelectionModel().select(1);
			btnAnterior.setVisible(true);
			btnProximo.setVisible(false);
			if (cbxTipoVeiculo.getValue() != null)
				btnSalvar.setVisible(true);

		} else if (obj == btnSalvar) {

			try {
				carregarVeiculo();
				switch (cbxTipoVeiculo.getValue()) {
				case AUTOMOVEL:
					fachada.saveAutomovel(automovel);
					break;
				case CAMIONETE_DE_CARGA:
					fachada.saveCarga(carga);
					break;
				case CAMIONETE_DE_PASSAGEIROS:
					fachada.savePassageiro(passageiro);
					break;
				default:
					break;
				}

				notificacao.mensagemSucesso("Veículo Salvo Com Sucesso");
				limparCampos();
			} catch (BusinessException e) {
				notificacao.mensagemErro("Salvar Veículo!!!", e.getMessage());
				e.printStackTrace();
			}
		} else if (obj == btnBuscarFilial) {
			try {
				if (!tfdFilial.getText().trim().isEmpty()) {
					Filial filial = notificacao.selecionar(fachada.searchAllFilial(tfdFilial.getText().trim()));

					if (filial != null) {
						this.filial = filial;
						tfdFilial.setText(filial + "");
					}
				} else
					notificacao.mensagemAtencao();

			} catch (BusinessException e) {
				notificacao.mensagemErro("Buscar Filial", e.getMessage());
				e.printStackTrace();
			}
		} else if (obj == btnMaisCategoria) {
			notificacao.showDialogo(Tela.CADASTRO_CATEGORIA);
		} else if (obj == btnMaisFilial) {
			notificacao.showDialogo(Tela.CADASTRO_FILIAL);
		}

	}

	@FXML
	void selected(ActionEvent event) {

		if (cbxTipoVeiculo.getValue() != null) {
			switch (cbxTipoVeiculo.getValue()) {
			case AUTOMOVEL:
				paneAutomovel.setVisible(true);
				paneCarga.setVisible(false);
				panePassageiros.setVisible(false);
				break;
			case CAMIONETE_DE_CARGA:
				paneAutomovel.setVisible(false);
				paneCarga.setVisible(true);
				panePassageiros.setVisible(false);
				break;
			case CAMIONETE_DE_PASSAGEIROS:
				paneAutomovel.setVisible(false);
				paneCarga.setVisible(false);
				panePassageiros.setVisible(true);
				break;
			default:
				break;
			}

			btnSalvar.setVisible(tabVeiculos.isSelected());
			tabVeiculos.setText(cbxTipoVeiculo.getValue() + "");

		}
	}

	@FXML
	void click(MouseEvent event) {

		System.out.println("click");

		if (tabGeral.isSelected()) {
			btnProximo.setVisible(true);
			btnAnterior.setVisible(false);
			btnSalvar.setVisible(false);
		} else if (tabVeiculos.isSelected()) {
			btnAnterior.setVisible(true);
			btnProximo.setVisible(false);
			if (cbxTipoVeiculo.getValue() != null)
				btnSalvar.setVisible(true);
		}

	}

	@Override
	protected void limparCampos() {

		cbxTipoVeiculo.getSelectionModel().clearSelection();

		cbxAirbag.getSelectionModel().clearSelection();
		cbxCategoria.getSelectionModel().clearSelection();
		cbxTamanho.getSelectionModel().clearSelection();
		cbxTipoCambio.getSelectionModel().clearSelection();
		cbxTipoCombustivel.getSelectionModel().clearSelection();
		cbxAcionamento.getSelectionModel().clearSelection();

		tfdAnoFabricacao.setText("");
		tfdAnoModelo.setText("");
		tfdCapacidadeCarga.setText("");
		tfdCor.setText("");
		tfdDesempenho.setText("");
		tfdDistancia.setText("");
		tfdFabricante.setText("");
		tfdModelo.setText("");
		tfdNumChassi.setText("");
		tfdNumMotor.setText("");
		tfdPlaca.setText("");
		tfdPotencia.setText("");
		tfdTorqueMotor.setText("");
		tfdVolume.setText("");
		tfdFilial.setText("");

		spnNumPassageiros.getEditor().setText("0");
		spnNumPortas.getEditor().setText("0");

		ckbArcondicionado.setSelected(false);
		ckbRodasLigaLeve.setSelected(false);
		ckbControlePoluicao.setSelected(false);
		ckbCameraRe.setSelected(false);
		ckbCintosRetrateis.setSelected(false);
		ckbDirecaoAssistida.setSelected(false);
		ckbDirecaoHidraulica.setSelected(false);
		ckbDvd.setSelected(false);
		ckbMp3.setSelected(false);
		ckbRadio.setSelected(false);

		tabGeral.getTabPane().getSelectionModel().select(0);
		paneAutomovel.setVisible(false);
		paneCarga.setVisible(false);
		panePassageiros.setVisible(false);

		tabVeiculos.setText("Veículo");

		carga = null;
		passageiro = null;
		automovel = null;
		filial = null;

		btnAnterior.setVisible(false);
		btnSalvar.setVisible(false);
		btnProximo.setVisible(true);

	}

	private void carregarVeiculo() {

		switch (cbxTipoVeiculo.getValue()) {
		case AUTOMOVEL:
			carregarAutomovel();
			break;
		case CAMIONETE_DE_CARGA:
			carregarCarga();
			break;
		case CAMIONETE_DE_PASSAGEIROS:
			carregarPassageiro();
			break;

		default:
			break;
		}

	}

	private void carregarAutomovel() {

		if (automovel == null) {
			automovel = new Automovel();
			automovel.setAlugado(false);
			automovel.setManutencao(false);
		}
		automovel.setFilial(filial);
		automovel.setAno_fabricacao(Integer.parseInt(tfdAnoFabricacao.getText().trim()));
		automovel.setAno_modelo(Integer.parseInt(tfdAnoModelo.getText().trim()));
		automovel.setArcondicionado(ckbArcondicionado.isSelected());
		automovel.setCamera_re(ckbCameraRe.isSelected());
		automovel.setCategoria(cbxCategoria.getValue());
		automovel.setCor(tfdCor.getText().trim());
		automovel.setDirecao_hidraulica(ckbDirecaoHidraulica.isSelected());
		automovel.setDvd(ckbDvd.isSelected());
		automovel.setFabricante(tfdFabricante.getText().trim());
		automovel.setModelo(tfdModelo.getText().trim());
		automovel.setMp3(ckbMp3.isSelected());
		automovel.setNumero_chassi(tfdNumChassi.getText().trim());
		automovel.setNumero_motor(tfdNumMotor.getText().trim());
		automovel.setNumero_passageiros(spnNumPassageiros.getValue());
		automovel.setNumero_portas(spnNumPortas.getValue());
		automovel.setPlaca(tfdPlaca.getText().trim());
		automovel.setRadio(ckbRadio.isSelected());
		automovel.setTamanho(cbxTamanho.getValue());
		automovel.setTipo_cambio(cbxTipoCambio.getValue());
		automovel.setTipo_combustivel(cbxTipoCombustivel.getValue());
		automovel.setTorque_motor(tfdTorqueMotor.getText().trim());
	}

	private void carregarPassageiro() {

		if (passageiro == null) {
			passageiro = new Passageiro();
			passageiro.setAlugado(false);
			passageiro.setManutencao(false);
		}
		passageiro.setFilial(filial);
		passageiro.setAirbag(cbxAirbag.getValue());
		passageiro.setAno_fabricacao(Integer.parseInt(tfdAnoFabricacao.getText().trim()));
		passageiro.setAno_modelo(Integer.parseInt(tfdAnoModelo.getText().trim()));
		passageiro.setCategoria(cbxCategoria.getValue());
		passageiro.setCintos_retrateis(ckbCintosRetrateis.isSelected());
		passageiro.setControle_poluicao(ckbControlePoluicao.isSelected());
		passageiro.setCor(tfdCor.getText().trim());
		passageiro.setFabricante(tfdFabricante.getText().trim());
		passageiro.setModelo(tfdModelo.getText().trim());
		passageiro.setNumero_chassi(tfdNumChassi.getText().trim());
		passageiro.setNumero_motor(tfdNumMotor.getText().trim());
		passageiro.setNumero_passageiros(spnNumPassageiros.getValue());
		passageiro.setNumero_portas(spnNumPortas.getValue());
		passageiro.setPlaca(tfdPlaca.getText().trim());
		passageiro.setRodas_liga_leve(ckbRodasLigaLeve.isSelected());
		passageiro.setTipo_combustivel(cbxTipoCombustivel.getValue());
		passageiro.setTorque_motor(tfdTorqueMotor.getText().trim());
	}

	private void carregarCarga() {

		if (carga == null) {
			carga = new Carga();
			carga.setAlugado(false);
			carga.setManutencao(false);
		}
		carga.setFilial(filial);
		carga.setAcionamento(cbxAcionamento.getValue());
		carga.setAno_fabricacao(Integer.parseInt(tfdAnoFabricacao.getText().trim()));
		carga.setAno_modelo(Integer.parseInt(tfdAnoModelo.getText().trim()));
		carga.setCapacidade_carga(Float.parseFloat(tfdCapacidadeCarga.getText().trim()));
		carga.setCategoria(cbxCategoria.getValue());
		carga.setCor(tfdCor.getText().trim());
		carga.setDesempenho(tfdDesempenho.getText().trim());
		carga.setDistancia(tfdDistancia.getText().trim());
		carga.setFabricante(tfdFabricante.getText().trim());
		carga.setModelo(tfdModelo.getText().trim());
		carga.setNumero_chassi(tfdNumChassi.getText().trim());
		carga.setNumero_motor(tfdNumMotor.getText().trim());
		carga.setNumero_passageiros(spnNumPassageiros.getValue());
		carga.setNumero_portas(spnNumPortas.getValue());
		carga.setPlaca(tfdPlaca.getText().trim());
		carga.setPotencia(tfdPotencia.getText().trim());
		carga.setTipo_combustivel(cbxTipoCombustivel.getValue());
		carga.setTorque_motor(tfdTorqueMotor.getText().trim());
		carga.setVolume_combustivel(tfdVolume.getText().trim());
	}

	private void carregarCampo(Automovel automovel) {

		filial = automovel.getFilial();
		tfdFilial.setText(filial + "");

		tfdAnoFabricacao.setText(automovel.getAno_fabricacao() + "");
		tfdAnoModelo.setText(automovel.getAno_modelo() + "");
		ckbArcondicionado.setSelected(automovel.getArcondicionado());
		ckbCameraRe.setSelected(automovel.getCamera_re());
		cbxCategoria.setValue(automovel.getCategoria());
		tfdCor.setText(automovel.getCor());
		ckbDirecaoHidraulica.setSelected(automovel.getDirecao_hidraulica());
		ckbDvd.setSelected(automovel.getDvd());
		tfdFabricante.setText(automovel.getFabricante());
		tfdModelo.setText(automovel.getModelo());
		ckbMp3.setSelected(automovel.getMp3());
		tfdNumChassi.setText(automovel.getNumero_chassi());
		tfdNumMotor.setText(automovel.getNumero_motor());
		spnNumPassageiros.getValueFactory().setValue(automovel.getNumero_passageiros());
		spnNumPortas.getValueFactory().setValue(automovel.getNumero_portas());
		tfdPlaca.setText(automovel.getPlaca());
		ckbRadio.setSelected(automovel.getRadio());
		cbxTamanho.setValue(automovel.getTamanho());
		cbxTipoCambio.setValue(automovel.getTipo_cambio());
		cbxTipoCombustivel.setValue(automovel.getTipo_combustivel());
		tfdTorqueMotor.setText(automovel.getTorque_motor());
	}

	private void carregarCampo(Passageiro passageiro) {

		filial = passageiro.getFilial();
		tfdFilial.setText(filial + "");

		cbxAirbag.setValue(passageiro.getAirbag());
		tfdAnoFabricacao.setText(passageiro.getAno_fabricacao() + "");
		tfdAnoModelo.setText(passageiro.getAno_modelo() + "");
		cbxCategoria.setValue(passageiro.getCategoria());
		ckbCintosRetrateis.setSelected(passageiro.getCintos_retrateis());
		ckbControlePoluicao.setSelected(passageiro.getControle_poluicao());
		tfdCor.setText(passageiro.getCor());
		tfdFabricante.setText(passageiro.getFabricante());
		tfdModelo.setText(passageiro.getModelo());
		tfdNumChassi.setText(passageiro.getNumero_chassi());
		tfdNumMotor.setText(passageiro.getNumero_motor());
		spnNumPassageiros.getValueFactory().setValue(passageiro.getNumero_passageiros());
		spnNumPortas.getValueFactory().setValue(passageiro.getNumero_portas());
		tfdPlaca.setText(passageiro.getPlaca());
		ckbRodasLigaLeve.setSelected(passageiro.getRodas_liga_leve());
		cbxTipoCombustivel.setValue(passageiro.getTipo_combustivel());
		tfdTorqueMotor.setText(passageiro.getTorque_motor());
	}

	private void carregarCampo(Carga carga) {

		filial = carga.getFilial();
		tfdFilial.setText(filial + "");

		cbxAcionamento.setValue(carga.getAcionamento());
		tfdAnoFabricacao.setText(carga.getAno_fabricacao() + "");
		tfdAnoModelo.setText(carga.getAno_modelo() + "");
		tfdCapacidadeCarga.setText(carga.getCapacidade_carga() + "");
		cbxCategoria.setValue(carga.getCategoria());
		tfdCor.setText(carga.getCor());
		tfdDesempenho.setText(carga.getDesempenho());
		tfdDistancia.setText(carga.getDistancia());
		tfdFabricante.setText(carga.getFabricante());
		tfdModelo.setText(carga.getModelo());
		tfdNumChassi.setText(carga.getNumero_chassi());
		tfdNumMotor.setText(carga.getNumero_motor());
		spnNumPassageiros.getValueFactory().setValue(carga.getNumero_passageiros());
		spnNumPortas.getValueFactory().setValue(carga.getNumero_portas());
		tfdPlaca.setText(carga.getPlaca());
		tfdPotencia.setText(carga.getPotencia());
		cbxTipoCombustivel.setValue(carga.getTipo_combustivel());
		tfdTorqueMotor.setText(carga.getTorque_motor());
		tfdVolume.setText(carga.getVolume_combustivel());
	}

	@Override
	public void update(Tela tela, Entidade entidade) {

		if (entidade instanceof Categoria) {
			Categoria categoria = (Categoria) entidade;
			cbxCategoria.getItems().add(categoria);
		}
		if (tela == Tela.EDITAR_VEICULO) {
			if (entidade instanceof Veiculo) {
				Veiculo veiculo = (Veiculo) entidade;

				if (entidade instanceof Carga) {
					carga = (Carga) entidade;
					cbxTipoVeiculo.setValue(TipoVeiculo.CAMIONETE_DE_CARGA);
					paneCarga.setVisible(true);
					paneAutomovel.setVisible(false);
					panePassageiros.setVisible(false);
					carregarCampo(carga);
				} else if (veiculo instanceof Automovel) {
					automovel = (Automovel) veiculo;
					cbxTipoVeiculo.setValue(TipoVeiculo.AUTOMOVEL);
					paneAutomovel.setVisible(true);
					paneCarga.setVisible(false);
					panePassageiros.setVisible(false);
					carregarCampo(automovel);
				} else if (veiculo instanceof Passageiro) {
					passageiro = (Passageiro) veiculo;
					cbxTipoVeiculo.setValue(TipoVeiculo.CAMIONETE_DE_PASSAGEIROS);
					paneAutomovel.setVisible(false);
					paneCarga.setVisible(false);
					panePassageiros.setVisible(true);
					carregarCampo(passageiro);
				}
			}
			lblTitulo.setText(tela + "");
		} else if (tela == Tela.CADASTRO_VEICULO) {
			lblTitulo.setText(tela + "");
			limparCampos();
		}

	}

}
