package com.kafka.entity;

import com.kafka.enums.NotificationType;
import com.kafka.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    public Notification(Status status, NotificationType notificationType, String message) {
        this.status = status;
        this.notificationType = notificationType;
        this.message = message;
        this.createdDate = LocalDateTime.now();
    }

    public Notification(Status status, NotificationType notificationType, String message, long totalNotification) {
        this.status = status;
        this.notificationType = notificationType;
        this.message = message;
        this.totalNotification = totalNotification;
        this.createdDate = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotBlank
    @Lob
    private String message;

    @NotNull
    private LocalDateTime createdDate;

    @NotNull
    private long totalNotification;
}
