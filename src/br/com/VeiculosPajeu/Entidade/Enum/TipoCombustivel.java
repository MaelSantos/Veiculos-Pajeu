package br.com.VeiculosPajeu.Entidade.Enum;

public enum TipoCombustivel {

	GASOLINA("Gasolina"), ETANOL("Etanol"), DIESEL("Diesel"), BIOCOMBUSTIVEL("Biocombustível");
	
	String tipo;
	private TipoCombustivel(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return tipo;
	}
}
