package com.gourmetconnect.api.service;

import com.gourmetconnect.api.model.Chat;
import com.gourmetconnect.api.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }

    public Optional<Chat> getChatById(String id) {
        return chatRepository.findById(id);
    }

    public List<Chat> getUserChats(String userId) {
        return chatRepository.findByParticipantsContainingOrderByLastMessageAtDesc(userId);
    }

    public Chat updateChat(String id, Chat chat) {
        chat.setId(id);
        return chatRepository.save(chat);
    }

    public void deleteChat(String id) {
        chatRepository.deleteById(id);
    }
}
