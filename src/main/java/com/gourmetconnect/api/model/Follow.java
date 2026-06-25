package com.gourmetconnect.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "follows")
@CompoundIndexes({
    @CompoundIndex(name = "follower_followed_idx", def = "{'follower_id': 1, 'followed_id': 1}", unique = true)
})
public class Follow {

    @Id
    private String id;

    @Indexed
    @Field("follower_id")
    private String followerId;

    @Indexed
    @Field("followed_id")
    private String followedId;

    @Field("created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
