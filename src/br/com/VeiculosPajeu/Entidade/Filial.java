package br.com.VeiculosPajeu.Entidade;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "filial")
@SequenceGenerator(name = Entidade.sequence, sequenceName = Filial.sequence, initialValue = 1, allocationSize = 1)
public class Filial extends Entidade {
	
	@Column(length = 50, nullable = false)
	private String nome;// nome VARCHAR (50) NOT NULL,
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco")
	private Endereco endereco; // id_endereco INTEGER REFERENCES ENDERECO(id)
	
	private static final long serialVersionUID = 1L;

	protected static final String sequence = "filial_sequence";
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String detalhesEntidade() {
		return "Nome: " + nome + "\n\nEndereço: \n" + endereco.detalhesEntidade();
	}

	@Override
	public String toString() {
		return "Nome: " + nome + " - " + endereco;
	}
}
