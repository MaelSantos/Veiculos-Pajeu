package br.com.VeiculosPajeu.Controle;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import br.com.VeiculosPajeu.App.App;
import br.com.VeiculosPajeu.Fachada.Fachada;
import br.com.VeiculosPajeu.Util.Ouvinte;
import br.com.VeiculosPajeu.View.Notificacao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public abstract class Controle implements Initializable, Ouvinte {

	@FXML
	protected AnchorPane pane;
	
	protected Notificacao notificacao;
	protected Fachada fachada;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		App.addOuvinte(this);
		notificacao = Notificacao.getInstance();
		fachada = Fachada.getInstance();
		
		setColor(pane, ControleAparencia.getColor());
		
		init();
		
	}

	protected String getDate(LocalDate date) {

		String data = null;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		data = formatter.format(date);
		
		return data;
	}
	
	@Override
	public void setColor(String color) {
		pane.setStyle("-fx-background-color:" + color + ";");
	}
	
	protected void setColor(AnchorPane pane, String color) {
		pane.setStyle("-fx-background-color:" + color + ";");
	}

	protected abstract void init();

	public abstract void action(ActionEvent event);

	protected abstract void limparCampos();
}
