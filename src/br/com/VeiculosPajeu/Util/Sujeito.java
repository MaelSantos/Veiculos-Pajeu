package br.com.VeiculosPajeu.Util;

import java.util.ArrayList;
import java.util.List;

import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;

public class Sujeito {

	private List<Ouvinte> ouvintes;

	public Sujeito() {
		ouvintes = new ArrayList<>();
	}

	public void addOuvinte(Ouvinte ouvinte) {
		ouvintes.add(ouvinte);
	}

	public void notificarOuvintes(Tela tela, Entidade entidade) {
		for (Ouvinte ouvinte : ouvintes)
			ouvinte.update(tela, entidade);
	}

	public void updateColor(String color) {
		for (Ouvinte ouvinte : ouvintes)
			ouvinte.setColor(color);
	}

}
