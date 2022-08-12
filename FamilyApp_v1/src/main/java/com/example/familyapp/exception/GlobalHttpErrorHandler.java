package com.example.familyapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FamilyNotFoundException.class)
    public ResponseEntity<Object> handleFamilyNotFoundException(FamilyNotFoundException exception) {
        return new ResponseEntity<>("correct data please", HttpStatus.BAD_REQUEST);
    }
}
