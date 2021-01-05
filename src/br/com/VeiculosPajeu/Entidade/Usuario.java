package br.com.VeiculosPajeu.Entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table (name = "usuario")
@SequenceGenerator(name = Entidade.sequence, sequenceName = Usuario.sequence, initialValue = 1, allocationSize = 1)
public abstract class Usuario extends Entidade{
	
	@Column(length = 50)
	private String nome; //nome VARCHAR (50),
	@Column(length = 20, nullable = false, unique = true)
	private String login; //login VARCHAR (20) NOT NULL UNIQUE,
	@Column(length = 30, nullable = false)
	private String senha; //senha VARCHAR (11) NOT NULL
	
	private static final long serialVersionUID = 1L;
	
	protected static final String sequence = "usuario_sequence";
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	@Override
	public String detalhesEntidade() {
		return "Nome: " + nome + "\nLogin: " + login;
	}
	
}
