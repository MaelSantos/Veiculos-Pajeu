package br.com.VeiculosPajeu.Entidade.Enum;

public enum TipoCategoria {

	LUXO("Luxo"), NORMAL("Normal"), ECONOMICA("Econômica");
	
	private String tipo;

	private TipoCategoria(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return tipo;
	}
}
