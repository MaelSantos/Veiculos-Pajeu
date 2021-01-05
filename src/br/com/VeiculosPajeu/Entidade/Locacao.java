package br.com.VeiculosPajeu.Entidade;

import java.time.LocalDate;
import java.time.LocalTime;

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
@Table(name = "locacao")
@SequenceGenerator(name = Entidade.sequence, sequenceName = Locacao.sequence, initialValue = 1, allocationSize = 1)
public class Locacao extends Entidade {

	@OneToOne
	@JoinColumn(name = "cliente")
	private Cliente cliente; // id_cliente INTEGER REFERENCES CLIENTE (id),
	@Column(length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoLocacao tipoLocacao; // tipoLocacao VARCHAR(20) NOT NULL,
	@Column(nullable = false)
	private LocalDate data_locacao; // data_locacao DATE NOT NULL,
	@Column(nullable = false)
	private LocalDate data_devolucao; // data_devolucao DATE NOT NULL,
	@Column(nullable = false)
	private LocalTime hora_devolucao; //hora_devolucao TIMER NOT NULL,
	@Column(nullable = false)
	private Float diaria; // diaria FLOAT NOT NULL,
	@Column
	private Float valor_total; // valor_total FLOAT,
	@Column
	private Boolean ativo; // ativo BOOLEAN

	@OneToOne
	@JoinColumn(name = "veiculo")
	private Veiculo veiculo; // id_veiculo INTEGER REFERENCES VEICULO (id),

	@OneToOne
	@JoinColumn(name = "filial_locacao")
	private Filial filial_locacao; // filial_locacao INTEGER REFERENCES FILIAL (id),

	@OneToOne
	@JoinColumn(name = "filial_devolucao")
	private Filial filial_devolucao; // filial_devolucao INTEGER REFERENCES FILIAL (id)

	@OneToOne
	@JoinColumn(name = "usuario")
	private Usuario usuario; // id_usuario INTEGER REFERENCES USUARIO (id)
	
	@OneToOne(mappedBy = "locacao")
	@JoinColumn(name = "financeiro")
	private Financeiro financeiro;// id_finaceiro INTEGER REFERENCES FINANCEIRO (id)

	private static final long serialVersionUID = 1L;

	protected static final String sequence = "locacao_sequence";

	public Float getDiaria() {
		return diaria;
	}

	public void setDiaria(Float diaria) {
		this.diaria = diaria;
	}

	public Float getValor_total() {
		return valor_total;
	}

	public void setValor_total(Float valor_total) {
		this.valor_total = valor_total;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Filial getFilial_locacao() {
		return filial_locacao;
	}

	public void setFilial_locacao(Filial filial_locacao) {
		this.filial_locacao = filial_locacao;
	}

	public Filial getFilial_devolucao() {
		return filial_devolucao;
	}

	public void setFilial_devolucao(Filial filial_devolucao) {
		this.filial_devolucao = filial_devolucao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public TipoLocacao getTipoLocacao() {
		return tipoLocacao;
	}

	public void setTipoLocacao(TipoLocacao tipoLocacao) {
		this.tipoLocacao = tipoLocacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Financeiro getFinanceiro() {
		return financeiro;
	}

	public void setFinanceiro(Financeiro financeiro) {
		this.financeiro = financeiro;
	}

	public LocalTime getHora_devolucao() {
		return hora_devolucao;
	}

	public void setHora_devolucao(LocalTime hora_devolucao) {
		this.hora_devolucao = hora_devolucao;
	}
	
	@Override
	public String detalhesEntidade() {
		return "Diaria: " + diaria + "\nValor Total: " + valor_total + "\nTipo de Locação: " + tipoLocacao
				+ "\nData de Locação: " + data_locacao + "\nData de Devolução: " + data_devolucao + "\nAtivo: " + ativo
				+ "\n\nCliente: \n" + cliente.detalhesEntidade() + "\n\n*Veiculo: \n" + veiculo.detalhesEntidade()
				+ "\n\nFilial Feita a Locação: \n" + filial_locacao.getNome();
	}

	@Override
	public String toString() {
		return "Valor Total[" + valor_total + "] Tipo de Locação[" + tipoLocacao + "] Data de Locação[" + data_locacao
				+ "] Filial de Locação[" + filial_locacao + "]";
	}

}
