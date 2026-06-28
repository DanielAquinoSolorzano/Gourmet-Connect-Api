package com.gourmetconnect.api.repository;

import com.gourmetconnect.api.model.SavedPost;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface SavedPostRepository extends MongoRepository<SavedPost, String> {
    List<SavedPost> findByUserIdOrderBySavedAtDesc(String userId);
    List<SavedPost> findByUserIdAndCollectionNameOrderBySavedAtDesc(String userId, String collectionName);
    Optional<SavedPost> findByUserIdAndPostId(String userId, String postId);
}
