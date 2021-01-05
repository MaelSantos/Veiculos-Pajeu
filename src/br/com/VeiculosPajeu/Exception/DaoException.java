package br.com.VeiculosPajeu.Exception;

@SuppressWarnings("serial")
public class DaoException extends Exception{

	public DaoException(String mensagem) {
		super(mensagem.toUpperCase());
	}
	
}
