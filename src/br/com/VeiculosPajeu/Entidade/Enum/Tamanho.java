package br.com.VeiculosPajeu.Entidade.Enum;

public enum Tamanho {

	GRANDE("Grande"), MEDIO("Médio"), PEQUENO("Pequeno");

	private String tipo;

	private Tamanho(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return tipo;
	}
}
