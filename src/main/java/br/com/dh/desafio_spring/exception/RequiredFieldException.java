package br.com.dh.desafio_spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredFieldException extends Exception{
    public RequiredFieldException(String message) {
        super(message);
    }
}
