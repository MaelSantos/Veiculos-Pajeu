package br.com.VeiculosPajeu.Controle;

import java.io.File;
import java.io.IOException;

import br.com.VeiculosPajeu.App.App;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Funcionario;
import br.com.VeiculosPajeu.Entidade.SuperUsuario;
import br.com.VeiculosPajeu.Entidade.Usuario;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import br.com.VeiculosPajeu.Entidade.Enum.TipoFuncionario;
import br.com.VeiculosPajeu.Exception.BusinessException;
import br.com.VeiculosPajeu.Util.Backup;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;

public class ControleConfiguracoes extends Controle {

	@FXML
    private Button btnAlterarConf;

    @FXML
    private Button btnAparencia;

    @FXML
    private Button btnBackup;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnResetarSenha;
	
	private DirectoryChooser chooser;
	
	@Override
	public void update(Tela tela, Entidade entidade) {

		if (tela == Tela.MENU)
			if (entidade instanceof Usuario) {

				if (entidade instanceof SuperUsuario) {
					btnAlterarConf.setVisible(true);
					btnResetarSenha.setVisible(true);
				} else if (entidade instanceof Funcionario) {
					
					if(((Funcionario) entidade).getTipoFuncionario() == TipoFuncionario.ATENDENTE)
					{
						btnAlterarConf.setVisible(false);
						btnResetarSenha.setVisible(true);						
					}
					else
					{
						btnAlterarConf.setVisible(true);
						btnResetarSenha.setVisible(true);
					}
				}
				else {
					btnAlterarConf.setVisible(false);
					btnResetarSenha.setVisible(false);
				}

			}
	}

	@Override
	protected void init() {
		chooser = new DirectoryChooser();
		chooser.setInitialDirectory(new File(System.getProperty("user.home")));
	}

	@Override
	public void action(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnBackup) {

			try {
				File file = chooser.showDialog(App.stage);
				
				if(file != null)
				{
					Backup.backup(file.getAbsolutePath()+"/backup");					
					notificacao.mensagemSucesso("Backup Realizado Com Sucesso");
				}
			} catch (IOException e) {
				e.printStackTrace();
				notificacao.mensagemErro("Erro ao Fazer Backup", e.getMessage());
			}
		}
		else if(obj == btnAtualizar)
		{
			try {
				fachada.verificarAtrasadosFinanceiro();
				fachada.verificarAtrasadosReserva();
				fachada.verificarManutencaoVeiculo();
				
				notificacao.mensagemSucesso("Sistema Atualizado");
			} catch (BusinessException e) {
				notificacao.mensagemErro("Atualizar Sistema", e.getMessage());
			}
		}
		else if (obj == btnResetarSenha) {
			notificacao.showDialogo(Tela.RESETAR_SENHA);
			App.notificarOuvintes(Tela.RESETAR_SENHA);
		}
		else if (obj == btnAlterarConf) {
			App.notificarOuvintes(Tela.EDITAR_CONFIGURACAO);
		}
		else if(obj == btnAparencia) {
			App.notificarOuvintes(Tela.APARENCIA);
		}
	}

	@Override
	protected void limparCampos() {
		// TODO Stub de m�todo gerado automaticamente

	}

}
