package com.kafka.dto;

import com.kafka.enums.NotificationType;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NotificationDto {

    @NotNull
    public NotificationType notificationType;

    @NotBlank
    @Lob
    private String message;
}
