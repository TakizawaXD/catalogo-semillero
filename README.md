# 🛍️ Catálogo Semillero

<div align="center">

![Angular](https://img.shields.io/badge/Angular-22-DD0031?style=for-the-badge&logo=angular)
![TypeScript](https://img.shields.io/badge/TypeScript-5.0-3178C6?style=for-the-badge&logo=typescript)
![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4-6DB33F?style=for-the-badge&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-336791?style=for-the-badge&logo=postgresql)
![JWT](https://img.shields.io/badge/JWT-Authentication-000000?style=for-the-badge&logo=jsonwebtokens)

</div>

Aplicación full-stack para la gestión y visualización de un catálogo de productos, desarrollada con Angular en el frontend y Spring Boot en el backend. Este proyecto combina una interfaz moderna con lógica de negocio real, autenticación, validaciones, persistencia y documentación de API.

## 📌 Visión general

Catálogo Semillero es un proyecto académico orientado a fortalecer habilidades en:

- Arquitectura de software
- Desarrollo frontend con Angular
- Desarrollo backend con Java + Spring Boot
- Consumo de APIs REST
- Autenticación y autorización con JWT
- Formulario reactivos y validación
- Persistencia y manejo de datos
- Buenas prácticas de UX y organización del código

La solución está compuesta por dos partes claramente diferenciadas:

- Frontend: Angular 22 + TypeScript + Angular Material
- Backend: Java 21 + Spring Boot 4 + Spring Security + JPA + PostgreSQL/H2

## 🎯 Objetivos del proyecto

- Construir una experiencia de catálogo funcional y escalable
- Separar responsabilidades entre frontend y backend
- Aplicar autenticación por roles de usuario
- Gestionar productos, usuarios y carrito de compras
- Implementar formularios con validaciones reactivas
- Facilitar la documentación de la API con Swagger
- Crear una base sólida para continuar ampliando la aplicación

## ✨ Funcionalidades principales

- Catálogo de productos con listado y detalle
- Filtro por categorías y búsqueda básica
- Vista de carrito con cálculo automático de cantidades y total
- Persistencia del carrito en localStorage
- Registro y login de usuarios
- Autorización por roles: administrador y usuario estándar
- Administración de productos y edición de datos
- Formularios reactivos con validación de entrada
- Rutas protegidas con guards de navegación
- Manejo de estados de carga, error y vacío
- Documentación de endpoints con Swagger/OpenAPI
- Soporte para desarrollo local y entorno de producción

## 🧰 Stack tecnológico

### Frontend

- Angular 22
- TypeScript
- HTML5
- CSS3
- Angular Material
- RxJS
- Angular Router
- HttpClient
- Reactive Forms
- LocalStorage

### Backend

- Java 21
- Spring Boot 4
- Spring Security
- Spring Data JPA
- PostgreSQL
- H2 Database
- Flyway
- JWT
- OpenAPI / Swagger UI
- Maven

## 🏗️ Arquitectura del sistema

El proyecto sigue un enfoque basado en capas y separación de responsabilidades:

- Frontend: componentes, páginas, servicios, guards, validadores y modelos
- Backend: controladores, servicios, repositorios, entidades, seguridad y autenticación
- Persistencia: JPA con base de datos relacional
- Seguridad: JWT + Spring Security
- Comunicación: API REST sobre HTTP

### Diagrama conceptual

```text
[ Angular Frontend ]
        │
        │ HTTP / REST
        ▼
[ Spring Boot API ]
        │
        ├── Auth Service
        ├── Product Service
        ├── User Service
        ├── Security / JWT
        └── JPA Repository Layer
        │
        ▼
[ PostgreSQL / H2 ]
```

## 📁 Estructura del repositorio

```text
catalogo-semillero/
├── catalogo-semillero-backend/      # Backend Java/Spring Boot
│   ├── src/
│   ├── pom.xml
│   ├── mvnw
│   ├── mvnw.cmd
│   └── README.md
├── public/                          # Archivos públicos del frontend
├── src/                             # Código fuente del frontend Angular
├── angular.json                     # Configuración del proyecto Angular
├── package.json                     # Dependencias y scripts del frontend
├── package-lock.json                # Lockfile de npm
├── .gitignore
├── .editorconfig
├── .prettierrc
├── netlify.toml                    # Configuración de despliegue opcional
├── vercel.json                     # Configuración de despliegue opcional
├── README.md                       # Documentación principal
├── README-TALLER-1.md             # Documentación del taller 1
├── README-TALLER-2.md             # Documentación del taller 2
├── README-TALLER-3.md             # Documentación del taller 3
├── readmeTaller4.md               # Documentación del taller 4
├── README-TALLER-5.md             # Documentación del taller 5
├── TODO_BACKEND.md                # Tareas pendientes del backend
└── LICENSE                        # Si se agrega posteriormente
```

## 🧪 Requisitos previos

Antes de ejecutar el proyecto asegúrate de contar con:

- Node.js 20 o superior
- npm 10 o superior
- Java 21
- Maven 3.9 o superior
- Git

Opcional:

- PostgreSQL para entorno de producción
- VS Code, IntelliJ IDEA o Eclipse

## 🚀 Configuración y ejecución

### 1) Frontend (Angular)

Instala las dependencias:

```bash
npm install
```

Ejecuta la aplicación en modo desarrollo:

```bash
npm start
```

La app estará disponible en:

```text
http://localhost:4200
```

### 2) Backend (Spring Boot)

Desde la carpeta del backend:

```bash
cd catalogo-semillero-backend
./mvnw spring-boot:run
```

En Windows PowerShell:

```powershell
cd catalogo-semillero-backend
./mvnw.cmd spring-boot:run
```

El backend quedará disponible en:

```text
http://localhost:8080
```

## 🔐 Variables de entorno

El backend usa diferentes variables según el entorno de ejecución.

```bash
JWT_SECRET_BASE64=
DATABASE_URL=
DATABASE_USERNAME=
DATABASE_PASSWORD=
CORS_ALLOWED_ORIGINS=
SPRING_PROFILES_ACTIVE=dev
```

### Generar una clave JWT

En PowerShell:

```powershell
$bytes = New-Object byte[] 32
[System.Security.Cryptography.RandomNumberGenerator]::Fill($bytes)
[Convert]::ToBase64String($bytes)
```

Guarda el valor generado en `JWT_SECRET_BASE64` y no lo subas a Git.

## 🗃️ Perfiles de ejecución

El backend soporta los siguientes perfiles:

- `dev`: usa H2 en memoria para desarrollo local
- `test`: almacena datos en H2 para pruebas
- `prod`: usa PostgreSQL y configuración de producción

Ejemplo para activar producción localmente:

```powershell
$env:SPRING_PROFILES_ACTIVE = "prod"
./mvnw.cmd spring-boot:run
```

## 👤 Usuarios de prueba

Se incluyen credenciales de ejemplo para probar el sistema localmente:

| Rol | Correo | Contraseña |
| --- | --- | --- |
| Administrador | `admin@wposs.com` | `admin123` |
| Usuario estándar | `usuario@wposs.com` | `usuario123` |
| Usuario API externa | `john@mail.com` | `changeme` |

## 🛡️ Autenticación y autorización

El sistema utiliza JWT para proteger rutas de administración.

### Flujo principal

1. El usuario se registra o inicia sesión
2. El backend valida las credenciales
3. Se genera un token JWT
4. El cliente lo envía en el header `Authorization: Bearer <token>`
5. El backend valida el token y autoriza el acceso

### Rutas protegidas

- Registro público
- Login público
- Consulta de productos pública
- Gestión de productos restringida a usuarios con rol administrador

## 📚 Documentación de la API

La API incluye documentación OpenAPI/Swagger.

Cuando el backend esté corriendo:

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

Para probar rutas protegidas desde Swagger:

1. Inicia sesión en `/api/auth/login`
2. Copia el valor del `accessToken`
3. Pégalo en el botón **Authorize** del Swagger UI

## 🧪 Scripts disponibles

### Frontend

```bash
npm start
npm run build
npm test
npm run watch
```

### Backend

```bash
./mvnw test
./mvnw spring-boot:run
./mvnw package
```

## 🧩 Características del módulo frontend

- Rutas y navegación principal
- Protección de rutas con guards
- Formularios reactivos con validaciones
- Carrito de compras reactivo
- Persistencia del estado en almacenamiento local
- Presentación modular por componentes
- Integración con API REST

## 🧱 Características del módulo backend

- CRUD de productos
- Gestión de usuarios
- Seguridad con JWT
- Encriptación de contraseñas con BCrypt
- Validación de entrada con Bean Validation
- Persistencia con JPA
- Migrations con Flyway
- Configuración por perfiles de entorno

## 📖 Documentación por taller

El proyecto se desarrolló en varias etapas. Puedes consultar cada entrega por separado:

- [Taller 1](./README-TALLER-1.md)
- [Taller 2](./README-TALLER-2.md)
- [Taller 3](./README-TALLER-3.md)
- [Taller 4](./readmeTaller4.md)
- [Taller 5](./README-TALLER-5.md)

## 📈 Estado del proyecto

El repositorio se encuentra en desarrollo académico y evolutivo. La base funcional ya está definida y organizada, con un enfoque práctico en Angular y Spring Boot, lista para continuar expandiéndose con nuevas funcionalidades.

## 🚀 Mejoras futuras

- Mejoras en filtros y búsqueda avanzada
- Paginación y ordenamiento de resultados
- Dashboard administrativo con métricas
- Gestión más completa de usuarios y permisos
- Pruebas E2E con Playwright/Cypress
- Pipeline CI/CD
- Optimización de rendimiento e imágenes
- Internacionalización (i18n)

## 🤝 Contribución

Si deseas colaborar en el proyecto:

1. Haz un fork del repositorio
2. Crea una rama para tu funcionalidad
3. Realiza tus cambios con buena documentación
4. Envía un pull request con una descripción clara

## 📄 Licencia

Este proyecto se comparte como material académico y de aprendizaje dentro del contexto del semillero. Se recomienda mantener la atribución de autoría y referenciar el repositorio original en caso de reutilización o ampliación.

## 🙌 Agradecimientos

Proyecto desarrollado dentro del semillero WPOSS con enfoque en aprendizaje práctico, arquitectura de software, desarrollo web moderno y aplicación real de tecnologías actuales.

---

Catálogo Semillero representa una base sólida para un sistema de comercio digital académico, con buena separación de responsabilidades, autenticación real, validaciones y una estructura lista para continuar evolucionando.
