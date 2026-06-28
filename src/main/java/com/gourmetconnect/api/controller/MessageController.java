package com.gourmetconnect.api.controller;

import com.gourmetconnect.api.dto.CreateMessageDTO;
import com.gourmetconnect.api.model.Message;
import com.gourmetconnect.api.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Tag(name = "Messages", description = "Endpoints para gestionar mensajes")
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    @Operation(summary = "Enviar mensaje")
    public ResponseEntity<Message> sendMessage(@RequestBody CreateMessageDTO dto) {
        Message message = new Message();
        message.setChatId(dto.getChatId());
        message.setSenderId(dto.getSenderId());
        message.setContent(dto.getContent());
        message.setCreatedAt(LocalDateTime.now());
        message.setRead(false);
        return ResponseEntity.status(HttpStatus.CREATED).body(messageService.sendMessage(message));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener mensaje por ID")
    public ResponseEntity<Message> getMessage(@PathVariable String id) {
        return messageService.getMessageById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/chat/{chatId}")
    @Operation(summary = "Obtener mensajes de un chat")
    public ResponseEntity<List<Message>> getChatMessages(@PathVariable String chatId) {
        return ResponseEntity.ok(messageService.getChatMessages(chatId));
    }

    @GetMapping("/chat/{chatId}/unread")
    @Operation(summary = "Obtener mensajes no leídos de un chat")
    public ResponseEntity<List<Message>> getUnreadMessages(@PathVariable String chatId) {
        return ResponseEntity.ok(messageService.getUnreadMessages(chatId));
    }

    @PutMapping("/{id}/read")
    @Operation(summary = "Marcar mensaje como leído")
    public ResponseEntity<Message> markAsRead(@PathVariable String id) {
        Message message = messageService.markAsRead(id);
        if (message != null) {
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar mensaje")
    public ResponseEntity<Void> deleteMessage(@PathVariable String id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }
}
