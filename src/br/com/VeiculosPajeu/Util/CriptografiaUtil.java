package br.com.VeiculosPajeu.Util;

import java.util.Base64;

public class CriptografiaUtil {

	public static String criptografar(byte[] bs) {
		return Base64.getEncoder().encodeToString(bs);
	}

	public static String descriptografar(String senha) {
		byte[] decodificar = Base64.getDecoder().decode(senha);
		String decodificado = new String(decodificar);
		return decodificado;
	}

	public static boolean isCriptografado(String senha) {
		boolean is64 = senha.matches("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$");
		try {
			if (is64)
			{
				senha = descriptografar(senha);
				is64 = senha.matches("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$");				
			}
		} catch (Exception e) {
			is64 = false;
		}

		return is64;
	}

	public static void main(String[] args) {
		
		String senha = "mael";
		
		System.out.println(isCriptografado(senha));
		System.out.println(isCriptografado(criptografar(senha.getBytes())));
		System.out.println(descriptografar(senha));
	}

}
