package br.com.VeiculosPajeu.Controle;

import java.awt.Desktop;
import java.net.URI;

import br.com.VeiculosPajeu.App.App;
import br.com.VeiculosPajeu.Entidade.Cliente;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Funcionario;
import br.com.VeiculosPajeu.Entidade.SuperUsuario;
import br.com.VeiculosPajeu.Entidade.Usuario;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import br.com.VeiculosPajeu.View.Notificacao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ControleMenu extends ControleAdapter {

	@FXML
    private Menu mnuHome;

    @FXML
    private MenuItem mniAtrasados;

    @FXML
    private MenuItem mniDisponiveis;

    @FXML
    private Menu mnuVeiculos;

    @FXML
    private MenuItem mniAlugar;

    @FXML
    private MenuItem mniReservar;

    @FXML
    private MenuItem mniRetirarReserva;

    @FXML
    private MenuItem mniDevolucao;

    @FXML
    private MenuItem mniManutencao;

    @FXML
    private Menu mnuCadastro;

    @FXML
    private MenuItem mniCadastrarCliente;

    @FXML
    private MenuItem mniCadastrarVeiculo;

    @FXML
    private MenuItem mniCadastrarFilial;

    @FXML
    private MenuItem mniCadastroCategoria;

    @FXML
    private MenuItem mniCadastrarUsuario;

    @FXML
    private Menu mnuBusca;

    @FXML
    private MenuItem mniBuscar;

    @FXML
    private Menu mnuFinanceiro;

    @FXML
    private MenuItem mniFinanceiro;

    @FXML
    private MenuItem mniGerenciarFinanceiro;

    @FXML
    private Menu mnuEstatistica;

    @FXML
    private MenuItem mniEstatistica;

    @FXML
    private MenuItem mniRelatorio;

    @FXML
    private Menu mnuHistorico;

    @FXML
    private MenuItem mniHistorio;

    @FXML
    private Menu mnuAjuda;

    @FXML
    private MenuItem mniAjuda;

    @FXML
    private MenuItem mniInformacacoes;

    @FXML
    private MenuItem mniContatarADM;

    @FXML
    private Menu mnuConfiguracoes;

    @FXML
    private MenuItem mniConfiguracoes;

    @FXML
    private MenuItem mniPerfil;

    @FXML
    private MenuItem mniDeslocar;

    @FXML
    private MenuItem mniEncerrar;

    @FXML
    private AnchorPane paneEdit;

	@Override
	public void action(ActionEvent event) {

		Object obj = event.getSource();

		// inicial
		if (obj == mniAtrasados)
			App.notificarOuvintes(Tela.ATRASADOS);
		if (obj == mniDisponiveis)
			App.notificarOuvintes(Tela.DISPONIVEIS);

		// cadastro
		else if (obj == mniCadastrarCliente)
			App.notificarOuvintes(Tela.CADASTRO_CLIENTE);
		else if (obj == mniCadastrarUsuario)
			App.notificarOuvintes(Tela.CADASTRO_USUARIO);
		else if (obj == mniCadastrarVeiculo)
			App.notificarOuvintes(Tela.CADASTRO_VEICULO);
		else if (obj == mniCadastrarFilial)
			App.notificarOuvintes(Tela.CADASTRO_FILIAL);

		else if (obj == mniAlugar)
			App.notificarOuvintes(Tela.LOCACAO);
		else if (obj == mniReservar)
			App.notificarOuvintes(Tela.RESERVA);
		else if (obj == mniRetirarReserva)
			App.notificarOuvintes(Tela.RETIRAR_RESERVA);
		else if (obj == mniDevolucao)
			App.notificarOuvintes(Tela.DEVOLUCAO);
		else if (obj == mniManutencao)
			App.notificarOuvintes(Tela.MANUTENCAO);

		else if (obj == mniCadastroCategoria)
			App.notificarOuvintes(Tela.CADASTRO_CATEGORIA);

		// busca
		else if (obj == mniBuscar)
			App.notificarOuvintes(Tela.BUSCAR);

		// util
		else if (obj == mniConfiguracoes)
			App.notificarOuvintes(Tela.CONFIGURACAO);
		else if (obj == mniPerfil)
			App.notificarOuvintes(Tela.PERFIL);
		else if (obj == mniRelatorio)
			App.notificarOuvintes(Tela.RELATORIO);
		else if (obj == mniEstatistica)
			App.notificarOuvintes(Tela.ESTATISTICA);
		else if (obj == mniHistorio)
			App.notificarOuvintes(Tela.HISTORICO);
		else if (obj == mniFinanceiro)
			App.notificarOuvintes(Tela.FINANCEIRO);
		else if (obj == mniGerenciarFinanceiro)
			App.notificarOuvintes(Tela.GERENCIAR_FINANCEIRO);
		else if (obj == mniDeslocar) {
			App.changeStage(Tela.LOGIN);
			App.notificarOuvintes(Tela.DISPONIVEIS);
		} else if (obj == mniEncerrar) {
			if (Notificacao.getInstance().showConfirmacao("Sair?", "", "Deseja Realmente Sair?"))
				System.exit(0);
		} else if (obj == mniInformacacoes)
			App.notificarOuvintes(Tela.INFORMACOES);
		else if (obj == mniAjuda)
			App.notificarOuvintes(Tela.AJUDA);
		else if (obj == mniContatarADM) {
			Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
			if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
				try {
					desktop.browse(new URI("https://MaelSantos.github.io/"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	@SuppressWarnings("static-access")
	public void updateTela(Pane paneNovo) {
		paneEdit.setBottomAnchor(paneNovo, 0.0);
		paneEdit.setTopAnchor(paneNovo, 0.0);
		paneEdit.setLeftAnchor(paneNovo, 0.0);
		paneEdit.setRightAnchor(paneNovo, 0.0);
		paneEdit.getChildren().setAll(paneNovo);
	}

	@Override
	public void update(Tela tela, Entidade entidade) {

		if (tela != null) {
			if (tela != Tela.MENU && tela != Tela.LOGIN && tela != Tela.RESETAR_SENHA) {
				updateTela(App.changePane(tela));
			} else if (tela == Tela.MENU) {
				if (entidade instanceof Usuario) {

					if (entidade instanceof SuperUsuario) {						
						acessoTotal();
					} else if (entidade instanceof Funcionario) {
						
						Funcionario funcionario = (Funcionario) entidade;
						
						switch (funcionario.getTipoFuncionario()) {
						case ADMINISTRADOR:							
							acessoTotal();
							break;
						case ATENDENTE:
							acessoTotal();
							mnuHistorico.setVisible(false);
							mniCadastrarUsuario.setVisible(false);
							mniCadastrarFilial.setVisible(false);
							mniCadastrarVeiculo.setVisible(false);
							mniCadastroCategoria.setVisible(false);
							break;
						default:
							break;
						}						
					} else if (entidade instanceof Cliente) {
						
						mnuCadastro.setVisible(false);
						mnuFinanceiro.setVisible(false);
						mnuHistorico.setVisible(false);
						mnuEstatistica.setVisible(false);
						
						mniConfiguracoes.setVisible(false);
						mniAtrasados.setVisible(false);
						mniRetirarReserva.setVisible(false);
						mniAlugar.setVisible(false);
						mniDevolucao.setVisible(false);
						mniManutencao.setVisible(false);
						mniConfiguracoes.setVisible(false);
					}
				}

			}

		}
	}

	
	private void acessoTotal()
	{
		mniCadastrarVeiculo.setVisible(true);
		mniCadastrarFilial.setVisible(true);
		mniCadastroCategoria.setVisible(true);
		mniCadastrarUsuario.setVisible(true);
		mniHistorio.setVisible(true);						
		mnuCadastro.setVisible(true);
		mniConfiguracoes.setVisible(true);
		mniAtrasados.setVisible(true);
		mniRetirarReserva.setVisible(true);
		mniAlugar.setVisible(true);
		mniDevolucao.setVisible(true);
		mniManutencao.setVisible(true);						
		mnuFinanceiro.setVisible(true);
		mniConfiguracoes.setVisible(true);
		mnuHistorico.setVisible(true);
		mnuEstatistica.setVisible(true);						
		mniCadastrarFilial.setVisible(true);
		mniCadastroCategoria.setVisible(true);
		mniCadastrarUsuario.setVisible(true);
		mniHistorio.setVisible(true);
	}
			
	
}
