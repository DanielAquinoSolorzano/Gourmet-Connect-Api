package com.gourmetconnect.api.repository;

import com.gourmetconnect.api.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findByChatIdOrderByCreatedAtDesc(String chatId);
    List<Message> findByChatIdAndIsReadFalse(String chatId);
}
