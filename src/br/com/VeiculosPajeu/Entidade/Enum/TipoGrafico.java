package br.com.VeiculosPajeu.Entidade.Enum;

public enum TipoGrafico {

	LINHA("Linha"), PIZZA("Pizza"), BARRA("Barra");

	private String tipo;

	private TipoGrafico(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return tipo;
	}
}
