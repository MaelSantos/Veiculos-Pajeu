package br.com.VeiculosPajeu.Entidade.Enum;

public enum TipoBanco {

	MYSQL("MySql"), POSTGRESQL("PostgreSql");

	private String tipo;

	private TipoBanco(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return tipo;
	}

}
