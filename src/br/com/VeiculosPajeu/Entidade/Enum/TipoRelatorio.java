package br.com.VeiculosPajeu.Entidade.Enum;

public enum TipoRelatorio {

	CLIENTE_PF("Clientes Físicos", "Cliente Fisico.jrxml"), 
	CLIENTE_PJ("Clientes Jurídicos", "Cliente Juridico.jrxml"), 
	RESERVA_PERIODO("Reservas por Período", "Reserva Periodo.jrxml"),
	LOCACAO_PERIODO("Locações por Período", "Locacao Periodo.jrxml"),
	LOCACAO_CLIENTE("Locações por Cliente", "Locacao Cliente.jrxml"),
	FINANCEIRO_PERIODO("Financeiro Por Período", "Financeiro.jrxml");

	private String tipo;
	private String caminho;

	private TipoRelatorio(String tipo, String caminho) {
		this.tipo = tipo;
		this.caminho = caminho;
	}

	public String getCaminho() {
		return caminho;
	}

	@Override
	public String toString() {
		return tipo;
	}

}
