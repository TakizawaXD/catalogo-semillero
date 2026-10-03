# 🚀 Plan de Desarrollo y Documentación: API Backend (Pendiente)

Este documento contiene la hoja de ruta y la documentación futura proyectada para los 5 módulos del Backend del Catálogo WPOSS, construido con **Spring Boot 3.5.x** y **Java 21**.

> **Estado actual:** Pendiente ⏳ (A desarrollar en un repositorio nuevo).

---

## 📋 Módulo 01: Fundamentos de Spring Boot (API REST en memoria)
**Objetivo:** Crear la estructura base, modelo, repositorio en memoria, servicio y controladores REST.

### 🚀 Comandos y Estructura Inicial (Módulo 01)

#### Comandos para generar el proyecto
Ejecuta estos comandos en tu terminal (en una carpeta al mismo nivel que tu frontend, NO adentro) para descargar el proyecto base:
```bash
mkdir catalogo-semillero-backend
cd catalogo-semillero-backend
curl -G https://start.spring.io/starter.zip -d dependencies=web,devtools -d javaVersion=21 -d type=maven-project -d groupId=com.wposs -d artifactId=catalogo -d name=catalogo -d packageName=com.wposs.catalogo -o catalogo.zip
tar -xf catalogo.zip
rm catalogo.zip
```

#### Estructura de paquetes
Dentro de `src/main/java/com/wposs/catalogo`, crea la siguiente estructura de carpetas:
```text
com.wposs.catalogo
 ├── controlador/
 ├── modelo/
 ├── repositorio/
 └── servicio/
```

#### Archivos principales a crear
Dentro de los paquetes, deberás crear estos archivos para completar el Módulo 1:
- `modelo/Producto.java` (Usar un `record` de Java)
- `repositorio/ProductoRepositorio.java` (Clase con anotación `@Repository`)
- `servicio/ProductoServicio.java` (Clase con anotación `@Service`)
- `controlador/ProductoControlador.java` (Clase con anotación `@RestController`)

### Tareas Pendientes
- [ ] Inicializar proyecto en `start.spring.io` (Maven, Java 21, Spring Boot 3.5.x, Web, DevTools).
- [ ] Crear estructura de paquetes `com.wposs.catalogo` (controlador, servicio, repositorio, modelo).
- [ ] Implementar modelo `Producto` como un `record` (con `BigDecimal` para precio).
- [ ] Implementar `ProductoRepositorio` usando `ConcurrentHashMap` y `AtomicLong` con carga inicial de datos.
- [ ] Implementar `ProductoServicio` con validaciones de negocio e inyección por constructor (campos `final`).
- [ ] Exponer 7 endpoints REST (CRUD + filtrado + estadísticas).
- [ ] Externalizar configuración en `application.properties`.
- [ ] Escribir 5 pruebas unitarias/integración con `MockMvc`.
- [ ] Actualizar README con instrucciones de ejecución local.

---

## 🗄️ Módulo 02: Persistencia con JPA e Hibernate
**Objetivo:** Migrar los datos de memoria a una base de datos PostgreSQL real usando Docker.

### Tareas Pendientes
- [ ] Añadir dependencias de Spring Data JPA, PostgreSQL Driver y H2 (para pruebas).
- [ ] Levantar instancia de PostgreSQL en Docker (`docker run -d --name catalogo-db...`).
- [ ] Convertir `Producto` a `@Entity` y crear entidad `Categoria`.
- [ ] Configurar relación `@ManyToOne` y `@OneToMany` (evitando tablas intermedias indeseadas).
- [ ] Crear interfaces `JpaRepository` con consultas derivadas y un `@Query` con `join fetch`.
- [ ] Solucionar problema de N+1 (demostrado en README).
- [ ] Demostrar y solucionar la `LazyInitializationException` con `open-in-view=false`.
- [ ] Aplicar `@Transactional` en el servicio (readOnly y modificación).
- [ ] Escribir 7 pruebas usando `@DataJpaTest` y H2.

---

## 🛡️ Módulo 03: DTOs, validaciones y manejo de errores
**Objetivo:** Proteger la capa web, evitando filtrar entidades directas, validando entradas y estandarizando errores.

### Tareas Pendientes
- [ ] Agregar dependencia `spring-boot-starter-validation`.
- [ ] Crear `records` DTO para entrada (Nuevo/Actualizar) y salida (Resumen/Detalle).
- [ ] Implementar mappers simples en capa `@Component`.
- [ ] Añadir anotaciones de Bean Validation (`@NotBlank`, `@DecimalMin`, `@PositiveOrZero`) en DTOs.
- [ ] Crear excepciones personalizadas (`RecursoNoEncontradoException`, `RecursoDuplicadoException`, etc.) que extiendan `RuntimeException`.
- [ ] Implementar `@RestControllerAdvice` para manejar errores (404, 400, 409, 500) devolviendo siempre un `ErrorRespuesta`.
- [ ] Demostrar rollback transaccional y ocultamiento de campos en el README.
- [ ] Escribir 8 pruebas enfocadas en validaciones y manejo de errores.

---

## 🔐 Módulo 04: Spring Security y JWT
**Objetivo:** Añadir autenticación, roles de usuario y control de acceso seguro a los endpoints.

### Tareas Pendientes
- [ ] Añadir `spring-boot-starter-security` y dependencias de `jjwt` (0.12.x).
- [ ] Crear entidad `Usuario` (id, usuario, correo, contraseña con BCrypt, rol Enum, activo).
- [ ] Implementar `UserDetailsService` propio.
- [ ] Crear endpoints de autenticación (`/api/auth/registro`, `/api/auth/login`, `/api/auth/yo`).
- [ ] Implementar `ServicioJwt` (generación, validación, firma) asegurando secretos por variables de entorno.
- [ ] Escribir `FiltroJwt` (`OncePerRequestFilter`) para interpretar `Authorization: Bearer`.
- [ ] Configurar `SecurityFilterChain` (rutas públicas vs protegidas, `STATELESS`, `csrf.disable()`).
- [ ] Configurar manejo de errores de seguridad (401 y 403) usando `exceptionHandling`.
- [ ] Configurar CORS adecuadamente.
- [ ] Escribir 9 pruebas de seguridad (`@WithMockUser`).
- [ ] Documentar comportamiento y token JWT en el README.

---

## ☁️ Módulo 05: Documentación, perfiles y despliegue
**Objetivo:** Estandarizar la API con OpenAPI, usar Flyway, empaquetar con Docker y desplegar en la nube.

### Tareas Pendientes
- [ ] Integrar `springdoc-openapi-starter-webmvc-ui` y añadir anotaciones de `@Tag` y `@Operation`.
- [ ] Configurar OpenAPI para aceptar tokens JWT en Swagger UI.
- [ ] Crear perfiles en `application.properties` (`dev`, `test`, `prod`) gestionando CORS y logging.
- [ ] Integrar migraciones con **Flyway** (`V1__...sql`) desactivando `ddl-auto`.
- [ ] Escribir `Dockerfile` de construcción en dos etapas (Multi-stage build).
- [ ] Crear `docker-compose.yaml` conectando la API y PostgreSQL con `healthcheck`.
- [ ] Activar endpoints vitales de Actuator (`health`, `info`).
- [ ] Realizar despliegue real (Render/Railway/VPS) gestionando variables de entorno adecuadamente.

---

> **Nota para el desarrollador (tú):** Cuando vayas a iniciar este bloque, recuerda crear una nueva carpeta fuera de la aplicación de frontend y ejecutar el paso de inicialización de Spring Boot, conectando el nuevo proyecto a un repositorio limpio en GitHub.
