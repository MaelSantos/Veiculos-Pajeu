package br.com.VeiculosPajeu.Entidade.Enum;

public enum TipoFuncionario {

	ATENDENTE("Atendente"), ADMINISTRADOR("Administrador");
	
	private String tipo;

	private TipoFuncionario(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return tipo;
	}
	
}
