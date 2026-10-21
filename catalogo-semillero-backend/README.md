# Catálogo WPOSS Backend

API REST desarrollada con Spring Boot 3, Java 21, Spring Data JPA y PostgreSQL.

## Ejecutar localmente

Se requiere Java 21. Para el perfil `dev`, el backend usa H2 en memoria y aplica el esquema con Flyway:

```powershell
.\mvnw.cmd spring-boot:run
```

El perfil de desarrollo usa una clave JWT de ejemplo solo para uso local. Para iniciar la aplicación con una clave propia, define `JWT_SECRET_BASE64` con una clave aleatoria de al menos 32 bytes codificada en Base64.

Para generar una clave en PowerShell:

```powershell
$bytes = New-Object byte[] 32
[System.Security.Cryptography.RandomNumberGenerator]::Fill($bytes)
[Convert]::ToBase64String($bytes)
```

Guarda el resultado en la variable de entorno `JWT_SECRET_BASE64`; no lo agregues a Git.

## Perfiles y base de datos

- `dev` (predeterminado): H2 en memoria.
- `test`: H2 independiente para pruebas.
- `prod`: PostgreSQL. Define `DATABASE_URL`, `DATABASE_USERNAME`, `DATABASE_PASSWORD`, `JWT_SECRET_BASE64` y `CORS_ALLOWED_ORIGINS`.

Para ejecutar con el perfil de producción localmente, configura esas variables y activa `prod`, por ejemplo:

```powershell
$env:SPRING_PROFILES_ACTIVE = "prod"
.\mvnw.cmd spring-boot:run
```

Las migraciones versionadas en `src/main/resources/db/migration` se ejecutan con PostgreSQL en el perfil `prod`, donde Hibernate valida el esquema y no lo modifica. En una base de datos existente sin historial de Flyway, la versión 1 se registra como baseline y se aplica la migración de usuarios. Los perfiles locales `dev` y `test` usan H2 y generación de esquema por Hibernate.

## Autenticación

1. `POST /api/auth/registro` crea una cuenta con rol `USER`. El rol no se acepta desde el cliente y la contraseña se guarda con BCrypt.
2. `POST /api/auth/login` autentica las credenciales y devuelve un JWT.
3. Envía el token en las rutas protegidas mediante `Authorization: Bearer <token>`.
4. `GET /api/auth/yo` devuelve la cuenta correspondiente al token.

La consulta de productos es pública. Crear, modificar o eliminar productos requiere un usuario autenticado con rol `ADMIN`. El registro público nunca asigna ese rol; para administrar roles se requiere un proceso controlado por el operador de la base de datos.

## OpenAPI / Swagger UI

Con la aplicación ejecutándose:

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- Especificación OpenAPI: `http://localhost:8080/v3/api-docs`

En Swagger UI, inicia sesión en `/api/auth/login`, copia el valor de `accessToken` y pégalo en **Authorize**. Swagger UI agrega el prefijo `Bearer` al encabezado.

## Docker

Construye la imagen desde la carpeta del backend:

```powershell
docker build -t catalogo-backend .
```

El `Dockerfile` compila la aplicación en una etapa Maven y ejecuta el JAR en una imagen Java 21 como usuario sin privilegios. Para producción, inyecta las variables de entorno necesarias al crear el contenedor.

## Pruebas

```powershell
.\mvnw.cmd test
```
