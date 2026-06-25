package com.gourmetconnect.api.dto;

import lombok.Data;

@Data
public class SaveItemDTO {
    private String userId;
    private String postId;
    private String collectionName;
}
