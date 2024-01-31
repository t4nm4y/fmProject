package com.flexmoney.projectfm.exception;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleNotFoundException(MethodArgumentNotValidException ex){
        return new ResponseEntity<>(ex.getDetailMessageArguments(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CannotGetJdbcConnectionException.class)
    public ResponseEntity handleNotFoundException(CannotGetJdbcConnectionException ex){
        return new ResponseEntity<>("Can't connect to the database server! Make sure its running.",HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity handleNotFoundException(DuplicateKeyException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

}
