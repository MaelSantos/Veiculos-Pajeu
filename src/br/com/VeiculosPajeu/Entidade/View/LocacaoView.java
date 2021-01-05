package br.com.VeiculosPajeu.Entidade.View;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import br.com.VeiculosPajeu.Entidade.Cliente;
import br.com.VeiculosPajeu.Entidade.Enum.TipoLocacao;

@Entity
@Immutable
@Subselect("select * from locacaoView")
public class LocacaoView implements Serializable{
	
//	a.cliente, a.data_locacao, a.data_devolucao, a.valor_total, a.tipoLocacao
	
	@Id
	private Integer id;
	
	@Column
	private Float valor_total; // valor_total FLOAT,
	@Column(length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoLocacao tipoLocacao; // tipoLocacao VARCHAR(20) NOT NULL,
	@Column(nullable = false)
	private LocalDate data_locacao; // data_locacao DATE NOT NULL,
	@Column(nullable = false)
	private LocalDate data_devolucao; // data_devolucao DATE NOT NULL,
	
	@OneToOne
	@JoinColumn(name = "cliente")
	private Cliente cliente; // id_cliente INTEGER REFERENCES CLIENTE (id),

	private static final long serialVersionUID = 1L;
	
	public Float getValor_total() {
		return valor_total;
	}

	public void setValor_total(Float valor_total) {
		this.valor_total = valor_total;
	}

	public TipoLocacao getTipoLocacao() {
		return tipoLocacao;
	}

	public void setTipoLocacao(TipoLocacao tipoLocacao) {
		this.tipoLocacao = tipoLocacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getData_locacao() {
		return data_locacao;
	}

	public void setData_locacao(LocalDate data_locacao) {
		this.data_locacao = data_locacao;
	}

	public LocalDate getData_devolucao() {
		return data_devolucao;
	}

	public void setData_devolucao(LocalDate data_devolucao) {
		this.data_devolucao = data_devolucao;
	}
	
}
