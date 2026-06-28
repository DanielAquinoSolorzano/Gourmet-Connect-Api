package com.gourmetconnect.api.controller;

import com.gourmetconnect.api.dto.SavePostDTO;
import com.gourmetconnect.api.model.SavedPost;
import com.gourmetconnect.api.service.SavedPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/saved-posts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Tag(name = "Saved Posts", description = "Endpoints para gestionar colecciones de posts guardados")
public class SavedPostController {

    private final SavedPostService savedPostService;

    @PostMapping
    @Operation(summary = "Guardar un post")
    public ResponseEntity<SavedPost> savePost(@RequestBody SavePostDTO dto) {
        SavedPost savedPost = new SavedPost();
        savedPost.setUserId(dto.getUserId());
        savedPost.setPostId(dto.getPostId());
        savedPost.setCollectionName(dto.getCollectionName() != null ? dto.getCollectionName() : "default");
        savedPost.setSavedAt(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPostService.savePost(savedPost));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener post guardado por ID")
    public ResponseEntity<SavedPost> getSavedPost(@PathVariable String id) {
        return savedPostService.getSavedPostById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Obtener todos los posts guardados de un usuario")
    public ResponseEntity<List<SavedPost>> getUserSavedPosts(@PathVariable String userId) {
        return ResponseEntity.ok(savedPostService.getUserSavedPosts(userId));
    }

    @GetMapping("/user/{userId}/collection/{collectionName}")
    @Operation(summary = "Obtener posts guardados de un usuario por colección")
    public ResponseEntity<List<SavedPost>> getUserSavedPostsByCollection(@PathVariable String userId, @PathVariable String collectionName) {
        return ResponseEntity.ok(savedPostService.getUserSavedPostsByCollection(userId, collectionName));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un post guardado")
    public ResponseEntity<Void> removeSavedPost(@PathVariable String id) {
        savedPostService.removeSavedPost(id);
        return ResponseEntity.noContent().build();
    }
}
