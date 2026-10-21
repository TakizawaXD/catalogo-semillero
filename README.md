# Catálogo Semillero

Aplicación web full-stack para la gestión y visualización de un catálogo de productos, desarrollada con Angular en el frontend y Spring Boot en el backend. El proyecto está orientado a la práctica de desarrollo moderno, arquitectura limpia, consumo de APIs REST, autenticación por roles y buenas prácticas de UX.

## Descripción general

Catálogo Semillero es un proyecto académico y de aprendizaje que simula una tienda en línea con funcionalidades de catálogo, autenticación, administración de productos, carrito de compras y navegación protegida. La solución se compone de dos partes principales:

- Frontend: Angular 22 + TypeScript + Angular Material
- Backend: Java 21 + Spring Boot 4 + Spring Security + JPA + PostgreSQL/H2

El objetivo es demostrar la integración entre una interfaz moderna y un servicio backend robusto, con separación clara de responsabilidades y estructura escalable.

## Objetivos del proyecto

- Construir una aplicación de catálogo funcional y visualmente clara
- Consumir un backend REST con Angular
- Implementar autenticación y control de acceso por roles
- Gestionar productos, usuarios y carrito de compras
- Aplicar validaciones y buenas prácticas de experiencia de usuario
- Preparar la base para una solución realista y ampliable

## Funcionalidades principales

- Catálogo de productos con listado y detalle
- Filtro y búsqueda por categorías o criterios básicos
- Carrito de compras con cálculo dinámico de cantidades y total
- Persistencia del estado del carrito en localStorage
- Autenticación de usuarios con login y registro
- Control de acceso por roles: administrador y usuario estándar
- Administración de productos y edición de datos
- Formularios reactivos con validaciones
- Rutas protegidas y guardas de navegación
- Soporte para entornos de desarrollo y producción
- Documentación de API con Swagger/OpenAPI

## Stack tecnológico

### Frontend

- Angular 22
- TypeScript
- HTML5
- CSS3
- Angular Material
- RxJS
- Angular Router
- HttpClient
- Forms reactivos

### Backend

- Java 21
- Spring Boot 4
- Spring Security
- Spring Data JPA
- PostgreSQL
- H2 Database
- Flyway
- JWT
- OpenAPI / Swagger

## Estructura del repositorio

```text
catalogo-semillero/
├── catalogo-semillero-backend/   # API REST y lógica de negocio
├── public/                       # Archivos públicos del frontend
├── src/                          # Código fuente del frontend Angular
├── angular.json                  # Configuración del proyecto Angular
├── package.json                  # Dependencias y scripts del frontend
├── package-lock.json             # Lock file de npm
├── netlify.toml                 # Configuración de despliegue (si aplica)
├── vercel.json                  # Configuración de despliegue (si aplica)
├── README.md                    # Documentación principal
├── README-TALLER-1.md           # Documentación del taller 1
├── README-TALLER-2.md           # Documentación del taller 2
├── README-TALLER-3.md           # Documentación del taller 3
├── readmeTaller4.md             # Documentación del taller 4
├── README-TALLER-5.md           # Documentación del taller 5
└── TODO_BACKEND.md              # Seguimiento de tareas backend
```

## Requisitos previos

Antes de iniciar el proyecto asegúrate de tener instalado:

- Node.js 20 o superior
- npm 10 o superior
- Java 21
- Maven 3.9 o superior
- Git

Opcionalmente:

- PostgreSQL para entorno de producción
- IDE como IntelliJ IDEA, VS Code o Eclipse

## Configuración del entorno

### Frontend

Instala las dependencias del proyecto Angular:

```bash
npm install
```

Inicia la aplicación en modo desarrollo:

```bash
npm start
```

La aplicación quedará disponible en:

```text
http://localhost:4200
```

### Backend

Desde la carpeta del backend:

```bash
cd catalogo-semillero-backend
./mvnw spring-boot:run
```

En entorno local, el perfil por defecto usa H2 en memoria. Si deseas ejecutarlo con perfil de producción, puedes configurar las variables de entorno y activar el perfil `prod`.

## Variables de entorno del backend

El backend usa estas variables para configuración según el entorno:

```bash
JWT_SECRET_BASE64=
DATABASE_URL=
DATABASE_USERNAME=
DATABASE_PASSWORD=
CORS_ALLOWED_ORIGINS=
SPRING_PROFILES_ACTIVE=dev
```

### Ejemplo para generación de clave JWT

En PowerShell:

```powershell
$bytes = New-Object byte[] 32
[System.Security.Cryptography.RandomNumberGenerator]::Fill($bytes)
[Convert]::ToBase64String($bytes)
```

Guarda el valor generado en `JWT_SECRET_BASE64` y no lo subas a Git.

## Usuarios de prueba

El proyecto incluye credenciales de ejemplo para pruebas locales:

| Rol | Correo | Contraseña |
| --- | --- | --- |
| Administrador | `admin@wposs.com` | `admin123` |
| Usuario estándar | `usuario@wposs.com` | `usuario123` |
| Usuario API externa | `john@mail.com` | `changeme` |

## Ejecución completa del sistema

1. Inicia el backend
2. Inicia el frontend
3. Accede a la app desde `http://localhost:4200`
4. Usa la API en `http://localhost:8080`
5. Consulta Swagger en `http://localhost:8080/swagger-ui.html`

## Documentación de API

La API expone documentación OpenAPI/Swagger. Cuando el backend está corriendo:

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

Para probar rutas protegidas desde Swagger, inicia sesión en `/api/auth/login` y usa el token JWT devuelto en la autenticación del navegador.

## Scripts disponibles

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

## Arquitectura del sistema

La aplicación sigue un enfoque basado en capas y responsabilidades bien definidas:

- Frontend: componentes, servicios, páginas, rutas, validadores, guards
- Backend: controladores, servicios, repositorios, entidades, seguridad, autenticación
- Persistencia: JPA + base de datos relacional
- Autorización: roles y JWT
- Comunicación: API REST sobre HTTP

## Estándar de desarrollo

El proyecto se desarrolla con una estructura ordenada y mantenible, priorizando:

- separación de responsabilidades
- componentes reutilizables
- validaciones centradas en la experiencia de usuario
- manejo seguro de sesiones y tokens
- escalabilidad y claridad en el código

## Documentación por taller

El proyecto se desarrolló en varias etapas con documentación específica:

- [Taller 1](./README-TALLER-1.md)
- [Taller 2](./README-TALLER-2.md)
- [Taller 3](./README-TALLER-3.md)
- [Taller 4](./readmeTaller4.md)
- [Taller 5](./README-TALLER-5.md)

## Estado del proyecto

Este repositorio representa un proyecto en desarrollo académico con enfoque práctico en frontend y backend. Actualmente se trabaja con una arquitectura funcional, modular y lista para seguir ampliando funcionalidades.

## Mejoras futuras

- Integración con más módulos administrativos
- Mejoras en filtros y búsqueda avanzada
- Paginación y ordenamiento de resultados
- Gestión más completa de usuarios y permisos
- Dashboard con métricas de negocio
- Pruebas E2E con Playwright o Cypress
- Pipeline CI/CD para automatización
- Optimización de rendimiento y carga de imágenes

## Licencia

Este proyecto se comparte como material académico y de aprendizaje dentro del contexto del semillero. Se recomienda conservar la atribución de autoría y referenciar el repositorio original en caso de reutilización o extensión.

## Agradecimientos

Proyecto desarrollado dentro del semillero WPOSS, enfocado en la aplicación práctica de tecnologías modernas, arquitectura de software y aprendizaje colaborativo.

---

Catálogo Semillero es una base sólida para proyectos de e-commerce académico, con enfoque en calidad, arquitectura, experiencia de usuario y buenas prácticas de desarrollo.
