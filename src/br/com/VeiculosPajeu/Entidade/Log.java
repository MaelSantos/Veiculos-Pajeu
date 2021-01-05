package br.com.VeiculosPajeu.Entidade;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "log")
public class Log implements Serializable{

	@Id
	@Column(columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false)
	private LocalDate data; // data DATE NOT NULL,
	@Column(length = 50, nullable = false)
	private String alteracao;// alteracao VARCHAR(50) NOT NULL,
	@Column(length = 50, nullable = false)
	private String autor;// autor VARCHAR(50) NOT NULL,
	@Column(length = 50, nullable = false)
	private String tabela;// tabela VARCHAR(50) NOT NULL,
	@Column(length = 255, nullable = false)
	private String anterior; //anterior VARCHAR(255) NOT NULL

	private static final long serialVersionUID = 1L;
	
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getAlteracao() {
		return alteracao;
	}

	public void setAlteracao(String alteracao) {
		this.alteracao = alteracao;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTabela() {
		return tabela;
	}

	public void setTabela(String tabela) {
		this.tabela = tabela;
	}

	public String getAnterior() {
		return anterior;
	}

	public void setAnterior(String anterior) {
		this.anterior = anterior;
	}
}
