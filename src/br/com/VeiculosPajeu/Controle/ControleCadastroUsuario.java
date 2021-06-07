package br.com.VeiculosPajeu.Controle;

import br.com.VeiculosPajeu.App.App;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Funcionario;
import br.com.VeiculosPajeu.Entidade.SuperUsuario;
import br.com.VeiculosPajeu.Entidade.Usuario;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import br.com.VeiculosPajeu.Entidade.Enum.TipoFuncionario;
import br.com.VeiculosPajeu.Entidade.Enum.TipoUsuario;
import br.com.VeiculosPajeu.Exception.BusinessException;
import br.com.VeiculosPajeu.Exception.ValidationException;
import br.com.VeiculosPajeu.Util.CriptografiaUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ControleCadastroUsuario extends Controle {

	@FXML
	private Button btnSalvar;

	@FXML
	private Label lblTitulo;

	@FXML
	private TextField tfdNome;

	@FXML
	private TextField tfdLogin;

	@FXML
	private PasswordField tfdSenha;

	@FXML
	private PasswordField tfdConfirmar;

	@FXML
	private ComboBox<TipoUsuario> cbxTipo;

	@FXML
	private GridPane paneFuncionario;

	@FXML
	private TextField tfdCargo;

	@FXML
	private ComboBox<TipoFuncionario> cbxTipoFuncionario;

	@FXML
	private Label lblTipoFuncionario;

	@FXML
	private GridPane paneSuperUsuario;

	@FXML
	private PasswordField tfdSenhaPadrao;

	@FXML
	private PasswordField tfdConfirmarSenhaPadrao;

	private Funcionario funcionario;
	private SuperUsuario superUsuario;

	private CriptografiaUtil criptografiaUtil;

	@Override
	protected void init() {

		criptografiaUtil = CriptografiaUtil.getInstance();

		cbxTipo.getItems().setAll(TipoUsuario.values());
		cbxTipoFuncionario.getItems().setAll(TipoFuncionario.values());

		cbxTipo.setButtonCell(new ListCell<TipoUsuario>() {
			@Override
			protected void updateItem(TipoUsuario item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Tipo de Usuário");
				} else {
					setText(item.toString());
				}
			}
		});

		cbxTipoFuncionario.setButtonCell(new ListCell<TipoFuncionario>() {
			@Override
			protected void updateItem(TipoFuncionario item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Tipo de Funcionário");
				} else {
					setText(item.toString());
				}
			}
		});

	}

	@Override
	public void action(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnSalvar) {

			try {
				if (cbxTipo.getValue() != null) {
					carregarUsuario();
					validarSenhas();
					switch (cbxTipo.getValue()) {
					case FUNCIONARIO:
						fachada.saveFuncionario(funcionario);
						App.notificarOuvintes(Tela.CADASTRO_USUARIO, funcionario);
						break;
					case SUPER_USUARIO:
						fachada.saveSuperUsuario(superUsuario);
						App.notificarOuvintes(Tela.CADASTRO_USUARIO, superUsuario);
					default:
						break;
					}

					notificacao.mensagemSucesso("Usuário Salvo Com Sucesso");
					limparCampos();
				} else
					notificacao.mensagemAtencao();

			} catch (BusinessException | ValidationException e) {
				notificacao.mensagemErro("Salvar Usuário", e.getMessage());
				e.printStackTrace();
			}

		}

	}

	@FXML
	private void selection(ActionEvent event) {

		if (cbxTipo.getValue() != null) {
			switch (cbxTipo.getValue()) {
			case SUPER_USUARIO:
				paneSuperUsuario.setVisible(true);
				paneFuncionario.setVisible(false);
				break;
			case FUNCIONARIO:
				paneSuperUsuario.setVisible(false);
				paneFuncionario.setVisible(true);
			default:
				break;
			}
		}
	}

	private void carregarUsuario() {

		if (cbxTipo.getValue() != null) {
			switch (cbxTipo.getValue()) {
			case FUNCIONARIO:
				if (funcionario == null)
					funcionario = new Funcionario();
				funcionario.setCargo(tfdCargo.getText().trim());
				funcionario.setLogin(tfdLogin.getText().trim());
				funcionario.setNome(tfdNome.getText().trim());
				funcionario.setSenha(tfdSenha.getText().trim());
				funcionario.setTipoFuncionario(cbxTipoFuncionario.getValue());
				break;
			case SUPER_USUARIO:
				if (superUsuario == null)
					superUsuario = new SuperUsuario();
				superUsuario.setSenha_padrao(tfdSenhaPadrao.getText().trim());
				superUsuario.setLogin(tfdLogin.getText().trim());
				superUsuario.setNome(tfdNome.getText().trim());
				superUsuario.setSenha(tfdSenha.getText().trim());
			default:
				break;
			}
		}
	}

	private void validarSenhas() throws ValidationException {

		String senha = tfdSenha.getText().trim();
		String confirmar = tfdConfirmar.getText().trim();

		if (!senha.equals(confirmar)) {
			tfdConfirmar.setText("");
			throw new ValidationException("Senhas Diferentes!!!");
		}
		if (cbxTipo.getValue() == TipoUsuario.SUPER_USUARIO) {
			String padrao = tfdSenhaPadrao.getText().trim();
			String confirmarPadrao = tfdConfirmarSenhaPadrao.getText().trim();
			if (!padrao.equals(confirmarPadrao)) {
				tfdConfirmarSenhaPadrao.setText("");
				throw new ValidationException("Senha Padrão Diferente!!!");
			}
		}

	}

	@Override
	protected void limparCampos() {

		tfdCargo.setText("");
		tfdConfirmar.setText("");
		tfdConfirmarSenhaPadrao.setText("");
		tfdLogin.setText("");
		tfdNome.setText("");
		tfdSenha.setText("");
		tfdSenhaPadrao.setText("");

		cbxTipoFuncionario.getSelectionModel().clearSelection();

		superUsuario = null;
		funcionario = null;

	}

	@Override
	public void update(Tela tela, Entidade entidade) {

		if (tela == Tela.EDITAR_USUARIO) {
			if (entidade instanceof Usuario) {

				if (entidade instanceof SuperUsuario) {
					superUsuario = (SuperUsuario) entidade;
					paneSuperUsuario.setVisible(true);
					paneFuncionario.setVisible(false);
					cbxTipo.setValue(TipoUsuario.SUPER_USUARIO);
					modificarCampos(superUsuario);
				} else if (entidade instanceof Funcionario) {
					funcionario = (Funcionario) entidade;
					paneFuncionario.setVisible(true);
					paneSuperUsuario.setVisible(false);
					cbxTipo.setValue(TipoUsuario.FUNCIONARIO);
					modificarCampos(funcionario);
				}

				cbxTipo.setVisible(false);
				cbxTipoFuncionario.setVisible(false);
				lblTipoFuncionario.setVisible(false);

				lblTitulo.setText(tela + "");
			}

		} else if (tela == Tela.CADASTRO_USUARIO) {
			lblTitulo.setText(tela + "");
			limparCampos();
		}
		if (tela == Tela.MENU) {
			if (entidade instanceof SuperUsuario) {
				cbxTipo.getSelectionModel().clearSelection();
				cbxTipo.setVisible(true);
				paneSuperUsuario.setVisible(false);
				paneFuncionario.setVisible(false);
				cbxTipo.getSelectionModel().clearSelection();
			} else if (entidade instanceof Funcionario) {
				paneSuperUsuario.setVisible(false);
				paneFuncionario.setVisible(true);
				cbxTipo.setValue(TipoUsuario.FUNCIONARIO);
				cbxTipo.setVisible(false);
				paneFuncionario.setVisible(true);
			}
		}
	}

	private void modificarCampos(Usuario usuario) {

		if (funcionario != null) {
			tfdCargo.setText(funcionario.getCargo());
			cbxTipoFuncionario.setValue(funcionario.getTipoFuncionario());
		}
		if (superUsuario != null) {
			tfdSenhaPadrao.setText(criptografiaUtil.descriptografar(superUsuario.getSenha_padrao()));
			tfdConfirmarSenhaPadrao.setText(criptografiaUtil.descriptografar(superUsuario.getSenha_padrao()));
		}
		tfdLogin.setText(usuario.getLogin());
		tfdNome.setText(usuario.getNome());

		tfdSenha.setText(criptografiaUtil.descriptografar(usuario.getSenha()));
		tfdConfirmar.setText(criptografiaUtil.descriptografar(usuario.getSenha()));

	}

}
