package br.com.VeiculosPajeu.Entidade;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "cliente")
@SequenceGenerator(name = Entidade.sequence, sequenceName = Cliente.sequence, initialValue = 1, allocationSize = 1)
public abstract class Cliente extends Usuario {
	
	@Column(length = 50, nullable = false)
	private String nome; // nome VARCHAR (50) NOT NULL,
	@Column(length = 11, nullable = false)
	private String codigo; // codigo VARCHAR (50) NOT NULL,

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco")
	private Endereco endereco; // id_endereco INTEGER REFERENCES ENDERECO(id)

	private static final long serialVersionUID = 1L;
	
	protected static final String sequence = "cliente_sequence";
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public String detalhesEntidade() {
		return "Nome: " + nome + "\nCódigo: " + codigo + "\n\nEndereço: \n" + endereco.detalhesEntidade();
	}

	@Override
	public String toString() {
		return "Nome[" + nome + "] - Código[" + codigo + "]";
	}

}
