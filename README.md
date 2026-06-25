# GourmetConnect API

Backend REST API para la plataforma **GourmetConnect** - una aplicación social para conectar a usuarios interesados en gastronomía y compartir experiencias culinarias.

## 📋 Descripción

GourmetConnect API es un backend basado en **Spring Boot 4.1.0** que gestiona toda la lógica de negocio de la plataforma. Incluye funcionalidades de autenticación de usuarios, gestión de publicaciones, sistema de mensajería, notificaciones, geolocalización y más.

**Tecnologías principales:**
- Java 21
- Spring Boot 4.1.0
- MongoDB (Base de datos NoSQL)
- Swagger/OpenAPI 3.0 (Documentación automática)

---

## 🚀 Inicio Rápido

### Requisitos previos
- Java 21+
- Maven 3.6+
- MongoDB 5.0+

### Instalación

1. **Clonar el repositorio**
```bash
git clone https://github.com/DanielAquinoSolorzano/Gourmet-Connect-Api.git
cd GourmetConnectApi
```

2. **Configurar MongoDB**
Asegúrate de que MongoDB esté ejecutándose en `localhost:27017` (por defecto) o configura la conexión en `application.properties`.

3. **Ejecutar la aplicación**
```bash
./mvnw spring-boot:run
```

O usando Maven directamente:
```bash
mvn spring-boot:run
```

4. **Acceder a la documentación**
- Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- OpenAPI JSON: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## 📚 Documentación de Endpoints

### 👥 Usuarios

#### Registrar usuario
```http
POST /api/users
Content-Type: application/json

{
  "username": "juanperez",
  "email": "juan@example.com",
  "passwordHash": "hashed_password",
  "fullName": "Juan Pérez",
  "bio": "Amante de la gastronomía",
  "avatarUrl": "https://...",
  "latitude": -12.0483,
  "longitude": -76.9562
}
```
**Respuesta:** `201 Created` - Usuario creado

---

#### Listar todos los usuarios
```http
GET /api/users
```
**Respuesta:** `200 OK` - Lista de usuarios

---

#### Obtener usuario por ID
```http
GET /api/users/{id}
```
**Parámetro:**
- `id` (string): ID único del usuario (MongoDB ObjectId)

**Respuesta:** `200 OK` o `404 Not Found`

---

#### Buscar usuarios cercanos (Geolocalización)
```http
GET /api/users/nearby?lng=-76.95&lat=-12.04&radius=5.0
```
**Parámetros:**
- `lng` (number): Longitud (coordenada X)
- `lat` (number): Latitud (coordenada Y)
- `radius` (number, opcional): Radio en kilómetros (default: 5.0)

**Respuesta:** `200 OK` - Usuarios dentro del radio especificado

---

### 📝 Posts

#### Crear post
```http
POST /api/posts
Content-Type: application/json

{
  "userId": "usuario_id",
  "content": "Este es mi post sobre gastronomía",
  "mediaUrl": "https://..."
}
```
**Respuesta:** `201 Created` - Post creado

---

#### Listar todos los posts
```http
GET /api/posts
```
**Respuesta:** `200 OK` - Lista de posts

---

#### Obtener post por ID
```http
GET /api/posts/{id}
```
**Respuesta:** `200 OK` o `404 Not Found`

---

#### Obtener posts de un usuario
```http
GET /api/posts/user/{userId}
```
**Respuesta:** `200 OK` - Posts del usuario

---

#### Actualizar post
```http
PUT /api/posts/{id}
Content-Type: application/json

{
  "content": "Contenido actualizado",
  "mediaUrl": "https://..."
}
```
**Respuesta:** `200 OK` - Post actualizado

---

#### Eliminar post
```http
DELETE /api/posts/{id}
```
**Respuesta:** `204 No Content`

---

### 💾 Items Guardados

#### Guardar item
```http
POST /api/saved-items
Content-Type: application/json

{
  "userId": "usuario_id",
  "postId": "post_id",
  "collectionName": "mis_favoritos"
}
```
**Respuesta:** `201 Created` - Item guardado

---

#### Obtener item guardado por ID
```http
GET /api/saved-items/{id}
```
**Respuesta:** `200 OK` o `404 Not Found`

---

#### Obtener todos los items guardados de un usuario
```http
GET /api/saved-items/user/{userId}
```
**Respuesta:** `200 OK` - Items guardados del usuario

---

#### Obtener items de una colección específica
```http
GET /api/saved-items/user/{userId}/collection/{collectionName}
```
**Respuesta:** `200 OK` - Items de la colección

---

#### Eliminar item guardado
```http
DELETE /api/saved-items/{id}
```
**Respuesta:** `204 No Content`

---

### 🔔 Notificaciones

#### Crear notificación
```http
POST /api/notifications
Content-Type: application/json

{
  "recipientId": "usuario_id",
  "type": "like",
  "content": "A alguien le gustó tu post",
  "expiresAt": "2024-12-31T23:59:59"
}
```
**Respuesta:** `201 Created` - Notificación creada

---

#### Obtener notificación por ID
```http
GET /api/notifications/{id}
```
**Respuesta:** `200 OK` o `404 Not Found`

---

#### Obtener todas las notificaciones de un usuario
```http
GET /api/notifications/user/{userId}
```
**Respuesta:** `200 OK` - Todas las notificaciones

---

#### Obtener notificaciones no leídas
```http
GET /api/notifications/user/{userId}/unread
```
**Respuesta:** `200 OK` - Notificaciones sin leer

---

#### Marcar notificación como leída
```http
PUT /api/notifications/{id}/read
```
**Respuesta:** `200 OK` - Notificación marcada como leída

---

#### Eliminar notificación
```http
DELETE /api/notifications/{id}
```
**Respuesta:** `204 No Content`

---

### 💬 Mensajes

#### Enviar mensaje
```http
POST /api/messages
Content-Type: application/json

{
  "chatId": "chat_id",
  "senderId": "usuario_id",
  "content": "Hola, ¿cómo estás?"
}
```
**Respuesta:** `201 Created` - Mensaje enviado

---

#### Obtener mensaje por ID
```http
GET /api/messages/{id}
```
**Respuesta:** `200 OK` o `404 Not Found`

---

#### Obtener mensajes de un chat
```http
GET /api/messages/chat/{chatId}
```
**Respuesta:** `200 OK` - Todos los mensajes del chat

---

#### Obtener mensajes no leídos de un chat
```http
GET /api/messages/chat/{chatId}/unread
```
**Respuesta:** `200 OK` - Mensajes sin leer

---

#### Marcar mensaje como leído
```http
PUT /api/messages/{id}/read
```
**Respuesta:** `200 OK` - Mensaje marcado como leído

---

#### Eliminar mensaje
```http
DELETE /api/messages/{id}
```
**Respuesta:** `204 No Content`

---

### 👥 Seguimientos (Follow)

#### Seguir a un usuario
```http
POST /api/follows
Content-Type: application/json

{
  "followerId": "usuario_seguidor_id",
  "followedId": "usuario_a_seguir_id"
}
```
**Respuesta:** `201 Created` - Seguimiento creado

---

#### Obtener follow por ID
```http
GET /api/follows/{id}
```
**Respuesta:** `200 OK` o `404 Not Found`

---

#### Obtener seguidores de un usuario
```http
GET /api/follows/followers/{userId}
```
**Respuesta:** `200 OK` - Lista de seguidores

---

#### Obtener usuarios seguidos
```http
GET /api/follows/following/{userId}
```
**Respuesta:** `200 OK` - Usuarios que está siguiendo

---

#### Dejar de seguir
```http
DELETE /api/follows/{id}
```
**Respuesta:** `204 No Content`

---

### 💬 Chats

#### Crear chat
```http
POST /api/chats
Content-Type: application/json

{
  "participants": ["usuario_id_1", "usuario_id_2"],
  "group": false
}
```
**Respuesta:** `201 Created` - Chat creado

---

#### Obtener chat por ID
```http
GET /api/chats/{id}
```
**Respuesta:** `200 OK` o `404 Not Found`

---

#### Obtener chats de un usuario
```http
GET /api/chats/user/{userId}
```
**Respuesta:** `200 OK` - Chats del usuario

---

#### Actualizar chat
```http
PUT /api/chats/{id}
Content-Type: application/json

{
  "participants": ["usuario_id_1", "usuario_id_2"],
  "group": true
}
```
**Respuesta:** `200 OK` - Chat actualizado

---

#### Eliminar chat
```http
DELETE /api/chats/{id}
```
**Respuesta:** `204 No Content`

---

## 📊 Estructura del Proyecto

```
GourmetConnectApi/
├── src/
│   ├── main/
│   │   └── java/com/gourmetconnect/api/
│   │       ├── controller/          # Controladores REST
│   │       ├── service/             # Lógica de negocio
│   │       ├── model/               # Entidades y Value Objects
│   │       ├── repository/          # Acceso a datos (MongoDB)
│   │       ├── dto/                 # Data Transfer Objects
│   │       └── GourmetConnectApiApplication.java
│   └── test/
├── pom.xml                          # Dependencias Maven
├── Dockerfile                       # Imagen Docker
├── docker-compose.yml               # Composición de servicios
└── README.md                        # Este archivo
```

---

## 🐳 Docker

### Ejecutar con Docker Compose

```bash
docker-compose up
```

Esto iniciará:
- API en `http://localhost:8080`
- MongoDB en `mongodb://localhost:27017`

---

## 🔒 Códigos de Estado HTTP

| Código | Descripción |
|--------|-------------|
| **200** | OK - Solicitud exitosa |
| **201** | Created - Recurso creado |
| **204** | No Content - Recurso eliminado |
| **400** | Bad Request - Solicitud inválida |
| **404** | Not Found - Recurso no encontrado |
| **500** | Internal Server Error - Error del servidor |

---

## 📝 Notas Importantes

- Todos los endpoints requieren solicitudes JSON válidas (Content-Type: application/json)
- Los IDs se generan automáticamente como ObjectId de MongoDB
- Las fechas se manejan en formato ISO 8601
- La geolocalización utiliza el índice geoespacial 2dsphere de MongoDB
- Las coordenadas de geolocalización siguen el formato GeoJSON (longitude, latitude)

---

## 🤝 Contribuir

1. Fork el repositorio
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

---

## 📄 Licencia

Este proyecto está bajo licencia MIT.

---

## 📧 Contacto

Para preguntas o sugerencias, contacta al equipo de desarrollo.

---

**Última actualización:** 2026-06-25  
**Versión:** 0.0.1-SNAPSHOT  
**Documentación Swagger:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
