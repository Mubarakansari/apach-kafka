package com.kafka.service.impl;

import com.kafka.dto.NotificationDto;
import com.kafka.entity.Notification;
import com.kafka.enums.NotificationType;
import com.kafka.enums.Status;
import com.kafka.exception.ErrorMessage;
import com.kafka.exception.MessageType;
import com.kafka.exception.NotFoundException;
import com.kafka.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;

    @RetryableTopic(attempts = "4")
    @KafkaListener(topics = "sms", groupId = "sms-group")
    public void onSms(@Payload NotificationDto data, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                      @Header(KafkaHeaders.OFFSET) int offset) {
        log.info("sms data>>" + data);
        try {
            if (data.notificationType.equals(NotificationType.EMAIl)) {
                throw new RuntimeException("Invalid data..............");
            }
            notificationRepository.save(new Notification(Status.SUCCESS, data.notificationType, data.getMessage(), notificationRepository.count() + 1L));
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException(new ErrorMessage(MessageType.WARNING, HttpStatus.NOT_FOUND, e.getMessage()));
        }
    }

    @DltHandler
    public void listenDLT(@Payload NotificationDto data, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                          @Header(KafkaHeaders.OFFSET) int offset) {
        notificationRepository.save(new Notification(Status.FAILED, data.notificationType, data.getMessage()));
    }


    @KafkaListener(topics = "notification", groupId = "notification-group")
    public void onNotification(@Payload NotificationDto data, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                               @Header(KafkaHeaders.OFFSET) int offset) {
        log.info("Received message [{}] from group1, partition-{} with offset-{}", data, partition, offset);
        log.info("notification number>>" + notificationRepository.count() + 1L);
        try {
            notificationRepository.save(new Notification(Status.SUCCESS, data.notificationType, data.getMessage(), notificationRepository.count() + 1L));
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException(new ErrorMessage(MessageType.WARNING, HttpStatus.NOT_FOUND, e.getMessage()));
        }
    }
}
