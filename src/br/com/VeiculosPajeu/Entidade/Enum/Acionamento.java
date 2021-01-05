package br.com.VeiculosPajeu.Entidade.Enum;

public enum Acionamento {

	MANUAL("Manual"), HIDRAULICO("Hidráulico");
	
	private String tipo;
	private Acionamento(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return tipo;
	}
	
}
