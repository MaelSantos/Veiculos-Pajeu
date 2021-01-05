package br.com.VeiculosPajeu.Entidade.View;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import br.com.VeiculosPajeu.Entidade.Categoria;

@MappedSuperclass
@Immutable
@Subselect("select * from VeiculoView")
public class VeiculoView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(length = 20)
	private String fabricante; // fabricante VARCHAR (20),
	@Column(length = 20, nullable = false)
	private String modelo; // modelo VARCHAR (20) NOT NULL,
	@Column
	private Integer ano_modelo; // ano_modelo INTERGE,
	@Column(length = 8, unique = true)
	private String placa; // placa VARCHAR (8) UNIQUE,

	@ManyToOne
	@JoinColumn(name = "categoria") // id_categoria INTEGER REFERENCES CATEGORIA (id)
	private Categoria categoria;

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getAno_modelo() {
		return ano_modelo;
	}

	public void setAno_modelo(Integer ano_modelo) {
		this.ano_modelo = ano_modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
