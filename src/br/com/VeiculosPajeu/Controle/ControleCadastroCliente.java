package br.com.VeiculosPajeu.Controle;

import java.time.LocalDate;

import br.com.VeiculosPajeu.Entidade.Cliente;
import br.com.VeiculosPajeu.Entidade.Endereco;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Fisica;
import br.com.VeiculosPajeu.Entidade.Juridica;
import br.com.VeiculosPajeu.Entidade.Enum.Estado;
import br.com.VeiculosPajeu.Entidade.Enum.Sexo;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import br.com.VeiculosPajeu.Entidade.Enum.TipoCliente;
import br.com.VeiculosPajeu.Exception.BusinessException;
import br.com.VeiculosPajeu.Util.MaskFieldUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ControleCadastroCliente extends Controle {

	@FXML
	private Label lblTitulo;

	@FXML
	private Button btnAnterior;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnProximo;

	@FXML
	private Tab tabGerais;

	@FXML
	private TextField tfdNome;

	@FXML
	private TextField tfdCodigo;

	@FXML
	private ComboBox<TipoCliente> cbxTipo;

	@FXML
	private GridPane paneFisica;

	@FXML
	private TextField tfdCpf;

	@FXML
	private ComboBox<Sexo> cbxGenero;

	@FXML
	private DatePicker dtpNascimento;

	@FXML
	private TextField tfdNumeroHabilitacao;

	@FXML
	private DatePicker dtpVencimento;

	@FXML
	private GridPane paneJuridica;

	@FXML
	private TextField tfdCnpj;

	@FXML
	private TextField tfdInscricao;

	@FXML
	private Tab tabEndereco;

	@FXML
	private TextField tfdRua;

	@FXML
	private TextField tfdNumero;

	@FXML
	private TextField tfdBairro;

	@FXML
	private TextField tfdCep;

	@FXML
	private TextField tfdCidade;

	@FXML
	private ComboBox<Estado> cbxEstado;

	@FXML
	private TextField tfdPais;

	@FXML
	private TextField tfdComplemento;

	private Juridica juridica;
	private Fisica fisica;

	private MaskFieldUtil maskFieldUtil;

	@Override
	protected void init() {

		maskFieldUtil = MaskFieldUtil.getInstance();

		dtpNascimento.setValue(LocalDate.of(1997, 01, 01));

		cbxEstado.getItems().setAll(Estado.values());
		cbxTipo.getItems().setAll(TipoCliente.values());
		cbxGenero.getItems().setAll(Sexo.values());

		cbxEstado.setButtonCell(new ListCell<Estado>() {
			@Override
			protected void updateItem(Estado item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Estado");
				} else {
					setText(item.toString());
				}
			}
		});

		cbxTipo.setButtonCell(new ListCell<TipoCliente>() {
			@Override
			protected void updateItem(TipoCliente item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Tipo de Cliente");
				} else {
					setText(item.toString());
				}
			}
		});

		cbxGenero.setButtonCell(new ListCell<Sexo>() {
			@Override
			protected void updateItem(Sexo item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Gênero");
				} else {
					setText(item.toString());
				}
			}
		});

		maskFieldUtil.cpfField(tfdCpf);
		maskFieldUtil.cnpjField(tfdCnpj);
	}

	@Override
	public void action(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == cbxTipo) {
			if (cbxTipo.getValue() == TipoCliente.FISICA) {
				paneFisica.setVisible(true);
				paneJuridica.setVisible(false);
			} else if (cbxTipo.getValue() == TipoCliente.JURIDICO) {
				paneFisica.setVisible(false);
				paneJuridica.setVisible(true);
			}
		} else if (obj == btnProximo) {
			tabGerais.getTabPane().getSelectionModel().select(1);
			btnProximo.setVisible(false);
			btnAnterior.setVisible(true);
			btnSalvar.setVisible(true);
		} else if (obj == btnAnterior) {
			tabEndereco.getTabPane().getSelectionModel().select(0);
			btnProximo.setVisible(true);
			btnAnterior.setVisible(false);
			btnSalvar.setVisible(false);
		} else if (obj == btnSalvar) {
			if (cbxTipo.getValue() == TipoCliente.FISICA) {
				try {
					carregarCliente(fisica);
					fachada.createOrUpdateFisica(fisica);
					notificacao.mensagemSucesso("Cliente Salvo Com Sucesso");
					limparCampos();
				} catch (BusinessException e) {
					notificacao.mensagemErro("Salvar Cliente", e.getMessage());
					e.printStackTrace();
				}
			} else if (cbxTipo.getValue() == TipoCliente.JURIDICO) {
				try {
					carregarCliente(juridica);
					fachada.createOrUpdateJuridica(juridica);
					notificacao.mensagemSucesso("Cliente Salvo Com Sucesso");
					limparCampos();
				} catch (BusinessException e) {
					notificacao.mensagemErro("Salvar Cliente", e.getMessage());
					e.printStackTrace();
				}
			} else
				notificacao.mensagemAtencao();
		}

	}

	@FXML
	void click(MouseEvent event) {

		if (tabGerais.isSelected()) {
			btnAnterior.setVisible(false);
			btnSalvar.setVisible(false);
			btnProximo.setVisible(true);
		} else if (tabEndereco.isSelected()) {
			btnAnterior.setVisible(true);
			btnProximo.setVisible(false);
			btnSalvar.setVisible(true);
		}

	}

	private void carregarCliente(Cliente cliente) {
		Endereco endereco = null;
		if (cliente != null)
			endereco = cliente.getEndereco();
		else if (endereco == null)
			endereco = new Endereco();

		endereco.setBairro(tfdBairro.getText().trim());
		endereco.setCidade(tfdCidade.getText().trim());
		endereco.setRua(tfdRua.getText().trim());
		endereco.setEstado(cbxEstado.getValue());
		endereco.setNumero(tfdNumero.getText().trim());
		endereco.setComplemento(tfdComplemento.getText().trim());
		endereco.setCep(tfdCep.getText().trim());
		endereco.setPais(tfdPais.getText().trim());

		if (cbxTipo.getValue() == TipoCliente.FISICA) {
			if (fisica == null)
				fisica = new Fisica();
			fisica.setCodigo(tfdCodigo.getText().trim());
			fisica.setNome(tfdNome.getText().trim());
			fisica.setEndereco(endereco);

			fisica.setCpf(tfdCpf.getText().trim());
			fisica.setNumero_habilitacao(tfdNumeroHabilitacao.getText().trim());
			fisica.setSexo(cbxGenero.getValue());

			fisica.setData_de_nascimento(dtpNascimento.getValue());
			fisica.setVencimento_habilitacao(dtpVencimento.getValue());

			// usuário
			// usuário
			fisica.setLogin(maskFieldUtil.removerMascaraCpf(tfdCpf.getText().trim()));
			fisica.setSenha(maskFieldUtil.removerMascaraCpf(tfdCpf.getText().trim()));
		} else if (cbxTipo.getValue() == TipoCliente.JURIDICO) {
			if (juridica == null)
				juridica = new Juridica();
			juridica.setCodigo(tfdCodigo.getText().trim());
			juridica.setNome(tfdNome.getText().trim());
			juridica.setEndereco(endereco);

			juridica.setCnpj(tfdCnpj.getText().trim());
			juridica.setInscricao_estadual(tfdInscricao.getText().trim());

			// usuário
			juridica.setLogin(maskFieldUtil.removerMascaraCpf(tfdCodigo.getText().trim()));
			juridica.setSenha(maskFieldUtil.removerMascaraCpf(tfdCodigo.getText().trim()));

		}
	}

	private void carregarCampos(Cliente cliente) {

		tfdBairro.setText(cliente.getEndereco().getBairro());
		tfdCidade.setText(cliente.getEndereco().getCidade());
		tfdRua.setText(cliente.getEndereco().getRua());
		cbxEstado.setValue(cliente.getEndereco().getEstado());
		tfdNumero.setText(cliente.getEndereco().getNumero());
		tfdComplemento.setText(cliente.getEndereco().getComplemento());
		tfdCep.setText(cliente.getEndereco().getCep());
		tfdPais.setText(cliente.getEndereco().getPais());

		if (cbxTipo.getValue() == TipoCliente.FISICA) {

			tfdCodigo.setText(fisica.getCodigo());
			tfdNome.setText(fisica.getNome());

			tfdCpf.setText(fisica.getCpf());
			tfdNumeroHabilitacao.setText(fisica.getNumero_habilitacao());
			cbxGenero.setValue(fisica.getSexo());

			dtpNascimento.getEditor().setText(getDate(fisica.getData_de_nascimento()));
			dtpVencimento.getEditor().setText(getDate(fisica.getVencimento_habilitacao()));

			dtpNascimento.setValue(fisica.getData_de_nascimento());
			dtpVencimento.setValue(fisica.getVencimento_habilitacao());

		} else if (cbxTipo.getValue() == TipoCliente.JURIDICO) {
			tfdCodigo.setText(juridica.getCodigo());
			tfdNome.setText(juridica.getNome());

			tfdCnpj.setText(juridica.getCnpj());
			tfdInscricao.setText(juridica.getInscricao_estadual());
		}

	}

	protected void limparCampos() {
		cbxTipo.getSelectionModel().clearSelection();
		paneFisica.setVisible(false);
		paneJuridica.setVisible(false);

		tfdCodigo.setText("");
		tfdNome.setText("");

		tfdBairro.setText("");
		tfdCidade.setText("");
		tfdRua.setText("");
		cbxEstado.getSelectionModel().clearSelection();
		tfdNumero.setText("");
		tfdComplemento.setText("");
		tfdCep.setText("");
		tfdPais.setText("");

		tfdCpf.setText("");
		tfdNumeroHabilitacao.setText("");
		cbxGenero.getSelectionModel().clearSelection();
		dtpNascimento.setValue(LocalDate.of(1997, 01, 01));
		dtpVencimento.getEditor().setText("");

		tfdCnpj.setText("");
		tfdInscricao.setText("");

		fisica = null;
		juridica = null;

		tabGerais.getTabPane().getSelectionModel().select(0);

		btnAnterior.setVisible(false);
		btnSalvar.setVisible(false);
		btnProximo.setVisible(true);

		System.gc();
	}

	@Override
	public void update(Tela tela, Entidade entidade) {

		if (tela == Tela.EDITAR_CLIENTE) {
			if (entidade instanceof Fisica) {
				fisica = (Fisica) entidade;
				cbxTipo.setValue(TipoCliente.FISICA);
				paneFisica.setVisible(true);
				paneJuridica.setVisible(false);
				carregarCampos(fisica);

			} else if (entidade instanceof Juridica) {
				juridica = (Juridica) entidade;
				cbxTipo.setValue(TipoCliente.JURIDICO);
				paneJuridica.setVisible(true);
				paneFisica.setVisible(false);
				carregarCampos(juridica);
			}
			cbxTipo.setVisible(false);
			lblTitulo.setText(tela + "");
		} else if (tela == Tela.CADASTRO_CLIENTE) {
			if (!cbxTipo.isVisible()) {
				cbxTipo.setVisible(true);
			}
			lblTitulo.setText(tela + "");
			limparCampos();
		}

	}

}
