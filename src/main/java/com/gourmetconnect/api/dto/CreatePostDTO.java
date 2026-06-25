package com.gourmetconnect.api.dto;

import lombok.Data;

@Data
public class CreatePostDTO {
    private String userId;
    private String content;
    private String mediaUrl;
}
