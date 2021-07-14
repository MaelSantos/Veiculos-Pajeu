package br.com.VeiculosPajeu.Controle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.VeiculosPajeu.App.App;
import br.com.VeiculosPajeu.Dao.DaoXml;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import br.com.VeiculosPajeu.View.Notificacao;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ControleCarregar extends ControleAdapter {

	@FXML
	private AnchorPane pane;

	@FXML
	private ProgressBar pgbCarregar;

	@FXML
	private Label lblInformacao;

	private Service<Object> servico;
	private String texto;
	double porcentagem = 0.0;

	public Pane carregarArquivo(Tela caminho) throws IOException {
		Pane pane = FXMLLoader.load(
				getClass().getClassLoader().getResource("br/com/VeiculosPajeu/View/" + caminho.getCaminho() + ".fxml"));
		this.texto = "Tela - " + caminho;

		App.addTela(pane, caminho);

		return pane;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		pane.setStyle("-fx-background-color:" + DaoXml.getInstance().buscarAparencia() + ";");

		servico = new Service<Object>() {
			@Override
			protected Task<Object> createTask() {
				return new Task<Object>() {

					int contador = 0;

					public void updateData() {
						updateMessage(texto);
						porcentagem += 100 / 28; // porcentagem total dividido por quantidade de telas
						updateProgress(porcentagem, 100);
						System.out.println(contador++);
						System.out.println(texto);
					}

					@Override
					protected Void call() throws Exception {
						// peso por tela - Double sumPerView = 1.0/ Tela.values().length;
						try {

							App.aparencia = carregarArquivo(Tela.APARENCIA);
							updateData();

							App.login = carregarArquivo(Tela.LOGIN);
							updateData();
							App.menu = carregarArquivo(Tela.MENU);
							updateData();
							App.atrasados = carregarArquivo(Tela.ATRASADOS);
							updateData();
							App.disponiveis = carregarArquivo(Tela.DISPONIVEIS);
							updateData();

							App.configuracao = carregarArquivo(Tela.CONFIGURACAO);
							updateData();
							App.cadastroCliente = carregarArquivo(Tela.CADASTRO_CLIENTE);
							updateData();
							App.cadastroFilial = carregarArquivo(Tela.CADASTRO_FILIAL);
							updateData();
							App.cadastroVeiculo = carregarArquivo(Tela.CADASTRO_VEICULO);
							updateData();
							App.cadastroUsuario = carregarArquivo(Tela.CADASTRO_USUARIO);
							updateData();

							App.busca = carregarArquivo(Tela.BUSCAR);
							updateData();
							App.detalhes = carregarArquivo(Tela.DETALHES);
							updateData();
							App.perfil = carregarArquivo(Tela.PERFIL);
							updateData();

							App.resetarSenha = carregarArquivo(Tela.RESETAR_SENHA);
							updateData();

							App.locacao = carregarArquivo(Tela.LOCACAO);
							updateData();
							App.reserva = carregarArquivo(Tela.RESERVA);
							updateData();
							App.retirarReserva = carregarArquivo(Tela.RETIRAR_RESERVA);
							updateData();
							App.devolucao = carregarArquivo(Tela.DEVOLUCAO);
							updateData();
							App.manutencao = carregarArquivo(Tela.MANUTENCAO);
							updateData();

							App.cadastroCategoria = carregarArquivo(Tela.CADASTRO_CATEGORIA);
							updateData();
							App.editarConfiguracao = carregarArquivo(Tela.EDITAR_CONFIGURACAO);
							updateData();

							App.informacoes = carregarArquivo(Tela.INFORMACOES);
							updateData();
							App.ajuda = carregarArquivo(Tela.AJUDA);
							updateData();
							App.relatorio = carregarArquivo(Tela.RELATORIO);
							updateData();
							App.estatistica = carregarArquivo(Tela.ESTATISTICA);
							updateData();
							App.historico = carregarArquivo(Tela.HISTORICO);
							updateData();
							App.gerenciarFinanceiro = carregarArquivo(Tela.GERENCIAR_FINANCEIRO);
							updateData();
							App.financeiro = carregarArquivo(Tela.FINANCEIRO);
							updateData();
							App.pagamento = carregarArquivo(Tela.PAGAMENTO);
							updateData();

							App.loginScene = new Scene(App.login);
							App.menuScene = new Scene(App.menu);

							return null;
						} catch (Exception e) {
							e.printStackTrace();
							Notificacao.getInstance().mensagemErro("", "Erro ao carregar tela!!!, Contate o ADM");
							throw new Exception();
						}
					}

					@Override
					protected void succeeded() {
						super.succeeded();
						App.changeStage(Tela.LOGIN);
						ControleAtualizacao.getInstace();
					}

					@Override
					protected void scheduled() {
						updateMessage("Informações do Banco de Dados...");
						super.scheduled();
					}
				};

			}
		};

		// fazendo o bind (ligando) nas proprety
		lblInformacao.textProperty().bind(servico.messageProperty());
		pgbCarregar.progressProperty().bind(servico.progressProperty());
		// precisa inicializar o Service
		servico.start();
	}

}
