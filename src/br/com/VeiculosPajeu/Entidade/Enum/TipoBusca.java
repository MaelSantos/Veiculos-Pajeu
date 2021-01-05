package br.com.VeiculosPajeu.Entidade.Enum;

public enum TipoBusca {

	CLIENTE("Cliente"), FILIAL("Filial"), VEICULO("Veículo"),
	LOCACAO("Locação"), RESERVA("Reserva"), CATEGORIA("Categoria"), 
	USUARIO("Usuário");
	
	private String tipo;

	private TipoBusca(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return tipo;
	}
}
