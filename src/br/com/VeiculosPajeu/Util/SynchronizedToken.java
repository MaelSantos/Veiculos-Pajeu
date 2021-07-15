package br.com.VeiculosPajeu.Util;

import java.util.HashMap;
import java.util.Random;

public class SynchronizedToken {

	private static SynchronizedToken instance;

	private HashMap<String, String> tokens;

	private SynchronizedToken() {
		tokens = new HashMap<>();
	}

	public static SynchronizedToken getInstance() {
		if (instance == null)
			instance = new SynchronizedToken();
		return instance;
	}

	public String gerarToken() {

		String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZ0123456789abcdefghijklmnopqrstuvwxyz";

		Random random = new Random();

		String chave = "";
		int index = -1;
		for (int i = 0; i < 9; i++) {
			index = random.nextInt(letras.length());
			chave += letras.substring(index, index + 1);
		}

		return chave;
	}

	public boolean addToken(String chave, String valor) {

		String antigoValor = tokens.put(chave, valor);
		System.out.println(tokens);
		
		if (antigoValor != null && antigoValor.equals(valor)) {
			return false;
		}
		return true;
	}

	public void removerToken(String chave) {
		tokens.remove(chave);
		System.out.println(tokens);
	}

	public boolean isToken(String token) {
		return tokens.containsValue(token);
	}

//	public static void main(String[] args) {
//
//		SynchronizedToken syncr = SynchronizedToken.getInstance();
//
//		String chave = syncr.gerarToken();
//		System.out.println(chave);
//
//		System.out.println(syncr.addToken(chave, "Mael"));
//		System.out.println(syncr.addToken(chave, "Mael"));
//		System.out.println(syncr.addToken(chave, "Mael"));
//
//		System.out.println(syncr.tokens);
//	}

}
