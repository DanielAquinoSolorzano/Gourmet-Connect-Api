package com.gourmetconnect.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "notifications")
public class Notification {

    @Id
    private String id;

    @Indexed
    @Field("recipient_id")
    private String recipientId;

    private String type; // new_follow, new_message, post_liked, etc

    private String content;

    @Field("is_read")
    private boolean isRead = false;

    @Indexed
    @Field("created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Field("expires_at")
    private LocalDateTime expiresAt; // For TTL index in MongoDB
}
