package com.gourmetconnect.api.model.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStats {

    @Field("followers_count")
    private int followersCount = 0;

    @Field("following_count")
    private int followingCount = 0;

    @Field("posts_count")
    private int postsCount = 0;
}
