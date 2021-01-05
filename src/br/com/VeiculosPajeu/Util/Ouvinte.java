package br.com.VeiculosPajeu.Util;

import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Enum.Tela;

public interface Ouvinte {
	public void update(Tela tela, Entidade entidade);
	public void setColor(String color);
}
