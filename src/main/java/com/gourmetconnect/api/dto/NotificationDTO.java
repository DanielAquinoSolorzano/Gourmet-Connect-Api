package com.gourmetconnect.api.dto;

import lombok.Data;

@Data
public class NotificationDTO {
    private String recipientId;
    private String senderId;
    private String type;
    private String postId;
}
