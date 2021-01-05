package br.com.VeiculosPajeu.Entidade.Enum;

public enum TipoEstatistica {

	LOCACAO_RESERVA("Locação e Reserva"), LOCACAO_ATIVA("Locação Ativa - Vencida"),
	RESERVA_ATIVA("Reserva Ativa - Vencida")

	;

	private String tipo;

	private TipoEstatistica(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return tipo;
	}

}
