package com.gourmetconnect.api.controller;

import com.gourmetconnect.api.dto.UserRegisterDTO;
import com.gourmetconnect.api.model.User;
import com.gourmetconnect.api.model.valueobjects.Profile;
import com.gourmetconnect.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Endpoints para la gestión, registro y geolocalización de usuarios en GourmetConnect")
public class UserController {

    private final UserService userService;

    /**
     * Endpoint para registrar un usuario.
     * POST http://localhost:8080/api/users
     */
    @PostMapping
    @Operation(summary = "Registrar un nuevo usuario", description = "Crea un usuario en la base de datos a partir de un DTO de registro.")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO dto) {
        try {
            // 1. Mapeamos los datos planos del DTO a nuestra estructura orientada al dominio
            User user = new User();
            user.setUsername(dto.getUsername());
            user.setEmail(dto.getEmail());
            user.setPasswordHash(dto.getPasswordHash());

            Profile profile = new Profile();
            profile.setFullName(dto.getFullName());
            profile.setBio(dto.getBio());
            profile.setAvatarUrl(dto.getAvatarUrl());

            // Creamos el GeoJsonPoint de forma segura en Java
            profile.setLocation(new GeoJsonPoint(dto.getLongitude(), dto.getLatitude()));
            user.setProfile(profile);

            // 2. Enviamos al servicio
            User createdUser = userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error inesperado al registrar el usuario.");
        }
    }

    /**
     * Endpoint para buscar usuarios cercanos mediante geolocalización.
     * GET http://localhost:8080/api/users/nearby?lng=-76.95&lat=-12.04&radius=5.0
     */
    @GetMapping("/nearby")
    @Operation(
            summary = "Buscar usuarios cercanos",
            description = "Retorna una lista de usuarios dentro de un radio en kilómetros utilizando el índice geoespacial 2dsphere de MongoDB."
    )
    public ResponseEntity<List<User>> getUsersNearby(
            @Parameter(description = "Longitud de la ubicación central (coordenada X)", example = "-76.9562")
            @RequestParam double lng,

            @Parameter(description = "Latitud de la ubicación central (coordenada Y)", example = "-12.0483")
            @RequestParam double lat,

            @Parameter(description = "Radio máximo de búsqueda en kilómetros", example = "10.0")
            @RequestParam(defaultValue = "5.0") double radius) {

        List<User> users = userService.findUsersNearby(lng, lat, radius);
        return ResponseEntity.ok(users);
    }

    /**
     * Endpoint para listar todos los usuarios.
     * GET http://localhost:8080/api/users
     */
    @GetMapping
    @Operation(summary = "Listar todos los usuarios", description = "Retorna una lista con todos los usuarios registrados. Útil para entornos de desarrollo.")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Endpoint para buscar un usuario por su ID.
     * GET http://localhost:8080/api/users/{id}
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por ID", description = "Busca un usuario específico utilizando su identificador único (_id de MongoDB).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<User> getById(
            @Parameter(description = "ID único del usuario", example = "60d5ec49f83c5123456789ab")
            @PathVariable String id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}