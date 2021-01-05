package br.com.VeiculosPajeu.Exception;

@SuppressWarnings("serial")
public class ValidationException extends Exception{

	public ValidationException(String message) {
        super(message.toUpperCase());
    }
    
    
}
