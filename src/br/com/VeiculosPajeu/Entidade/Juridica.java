package br.com.VeiculosPajeu.Entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa_juridica")
@SequenceGenerator(name = Entidade.sequence, sequenceName = Juridica.sequence, initialValue = 1, allocationSize = 1)
public class Juridica extends Cliente {
	
	@Column(length = 18, nullable = false, unique = true)
	private String cnpj; // CNPJ VARCHAR (14) NOT NULL UNIQUE,
	@Column(length = 20, nullable = false)
	private String inscricao_estadual; // inscricao_estadual VARCHAR (20) NOT NULL
	
	private static final long serialVersionUID = 1L;

	protected static final String sequence = "juridica_sequence";
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricao_estadual() {
		return inscricao_estadual;
	}

	public void setInscricao_estadual(String inscricao_estadual) {
		this.inscricao_estadual = inscricao_estadual;
	}

	@Override
	public String detalhesEntidade() {
		return "CNPJ: " + cnpj + "\nInscrição Estadual: " + inscricao_estadual + "\n" + super.detalhesEntidade();
	}

}
