package com.example.familymemberapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FamilyNotFoundException.class)
    public ResponseEntity<Object> handleFamilyNotFoundException(FamilyNotFoundException exception) {
        return new ResponseEntity<>("Family with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }
}
