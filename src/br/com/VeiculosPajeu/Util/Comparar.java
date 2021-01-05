package br.com.VeiculosPajeu.Util;

import java.util.Comparator;

import br.com.VeiculosPajeu.Entidade.Categoria;
import br.com.VeiculosPajeu.Entidade.Entidade;

public class Comparar implements Comparator<Entidade>{

	@Override
	public int compare(Entidade arg0, Entidade arg1) {
		
		if (arg1 instanceof Categoria) {
			Categoria categoria1 = (Categoria) arg0;
			Categoria categoria2 = (Categoria) arg1;
			
			int tipo;
			int tamanho;
			float valor1;
			float valor2;
			
			tipo = categoria1.getTipo().compareTo(categoria2.getTipo());
			tamanho = categoria1.getTamanho().compareTo(categoria2.getTamanho());
			valor1 = categoria1.getValor();
			valor2 = categoria2.getValor();
				
			if(tipo == 0 && tamanho != 0)
				return tamanho*(-1);
			if(tipo == 0 && tamanho == 0)
			{
				if(valor1 == valor2)
					return 0;
				else if(valor1 < valor2)
					return -1;
				else if(valor1 > valor2)
					return 1;
			}
			else
				return tipo*(-1);
		}
		
		return 0;
	}	
}
