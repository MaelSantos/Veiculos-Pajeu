package br.com.VeiculosPajeu.Entidade;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.VeiculosPajeu.Entidade.Enum.TipoCombustivel;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "veiculo")
@SequenceGenerator(name = Entidade.sequence, sequenceName = Veiculo.sequence, initialValue = 1, allocationSize = 1)
public abstract class Veiculo extends Entidade {
	
	@Column(length = 20)
	private String fabricante; // fabricante VARCHAR (20),
	@Column(length = 20, nullable = false)
	private String modelo; // modelo VARCHAR (20) NOT NULL,
	@Column(length = 8, unique = true)
	private String placa; // placa VARCHAR (8) UNIQUE,
	@OneToOne
	@JoinColumn(name = "filial")
	private Filial filial; // filial INTEGER REFERENCES FILIAL (id),
	@ManyToOne
	@JoinColumn(name = "categoria") // id_categoria INTEGER REFERENCES CATEGORIA (id)
	private Categoria categoria;
	@Column(length = 20)
	private String numero_chassi; // numero_chassi VARCHAR (20),
	@Column
	private Integer numero_passageiros; // numero_passageiros INTERGE,
	@Column
	private Integer numero_portas; // numero_portas INTERGE,
	@Column
	private Integer ano_fabricacao; // ano_fabricacao INTERGE,
	@Column
	private Integer ano_modelo; // ano_modelo INTERGE,
	@Column(length = 20)
	private String numero_motor; // numero_motor VARCHAR (20),
	@Column(length = 20)
	private String cor; // cor VARCHAR (10),
	@Column(length = 20)
	private String torque_motor;// torque_motor VARCHAR (20),
	@Column(length = 14)
	@Enumerated(EnumType.STRING)
	private TipoCombustivel tipo_combustivel; // tipo_combustivel VARCHAR (13),

	@Column
	private Float quilometragem; //quilometragem FLOAT,
	
	@Column
	private Boolean manutencao; // manutencao BOOLEAN,
	@Column 
	private LocalTime horas_manutencao; //horas_manutencao Timer,

	@Column
	private Boolean alugado; // reservado BOOLEAN,
	
	private static final long serialVersionUID = 1L;
	
	protected static final String sequence = "veiculo_sequence";
	
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

	public Integer getNumero_passageiros() {
		return numero_passageiros;
	}

	public void setNumero_passageiros(Integer numero_passageiros) {
		this.numero_passageiros = numero_passageiros;
	}

	public Integer getNumero_portas() {
		return numero_portas;
	}

	public void setNumero_portas(Integer numero_portas) {
		this.numero_portas = numero_portas;
	}

	public Integer getAno_fabricacao() {
		return ano_fabricacao;
	}

	public void setAno_fabricacao(Integer ano_fabricacao) {
		this.ano_fabricacao = ano_fabricacao;
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

	public String getNumero_chassi() {
		return numero_chassi;
	}

	public void setNumero_chassi(String numero_chassi) {
		this.numero_chassi = numero_chassi;
	}

	public String getNumero_motor() {
		return numero_motor;
	}

	public void setNumero_motor(String numero_motor) {
		this.numero_motor = numero_motor;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getTorque_motor() {
		return torque_motor;
	}

	public void setTorque_motor(String torque_motor) {
		this.torque_motor = torque_motor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public TipoCombustivel getTipo_combustivel() {
		return tipo_combustivel;
	}

	public void setTipo_combustivel(TipoCombustivel tipo_combustivel) {
		this.tipo_combustivel = tipo_combustivel;
	}

	@Override
	public String detalhesEntidade() {
		return "Fabricante: " + fabricante + "\nModelo: " + modelo + "\nNumero de Passageiros: " + numero_passageiros
				+ "\nNumero de Portas: " + numero_portas + "\nAno de Fabricação: " + ano_fabricacao
				+ "\nAno do Modelo: " + ano_modelo + "\nPlaca: " + placa + "\nNumero do chassi: " + numero_chassi
				+ "\nNumero do Motor: " + numero_motor + "\nCor: " + cor + "\nTorque do Motor: " + torque_motor
				+ "\nTipo de Combustível: " + tipo_combustivel + " *Categoria: \n" + categoria.detalhesEntidade();
	}

	@Override
	public String toString() {
		return "Fabricante[" + fabricante + "] - Modelo[" + modelo + "] - Ano do Modelo[" + ano_modelo + "] Placa["
				+ placa + "] - Cor[" + cor + "]";
	}

	public Boolean getAlugado() {
		return alugado;
	}

	public void setAlugado(Boolean alugado) {
		this.alugado = alugado;
	}

	public Boolean getManutencao() {
		return manutencao;
	}

	public void setManutencao(Boolean manutencao) {
		this.manutencao = manutencao;
	}

	public Float getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(Float quilometragem) {
		this.quilometragem = quilometragem;
	}

	public LocalTime getHoras_manutencao() {
		return horas_manutencao;
	}

	public void setHoras_manutencao(LocalTime horas_manutencao) {
		this.horas_manutencao = horas_manutencao;
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

}
