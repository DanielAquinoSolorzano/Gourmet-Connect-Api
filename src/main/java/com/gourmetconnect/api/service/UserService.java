package com.gourmetconnect.api.service;


import com.gourmetconnect.api.model.User;
import com.gourmetconnect.api.model.valueobjects.Profile;
import com.gourmetconnect.api.model.valueobjects.UserStats;
import com.gourmetconnect.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // Genera el constructor para la inyección de dependencias de Lombok
public class UserService {

    // Inyección del repositorio usando constructor (gracias a @RequiredArgsConstructor)
    private final UserRepository userRepository;

    /**
     * Registra un nuevo usuario aplicando las reglas de negocio básicas.
     */
    public User registerUser(User user) {
        // 1. Validar unicidad de Email
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("El correo electrónico ya está en uso.");
        }

        // 2. Validar unicidad de Username
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("El nombre de usuario ya está tomado.");
        }

        // 3. Inicializar componentes esenciales si vienen nulos
        if (user.getProfile() == null) {
            user.setProfile(new Profile());
        }
        if (user.getStats() == null) {
            user.setStats(new UserStats()); // Inicializa contadores en 0
        }

        // NOTA: Aquí es donde más adelante aplicarías:
        // user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));

        // 4. Guardar en la base de datos
        return userRepository.save(user);
    }

    /**
     * Busca un usuario por su ID.
     */
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    /**
     * Busca usuarios cercanos utilizando el índice 2dsphere.
     * @param longitude Longitud central
     * @param latitude Latitud central
     * @param distanceInKm Radio de búsqueda en kilómetros
     */
    public List<User> findUsersNearby(double longitude, double latitude, double distanceInKm) {
        GeoJsonPoint point = new GeoJsonPoint(longitude, latitude);

        // Convertimos la distancia en kilómetros a la métrica que entiende Spring Data
        Distance maxDistance = new Distance(distanceInKm, Metrics.KILOMETERS);

        return userRepository.findByProfileLocationNear(point, maxDistance);
    }

    /**
     * Obtiene todos los usuarios del sistema (útil para pruebas).
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
