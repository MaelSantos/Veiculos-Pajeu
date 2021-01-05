package br.com.VeiculosPajeu.Entidade.Enum;

public enum EstadoFinanceiro {
	
	PENDENTE("Pendente", "#FF8C00"), PAGO("Pago", "#006400"), ATRASADO("Atrasado", "#8B0000"),
	CANCELADO("Cancelado", "#8B0000");
	
	private String estado, cor;

	private EstadoFinanceiro(String estado, String cor) {
		this.estado = estado;
		this.cor = cor;
	}
	
	public String getCor() {
		return cor;
	}
	
	@Override
	public String toString() {
		return estado;
	}
	
}
