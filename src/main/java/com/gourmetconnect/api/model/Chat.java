package com.gourmetconnect.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "chats")
public class Chat {

    @Id
    private String id;

    private List<String> participants = new ArrayList<>();

    @Field("is_group")
    private boolean isGroup = false;

    @Indexed
    @Field("created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Indexed
    @Field("last_message_at")
    private LocalDateTime lastMessageAt;
}
