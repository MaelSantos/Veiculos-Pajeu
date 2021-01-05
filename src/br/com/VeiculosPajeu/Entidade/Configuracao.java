package br.com.VeiculosPajeu.Entidade;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "configuracao")
@SequenceGenerator(name = Entidade.sequence, sequenceName = Configuracao.sequence, initialValue = 1, allocationSize = 1)
public class Configuracao extends Entidade {
	
	@Column
	private LocalTime horas_limpeza; // horas_limpeza INTERGE
	@Column
	private Float valorKmControle; // valorKmControle Float,
	@Column
	private Float valorKmLivre; // valorKmLivre Float
	@Column(length = 50)
	private String usuario; // usuario VARCHAR(50)
	
	private static final long serialVersionUID = 1L;

	protected static final String sequence = "configuracao_sequence";
	
	public LocalTime getHoras_limpeza() {
		return horas_limpeza;
	}

	public void setHoras_limpeza(LocalTime horas_limpeza) {
		this.horas_limpeza = horas_limpeza;
	}

	public Float getValorKmControle() {
		return valorKmControle;
	}

	public void setValorKmControle(Float valorKmControle) {
		this.valorKmControle = valorKmControle;
	}

	public Float getValorKmLivre() {
		return valorKmLivre;
	}

	public void setValorKmLivre(Float valorKmLivre) {
		this.valorKmLivre = valorKmLivre;
	}

	@Override
	public String detalhesEntidade() {
		return "Horas de Limpeza: " + horas_limpeza + "\nValor de KmControle: " + valorKmControle
				+ "\nValor de KmLivre: " + valorKmLivre;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
