package com.kafka.controller;

import com.kafka.dto.NotificationDto;
import com.kafka.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notificationController")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("send-message")
    public ResponseEntity<?> sendMessage(@RequestBody NotificationDto notificationDto) {
        return ResponseEntity.ok().body(notificationService.sendMessage(notificationDto));
    }

    @PostMapping("send-notification")
    public ResponseEntity<?> sendNotification(@RequestBody NotificationDto notificationDto) {
        return ResponseEntity.ok().body(notificationService.sendNotification(notificationDto));
    }
}
