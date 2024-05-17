package com.kafka.service.impl;

import com.kafka.dto.NotificationDto;
import com.kafka.enums.Topic;
import com.kafka.exception.ErrorMessage;
import com.kafka.exception.MessageType;
import com.kafka.exception.NotFoundException;
import com.kafka.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public ResponseEntity<?> sendMessage(NotificationDto notificationDto) {
        try {
                kafkaTemplate.send(Topic.SMS_TOPIC.getName(), notificationDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Sms Successfully created");
        } catch (Exception e) {
//            e.printStackTrace();
            throw new NotFoundException(new ErrorMessage(MessageType.ERROR, HttpStatus.NOT_FOUND, "Sms not send."));
        }
    }

    @Override
    public ResponseEntity<?> sendNotification(NotificationDto notificationDto) {
        for (int i = 0; i < 1; i++) {
            CompletableFuture<SendResult<String, Object>> completableFuture = kafkaTemplate.send(Topic.NOTIFICATION_TOPIC.getName(), notificationDto);
            completableFuture.whenComplete((stringObjectSendResult, throwable) -> {
                if (throwable == null) {
                    log.info("Response>>" + stringObjectSendResult.toString());
                } else {
                    log.error("throwable>>" + throwable);
                    throw new NotFoundException(new ErrorMessage(MessageType.ERROR, HttpStatus.NOT_FOUND, "Notification not send."));
                }
            });
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Notification Successfully created.");
    }
}
