package br.com.VeiculosPajeu.Entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.VeiculosPajeu.Entidade.Enum.TipoAirBag;

@Entity
@Table(name = "camionete_passageiros")
@SequenceGenerator(name = Entidade.sequence, sequenceName = Passageiro.sequence, initialValue = 1, allocationSize = 1)
public class Passageiro extends Veiculo {
	
	@Column(length = 16)
	@Enumerated(EnumType.STRING)
	private TipoAirBag airbag; // air_bag VARCHAR(16),
	@Column
	private Boolean direcao_assistida; // direcao_assistida BOOLEAN,
	@Column
	private Boolean cintos_retrateis; // cintos_retrateis BOOLEAN,
	@Column
	private Boolean rodas_liga_leve; // rodas_liga_leve BOOLEAN,
	@Column
	private Boolean controle_poluicao; // controle_poluicao BOOLEAN

	private static final long serialVersionUID = 1L;

	protected static final String sequence = "passageiro_sequence";
	
	public TipoAirBag getAirbag() {
		return airbag;
	}

	public void setAirbag(TipoAirBag airbag) {
		this.airbag = airbag;
	}

	public Boolean getCintos_retrateis() {
		return cintos_retrateis;
	}

	public void setCintos_retrateis(Boolean cintos_retrateis) {
		this.cintos_retrateis = cintos_retrateis;
	}

	public Boolean getRodas_liga_leve() {
		return rodas_liga_leve;
	}

	public void setRodas_liga_leve(Boolean rodas_liga_leve) {
		this.rodas_liga_leve = rodas_liga_leve;
	}

	public Boolean getControle_poluicao() {
		return controle_poluicao;
	}

	public void setControle_poluicao(Boolean controle_poluicao) {
		this.controle_poluicao = controle_poluicao;
	}

	@Override
	public String detalhesEntidade() {
		return super.detalhesEntidade() + "\nAirbag: " + airbag + "\nDireção Assistida: " + direcao_assistida
				+ "\nCintos de Segurança Retrateis: " + cintos_retrateis + "\nRodas Liga de Leve: " + rodas_liga_leve
				+ "\nControle de Poluição: " + controle_poluicao;
	}
	
}
