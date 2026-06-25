package com.gourmetconnect.api.controller;

import com.gourmetconnect.api.dto.SaveItemDTO;
import com.gourmetconnect.api.model.SavedItem;
import com.gourmetconnect.api.service.SavedItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/saved-items")
@RequiredArgsConstructor
@Tag(name = "Saved Items", description = "Endpoints para gestionar items guardados")
public class SavedItemController {

    private final SavedItemService savedItemService;

    @PostMapping
    @Operation(summary = "Guardar un item")
    public ResponseEntity<SavedItem> saveItem(@RequestBody SaveItemDTO dto) {
        SavedItem item = new SavedItem();
        item.setUserId(dto.getUserId());
        item.setPostId(dto.getPostId());
        item.setCollectionName(dto.getCollectionName() != null ? dto.getCollectionName() : "default");
        item.setSavedAt(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItemService.saveItem(item));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener item guardado por ID")
    public ResponseEntity<SavedItem> getSavedItem(@PathVariable String id) {
        return savedItemService.getSavedItemById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Obtener todos los items guardados de un usuario")
    public ResponseEntity<List<SavedItem>> getUserSavedItems(@PathVariable String userId) {
        return ResponseEntity.ok(savedItemService.getUserSavedItems(userId));
    }

    @GetMapping("/user/{userId}/collection/{collectionName}")
    @Operation(summary = "Obtener items guardados de una colección específica")
    public ResponseEntity<List<SavedItem>> getSavedItemsByCollection(
            @PathVariable String userId,
            @PathVariable String collectionName) {
        return ResponseEntity.ok(savedItemService.getUserSavedItemsByCollection(userId, collectionName));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un item guardado")
    public ResponseEntity<Void> deleteSavedItem(@PathVariable String id) {
        savedItemService.deleteSavedItem(id);
        return ResponseEntity.noContent().build();
    }
}
