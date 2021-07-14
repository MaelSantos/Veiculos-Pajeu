package br.com.VeiculosPajeu.Controle;

import br.com.VeiculosPajeu.App.App;
import br.com.VeiculosPajeu.Dao.Dao;
import br.com.VeiculosPajeu.Entidade.SuperUsuario;
import br.com.VeiculosPajeu.Entidade.Usuario;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import br.com.VeiculosPajeu.Exception.BusinessException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControleLogin extends ControleAdapter {

	@FXML
	private TextField tfdLogin;

	@FXML
	private PasswordField tfdSenha;

	@FXML
	private Button btnEntrar;

	@FXML
	private Button btnSair;

	@FXML
	private Button btnResetarSenha;

	private Usuario usuario;

	@Override
	protected void init() {
		try {
			Long cont = fachada.searchCont(Usuario.class);

			if (cont == 0) {
				Dao.resetConnection();
				SuperUsuario usuario = fachada.searchSuperUsuario(1);
				fachada.saveSuperUsuario(usuario);
			}

		} catch (BusinessException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void action(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnEntrar) {
			try {
				usuario = fachada.searchUser(tfdLogin.getText().trim(), tfdSenha.getText().trim());
				if (usuario != null) {
					App.notificarOuvintes(Tela.MENU, usuario);
					App.changeStage(Tela.MENU);
					limparCampos();
				} else
					notificacao.mensagemErro("Fazer Login", "Login ou Senha Errados!!!");
			} catch (BusinessException e) {
				notificacao.mensagemErro("Login ou Senha Errados!!!", e.getMessage());
				e.printStackTrace();
			}
		} else if (obj == btnResetarSenha) {

			notificacao.showDialogo(Tela.RESETAR_SENHA);
			App.notificarOuvintes(Tela.RESETAR_SENHA);

		} else if (obj == btnSair) {
			if (notificacao.showConfirmacao("Sair?", "", "Deseja Realmente Sair?"))
				System.exit(0);
		}

	}

	@Override
	protected void limparCampos() {
		tfdLogin.setText("");
		tfdSenha.setText("");
	}

}