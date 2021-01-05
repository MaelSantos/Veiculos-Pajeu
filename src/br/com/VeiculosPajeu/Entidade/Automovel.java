package br.com.VeiculosPajeu.Entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.VeiculosPajeu.Entidade.Enum.Tamanho;
import br.com.VeiculosPajeu.Entidade.Enum.TipoCambio;

@Entity
@Table(name = "automovel")
@SequenceGenerator(name = Entidade.sequence, sequenceName = Automovel.sequence, initialValue = 1, allocationSize = 1)
public class Automovel extends Veiculo {
	
	@Column(length = 7, nullable = false)
	@Enumerated(EnumType.STRING)
	private Tamanho tamanho; // tamanho VARCHAR (7) NOT NULL,
	@Column
	private Boolean arcondicionado; // arcondicionado BOOLEAN,
	@Column
	private Boolean radio; // radio BOOLEAN,
	@Column
	private Boolean dvd; // DVD BOOLEAN,
	@Column
	private Boolean mp3; // MP3 BOOLEAN,
	@Column
	private Boolean direcao_hidraulica; // direcao_hidraulica BOOLEAN,
	@Column
	private Boolean camera_re; // camera_re BOOLEAN,
	@Column(length = 10)
	@Enumerated(EnumType.STRING)
	private TipoCambio tipo_cambio;// tipo_cambio VARCHAR (10)

	private static final long serialVersionUID = 1L;

	protected static final String sequence = "automovel_sequence";
	
	public Tamanho getTamanho() {
		return tamanho;
	}

	public void setTamanho(Tamanho tamanho) {
		this.tamanho = tamanho;
	}

	public Boolean getArcondicionado() {
		return arcondicionado;
	}

	public void setArcondicionado(Boolean arcondicionado) {
		this.arcondicionado = arcondicionado;
	}

	public Boolean getRadio() {
		return radio;
	}

	public void setRadio(Boolean radio) {
		this.radio = radio;
	}

	public Boolean getDvd() {
		return dvd;
	}

	public void setDvd(Boolean dvd) {
		this.dvd = dvd;
	}

	public Boolean getMp3() {
		return mp3;
	}

	public void setMp3(Boolean mp3) {
		this.mp3 = mp3;
	}

	public Boolean getCamera_re() {
		return camera_re;
	}

	public void setCamera_re(Boolean camera_re) {
		this.camera_re = camera_re;
	}

	public TipoCambio getTipo_cambio() {
		return tipo_cambio;
	}

	public void setTipo_cambio(TipoCambio tipo_cambio) {
		this.tipo_cambio = tipo_cambio;
	}

	public Boolean getDirecao_hidraulica() {
		return direcao_hidraulica;
	}

	public void setDirecao_hidraulica(Boolean direcao_hidraulica) {
		this.direcao_hidraulica = direcao_hidraulica;
	}

	@Override
	public String detalhesEntidade() {
		return "Tamanho: " + tamanho + "\nAr Condicionado: " + arcondicionado + "\nRádio: " + radio + "\nDVD: " + dvd
				+ "\nMP3: " + mp3 + "\nDireção Hidraulica: " + direcao_hidraulica + "\nCâmera ré: " + camera_re
				+ "\nTipo Câmbio: " + tipo_cambio + "\n" + super.detalhesEntidade();
	}
}
