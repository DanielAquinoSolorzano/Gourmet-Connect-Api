package com.gourmetconnect.api.dto;

import lombok.Data;

@Data
public class CreateMessageDTO {
    private String chatId;
    private String senderId;
    private String content;
}
