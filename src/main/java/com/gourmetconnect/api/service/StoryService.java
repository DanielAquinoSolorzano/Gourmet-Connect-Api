package com.gourmetconnect.api.service;

import com.gourmetconnect.api.model.Story;
import com.gourmetconnect.api.repository.StoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoryService {

    private final StoryRepository storyRepository;

    public Story createStory(Story story) {
        return storyRepository.save(story);
    }

    public Optional<Story> getStoryById(String id) {
        return storyRepository.findById(id);
    }

    public List<Story> getUserStories(String authorId) {
        return storyRepository.findByAuthorIdOrderByCreatedAtDesc(authorId);
    }

    public void deleteStory(String id) {
        storyRepository.deleteById(id);
    }
}
