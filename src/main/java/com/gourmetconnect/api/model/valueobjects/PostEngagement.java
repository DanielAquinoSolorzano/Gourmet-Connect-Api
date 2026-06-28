package com.gourmetconnect.api.model.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostEngagement {
    @Field("likes_count")
    private int likesCount = 0;

    @Field("comments_count")
    private int commentsCount = 0;

    @Field("shares_count")
    private int sharesCount = 0;
}
