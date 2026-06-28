package com.gourmetconnect.api.controller;

import com.gourmetconnect.api.dto.CreateRelationshipDTO;
import com.gourmetconnect.api.model.Relationship;
import com.gourmetconnect.api.service.RelationshipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/relationships")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Tag(name = "Relationships", description = "Endpoints para gestionar seguimientos entre usuarios")
public class RelationshipController {

    private final RelationshipService relationshipService;

    @PostMapping
    @Operation(summary = "Seguir a un usuario")
    public ResponseEntity<Relationship> followUser(@RequestBody CreateRelationshipDTO dto) {
        Relationship relationship = new Relationship();
        relationship.setFollowerId(dto.getFollowerId());
        relationship.setFollowedId(dto.getFollowedId());
        relationship.setStatus("active");
        relationship.setCreatedAt(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(relationshipService.followUser(relationship));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener relationship por ID")
    public ResponseEntity<Relationship> getRelationship(@PathVariable String id) {
        return relationshipService.getRelationshipById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/followers/{userId}")
    @Operation(summary = "Obtener seguidores de un usuario")
    public ResponseEntity<List<Relationship>> getFollowers(@PathVariable String userId) {
        return ResponseEntity.ok(relationshipService.getFollowers(userId));
    }

    @GetMapping("/following/{userId}")
    @Operation(summary = "Obtener usuarios seguidos por un usuario")
    public ResponseEntity<List<Relationship>> getFollowing(@PathVariable String userId) {
        return ResponseEntity.ok(relationshipService.getFollowing(userId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Dejar de seguir a un usuario")
    public ResponseEntity<Void> unfollowUser(@PathVariable String id) {
        relationshipService.unfollowUser(id);
        return ResponseEntity.noContent().build();
    }
}
