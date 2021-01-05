package br.com.VeiculosPajeu.Entidade.Enum;

public enum TipoHistorico {

	INSERT("Insert","INSERT"), UPDATE("Update","UPDATE"), DELETE("Delete","DELETE"), TODOS("Todos","");
	
	private String tipo, valor;

	private TipoHistorico(String tipo, String valor) {
		this.tipo = tipo;
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}
	
	@Override
	public String toString() {
		return tipo;
	}
	
}
