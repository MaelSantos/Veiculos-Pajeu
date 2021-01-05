package br.com.VeiculosPajeu.Entidade.Enum;

public enum TipoLocacao {

	KM_LIVRE("Km Livre"), KM_CONTROLE("Km Controle");
	
	private String tipo;

	private TipoLocacao(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return tipo;
	}
	
}
