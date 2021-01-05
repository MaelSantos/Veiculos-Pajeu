package br.com.VeiculosPajeu.Entidade;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.VeiculosPajeu.Entidade.Enum.TipoLocacao;

@Entity
@Table(name = "reserva")
@SequenceGenerator(name = Entidade.sequence, sequenceName = Reserva.sequence, initialValue = 1, allocationSize = 1)
public class Reserva extends Entidade {

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "cliente")
	private Cliente cliente; // id_cliente INTEGER REFERENCES CLIENTE (id),
	@Column(length = 11, nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoLocacao tipo_locacao; // tipo_locacao VARCHAR (11) NOT NULL,
	@Column(nullable = false)
	private LocalDate data_reserva; // data_reserva DATE NOT NULL,
	@Column(nullable = false)
	private LocalDate data_locacao; // data_locacao DATE NOT NULL,
	@Column(nullable = false)
	private LocalDate data_devolucao; // data_devolucao DATE NOT NULL,
	@Column
	private Boolean ativo;
	@Column(nullable = false)
	private LocalTime hora_reserva; // hora_reserva INTERGE

	@OneToOne
	@JoinColumn(name = "categoria")
	private Categoria categoria; // id_categoria INTEGER REFERENCES CATEGORIA (id)

	@OneToOne
	@JoinColumn(name = "usuario")
	private Usuario usuario; // id_usuario INTEGER REFERENCES USUARIO (id)

	private static final long serialVersionUID = 1L;

	protected static final String sequence = "reserva_sequence";

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public TipoLocacao getTipo_locacao() {
		return tipo_locacao;
	}

	public void setTipo_locacao(TipoLocacao tipo_locacao) {
		this.tipo_locacao = tipo_locacao;
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

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public LocalDate getData_reserva() {
		return data_reserva;
	}

	public void setData_reserva(LocalDate data_reserva) {
		this.data_reserva = data_reserva;
	}

	public LocalTime getHora_reserva() {
		return hora_reserva;
	}

	public void setHora_reserva(LocalTime localTime) {
		this.hora_reserva = localTime;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String detalhesEntidade() {
		return "Tipo de Locação: " + tipo_locacao + "\nData de Locação: " + data_locacao + "\nData de Devolução: "
				+ data_devolucao + "\nData de Reserva: " + data_reserva + "\nAtivo: " + ativo + "\n\nCliente: \n"
				+ cliente.detalhesEntidade() + "\n\n*Categoria: \n" + categoria.detalhesEntidade();
	}

	@Override
	public String toString() {
		return "Categoria[" + categoria + "] " + "Cliente[" + cliente + "] Tipo de Locação[" + tipo_locacao
				+ "] Data de Reserva[" + data_reserva + "] Dora de Reserva[" + hora_reserva + "]";
	}

}
