use gourmetconnect_db;

// Usuario 1: Chef Local
var idChef = db.users.insertOne({
  username: "chef_mateo_lima",
  email: "mateo.rivera@gmail.com",
  password_hash: "$2b$10$HashSeguro...",
  profile: {
    full_name: "Mateo Rivera",
    bio: "Chef ejecutivo apasionado por la cocina fusión nikkei.",
    avatar_url: "https://plus.unsplash.com/premium_photo-1689568126014-06fea9d5d341?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    location: { type: "Point", coordinates: [-77.0369, -12.0464] } // Lima
  },
  stats: { followers_count: 1250, following_count: 340, posts_count: 45 },
  created_at: new Date("2026-05-10"),
  is_active: true
}).insertedId;

// Usuario 2: Productora de Café
var idFinca = db.users.insertOne({
  username: "cafe_chanchamayo_oficial",
  email: "ventas@cafechanchamayo.pe",
  password_hash: "$2b$10$HashSeguro...",
  profile: {
    full_name: "Finca La Esperanza",
    bio: "Productores directos de café de altura en Chanchamayo.",
    avatar_url: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSQICwc4phTMLNULoRz3k_s8OMS4JXu6AVs8w&s",
    location: { type: "Point", coordinates: [-75.321, -11.056] } // Chanchamayo
  },
  stats: { followers_count: 5400, following_count: 120, posts_count: 112 },
  created_at: new Date("2026-04-15"),
  is_active: true
}).insertedId;

// Usuario 3: Foodie Influencer
var idSofia = db.users.insertOne({
  username: "sofia_sabe_rico",
  email: "sofia.foodie@outlook.com",
  password_hash: "$2b$10$HashSeguro...",
  profile: {
    full_name: "Sofía Mendoza",
    bio: "Explorando los sabores ocultos del Perú. 🇵🇪",
    avatar_url: "https://plus.unsplash.com/premium_photo-1690407617542-2f210cf20d7e?q=80&w=687&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    location: { type: "Point", coordinates: [-71.5369, -13.5319] } // Cusco
  },
  stats: { followers_count: 8900, following_count: 600, posts_count: 230 },
  created_at: new Date("2026-06-01"),
  is_active: true
}).insertedId;

print("👥 Usuarios insertados correctamente.");



// 1. Post Normal (Feed)
var idPost1 = db.posts.insertOne({
  author_id: idSofia,
  type: "standard",
  content: {
    text: "Acabo de probar el nuevo lote de café de @cafe_chanchamayo_oficial. ¡Notas cítricas increíbles!",
    media: [{ type: "image", url: "https://imgmedia.buenazo.pe/970x533/buenazo/original/2026/05/13/6a04a6a662846ceb460c0a8f.webp" }],
    tags: ["#CafePeruano", "#Chanchamayo"]
  },
  location: { type: "Point", coordinates: [-71.5369, -13.5319] },
  engagement: { likes_count: 145, comments_count: 12, shares_count: 8 },
  comments: [
    { user_id: idChef, text: "¡Se ve espectacular! ¿Dónde consigo el paquete?", created_at: new Date() }
  ],
  created_at: new Date(),
  updated_at: new Date()
}).insertedId;

// 2. Historia (Story) - Solo Imagen, se borra sola en 24h
db.stories.insertOne({
  author_id: idChef,
  media_url: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTT7QqAStLtwhTn-TC_DBI9CinTSnLAzgjNpA&s",
  type: "image",
  created_at: new Date(),
  expires_at: new Date(Date.now() + 24 * 60 * 60 * 1000) // 24 horas desde ahora
});

// 3. Post Guardado (Sofía guarda el post del Chef)
db.saved_posts.insertOne({
  user_id: idSofia,
  post_id: idPost1,
  collection_name: "Recetas Favoritas",
  saved_at: new Date()
});

print("📸 Contenido multimedia y stories insertados.");



// 1. Relaciones Sociales
db.relationships.insertOne({
  follower_id: idSofia,
  followed_id: idChef,
  created_at: new Date(),
  status: "active"
});

db.relationships.insertOne({
  follower_id: idChef,
  followed_id: idFinca,
  created_at: new Date(),
  status: "active"
});

// 2. Chat Privado (DM) entre Sofía y el Chef
db.chats.insertOne({
  participants: [idSofia, idChef],
  messages: [
    {
      sender_id: idSofia,
      text: "Hola Chef, ¿me podrías pasar la receta de ese tiradito?",
      sent_at: new Date(Date.now() - 3600000) // Hace 1 hora
    },
    {
      sender_id: idChef,
      text: "¡Claro Sofía! Te la envío por aquí mismo.",
      sent_at: new Date()
    }
  ],
  last_message_at: new Date(),
  is_group: false
});

// 3. Notificación
db.notifications.insertOne({
  recipient_id: idChef,
  sender_id: idSofia,
  type: "message",
  post_id: null,
  is_read: false,
  created_at: new Date()
});

print("🤝 Relaciones y mensajería directa configuradas.");