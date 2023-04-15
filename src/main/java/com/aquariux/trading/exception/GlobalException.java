package com.aquariux.trading.exception;

import com.aquariux.trading.model.Error;
import jakarta.persistence.NoResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler
    public ResponseEntity<Error> handleInvalidArgumentException(IllegalArgumentException ex) {
        Error error = new Error();
        error.setMessage("Id or UserId was invalid");

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Error> handleNoResultException(NoResultException ex) {
        Error error = new Error();
        error.setMessage("Symbol was invalid");

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
