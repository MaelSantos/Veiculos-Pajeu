package br.com.VeiculosPajeu.Entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.VeiculosPajeu.Entidade.Enum.Tamanho;
import br.com.VeiculosPajeu.Entidade.Enum.TipoCategoria;

@Entity
@Table(name = "categoria")
@SequenceGenerator(name = Entidade.sequence, sequenceName = Categoria.sequence, initialValue = 1, allocationSize = 1)
public class Categoria extends Entidade {
		
	@Column(length = 10, nullable = false, unique = true)
	private String nome; // nome VARCHAR (10) NOT NULL UNIQUE,
	@Column(length = 7, nullable = false)
	@Enumerated(EnumType.STRING)
	private Tamanho tamanho; // tamanho VARCHAR (7) NOT NULL,
	@Column(length = 9, nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoCategoria tipo; // tipo VARCHAR (6) NOT NULL,
	@Column(nullable = false)
	private Float valor;// valor FLOAT NOT NULL,

	@ManyToOne()
	@JoinColumn(name = "filial")
	private Filial filial; // id_filial INTEGER REFERENCES FILIAL(id)
	
	private static final long serialVersionUID = 1L;

	protected static final String sequence = "categoria_sequence";
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Tamanho getTamanho() {
		return tamanho;
	}

	public void setTamanho(Tamanho tamanho) {
		this.tamanho = tamanho;
	}

	public TipoCategoria getTipo() {
		return tipo;
	}

	public void setTipo(TipoCategoria tipo) {
		this.tipo = tipo;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	@Override
	public String toString() {
		return "Nome["+nome+"] Valor["+valor+"]";
	}
	
	@Override
	public String detalhesEntidade() {
		return "Nome: " + nome + "\nTamanho: " + tamanho + "\nTipo: " + tipo + "\nValor: "
				+ valor + "\n\nFilial: \n" + filial.getNome();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filial == null) ? 0 : filial.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((tamanho == null) ? 0 : tamanho.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Categoria) {
			Categoria categoria = (Categoria) obj;
			if(nome.equals(categoria.getNome()))
				return true;
			
		}
		return super.equals(obj);
	}
}

