package com.gourmetconnect.api.repository;

import com.gourmetconnect.api.model.User;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.geo.Distance;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    // 1. Métodos de búsqueda para la autenticación y validación de unicidad
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

    // 2. Validar si ya existen antes de registrar un nuevo usuario
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

    // 3. Consulta Geoespacial (¡El superpoder para tu índice 2dsphere!)
    // Busca usuarios cuyo "profile.location" esté cerca de un punto dentro de un radio (distancia) máximo
    List<User> findByProfileLocationNear(GeoJsonPoint point, Distance maxDistance);
}
