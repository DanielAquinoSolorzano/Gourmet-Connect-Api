package com.gourmetconnect.api.controller;

import com.gourmetconnect.api.dto.CreateStoryDTO;
import com.gourmetconnect.api.model.Story;
import com.gourmetconnect.api.service.StoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/stories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Tag(name = "Stories", description = "Endpoints para gestionar historias efímeras")
public class StoryController {

    private final StoryService storyService;

    @PostMapping
    @Operation(summary = "Crear nueva historia")
    public ResponseEntity<Story> createStory(@RequestBody CreateStoryDTO dto) {
        Story story = new Story();
        story.setAuthorId(dto.getAuthorId());
        story.setMediaUrl(dto.getMediaUrl());
        story.setType(dto.getType());
        story.setCreatedAt(LocalDateTime.now());
        story.setExpiresAt(LocalDateTime.now().plusHours(24));
        return ResponseEntity.status(HttpStatus.CREATED).body(storyService.createStory(story));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener historia por ID")
    public ResponseEntity<Story> getStory(@PathVariable String id) {
        return storyService.getStoryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{authorId}")
    @Operation(summary = "Obtener historias de un usuario")
    public ResponseEntity<List<Story>> getUserStories(@PathVariable String authorId) {
        return ResponseEntity.ok(storyService.getUserStories(authorId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar historia")
    public ResponseEntity<Void> deleteStory(@PathVariable String id) {
        storyService.deleteStory(id);
        return ResponseEntity.noContent().build();
    }
}
