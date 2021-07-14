package br.com.VeiculosPajeu.Controle;

import br.com.VeiculosPajeu.App.App;
import br.com.VeiculosPajeu.Entidade.Cliente;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Funcionario;
import br.com.VeiculosPajeu.Entidade.SuperUsuario;
import br.com.VeiculosPajeu.Entidade.Usuario;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

public class ControlePerfil extends ControleAdapter {

	@FXML
    private Button btnEditarPerfil;

    @FXML
    private Label lblNome;

    @FXML
    private Text txtLogin;

    @FXML
    private FlowPane flwCargo;

    @FXML
    private Text txtCargo;

    @FXML
    private FlowPane flwTipoFuncionario;

    @FXML
    private Text txtTipoFuncionario;

	private Funcionario funcionario;
	private SuperUsuario superUsuario;
	private Cliente cliente;

	@Override
	public void action(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnEditarPerfil) {
			if (funcionario != null)
				App.notificarOuvintes(Tela.EDITAR_USUARIO, funcionario);
			else if (superUsuario != null)
				App.notificarOuvintes(Tela.EDITAR_USUARIO, superUsuario);
			else if(cliente != null)
				App.notificarOuvintes(Tela.EDITAR_CLIENTE, cliente);
		}

	}

	@Override
	protected void limparCampos() {
		superUsuario = null;
		funcionario = null;
		cliente = null;
	}

	@Override
	public void update(Tela tela, Entidade entidade) {

		if (tela == Tela.MENU) {

			if (entidade instanceof SuperUsuario) {
				superUsuario = (SuperUsuario) entidade;
				flwCargo.setVisible(false);
				flwTipoFuncionario.setVisible(false);
				txtLogin.setText(superUsuario.getLogin());
				lblNome.setText(superUsuario.getNome());

			} else if (entidade instanceof Funcionario) {
				funcionario = (Funcionario) entidade;
				txtCargo.setVisible(true);
				flwCargo.setVisible(true);
				flwTipoFuncionario.setVisible(true);
				txtCargo.setText(funcionario.getCargo());
				txtLogin.setText(funcionario.getLogin());
				txtTipoFuncionario.setText(funcionario.getTipoFuncionario()+"");
				lblNome.setText(funcionario.getNome());
			}
			else if (entidade instanceof Cliente) {
				cliente = (Cliente) entidade;
				txtCargo.setVisible(false);
				flwCargo.setVisible(false);
				flwTipoFuncionario.setVisible(false);
				txtLogin.setText(cliente.getLogin());
				lblNome.setText(cliente.getNome());
			}

		}
		if (entidade instanceof Usuario) {
			Usuario usuario = (Usuario) entidade;

			if (usuario instanceof Funcionario) {
				if (funcionario != null)
					if (usuario.getId() == funcionario.getId()) {
						txtCargo.setText(funcionario.getCargo());
						txtLogin.setText(funcionario.getLogin());
						lblNome.setText(funcionario.getNome());
					}
			}
			if (usuario instanceof SuperUsuario) {
				if (superUsuario != null)
					if (usuario.getId() == superUsuario.getId()) {
						txtLogin.setText(superUsuario.getLogin());
						lblNome.setText(superUsuario.getNome());
					}
			}
			else
			{
				if (cliente != null)
					if (usuario.getId() == cliente.getId()) {
						txtLogin.setText(cliente.getLogin());
						lblNome.setText(cliente.getNome());
					}
			}

		}

	}

}
