package br.com.VeiculosPajeu.Entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.VeiculosPajeu.Entidade.Enum.Estado;

@Entity
@Table (name = "endereco")
@SequenceGenerator(name = Entidade.sequence, sequenceName = Endereco.sequence, initialValue = 1, allocationSize = 1)
public class Endereco extends Entidade {
	
	@Column(length = 50, nullable = false)
	private String rua; //rua VARCHAR (50) NOT NULL,
	@Column(length = 50, nullable = false)
	private String numero; //numero VARCHAR (50) NOT NULL,
	@Column(length = 50, nullable = false)
	private String bairro; //bairro VARCHAR (50) NOT NULL,
	@Column(length = 50, nullable = false)
	private String cep; //cep VARCHAR (50) NOT NULL,
	@Column(length = 50, nullable = false)
	private String cidade; //cidade VARCHAR (50) NOT NULL, 
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private Estado estado; //estado VARCHAR (50), 
	@Column(length = 50)
	private String pais; //pais VARCHAR (50), 
	@Column(length = 50)
	private String complemento; //complemento VARCHAR(50)

	private static final long serialVersionUID = 1L;

	protected static final String sequence = "endereco_sequence";
	
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}	
	
	@Override
	public String toString() {
		return "Rua "+rua+", "+numero+", "+bairro+", "+cidade;
	}
	
	@Override
	public String detalhesEntidade() {
		return "Rua: " + rua + "\nNúmero: " + numero + "\nBairro: " + bairro + "\nCEP: " + cep + "\nCidade: "
				+ cidade + "\nEstado: " + estado + "\nPais: " + pais + "\nComplemento: " + complemento;
	}	
	
}

