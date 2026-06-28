package com.gourmetconnect.api.repository;

import com.gourmetconnect.api.model.Story;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface StoryRepository extends MongoRepository<Story, String> {
    List<Story> findByAuthorIdOrderByCreatedAtDesc(String authorId);
}
