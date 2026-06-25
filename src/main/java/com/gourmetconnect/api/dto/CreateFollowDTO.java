package com.gourmetconnect.api.dto;

import lombok.Data;

@Data
public class CreateFollowDTO {
    private String followerId;
    private String followedId;
}
