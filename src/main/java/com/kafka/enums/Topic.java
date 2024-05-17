package com.kafka.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Topic {
    SMS_TOPIC("sms"),
    NOTIFICATION_TOPIC("notification");


    private final String name;
}
