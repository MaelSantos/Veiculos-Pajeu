package br.com.VeiculosPajeu.Entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "super_usuario")
@SequenceGenerator(name = Entidade.sequence, sequenceName = SuperUsuario.sequence, initialValue = 1, allocationSize = 1)
public class SuperUsuario extends Usuario{

	@Column(length = 30)
	private String senha_padrao; //senha_padrao VARCHAR(11),
	
	@OneToOne
	@JoinColumn(name = "configuracao")
	private Configuracao configuracao; //id_configuracao INTEGER REFERENCES CONFIGURACAO(id)
	
	private static final long serialVersionUID = 1L;
	
	protected static final String sequence = "superUsuario_sequence";
	
	public String getSenha_padrao() {
		return senha_padrao;
	}

	public void setSenha_padrao(String senha_padrao) {
		this.senha_padrao = senha_padrao;
	}

	public Configuracao getConfiguracao() {
		return configuracao;
	}

	public void setConfiguracao(Configuracao configuracao) {
		this.configuracao = configuracao;
	}
		
}
