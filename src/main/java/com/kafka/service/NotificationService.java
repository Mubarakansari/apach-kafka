package com.kafka.service;

import com.kafka.dto.NotificationDto;
import org.springframework.http.ResponseEntity;

public interface NotificationService {
    ResponseEntity<?> sendMessage(NotificationDto notificationDto);

    ResponseEntity<?> sendNotification(NotificationDto notificationDto);
}
