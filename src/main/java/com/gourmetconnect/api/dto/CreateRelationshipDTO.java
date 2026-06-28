package com.gourmetconnect.api.dto;

import lombok.Data;

@Data
public class CreateRelationshipDTO {
    private String followerId;
    private String followedId;
}
