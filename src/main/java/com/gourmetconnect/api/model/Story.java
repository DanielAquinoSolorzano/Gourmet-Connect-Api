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
@Document(collection = "stories")
public class Story {

    @Id
    private String id;

    @Indexed
    @Field("author_id")
    private String authorId;

    @Field("media_url")
    private String mediaUrl;

    private String type;

    @Field("created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Indexed(expireAfterSeconds = 0)
    @Field("expires_at")
    private LocalDateTime expiresAt = LocalDateTime.now().plusHours(24);
}
