package br.com.dh.desafio_spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class HandlerExceptions {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundException(NotFoundException ex){
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("Objeto não encontrado")
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity<ExceptionDetails> handlerEmailAlreadyRegisteredException(EmailAlreadyRegisteredException ex){
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("Email já cadastrado")
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RequiredFieldException.class)
    public ResponseEntity<ExceptionDetails> handlerRequiredFieldException(RequiredFieldException ex){
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("Preencha todos os atributos")
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<ExceptionDetails> handlerOutOfStockException(OutOfStockException ex){
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("Estoque insuficiente")
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(AlreadyExistingException.class)
    public ResponseEntity<ExceptionDetails> handlerAlreadyExistingException(AlreadyExistingException ex){
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("Dados já cadastrados")
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(ServerException.class)
    public ResponseEntity<ExceptionDetails> handlerServerException(ServerException ex){
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("Ocorreu um erro no servidor, tente novamente mais tarde")
                .message(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
