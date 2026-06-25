package com.gourmetconnect.api.service;

import com.gourmetconnect.api.model.Message;
import com.gourmetconnect.api.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public Message sendMessage(Message message) {
        return messageRepository.save(message);
    }

    public Optional<Message> getMessageById(String id) {
        return messageRepository.findById(id);
    }

    public List<Message> getChatMessages(String chatId) {
        return messageRepository.findByChatIdOrderByCreatedAtDesc(chatId);
    }

    public List<Message> getUnreadMessages(String chatId) {
        return messageRepository.findByChatIdAndIsReadFalse(chatId);
    }

    public Message markAsRead(String id) {
        Optional<Message> message = messageRepository.findById(id);
        if (message.isPresent()) {
            Message m = message.get();
            m.setRead(true);
            return messageRepository.save(m);
        }
        return null;
    }

    public void deleteMessage(String id) {
        messageRepository.deleteById(id);
    }
}
