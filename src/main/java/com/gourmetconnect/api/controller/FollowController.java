package com.gourmetconnect.api.controller;

import com.gourmetconnect.api.dto.CreateFollowDTO;
import com.gourmetconnect.api.model.Follow;
import com.gourmetconnect.api.service.FollowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/follows")
@RequiredArgsConstructor
@Tag(name = "Follows", description = "Endpoints para gestionar seguimientos entre usuarios")
public class FollowController {

    private final FollowService followService;

    @PostMapping
    @Operation(summary = "Seguir a un usuario")
    public ResponseEntity<Follow> followUser(@RequestBody CreateFollowDTO dto) {
        Follow follow = new Follow();
        follow.setFollowerId(dto.getFollowerId());
        follow.setFollowedId(dto.getFollowedId());
        follow.setCreatedAt(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(followService.followUser(follow));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener follow por ID")
    public ResponseEntity<Follow> getFollow(@PathVariable String id) {
        return followService.getFollowById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/followers/{userId}")
    @Operation(summary = "Obtener seguidores de un usuario")
    public ResponseEntity<List<Follow>> getFollowers(@PathVariable String userId) {
        return ResponseEntity.ok(followService.getFollowers(userId));
    }

    @GetMapping("/following/{userId}")
    @Operation(summary = "Obtener usuarios seguidos por un usuario")
    public ResponseEntity<List<Follow>> getFollowing(@PathVariable String userId) {
        return ResponseEntity.ok(followService.getFollowing(userId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Dejar de seguir a un usuario")
    public ResponseEntity<Void> unfollowUser(@PathVariable String id) {
        followService.unfollowUser(id);
        return ResponseEntity.noContent().build();
    }
}
