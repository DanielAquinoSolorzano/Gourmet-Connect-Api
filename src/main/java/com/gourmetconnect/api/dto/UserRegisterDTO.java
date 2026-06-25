package com.gourmetconnect.api.dto;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private String username;
    private String email;
    private String passwordHash;
    private String fullName;
    private String bio;
    private String avatarUrl;
    private double longitude;
    private double latitude;
}