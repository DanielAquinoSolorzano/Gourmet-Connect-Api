package com.gourmetconnect.api.dto;

import lombok.Data;

@Data
public class SavePostDTO {
    private String userId;
    private String postId;
    private String collectionName;
}
