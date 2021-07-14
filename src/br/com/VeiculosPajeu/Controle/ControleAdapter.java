package br.com.VeiculosPajeu.Controle;

import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;
import javafx.event.ActionEvent;

public abstract class ControleAdapter extends Controle {

	@Override
	public void update(Tela tela, Entidade entidade) {
	}

	@Override
	protected void init() {
	}

	@Override
	public void action(ActionEvent event) {
	}

	@Override
	protected void limparCampos() {
	}

}
