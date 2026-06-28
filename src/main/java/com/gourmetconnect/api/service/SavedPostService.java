package com.gourmetconnect.api.service;

import com.gourmetconnect.api.model.SavedPost;
import com.gourmetconnect.api.repository.SavedPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SavedPostService {

    private final SavedPostRepository savedPostRepository;

    public SavedPost savePost(SavedPost savedPost) {
        return savedPostRepository.save(savedPost);
    }

    public Optional<SavedPost> getSavedPostById(String id) {
        return savedPostRepository.findById(id);
    }

    public List<SavedPost> getUserSavedPosts(String userId) {
        return savedPostRepository.findByUserIdOrderBySavedAtDesc(userId);
    }

    public List<SavedPost> getUserSavedPostsByCollection(String userId, String collectionName) {
        return savedPostRepository.findByUserIdAndCollectionNameOrderBySavedAtDesc(userId, collectionName);
    }

    public void removeSavedPost(String id) {
        savedPostRepository.deleteById(id);
    }
}
