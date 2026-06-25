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
@Document(collection = "messages")
public class Message {

    @Id
    private String id;

    @Indexed
    @Field("chat_id")
    private String chatId;

    @Indexed
    @Field("sender_id")
    private String senderId;

    private String content;

    @Indexed
    @Field("created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Field("is_read")
    private boolean isRead = false;
}
