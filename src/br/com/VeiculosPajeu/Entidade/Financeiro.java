package br.com.VeiculosPajeu.Entidade;

import java.time.LocalTime;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.VeiculosPajeu.Entidade.Enum.EstadoFinanceiro;

@Entity
@Table(name = "financeiro")
@SequenceGenerator(name = Entidade.sequence, sequenceName = Financeiro.sequence, initialValue = 1, allocationSize = 1)
public class Financeiro extends Entidade {

	@OneToOne
	@JoinColumn(name = "locacao")
	private Locacao locacao; // id_locacao INTEGER REFERENCES LOCACAO (id)
	
	@Column(nullable = false)
	private LocalDate data_aberta; // data_aberta DATE NOT NULL,
	@Column(nullable = false)
	private LocalDate data_vencimento; // data_vencimento DATE NOT NULL,
	@Column(length = 10, nullable = false)
	@Enumerated(EnumType.STRING)
	private EstadoFinanceiro estado; // estado VARCHAR(8) NOT NULL
	@Column(nullable = false)
	private Float valor_total; // valor_total FLOAT NOT NULL,
	@Column
	private Float valor_pago; // valor_pago FLOAT,
	@Column
	private Float multa; // multa FLOAT,
	@Column
	private LocalTime horas_atrasados; // horas_atrasados TIMER,
	@Column
	private LocalDate data_paga; // data_paga DATE,
	@Column(nullable = false)

	private static final long serialVersionUID = 1L;

	protected static final String sequence = "financeiro_sequence";

	public Float getValor_total() {
		return valor_total;
	}

	public void setValor_total(Float valor_total) {
		this.valor_total = valor_total;
	}

	public Float getValor_pago() {
		return valor_pago;
	}

	public void setValor_pago(Float valor_pago) {
		this.valor_pago = valor_pago;
	}

	public Float getMulta() {
		return multa;
	}

	public void setMulta(Float multa) {
		this.multa = multa;
	}

	public LocalTime getHoras_atrasados() {
		return horas_atrasados;
	}

	public void setHoras_atrasados(LocalTime horas_atrasados) {
		this.horas_atrasados = horas_atrasados;
	}

	public EstadoFinanceiro getEstado() {
		return estado;
	}

	public void setEstado(EstadoFinanceiro estado) {
		this.estado = estado;
	}

	@Override
	public String detalhesEntidade() {
		// TODO Auto-generated method stub
		return null;
	}

	public Locacao getLocacao() {
		return locacao;
	}

	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
	}

	public LocalDate getData_aberta() {
		return data_aberta;
	}

	public void setData_aberta(LocalDate data_aberta) {
		this.data_aberta = data_aberta;
	}

	public LocalDate getData_vencimento() {
		return data_vencimento;
	}

	public void setData_vencimento(LocalDate data_vencimento) {
		this.data_vencimento = data_vencimento;
	}

	public LocalDate getData_paga() {
		return data_paga;
	}

	public void setData_paga(LocalDate data_paga) {
		this.data_paga = data_paga;
	}

	@Override
	public String toString() {
		return "Locacao[" + locacao + "] Data Aberta[" + data_aberta + "] Data de Vencimento["
				+ data_vencimento + "] Estado[" + estado + "] Valor Total[" + valor_total + "]";
	}

}
