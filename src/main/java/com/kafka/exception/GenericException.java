package com.kafka.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GenericException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> genericException(NotFoundException e) {
        ErrorMessage errorMessage = new ErrorMessage(MessageType.ERROR, HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
