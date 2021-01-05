package br.com.VeiculosPajeu.Entidade;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.VeiculosPajeu.Entidade.Enum.Sexo;

@Entity
@Table(name = "pessoa_fisica")
@SequenceGenerator(name = Entidade.sequence, sequenceName = Fisica.sequence, initialValue = 1, allocationSize = 1)
public class Fisica extends Cliente {
	
	@Column(length = 14, nullable = false, unique = true)
	private String cpf; // CPF VARCHAR (14) NOT NULL UNIQUE,
	@Column(length = 9)
	@Enumerated(EnumType.STRING)
	private Sexo sexo; // sexo VARCHAR (9),
	@Column
	private LocalDate data_de_nascimento; // data_de_nascimento DATE,
	@Column(length = 20, nullable = false, unique = true)
	private String numero_habilitacao; // numero_habilitacao VACHAR (20) NOT NULL UNIQUE,
	@Column(nullable = false)
	private LocalDate vencimento_habilitacao; // vencimento_habilitacao DATE NOT NULL

	private static final long serialVersionUID = 1L;

	protected static final String sequence = "fisica_sequence";
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public LocalDate getData_de_nascimento() {
		return data_de_nascimento;
	}

	public void setData_de_nascimento(LocalDate data_de_nascimento) {
		this.data_de_nascimento = data_de_nascimento;
	}

	public String getNumero_habilitacao() {
		return numero_habilitacao;
	}

	public void setNumero_habilitacao(String numero_habilitacao) {
		this.numero_habilitacao = numero_habilitacao;
	}

	public LocalDate getVencimento_habilitacao() {
		return vencimento_habilitacao;
	}

	public void setVencimento_habilitacao(LocalDate vencimento_habilitacao) {
		this.vencimento_habilitacao = vencimento_habilitacao;
	}

	@Override
	public String detalhesEntidade() {
		return "CPF: " + cpf + "\nSexo: " + sexo + "\nData de Nascimento: " + data_de_nascimento
				+ "\nNumero da Habilitação: " + numero_habilitacao + "\nVencimento da Habilitação: "
				+ vencimento_habilitacao + "\n" + super.detalhesEntidade();
	}

}
