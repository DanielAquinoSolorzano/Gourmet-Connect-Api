use gourmetconnect_db;
/*
db.users.drop();
db.posts.drop();
db.stories.drop();
db.relationships.drop();
db.notifications.drop();
db.chats.drop();
db.saved_posts.drop();
print("Base de datos limpiada exitosamente.");
*/

// 1. Users
db.createCollection("users");
db.users.createIndex({ "email": 1 }, { unique: true });
db.users.createIndex({ "username": 1 }, { unique: true });
db.users.createIndex({ "profile.location": "2dsphere" });

// 2. Posts (Feed Principal)
db.createCollection("posts");
db.posts.createIndex({ "created_at": -1 });
db.posts.createIndex({ "author_id": 1 });
db.posts.createIndex({ "location": "2dsphere" });
db.posts.createIndex({ "content.tags": 1 });

// 3. Stories (Contenido Efímero - Solo Imágenes)
db.createCollection("stories");
// TTL Index: Borra automáticamente después de 24h (86400 segundos)
db.stories.createIndex({ "expires_at": 1 }, { expireAfterSeconds: 0 });
db.stories.createIndex({ "author_id": 1 });

// 4. Relationships
db.createCollection("relationships");
db.relationships.createIndex({ "follower_id": 1, "followed_id": 1 }, { unique: true });

// 5. Notifications
db.createCollection("notifications");
db.notifications.createIndex({ "recipient_id": 1, "is_read": 1 });

// 6. Chats (Mensajería Directa)
db.createCollection("chats");
db.chats.createIndex({ "participants": 1 });
db.chats.createIndex({ "last_message_at": -1 });

// 7. Saved_Posts (Colecciones/Guardados)
db.createCollection("saved_posts");
db.saved_posts.createIndex({ "user_id": 1, "collection_name": 1 });

print(" Estructura avanzada configurada.");


