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
@Document(collection = "saved_posts")
@CompoundIndexes({
    @CompoundIndex(name = "user_post_idx", def = "{'user_id': 1, 'post_id': 1}", unique = true)
})
public class SavedPost {

    @Id
    private String id;

    @Indexed
    @Field("user_id")
    private String userId;

    @Field("collection_name")
    private String collectionName = "default";

    @Indexed
    @Field("post_id")
    private String postId;

    @Field("saved_at")
    private LocalDateTime savedAt = LocalDateTime.now();
}
