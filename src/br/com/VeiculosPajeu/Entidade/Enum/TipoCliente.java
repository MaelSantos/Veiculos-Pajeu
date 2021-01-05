package br.com.VeiculosPajeu.Entidade.Enum;

public enum TipoCliente {

	FISICA("Pessoa Física"), JURIDICO("Pessoa Juridica");
	
	private String tipo;

	private TipoCliente(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return tipo;
	}
	
}
