package com.kafka.exception;

import lombok.Getter;

@Getter

public enum MessageType {
    NOPE, SUCCESS, WARNING, ERROR, INFORMATION
}
