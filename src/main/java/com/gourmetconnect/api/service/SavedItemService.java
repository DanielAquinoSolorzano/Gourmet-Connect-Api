package com.gourmetconnect.api.service;

import com.gourmetconnect.api.model.SavedItem;
import com.gourmetconnect.api.repository.SavedItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SavedItemService {

    private final SavedItemRepository savedItemRepository;

    public SavedItem saveItem(SavedItem savedItem) {
        return savedItemRepository.save(savedItem);
    }

    public Optional<SavedItem> getSavedItemById(String id) {
        return savedItemRepository.findById(id);
    }

    public List<SavedItem> getUserSavedItems(String userId) {
        return savedItemRepository.findByUserIdOrderBySavedAtDesc(userId);
    }

    public List<SavedItem> getUserSavedItemsByCollection(String userId, String collectionName) {
        return savedItemRepository.findByUserIdAndCollectionNameOrderBySavedAtDesc(userId, collectionName);
    }

    public void deleteSavedItem(String id) {
        savedItemRepository.deleteById(id);
    }
}
