package com.gourmetconnect.api.repository;

import com.gourmetconnect.api.model.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ChatRepository extends MongoRepository<Chat, String> {
    List<Chat> findByParticipantsContainingOrderByLastMessageAtDesc(String userId);
}
