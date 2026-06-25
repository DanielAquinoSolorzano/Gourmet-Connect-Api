package com.gourmetconnect.api.model;
import com.gourmetconnect.api.model.valueobjects.Profile;
import com.gourmetconnect.api.model.valueobjects.UserStats;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id; // Mapea al _id (ObjectId) del diagrama

    @Indexed(unique = true)
    private String username;

    @Indexed(unique = true)
    private String email;

    @Field("password_hash")
    private String passwordHash; // Refleja el campo password_hash de tu diagrama

    private Profile profile; // Mapea el objeto anidado profile

    private UserStats stats; // Mapea el objeto anidado stats

    @Field("created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Field("is_active")
    private boolean isActive = true; // Mapea el booleano is_active de tu diagrama
}