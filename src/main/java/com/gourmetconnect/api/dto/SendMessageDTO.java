package com.gourmetconnect.api.dto;

import lombok.Data;

@Data
public class SendMessageDTO {
    private String senderId;
    private String text;
}
