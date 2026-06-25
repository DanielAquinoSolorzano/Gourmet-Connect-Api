package com.gourmetconnect.api.repository;

import com.gourmetconnect.api.model.Follow;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface FollowRepository extends MongoRepository<Follow, String> {
    List<Follow> findByFollowedIdOrderByCreatedAtDesc(String followedId);
    List<Follow> findByFollowerIdOrderByCreatedAtDesc(String followerId);
    Optional<Follow> findByFollowerIdAndFollowedId(String followerId, String followedId);
}
