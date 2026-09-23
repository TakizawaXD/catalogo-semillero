# Taller 2 - Conectar el catálogo a la API real

## Contexto

El taller 2 parte del catálogo del taller 1, pero ahora los componentes deben mostrar datos reales de la API de Platzi usando HttpClient.

## API a utilizar

Base URL:

```text
https://api.escuelajs.co/api/v1
```

## Endpoints relevantes

- GET /products
- GET /products/{id}
- GET /products?categoryId={id}
- GET /products?title={texto}
- GET /categories
- POST /products
- PUT /products/{id}
- DELETE /products/{id}

## Requerimientos

### 1. Configuración

- Usar provideHttpClient() en app.config.ts.
- La URL base debe estar en src/environments/environment.ts.
- No quemar la URL base dentro del servicio.

### 2. ProductoService

- Debe usar providedIn: 'root'.
- Debe inyectar HttpClient con inject().
- Debe tener métodos tipados con genéricos explícitos.
- Debe devolver Observable<T>.
- obtenerTodos() debe manejar errores con catchError y devolver of([]).

### 3. CategoriaService

- Mismo patrón que ProductoService.
- Debe consultar /categories.

### 4. Conectar el catálogo

- CatalogoPageComponent debe inyectar los servicios y exponer observables.
- La plantilla debe usar async.
- No debe haber subscribe() manual en componentes.
- El filtro por categoría debe realizar la consulta con categoryId en la API.
- Debe mostrar estados de carga y vacío.

### 5. CarritoService

- Estado con signal<Producto[]>([]).
- Exponer el estado con asReadonly().
- Cantidad y total con computed().
- Métodos agregar, quitar y vaciar.
- Persistencia en LocalStorage con try/catch.

### 6. Manejo de errores visibles

- Si falla la carga del catálogo, el usuario debe ver un mensaje en la UI.
- Si falla crear, editar o eliminar, no puede quedar silenciado.

## Criterios de evaluación

- Ningún componente inyecta HttpClient.
- Todos los métodos están tipados, sin any.
- Pipe async usado en plantillas.
- Carrito con Signals y computed.
- Persistencia en LocalStorage manejada con try/catch.
- Errores visibles para el usuario.
- No se modifican los componentes del taller 1 para cumplir la lógica del taller 2.

## Verificación antes de entregar

- ng build sin errores.
- No existe any en servicios.
- Ningún componente importa HttpClient.
- Ningún .subscribe() en componentes.
- En Network se hace una sola petición de carga.
- Con la red desconectada, la app muestra un mensaje y no una pantalla en blanco.
- Si la clave del carrito tiene contenido corrupto, la aplicación arranca sin romperse.

## Preguntas de sustentación

### ¿Por qué el servicio devuelve un Observable en vez de devolver los datos directamente?

Porque una petición HTTP es asíncrona. El observable permite reaccionar al flujo de la respuesta y consumirla de forma reactiva.

### ¿Qué pasa si se usa el mismo observable dos veces en la plantilla?

Sin compartir el flujo, la petición puede duplicarse si no se reutiliza correctamente.

### ¿Por qué productos está expuesto con asReadonly()?

Porque se quiere permitir lectura del estado sin permitir mutaciones directas desde fuera del servicio.

### ¿Qué pasa con LocalStorage corrupto?

La aplicación debe arrancar con un carrito vacío y no romper la UI.

## Conceptos a estudiar

- HttpClient
- Observables y operadores
- catchError
- Signals y computed
- LocalStorage y manejo de errores
- DI y servicios en Angular
