package br.com.VeiculosPajeu.Entidade.Enum;

public enum TipoUsuario {

	FUNCIONARIO("Funcionario"), SUPER_USUARIO("Super Usuário");
	
	private String tipo;

	private TipoUsuario(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return tipo;
	}
}
