package br.com.VeiculosPajeu.Exception;

@SuppressWarnings("serial")
public class BusinessException extends Exception{

    public BusinessException(String message) {
        super(message.toUpperCase());
    }
    
    
}
