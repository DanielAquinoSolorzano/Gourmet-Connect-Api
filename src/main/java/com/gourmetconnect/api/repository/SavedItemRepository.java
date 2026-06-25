package com.gourmetconnect.api.repository;

import com.gourmetconnect.api.model.SavedItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface SavedItemRepository extends MongoRepository<SavedItem, String> {
    List<SavedItem> findByUserIdOrderBySavedAtDesc(String userId);
    List<SavedItem> findByUserIdAndCollectionNameOrderBySavedAtDesc(String userId, String collectionName);
}
