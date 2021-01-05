package br.com.VeiculosPajeu.Entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.VeiculosPajeu.Entidade.Enum.Acionamento;

@Entity
@Table(name = "camionete_carga")
@SequenceGenerator(name = Entidade.sequence, sequenceName = Carga.sequence, initialValue = 1, allocationSize = 1)
public class Carga extends Veiculo {
	
	@Column(nullable = false)
	private Float capacidade_carga; // capacidade_carga FLOAT NOT NULL,
	@Column(length = 10)
	@Enumerated(EnumType.STRING)
	private Acionamento acionamento; // acionamento VACHAR (10),
	@Column(length = 20)
	private String desempenho; // desempenho_veiculo VACHAR (20),
	@Column(length = 20)
	private String potencia; // potencia VACHAR (20),
	@Column(length = 20)
	private String volume_combustivel; // volume_combustivel VACHAR (20),
	@Column(length = 20)
	private String distancia; // distancia_eixos VACHAR (20),
	
	private static final long serialVersionUID = 1L;

	protected static final String sequence = "carga_sequence";
	
	public Float getCapacidade_carga() {
		return capacidade_carga;
	}

	public void setCapacidade_carga(Float capacidade_carga) {
		this.capacidade_carga = capacidade_carga;
	}

	public Acionamento getAcionamento() {
		return acionamento;
	}

	public void setAcionamento(Acionamento acionamento) {
		this.acionamento = acionamento;
	}

	public String getDesempenho() {
		return desempenho;
	}

	public void setDesempenho(String desempenho) {
		this.desempenho = desempenho;
	}

	public String getPotencia() {
		return potencia;
	}

	public void setPotencia(String potencia) {
		this.potencia = potencia;
	}

	public String getVolume_combustivel() {
		return volume_combustivel;
	}

	public void setVolume_combustivel(String volume_combustivel) {
		this.volume_combustivel = volume_combustivel;
	}

	public String getDistancia() {
		return distancia;
	}

	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}

	@Override
	public String detalhesEntidade() {
		return "Capacidade de Carga: " + capacidade_carga + "\nAcionamento: " + acionamento
				+ "\nDesempenho: " + desempenho + "\nPotência: " + potencia + "\nVolume do Combustível: "
				+ volume_combustivel + "\nDistância: " + distancia+"\n"+super.detalhesEntidade();
	}
}
