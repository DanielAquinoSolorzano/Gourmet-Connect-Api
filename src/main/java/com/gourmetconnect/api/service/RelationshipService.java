package com.gourmetconnect.api.service;

import com.gourmetconnect.api.model.Relationship;
import com.gourmetconnect.api.repository.RelationshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RelationshipService {

    private final RelationshipRepository relationshipRepository;

    public Relationship followUser(Relationship relationship) {
        return relationshipRepository.save(relationship);
    }

    public Optional<Relationship> getRelationshipById(String id) {
        return relationshipRepository.findById(id);
    }

    public List<Relationship> getFollowers(String userId) {
        return relationshipRepository.findByFollowedIdOrderByCreatedAtDesc(userId);
    }

    public List<Relationship> getFollowing(String userId) {
        return relationshipRepository.findByFollowerIdOrderByCreatedAtDesc(userId);
    }

    public void unfollowUser(String id) {
        relationshipRepository.deleteById(id);
    }
}
