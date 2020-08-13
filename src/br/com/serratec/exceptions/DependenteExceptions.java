package br.com.serratec.exceptions;

public class DependenteExceptions extends RuntimeException{

    public DependenteExceptions(String message) {
        super(message);
    }

    public DependenteExceptions(Throwable cause) {
        super(cause);
    }

    public DependenteExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    
}