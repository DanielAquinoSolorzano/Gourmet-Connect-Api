package com.gourmetconnect.api.model.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostComment {
    @Field("user_id")
    private String userId;

    private String text;

    @Field("created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
