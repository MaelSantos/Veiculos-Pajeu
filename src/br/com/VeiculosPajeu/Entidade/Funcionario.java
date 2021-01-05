package br.com.VeiculosPajeu.Entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.VeiculosPajeu.Entidade.Enum.TipoFuncionario;

@Entity
@Table (name = "funcionario")
@SequenceGenerator(name = Entidade.sequence, sequenceName = Funcionario.sequence, initialValue = 1, allocationSize = 1)
public class Funcionario extends Usuario{
		
	@Column(length = 50, nullable = false)
	private String cargo; //cargo VARCHAR (20) NOT NULL
	@Column(length = 13, nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoFuncionario tipoFuncionario; //tipoFuncionario VARCHAR(13) NOT NULL

	private static final long serialVersionUID = 1L;

	protected static final String sequence = "funcionario_sequence";
	
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	@Override
	public String detalhesEntidade() {
		return "Cargo: " + cargo +"\nTipo de Funcionário: "+tipoFuncionario+ "\n"+super.detalhesEntidade();
	}

	public TipoFuncionario getTipoFuncionario() {
		return tipoFuncionario;
	}

	public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}
}
