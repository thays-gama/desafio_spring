package br.com.dh.desafio_spring.exception;

public class ServerException extends RuntimeException{
    public ServerException(String message) {
        super(message);
    }
}
