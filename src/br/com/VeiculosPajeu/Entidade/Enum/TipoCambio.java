package br.com.VeiculosPajeu.Entidade.Enum;

public enum TipoCambio {

	AUTOMATICO("Automático"), MANUAL("Manual");
	
	private String tipo;
	private TipoCambio(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return tipo;
	}
	
}
