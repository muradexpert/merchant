package com.bestcommerce.authserver.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotActivatedException.class)
    public ResponseEntity<ExceptionResponse> userNotActivated(UserNotActivatedException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("UNAUTHORIZED");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ExceptionResponse> handleConstraintViolation(
            ConstraintViolationException ex,WebRequest request)
    {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("Constraint Violations");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {

            ExceptionResponse response = new ExceptionResponse();
            response.setErrorCode("Argument Type Mismatch");
            response.setErrorMessage(ex.getMessage());
            response.setTimestamp(LocalDateTime.now());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

}
