# Taller 3 - Catálogo de productos

Este documento describe la parte correspondiente al taller 3 del proyecto de catálogo de productos en Angular.

## Objetivo

Implementar la lógica de servicios, inyección de dependencias, consumo de APIs, observables, señales y estado reactivo para un catálogo de productos.

## Funcionalidades desarrolladas

- Carga de productos desde la API
- Carga de categorías desde la API
- Filtro por categoría
- Vista de cliente y vista de administrador
- Carrito reactivo
- Persistencia con localStorage
- Login básico con roles
- Guards de autenticación y administración
- Componentes standalone

## Estructura de servicios

### ProductoService

Se encarga de consultar productos desde la API externa y manejar errores con `catchError`.

### CategoriaService

Consulta la lista de categorías y las devuelve como observable tipado.

### CarritoService

Mantiene el estado del carrito usando `signal` y `computed`.

## Archivos principales

- [src/app/services/producto.service.ts](src/app/services/producto.service.ts)
- [src/app/services/categoria.service.ts](src/app/services/categoria.service.ts)
- [src/app/services/carrito.service.ts](src/app/services/carrito.service.ts)
- [src/app/pages/catalogo-page/catalogo-page.ts](src/app/pages/catalogo-page/catalogo-page.ts)
- [src/app/guards/auth.guard.ts](src/app/guards/auth.guard.ts)
- [src/app/guards/admin.guard.ts](src/app/guards/admin.guard.ts)

## Tecnologías usadas

- Angular 22
- TypeScript
- RxJS
- Signals
- HttpClient

## Cómo probar la aplicación

```bash
npm install
npm start
```

Luego abre:

```text
http://localhost:4200/
```

## Observaciones

Este taller se enfocó en la separación de responsabilidades mediante servicios y en la gestión reactiva del estado.
