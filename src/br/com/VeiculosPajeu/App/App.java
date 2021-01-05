package br.com.VeiculosPajeu.App;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import br.com.VeiculosPajeu.Util.Ouvinte;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

	public static Stage stage;
	public static Scene loginScene, menuScene;
	private Pane carregar;
	public static Pane login, menu, atrasados, disponiveis, configuracao, perfil, ajuda;
	public static Pane cadastroCliente, cadastroFilial, cadastroVeiculo, cadastroUsuario;
	public static Pane busca, detalhes, resetarSenha, gerenciarFinanceiro,financeiro, pagamento;
	public static Pane locacao, reserva, retirarReserva, devolucao, cadastroCategoria, editarConfiguracao;
	public static Pane informacoes, aparencia, relatorio, estatistica, historico, manutencao;

	private static List<Ouvinte> ouvintes = new ArrayList<>();
	private static Map<Tela, Pane> telas = new HashMap<>();

	@Override
	public void start(Stage stage) {

		App.stage = stage;

		try {
			carregar = FXMLLoader
					.load(getClass().getClassLoader().getResource("br/com/VeiculosPajeu/View/Carregar.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		stage.setTitle("VP - Veículos Pajeú");
		stage.setScene(new Scene(carregar));
		stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("Icon.png")));
		stage.centerOnScreen();
		stage.show();
	}

	@Override
	public void stop() throws Exception {

		super.stop();
		System.exit(0);

	}

	public static void changeStage(Tela tela) {
		switch (tela) {
		case LOGIN:
			stage.setScene(loginScene);
			stage.centerOnScreen();
			break;
		case MENU:
			stage.setScene(menuScene);
			stage.centerOnScreen();
			break;
		default:
			break;
		}
	}

	public static Pane changePane(Tela tela) {
		switch (tela) {

		case EDITAR_USUARIO:
			return cadastroUsuario;
		case EDITAR_CONFIGURACAO:
			return editarConfiguracao;
		case EDITAR_FILIAL:
			return cadastroFilial;
		case EDITAR_CATEGORIA:
			return cadastroCategoria;
		case EDITAR_CLIENTE:
			return cadastroCliente;
		case EDITAR_VEICULO:
			return cadastroVeiculo;
		case EDITAR_LOCACAO:
			return locacao;
		case EDITAR_RESERVA:
			return reserva;

		default:
			return telas.get(tela);
		}
	}

	public static void addTela(Pane pane, Tela tela) {
		telas.put(tela, pane);
	}

	public static void addOuvinte(Ouvinte ouvinte) {
		ouvintes.add(ouvinte);
	}

	public static void notificarOuvintes(Tela tela, Entidade entidade) {
		for (Ouvinte ouvinte : ouvintes)
			ouvinte.update(tela, entidade);
	}
	
	public static void notificarOuvintes(Tela tela) {
		notificarOuvintes(tela, null);
	}

	public static void notificarOuvintes(Entidade entidade) {
		for (Ouvinte ouvinte : ouvintes)
			ouvinte.update(null, entidade);
	}
	
	public static void updateColor(String color) {
		for (Ouvinte ouvinte : ouvintes)
			ouvinte.setColor(color);
	}


	public static void main(String[] args) {
		launch(args);
	}
}
