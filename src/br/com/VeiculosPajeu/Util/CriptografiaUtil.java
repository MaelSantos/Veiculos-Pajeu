package br.com.VeiculosPajeu.Util;

import java.util.Base64;

public class CriptografiaUtil {

	private static CriptografiaUtil instance;

	private CriptografiaUtil() {
	}

	public static synchronized CriptografiaUtil getInstance() {
		if (instance == null)
			instance = new CriptografiaUtil();
		return instance;
	}

	public String criptografar(byte[] bs) {
		return Base64.getEncoder().encodeToString(bs);
	}

	public String descriptografar(String senha) {
		byte[] decodificar = Base64.getDecoder().decode(senha);
		String decodificado = new String(decodificar);
		return decodificado;
	}

	public boolean isCriptografado(String senha) {
		boolean is64 = senha.matches("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$");
		try {
			if (is64) {
				senha = descriptografar(senha);
				is64 = senha.matches("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$");
			}
		} catch (Exception e) {
			is64 = false;
		}

		return is64;
	}

	public static void main(String[] args) {
		
		CriptografiaUtil criptografiaUtil = getInstance();

		String senha = "mael";

		System.out.println(criptografiaUtil.isCriptografado(senha));
		System.out.println(criptografiaUtil.isCriptografado(criptografiaUtil.criptografar(senha.getBytes())));
		System.out.println(criptografiaUtil.descriptografar(senha));
	}

}
