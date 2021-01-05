package br.com.VeiculosPajeu.Controle;

import java.awt.Desktop;
import java.net.URI;

import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

public class ControleInformacoes extends Controle {
	
	@FXML
	private Hyperlink emailM;

	@Override
	public void update(Tela tela, Entidade entidade) {
		// TODO Stub de m�todo gerado automaticamente

	}

	@Override
	protected void init() {
	}

	@Override
	public void action(ActionEvent event) {
		
		if(event.getSource() == emailM) {
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

	@Override
	protected void limparCampos() {
		// TODO Stub de m�todo gerado automaticamente

	}

}
