package br.com.VeiculosPajeu.Controle;

import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Funcionario;
import br.com.VeiculosPajeu.Entidade.SuperUsuario;
import br.com.VeiculosPajeu.Entidade.Usuario;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import br.com.VeiculosPajeu.Exception.BusinessException;
import br.com.VeiculosPajeu.Util.CriptografiaUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

public class ControleResetarSenha extends Controle {

	@FXML
	private Label lblAndamento;

	@FXML
	private FlowPane flwLogin;

	@FXML
	private TextField tfdLogin;

	@FXML
	private PasswordField tfdSenha;

	@FXML
	private Button btnEntrar;

	@FXML
	private FlowPane flwConcluido;

	@FXML
	private Label lblConcluido;

	@FXML
	private FlowPane flwUsuario;

	@FXML
	private TextField tfdUsuario;

	@FXML
	private Button btnBuscar;

	private SuperUsuario superUsuario;

	private CriptografiaUtil criptografiaUtil;

	@Override
	public void update(Tela tela, Entidade entidade) {

		if (tela == Tela.RESETAR_SENHA) {
			limparCampos();
			flwConcluido.setVisible(false);
			flwLogin.setVisible(true);
			flwUsuario.setVisible(true);
		}

	}

	@Override
	protected void init() {
		criptografiaUtil = CriptografiaUtil.getInstance();
	}

	@Override
	public void action(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnBuscar) {
			try {
				if (superUsuario != null) {
					if (!tfdUsuario.getText().trim().isEmpty()) {
						Funcionario funcionario = notificacao
								.selecionar(fachada.searchAllFuncionario(tfdUsuario.getText().trim()));

						if (funcionario != null) {
							funcionario.setSenha(criptografiaUtil.descriptografar(superUsuario.getSenha_padrao()));
							fachada.saveFuncionario(funcionario);
							flwConcluido.setVisible(true);
							flwLogin.setVisible(false);
							flwUsuario.setVisible(false);
							limparCampos();
							lblAndamento.setText("Senha Resetada Com Sucesso");
							notificacao.mensagemSucesso("Senha Resetada Com Sucesso");
							lblConcluido.setText("Concluído");
						}
					} else
						notificacao.mensagemAtencao();
				} else
					notificacao.mensagemErro("Resetar Senha", "Autorização de Super Usuário Nescessaria!!!");

			} catch (BusinessException e) {
				notificacao.mensagemErro("Resetar Senha", e.getMessage());
				e.printStackTrace();
			}
		} else if (obj == btnEntrar) {
			try {
				Usuario usuario = fachada.searchUser(tfdLogin.getText().trim(), tfdSenha.getText().trim());

				if (usuario instanceof SuperUsuario) {
					superUsuario = (SuperUsuario) usuario;
					lblAndamento.setText("Permissão Concedida");
					lblConcluido.setText("Permissão Concedida");
					flwLogin.setVisible(false);
					flwConcluido.setVisible(true);

				} else if (superUsuario == null)
					notificacao.mensagemErro("Buscar Super Usuário", "Login ou Senha Errados");

			} catch (BusinessException e) {
				notificacao.mensagemErro("Buscar Super Usuário", e.getMessage());
				e.printStackTrace();
			}
		}

	}

	@Override
	protected void limparCampos() {

		lblAndamento.setText("Permissão de um Super Usuário Nescessaria!!!");

		tfdLogin.setText("");
		tfdSenha.setText("");

		superUsuario = null;
	}

}
