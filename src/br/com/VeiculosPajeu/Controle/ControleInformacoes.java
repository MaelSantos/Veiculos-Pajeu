package br.com.VeiculosPajeu.Controle;

import java.awt.Desktop;
import java.net.URI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

public class ControleInformacoes extends ControleAdapter {

	@FXML
	private Hyperlink emailM;

	@Override
	public void action(ActionEvent event) {

		if (event.getSource() == emailM) {
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

}
