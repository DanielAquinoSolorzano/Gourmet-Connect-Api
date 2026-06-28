package com.gourmetconnect.api.repository;

import com.gourmetconnect.api.model.Relationship;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface RelationshipRepository extends MongoRepository<Relationship, String> {
    List<Relationship> findByFollowedIdOrderByCreatedAtDesc(String followedId);
    List<Relationship> findByFollowerIdOrderByCreatedAtDesc(String followerId);
    Optional<Relationship> findByFollowerIdAndFollowedId(String followerId, String followedId);
}
