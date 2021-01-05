package br.com.VeiculosPajeu.Entidade.View;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import br.com.VeiculosPajeu.Entidade.Categoria;
import br.com.VeiculosPajeu.Entidade.Cliente;

@Entity
@Immutable
@Subselect("select * from reservaView")
public class ReservaView implements Serializable {

//	a.cliente, a.data_locacao, a.data_reserva, a.hora_reserva, a.categoria

	@Id
	private Integer id;
	
	@Column(nullable = false)
	private LocalDate data_locacao; // data_locacao DATE NOT NULL,

	@Column(nullable = false)
	private LocalDate data_reserva; // data_reserva DATE NOT NULL,
	@Column(nullable = false)
	private LocalTime hora_reserva; //hora_reserva INTERGE 
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "cliente")
	private Cliente cliente; // id_cliente INTEGER REFERENCES CLIENTE (id),

	@OneToOne
	@JoinColumn(name = "categoria")
	private Categoria categoria; // id_categoria INTEGER REFERENCES CATEGORIA (id)

	private static final long serialVersionUID = 1L;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalTime getHora_reserva() {
		return hora_reserva;
	}

	public void setHora_reserva(LocalTime hora_reserva) {
		this.hora_reserva = hora_reserva;
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

	public LocalDate getData_locacao() {
		return data_locacao;
	}

	public void setData_locacao(LocalDate data_locacao) {
		this.data_locacao = data_locacao;
	}

	public LocalDate getData_reserva() {
		return data_reserva;
	}

	public void setData_reserva(LocalDate data_reserva) {
		this.data_reserva = data_reserva;
	}

}
