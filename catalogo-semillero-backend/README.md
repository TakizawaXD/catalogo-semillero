# Catálogo WPOSS Backend

Este es el backend del catálogo, construido con Spring Boot, JPA y PostgreSQL.

## Ejecución Local

Para levantar la base de datos PostgreSQL usando Docker, ejecuta en esta carpeta:
```bash
docker-compose up -d
```
Luego puedes arrancar la aplicación de Spring Boot (que por defecto usa el perfil `dev` con H2 en memoria, pero puedes cambiarlo a `prod` en `application.properties` para usar Postgres).

---

## Conceptos Clave Demostrados

### 1. Ocultamiento de Campos (DTOs)
Utilizamos el patrón DTO (Data Transfer Object) para separar el modelo de base de datos (`@Entity Producto`) del objeto que se expone al cliente (`ProductoResponseDTO`).

**¿Por qué lo hacemos?**
- **Seguridad:** Evitamos enviar campos confidenciales, contraseñas o metadatos de auditoría al cliente.
- **Rendimiento:** Evitamos problemas de serialización JSON y ciclos infinitos (Ej. relaciones bidireccionales con JPA).
- **Desacoplamiento:** Si la estructura de la base de datos cambia, la respuesta del API (el contrato con el frontend) se mantiene intacta simplemente ajustando el Mapper.

En este proyecto, `ProductoResponseDTO` extrae solo lo estrictamente necesario (ej. `String categoriaNombre` en vez de exponer el objeto complejo `Categoria` entero).

### 2. Rollback Transaccional (`@Transactional`)
En la capa de servicio (`ProductoServicio.java`), los métodos que modifican datos (`crear`, `actualizar`, `eliminar`) están anotados con `@Transactional`.

**¿Cómo funciona?**
Spring inicia una transacción de base de datos al entrar al método. Si ocurre cualquier `RuntimeException` (por ejemplo, fallan las validaciones, ocurre un error interno, o se lanza `RecursoNoEncontradoException`), **Spring realiza un Rollback automático**.
Esto garantiza la consistencia: O se guardan todos los datos relacionados correctamente, o no se guarda ninguno, evitando que la base de datos quede en un estado corrupto o inconsistente si el código falla a mitad de la ejecución.
