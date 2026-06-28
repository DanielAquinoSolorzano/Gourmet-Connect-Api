package com.gourmetconnect.api.dto;

import lombok.Data;

@Data
public class CreateStoryDTO {
    private String authorId;
    private String mediaUrl;
    private String type;
}
