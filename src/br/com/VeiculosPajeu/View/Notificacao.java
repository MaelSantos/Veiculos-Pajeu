package br.com.VeiculosPajeu.View;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import br.com.VeiculosPajeu.Controle.ControleAparencia;
import br.com.VeiculosPajeu.Controle.ControleDialogo;
import br.com.VeiculosPajeu.Controle.ControleSelecionar;
import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Notificacao {

	private static Notificacao instance;

	private Alert alert;
	private Dialog<ButtonType> dialog;
	private Dialog<ButtonType> escolha;

	private ControleSelecionar controleSelecionar;
	private ControleDialogo controleDialogo;

	private Notificacao() {
	}

	public static synchronized Notificacao getInstance() {
		if (instance == null)
			instance = new Notificacao();
		return instance;
	}

	private void init() {

		alert = new Alert(AlertType.INFORMATION);

		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("");

		((Stage) alert.getDialogPane().getScene().getWindow()).getIcons()
				.add(new Image(getClass().getClassLoader().getResourceAsStream("Icon.png")));

		alert.initModality(Modality.APPLICATION_MODAL);

		setColor(ControleAparencia.getColor());

		try {

			dialog = new Dialog<>();
			dialog.setResizable(true);
			dialog.initModality(Modality.APPLICATION_MODAL);
			dialog.setWidth(700);
			dialog.setHeight(700);
			dialog.getDialogPane().setContent(
					FXMLLoader.load(getClass().getClassLoader().getResource("br/com/VeiculosPajeu/View/Dialogo.fxml")));
			dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);

			escolha = new Dialog<>();
			escolha.setResizable(true);
			escolha.initModality(Modality.APPLICATION_MODAL);
			escolha.setWidth(700);
			escolha.setHeight(700);
			escolha.getDialogPane().setContent(
					FXMLLoader.load(getClass().getClassLoader().getResource("br/com/VeiculosPajeu/View/Escolha.fxml")));
			escolha.getDialogPane().getButtonTypes().addAll(ButtonType.OK);

			controleSelecionar = ControleSelecionar.controleSelecionar;
			controleDialogo = ControleDialogo.controleDialogo;

			dialog.setOnCloseRequest(e -> {

				System.out.println("Fechado");
				if (!controleDialogo.getTelas().isEmpty()) {
					Tela telaAnterior = controleDialogo.getTelaAnterior();
					System.out.println("Tela Anterior = " + telaAnterior);
					System.out.println("Tela Controle = " + controleDialogo.getTela());
					if (telaAnterior != null) {
						Tela temp = controleDialogo.getTelaPosicao(controleDialogo.getTelas().size() - 2);

						if (temp != null) {
							System.out.println("Remove " + telaAnterior);
							controleDialogo.getTelas().remove(telaAnterior);
							System.out.println("Show " + temp);
							showDialogo(temp);
						}

					}
//					else if(controleDialogo.getTela() == telaAnterior)
//					{
//						System.out.println("Remove "+telaAnterior);
//						controleDialogo.getTelas().remove(telaAnterior);
//					}
					System.out.println("Lista: " + controleDialogo.getTelas());
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void setColor(String color) {
//		alert.getDialogPane().setStyle(".dialog-pane {\r\n" + 
//				"	-fx-background-color: #800000;\r\n" + 
//				"	-fx-text-fill: white;\r\n" + 
//				"}\r\n" + 
//				".dialog-pane > .content.label {\r\n" + 
//				"   -fx-background-color: #800000;\r\n" + 
//				"   -fx-text-fill:white;\r\n" + 
//				"}\r\n" + 
//				".dialog-pane:header .header-panel {\r\n" + 
//				"  -fx-background-color: #800000;\r\n" + 
//				"}");
	}

	public void showDialogo(Tela tela) {
		if (dialog == null) {
			init();
		}
		if (dialog.isShowing())
			dialog.close();

		controleDialogo.updateTela(tela);

		dialog.setWidth(700);
		dialog.setHeight(700);

		dialog.show();
//		Optional<ButtonType> optional = dialog.showAndWait();		
//		if(optional.get() == ButtonType.CLOSE)
//		{

//		}
	}

	@SuppressWarnings("unchecked")
	public <T extends Entidade> T selecionar(List<T> list) {

		if (escolha == null)
			init();

		controleSelecionar.carregarLista(list);
		escolha.setWidth(900);
		escolha.setHeight(600);
		Optional<ButtonType> optional = escolha.showAndWait();

		if (optional.get() == ButtonType.OK)
			return (T) controleSelecionar.getTblBusca().getSelectionModel().getSelectedItem();

		return null;
	}

	public void mensagemPersonalisado(AlertType alertType, String titulo, String cabecalho, String conteudo) {
		if (alert == null)
			init();

		alert.setAlertType(alertType);
		alert.setTitle(titulo);
		alert.setHeaderText(cabecalho);
		alert.setContentText(conteudo);
		alert.show();

	}

	public void mensagemErro(String causa, String conteudo) {
		if (alert == null)
			init();

		alert.setAlertType(AlertType.ERROR);
		alert.setTitle("Erro!!!");
		alert.setHeaderText("Erro ao " + causa);
		alert.setContentText(conteudo);
		alert.show();
	}

	public void mensagemAtencao() {
		if (alert == null)
			init();

		alert.setAlertType(AlertType.WARNING);
		alert.setTitle("Ação Necessaria!!!");
		alert.setHeaderText("");
		alert.setContentText("Informe Algum Dado Para Continuar");
		alert.show();
	}

	public void mensagemSucesso(String conteudo) {
		if (alert == null)
			init();

		alert.setAlertType(AlertType.INFORMATION);
		alert.setTitle("Concluído");
		alert.setHeaderText("");
		alert.setContentText(conteudo);
		alert.show();
	}

	public Boolean showConfirmacao(String titulo, String cabecalho, String conteudo) {

		if (alert == null)
			init();

		alert.setAlertType(AlertType.CONFIRMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(cabecalho);
		alert.setContentText(conteudo);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			return true;
		}
		return false;
	}
}
