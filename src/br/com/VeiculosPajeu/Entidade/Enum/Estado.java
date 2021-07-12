package br.com.VeiculosPajeu.Entidade.Enum;

import java.util.Arrays;
import java.util.Iterator;

public enum Estado {

	ACRE("Acre", "AC"), ALAGOAS("Alagoas", "AL"), AMAPA("Amapá", "AP"), AMAZONAS("Amazonas", "AM"),
	BAHIA("Bahia", "BA"), CEARA("Ceará", "CE"), DISTRITO_FERERAL("Distrito Federal", "DF"),
	ESPIRITO_SANTO("Espírito Santo", "ES"), GOIAS("Goiás", "GO"), MARANAO("Maranhão", "MA"),
	MATO_GROSSO("Mato Grosso", "MT"), MATO_GROSSO_DO_SUL("Mato Grosso do Sul", "MS"),
	MINAS_GERAIS("Minas Gerais", "MG"), PARA("Pará", "PA"), PARAIBA("Paraíba", "PB"), PARANA("Paraná", "PR"),
	PERNAMBUCO("Pernambuco", "PE"), PIAUL("Piauí", "PI"), RORAIMA("Roraima", "RR"), RONDONIA("Rondônia", "RO"),
	RIO_DE_JANEIRO("Rio de Janeiro", "RJ"), RIO_GRANDE_DO_NORTE("Rio Grande do Norte", "RN"),
	RIO_GRANDE_DO_SUL("Rio Grande do Sul", "RS"), SANTA_CATARINA("Santa Catarina", "SC"), SAO_PAULO("São Paulo", "SP"),
	SERGIPE("Sergipe", "SE"), TONANTINS("Tocantins", "TO");

	private String nome, sigla;

	Estado(String nome, String sigla) {
		this.nome = nome;
		this.sigla = sigla;
	}

	public static Estado getEstado(String estado) {

		Iterator<Estado> iterator = Arrays.asList(values()).iterator();
		while (iterator.hasNext()) {
			Estado e = iterator.next();
			if (e.toString().equalsIgnoreCase(estado))
				return e;
		}
		return null;
	}

	@Override
	public String toString() {
		return nome + " - " + sigla;
	}
}
