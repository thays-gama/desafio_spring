package br.com.dh.desafio_spring.exception;

public class AlreadyExistingException extends RuntimeException{
    public AlreadyExistingException(String message) {
        super(message);
    }
}
