package br.com.VeiculosPajeu.Entidade.Enum;

public enum TipoVeiculo {

	AUTOMOVEL("Automóvel"), CAMIONETE_DE_CARGA("Camionete de Carga"), CAMIONETE_DE_PASSAGEIROS("Camionete de Passageiros");
	
	private String tipo;

	private TipoVeiculo(String tipo) {
		this.tipo = tipo;
	};
	
	@Override
	public String toString() {
		return tipo;
	}
	
}
