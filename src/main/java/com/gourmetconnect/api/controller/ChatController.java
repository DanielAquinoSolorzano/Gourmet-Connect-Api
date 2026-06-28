package com.gourmetconnect.api.controller;

import com.gourmetconnect.api.dto.CreateChatDTO;
import com.gourmetconnect.api.dto.SendMessageDTO;
import com.gourmetconnect.api.model.Chat;
import com.gourmetconnect.api.model.Message;
import com.gourmetconnect.api.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/chats")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Tag(name = "Chats", description = "Endpoints para gestionar conversaciones")
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    @Operation(summary = "Crear nuevo chat")
    public ResponseEntity<Chat> createChat(@RequestBody CreateChatDTO dto) {
        Chat chat = new Chat();
        chat.setParticipants(dto.getParticipants());
        chat.setGroup(dto.isGroup());
        chat.setLastMessageAt(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(chatService.createChat(chat));
    }

    @PostMapping("/{chatId}/messages")
    @Operation(summary = "Enviar mensaje en un chat")
    public ResponseEntity<Chat> sendMessage(@PathVariable String chatId, @RequestBody SendMessageDTO dto) {
        Message message = new Message();
        message.setSenderId(dto.getSenderId());
        message.setText(dto.getText());
        message.setSentAt(LocalDateTime.now());

        Chat updatedChat = chatService.sendMessage(chatId, message);
        if (updatedChat != null) {
            return ResponseEntity.ok(updatedChat);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener chat por ID")
    public ResponseEntity<Chat> getChat(@PathVariable String id) {
        return chatService.getChatById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Obtener chats de un usuario")
    public ResponseEntity<List<Chat>> getUserChats(@PathVariable String userId) {
        return ResponseEntity.ok(chatService.getUserChats(userId));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar chat")
    public ResponseEntity<Chat> updateChat(@PathVariable String id, @RequestBody Chat chat) {
        return ResponseEntity.ok(chatService.updateChat(id, chat));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar chat")
    public ResponseEntity<Void> deleteChat(@PathVariable String id) {
        chatService.deleteChat(id);
        return ResponseEntity.noContent().build();
    }
}
