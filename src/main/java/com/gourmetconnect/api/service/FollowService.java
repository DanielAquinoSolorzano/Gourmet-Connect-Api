package com.gourmetconnect.api.service;

import com.gourmetconnect.api.model.Follow;
import com.gourmetconnect.api.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    public Follow followUser(Follow follow) {
        return followRepository.save(follow);
    }

    public Optional<Follow> getFollowById(String id) {
        return followRepository.findById(id);
    }

    public List<Follow> getFollowers(String userId) {
        return followRepository.findByFollowedIdOrderByCreatedAtDesc(userId);
    }

    public List<Follow> getFollowing(String userId) {
        return followRepository.findByFollowerIdOrderByCreatedAtDesc(userId);
    }

    public void unfollowUser(String id) {
        followRepository.deleteById(id);
    }

    public Optional<Follow> findFollow(String followerId, String followedId) {
        return followRepository.findByFollowerIdAndFollowedId(followerId, followedId);
    }
}
