package com.gourmetconnect.api.controller;

import com.gourmetconnect.api.dto.CreatePostDTO;
import com.gourmetconnect.api.model.Post;
import com.gourmetconnect.api.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Tag(name = "Posts", description = "Endpoints para gestionar publicaciones")
public class PostController {

    private final PostService postService;

    @PostMapping
    @Operation(summary = "Crear nuevo post")
    public ResponseEntity<Post> createPost(@RequestBody CreatePostDTO dto) {
        Post post = new Post();
        post.setAuthorId(dto.getAuthorId());
        post.setType(dto.getType());
        post.setContent(dto.getContent());
        post.setLocation(dto.getLocation());
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(post));
    }

    @GetMapping
    @Operation(summary = "Listar todos los posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener post por ID")
    public ResponseEntity<Post> getPost(@PathVariable String id) {
        return postService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{authorId}")
    @Operation(summary = "Obtener posts de un usuario")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable String authorId) {
        return ResponseEntity.ok(postService.getUserPosts(authorId));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar post")
    public ResponseEntity<Post> updatePost(@PathVariable String id, @RequestBody Post post) {
        return ResponseEntity.ok(postService.updatePost(id, post));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar post")
    public ResponseEntity<Void> deletePost(@PathVariable String id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
