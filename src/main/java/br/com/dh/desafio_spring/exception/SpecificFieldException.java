package br.com.dh.desafio_spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SpecificFieldException extends RuntimeException{
    public SpecificFieldException(String message) {
        super(message);
    }
}
