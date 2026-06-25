package com.gourmetconnect.api.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NotificationDTO {
    private String recipientId;
    private String type;
    private String content;
    private LocalDateTime expiresAt;
}
