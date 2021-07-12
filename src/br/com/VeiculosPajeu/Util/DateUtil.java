package br.com.VeiculosPajeu.Util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtil {

	private static DateUtil instance;

	private DateUtil() {
	}

	public static synchronized DateUtil getInstance() {
		if (instance == null)
			instance = new DateUtil();
		return instance;
	}

	public long DiferencaDias(LocalDate primeira, LocalDate segunda) {
		long dias;

		dias = primeira.until(segunda, ChronoUnit.DAYS);

		return dias;
	}

	public int DiferencaAnos(LocalDate primeira, LocalDate segunda) {
		int anos;

		int atual = primeira.getYear();
		int passada = segunda.getYear();

		anos = passada - atual;

		return anos;
	}

	public long DiferencaDias(LocalDate data) {
		long dias;

		LocalDate atual = LocalDate.now();
		dias = data.until(atual, ChronoUnit.DAYS);

		return dias;
	}

	public int DiferencaAnos(LocalDate data) {
		int anos;

		int atual = LocalDate.now().getYear();
		int passada = data.getYear();

		anos = atual - passada;

		return anos;
	}

	public static void main(String[] args) {

		DateUtil dateUtil = getInstance();

		LocalDate locacao = LocalDate.of(2019, 1, 20);
		LocalDate devolucao = LocalDate.of(2019, 1, 27);

		System.out.println(dateUtil.DiferencaDias(locacao, devolucao));

	}
}
