package br.com.VeiculosPajeu.Entidade.Enum;

public enum TipoAirBag {

	SIMPLESDIANTEIRA("Simplesdianteira"), DUPLO_DIANTEIRA("Duplo-Dianteira"), TOTAL("Total");
	
	private String tipo;
	private TipoAirBag(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return tipo;
	}
	
}
