package br.com.VeiculosPajeu.Entidade.Enum;

public enum Tela {

	LOGIN("Login"), MENU("Menu"), CONFIGURACAO("Configurações","Configuracao"), 
	
	ATRASADOS("Atrasados"), DISPONIVEIS("Disponiveis"),
	
	CADASTRO_CLIENTE("Cadastro Cliente","CadastroCliente"), CADASTRO_FILIAL("Cadastro Filial","CadastroFilial"),
	CADASTRO_VEICULO("Cadastro Veículo", "CadastroVeiculo"), CADASTRO_USUARIO("Cadastro Usuário", "CadastroUsuario"),
	
	BUSCAR("Busca"), DETALHES("Detalhes"), PERFIL("Perfil"), RESETAR_SENHA("Resetar Senha", "ResetarSenha"),
	
	EDITAR_CLIENTE("Editar Cliente"), EDITAR_FILIAL("Editar Filial"), EDITAR_VEICULO("Editar Veículo"),
	EDITAR_USUARIO("Editar Usuário"), EDITAR_CATEGORIA("Editar Categoria"),
	EDITAR_CONFIGURACAO("Editar Configurações", "EditarConfiguracao"),
	EDITAR_RESERVA("Editar Reserva"), EDITAR_LOCACAO("Editar Locação"),
	
	LOCACAO("Locação", "Locacao"), RESERVA("Reserva"), RETIRAR_RESERVA("Retirar Reserva", "RetirarReserva"),
	DEVOLUCAO("Devolução", "Devolucao"),CADASTRO_CATEGORIA("Cadastro Categoria", "CadastroCategoria"),
	MANUTENCAO("Manutenção", "Manutencao"), AJUDA("Ajuda"),
	
	INFORMACOES("Informações", "Informacoes"), APARENCIA("Aparência", "Aparencia"), DIALOGO("Dialogo"),
	RELATORIO("Relatório","Relatorio"), ESTATISTICA("Estatística","Estatistica"), 
	HISTORICO("Histórico","Historico"), FINANCEIRO("Financeiro"), GERENCIAR_FINANCEIRO("Gerenciar Financeiro", "GerenciarFinanceiro"),
	PAGAMENTO("Pagamento")
	
	;
	
	private String tela, caminho;
	private Tela(String tela, String caminho) {
		this.tela = tela;
		this.caminho = caminho;
	}
	private Tela(String caminho) {
		this.caminho = caminho;
		this.tela = caminho;
	}
	
	public String getCaminho() {
		return caminho;
	}
	
	@Override
	public String toString() {
		return tela;
	}
	
}
