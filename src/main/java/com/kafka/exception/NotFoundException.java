package com.kafka.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(ErrorMessage message) {
        super(message.getMessage());
    }
}
