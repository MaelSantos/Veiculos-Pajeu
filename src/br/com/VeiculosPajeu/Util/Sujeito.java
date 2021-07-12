package br.com.VeiculosPajeu.Util;

import java.util.ArrayList;
import java.util.Iterator;
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
		Iterator<Ouvinte> iterator = ouvintes.iterator();
		while(iterator.hasNext()) {
			Ouvinte ouvinte = iterator.next();
			ouvinte.update(tela, entidade);
		}
	}

	public void updateColor(String color) {
		Iterator<Ouvinte> iterator = ouvintes.iterator();
		while(iterator.hasNext()) {
			Ouvinte ouvinte = iterator.next();
			ouvinte.setColor(color);
		}
	}

}
