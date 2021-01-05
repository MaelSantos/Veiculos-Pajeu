package br.com.VeiculosPajeu.Entidade.Enum;

public enum Sexo {

	MASCULINO("Masculino"), FEMININO("Feminino");
	
	private String sexo;
	private Sexo(String  sexo) {
		this.sexo = sexo;
	}
	
	@Override
	public String toString() {
		return sexo;
	}
	
}
