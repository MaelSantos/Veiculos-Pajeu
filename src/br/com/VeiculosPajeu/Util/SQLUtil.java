package br.com.VeiculosPajeu.Util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import br.com.VeiculosPajeu.Entidade.Entidade;
import br.com.VeiculosPajeu.Entidade.Financeiro;

public enum SQLUtil {

	// buscas normais
	USUARIO_LOGIN("select a from Usuario a where a.login = :login and a.senha = :senha"),

	VEICULO_POR_CATEGORIA(
			"select a from Veiculo a where a.categoria = :trocar AND a.manutencao = :manutencao AND a.alugado = :alugado"),
	VEICULO_DISPONIVEL(
			"SELECT a FROM Veiculo a WHERE (LOWER(str(a.fabricante)) LIKE :trocar OR LOWER(str(a.modelo)) LIKE :trocar OR LOWER(str(a.numero_passageiros)) LIKE :trocar OR LOWER(str(a.numero_portas)) LIKE :trocar OR LOWER(str(a.ano_fabricacao)) LIKE :trocar OR LOWER(str(a.ano_modelo)) LIKE :trocar OR LOWER(str(a.placa)) LIKE :trocar OR LOWER(str(a.numero_chassi)) LIKE :trocar OR LOWER(str(a.numero_motor)) LIKE :trocar OR LOWER(str(a.cor)) LIKE :trocar OR LOWER(str(a.torque_motor)) LIKE :trocar OR LOWER(str(a.tipo_combustivel)) LIKE :trocar OR LOWER(str(a.categoria)) LIKE :trocar) AND a.manutencao = :manutencao AND a.alugado = :alugado"),

	RESERVA_ATIVA(
			"SELECT a FROM Reserva a WHERE (LOWER(str(a.tipo_locacao)) LIKE :trocar OR LOWER(str(a.data_locacao)) LIKE :trocar OR LOWER(str(a.data_devolucao)) LIKE :trocar OR LOWER(str(a.data_reserva)) LIKE :trocar OR LOWER(str(a.hora_reserva)) LIKE :trocar OR LOWER(str(a.cliente)) LIKE :trocar OR LOWER(str(a.categoria)) LIKE :trocar) AND a.ativo = :ativo"),
	LOCACAO_ATIVA(
			"SELECT a FROM Locacao a WHERE (LOWER(str(a.diaria)) LIKE :trocar OR LOWER(str(a.valor_total)) LIKE :trocar OR LOWER(str(a.tipoLocacao)) LIKE :trocar OR LOWER(str(a.data_locacao)) LIKE :trocar OR LOWER(str(a.data_devolucao)) LIKE :trocar OR LOWER(str(a.cliente)) LIKE :trocar OR LOWER(str(a.veiculo)) LIKE :trocar OR LOWER(str(a.filial_locacao)) LIKE :trocar OR LOWER(str(a.filial_devolucao)) LIKE :trocar) AND a.ativo = :ativo"),

	FINANCEIRO_ESTADO(
			"Select a From Financeiro a WHERE (a.data_aberta between :de AND :ate) AND LOWER(str(a.estado)) LIKE :estado"),

	// view
	RESERVA_VENCIDA("SELECT a FROM ReservaView a"), LOCACAO_VENCIDA("SELECT a FROM LocacaoView a"),

	VEICULO_DATA_FUTURA(
			"SELECT v from Veiculo v, Locacao l WHERE l.ativo = true AND l.data_devolucao < :data AND v = l.veiculo AND filial = :filial"),
	VEICULO_DATA_CORRENTE(
			"SELECT v from Veiculo v WHERE v.alugado = false AND v.manutencao = false AND filial = :filial"),

	AUTOMOVEL_MANUTENCAO("SELECT a FROM AutomovelView a"), CARGA_MANUTENCAO("SELECT a FROM CargaView a"),
	PASSAGEIROS_MANUTENCAO("SELECT a FROM PassageirosView a"),

	// Relatório
	LOCACAO_PERIODO("SELECT a FROM Locacao a WHERE a.data_locacao BETWEEN :inicio AND :fim"),
	LOCACAO_CLIENTE("SELECT a FROM Locacao a WHERE a.cliente = :cliente"),
	RESERVA_PERIODO("SELECT a FROM Reserva a WHERE a.data_reserva BETWEEN :inicio AND :fim"),

	LOG("Select a From Log a WHERE a.data = :data AND a.alteracao LIKE :alteracao")

	;

	private String sql;

	private SQLUtil(String sql) {
		this.sql = sql;
	}

	@Override
	public String toString() {

		return sql;
	}

	public static String buscaPorBusca(Class<? extends Entidade> classe) {
		String sql = "SELECT a FROM " + classe.getName() + " a WHERE ";

		List<Field> fields = new ArrayList<>();
		Iterator<Field> iteratorSuperClass = Arrays.asList(classe.getSuperclass().getDeclaredFields()).iterator();

		if (classe.getSuperclass() != Entidade.class) {
			while (iteratorSuperClass.hasNext()) {
				Field t = iteratorSuperClass.next();
				if (!t.getName().equalsIgnoreCase("serialVersionUID") && !t.getName().equalsIgnoreCase("sequence"))
					fields.add(t);
			}
		}

		Iterator<Field> iteratorClass = Arrays.asList(classe.getDeclaredFields()).iterator();
		while (iteratorClass.hasNext()) {
			Field t = iteratorClass.next();
			if (!t.getName().equalsIgnoreCase("serialVersionUID") && !t.getName().equalsIgnoreCase("sequence"))
				fields.add(t);
		}

		Iterator<Field> iteratorField = fields.iterator();
		int i = 0;
		while (iteratorField.hasNext()) {
			Field f = iteratorField.next();

			if (i == fields.size() - 1)
				sql += "LOWER(str(a." + f.getName() + ")) LIKE :trocar ";
			else
				sql += "LOWER(str(a." + f.getName() + ")) LIKE :trocar OR ";
			i++;
		}

		System.out.println(sql);
		return sql;
	}

	public static void main(String[] args) {

		buscaPorBusca(Financeiro.class);
	}

}
