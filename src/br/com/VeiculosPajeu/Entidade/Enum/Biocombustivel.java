package br.com.VeiculosPajeu.Entidade.Enum;

public enum Biocombustivel {

	BIOETANOL("Bioetanol"), BIODIESEL("biodiesel"), BIOGAS("Biogás"),
	BIOMETANOL("Biometanol"), BIOETER_DIMETILICO("Bioéter Dimetílico"), BIO_ETBE("Bio-ETBE"),
	BIO_MTBE("Bio-MTBE"), BIOCOMBUSTIVEIS_SINTETICOS("Biocombustíveis Sintéticos"),
	BIO_HIDROGENIO("Bio-Hidrogénio"), BIO_OLEO("Bio-óleo"),
	OLEO_VEGETAL("Óleo Vegetal"), BIOQUEROSENE("Bioquerosene");
	
	private String tipo;
	private Biocombustivel(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return tipo;
	}
		
}
