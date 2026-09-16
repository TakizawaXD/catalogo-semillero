<<<<<<< HEAD
# 📦 Catálogo Semillero

**Aplicación web de catálogo de productos desarrollada con Angular y TypeScript**

Una aplicación de demostración que implementa patrones de comunicación entre componentes Angular, con roles diferenciados (cliente y administrador) y gestión de estado reactivo.
=======
# Catálogo de productos

Aplicación Angular de un catálogo conectado a la Fake Store API de Platzi. El proyecto comenzó como el Taller 01 de componentes y ahora incorpora el Módulo 02: servicios, inyección de dependencias, `HttpClient`, observables y persistencia del carrito.
>>>>>>> b46a51e (Fix Angular app compile issues and add project docs)

---

<<<<<<< HEAD
## 🎯 Descripción

Catálogo Semillero es una aplicación educativa que pone en práctica conceptos fundamentales de Angular, específicamente:
- Comunicación entre componentes con `@Input()` y `@Output()`
- Gestión de estado con `signal()` y `computed()`
- Arquitectura de componentes standalone
- Patrones de filtrado y carrito de compras

> **Nota:** Este proyecto corresponde al Taller 01 del módulo de componentes y utiliza datos simulados.
=======
- Carga de productos desde la API externa.
- Carga de categorías desde la API externa.
- Filtrado por categoría usando el parámetro `categoryId` del endpoint.
- Vista de cliente con tarjetas de productos.
- Vista de administrador con tabla de productos.
- Cambio de rol local entre cliente y administrador.
- Carrito reactivo con cantidad y total calculados.
- Persistencia del carrito en `localStorage`.
- Estado visible de carga mientras se consultan productos.
- Estado vacío cuando la API no devuelve resultados.
- Mensaje visible si falla la carga del catálogo.

## Tecnologías

- Angular 22.1.7, con componentes standalone.
- TypeScript.
- RxJS y `Observable<T>`.
- `HttpClient` para comunicación HTTP.
- Signals: `signal`, `asReadonly` y `computed`.
- HTML y CSS.

> El enunciado original del taller menciona Angular 17, pero las dependencias actuales de este repositorio usan Angular 22. La implementación utiliza APIs estables y compatibles con el enfoque standalone.
>>>>>>> b46a51e (Fix Angular app compile issues and add project docs)

---

<<<<<<< HEAD
## ✨ Características

### Para Cliente
- 📋 Visualización de productos en tarjetas
- 🏷️ Filtro por categoría
- 🛒 Carrito simulado con cantidad total y monto
- ⚠️ Indicador de productos agotados
- 📭 Mensaje de estado vacío

### Para Administrador
- 📊 Visualización de productos en tabla
- ✏️ Acciones de editar producto
- 🗑️ Acciones de eliminar producto
- 👁️ Vista detallada de todos los datos

### General
- 🔄 Cambio de rol sin autenticación
- ⚡ Actualizaciones en tiempo real
- 📱 Interfaz intuitiva

---

## 🛠️ Tecnologías

| Tecnología | Versión | Uso |
|------------|---------|-----|
| **Angular** | 22.1.7 | Framework principal |
| **TypeScript** | - | Lenguaje de programación |
| **HTML** | - | Estructura |
| **CSS** | - | Estilos |
| **Signal API** | - | Gestión de estado |
| **Componentes Standalone** | - | Arquitectura |

**Composición del proyecto:**
- TypeScript: 69.9%
- HTML: 22.7%
- CSS: 7.4%

---

## 📋 Requisitos Previos

Asegúrate de tener instalado:

- **Node.js** (v18 o superior)
- **npm** (v9 o superior)
- **Angular CLI** (v22 o superior)

Verifica tus versiones:
=======
- Node.js.
- npm.
- Angular CLI, opcional si se usan los scripts del proyecto.

Comprobar las versiones instaladas:
>>>>>>> b46a51e (Fix Angular app compile issues and add project docs)

```bash
node --version
npm --version
ng version
```

<<<<<<< HEAD
---

## 🚀 Instalación y Configuración

### 1. Clonar el repositorio
=======
## Instalación
>>>>>>> b46a51e (Fix Angular app compile issues and add project docs)

```bash
git clone https://github.com/TakizawaXD/catalogo-semillero.git
cd catalogo-semillero
<<<<<<< HEAD
```

### 2. Instalar dependencias

```bash
npm install
```

### 3. Ejecutar en desarrollo
=======
npm install
```

## Ejecución

Iniciar el servidor de desarrollo:
>>>>>>> b46a51e (Fix Angular app compile issues and add project docs)

```bash
npm start
```

<<<<<<< HEAD
La aplicación estará disponible en: **`http://localhost:4200/`**
=======
Abrir:
>>>>>>> b46a51e (Fix Angular app compile issues and add project docs)

Se recarga automáticamente al realizar cambios en los archivos.

<<<<<<< HEAD
---

## 🔨 Comandos Disponibles

```bash
# Servidor de desarrollo
ng serve

# Compilar para producción
ng build

# Ejecutar pruebas unitarias
ng test

# Ejecutar pruebas con cobertura
ng test --code-coverage

# Linting del código
ng lint
```

---

## 📖 Guía de Uso

### Inicio de la Aplicación

La aplicación abre en **vista de Cliente**. Aquí puedes:

1. **Ver productos** como tarjetas con imagen, título, precio y categoría
2. **Filtrar por categoría** usando el selector disponible
3. **Agregar al carrito** productos disponibles
4. **Ver indicador** si un producto está agotado
5. **Cambiar a vista Administrador** usando el botón correspondiente

### Vista Cliente

- 📦 Productos mostrados como tarjetas
- 🔍 Filtro por categoría en tiempo real
- ➕ Botón para agregar productos al carrito
- ⚫ Indicador visual de productos sin stock
=======
También se puede usar directamente:

```bash
ng serve
```

## Scripts disponibles

| Comando | Descripción |
| --- | --- |
| `npm start` | Inicia el servidor de desarrollo. |
| `npm run build` | Compila la aplicación para producción. |
| `npm test` | Ejecuta las pruebas unitarias. |
| `npm run watch` | Compila en modo observación. |

## Documentación adicional

- [README del Taller 3](./README-TALLER-3.md)

## Configuración de la API

La URL base está centralizada en [src/environments/environment.ts](src/environments/environment.ts):

```ts
export const environment = {
	production: false,
	apiUrl: 'https://api.escuelajs.co/api/v1',
};
```

La URL no se repite dentro de los servicios. Cada servicio importa `environment` y construye su endpoint a partir de `apiUrl`.

### API utilizada

Base URL: `https://api.escuelajs.co/api/v1`

| Método | Endpoint | Servicio |
| --- | --- | --- |
| `GET` | `/products` | `obtenerTodos()` |
| `GET` | `/products/{id}` | `obtenerPorId(id)` |
| `GET` | `/products?categoryId={id}` | `obtenerTodos(categoryId)` |
| `POST` | `/products` | `crear(producto)` |
| `PUT` | `/products/{id}` | `actualizar(id, cambios)` |
| `DELETE` | `/products/{id}` | `eliminar(id)` |
| `GET` | `/categories` | `CategoriaService.obtenerTodos()` |
| `GET` | `/categories/{id}` | `CategoriaService.obtenerPorId(id)` |

## Configuración de `HttpClient`

La aplicación habilita HTTP desde [src/app/app.config.ts](src/app/app.config.ts):

```ts
import { provideHttpClient } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
	providers: [provideHttpClient()],
};
```

Ningún componente inyecta `HttpClient`. Las peticiones están encapsuladas en servicios reutilizables.

## Servicios

### `ProductoService`

Ubicación: [src/app/services/producto.service.ts](src/app/services/producto.service.ts)

- Está registrado con `providedIn: 'root'`.
- Inyecta `HttpClient` mediante `inject(HttpClient)`.
- Devuelve observables tipados: `Observable<Producto[]>`, `Observable<Producto>` y `Observable<boolean>`.
- Soporta listado, búsqueda por id, creación, actualización y eliminación.
- Acepta un `categoryId` opcional para filtrar desde la API.
- Convierte un error del listado en `of([])` y expone un mensaje mediante un signal de solo lectura.

### `CategoriaService`

Ubicación: [src/app/services/categoria.service.ts](src/app/services/categoria.service.ts)

- Utiliza la misma base de API.
- Consulta `/categories` y `/categories/{id}`.
- Devuelve `Observable<Categoria[]>` y `Observable<Categoria>`.
- Maneja el error de carga y expone un mensaje para la interfaz.

### `CarritoService`

Ubicación: [src/app/services/carrito.service.ts](src/app/services/carrito.service.ts)

El estado interno se mantiene con:

```ts
private readonly items = signal<Producto[]>(this.cargar());
readonly productos = this.items.asReadonly();
```

Los valores derivados se calculan con `computed()`:

- `cantidad`: número de productos en el carrito.
- `total`: suma de los precios.

Métodos disponibles:

- `agregar(producto)`.
- `quitar(id)`.
- `vaciar()`.

El contenido se guarda después de cada cambio en la clave `carrito_wposs`. La lectura inicial está protegida con `try/catch`; si el JSON está corrupto, el carrito inicia vacío. La escritura también está protegida para que un fallo de `localStorage` no rompa la aplicación.

## Flujo del catálogo

El componente [catalogo-page.ts](src/app/pages/catalogo-page/catalogo-page.ts) coordina la pantalla:

1. `categorias$` obtiene las categorías mediante `CategoriaService`.
2. `productos$` comienza consultando todos los productos.
3. Al cambiar el selector, se emite el id de categoría en un `BehaviorSubject`.
4. `switchMap` realiza una nueva petición con `categoryId`.
5. `shareReplay` permite reutilizar el resultado sin duplicar la petición entre consumidores.
6. La plantilla consume los observables con el pipe `async`.

No hay `subscribe()` manual en el componente de la página.

La plantilla muestra:

- `Cargando productos...` durante la petición.
- Un mensaje de error cuando falla la carga.
- `No hay productos para esta categoría.` cuando la respuesta está vacía.

## Componentes

### `CatalogoPageComponent`

Componente contenedor. Administra el rol, dispara el filtro de categoría, expone los observables y conecta los eventos de las vistas con `CarritoService`.

### `TargetaProductoComponent`

Recibe un `Producto` mediante `@Input()` y emite `agregarAlCarrito` mediante `@Output()`. No inyecta `HttpClient` ni conoce la fuente de datos.

### `TablaProductos`

Recibe la lista de productos mediante `@Input()` y emite los eventos `editar` y `eliminar`. Tampoco realiza peticiones HTTP.

## Estructura principal
>>>>>>> b46a51e (Fix Angular app compile issues and add project docs)

### Vista Administrador

- 📊 Productos en tabla con todos sus datos
- ✏️ Botón "Editar" para modificar productos
- 🗑️ Botón "Eliminar" para remover productos
- 🔀 Cambio a vista Cliente

### Carrito

- Muestra cantidad total de items
- Calcula el total en dinero
- Persiste durante la sesión

---

## 📁 Estructura del Proyecto

```
src/
├── app/
│   ├── components/
<<<<<<< HEAD
│   │   ├── targeta-producto/
│   │   │   ├── targeta-producto.ts
│   │   │   ├── targeta-producto.html
│   │   │   └── targeta-producto.css
│   │   └── tabla-productos/
│   │       ├── tabla-productos.ts
│   │       ├── tabla-productos.html
│   │       └── tabla-productos.css
=======
│   │   ├── tabla.productos/
│   │   └── targeta-producto/
>>>>>>> b46a51e (Fix Angular app compile issues and add project docs)
│   ├── models/
│   │   ├── categoria.ts
│   │   └── producto.ts
│   ├── pages/
│   │   └── catalogo-page/
│   ├── services/
│   │   ├── carrito.service.ts
│   │   ├── categoria.service.ts
│   │   └── producto.service.ts
│   ├── app.config.ts
│   ├── app.html
│   └── app.ts
├── environments/
│   └── environment.ts
├── main.ts
└── styles.css
```

<<<<<<< HEAD
---

## 🧩 Componentes
=======
## Verificación del módulo
>>>>>>> b46a51e (Fix Angular app compile issues and add project docs)

Compilar la aplicación:

<<<<<<< HEAD
**Propósito:** Mostrar información de un producto individual

```typescript
@Input({ required: true }) producto: Producto;
@Output() agregarAlCarrito = new EventEmitter<Producto>();
```

**Responsabilidades:**
- Recibe un producto y lo visualiza
- Emite evento al agregar al carrito
- No conoce el arreglo completo ni interactúa con servicios
=======
```bash
npm run build
```

Comprobar manualmente:
>>>>>>> b46a51e (Fix Angular app compile issues and add project docs)

1. Con la red desconectada, debe aparecer un mensaje de error del catálogo.
2. Al cambiar de categoría, la petición debe incluir `categoryId`.
3. La plantilla debe usar `async` y no debe haber `subscribe()` en componentes.
4. No debe existir `HttpClient` importado en componentes.
5. Al colocar JSON inválido en `localStorage` con la clave `carrito_wposs`, la aplicación debe iniciar con el carrito vacío.
6. La carga inicial no debe duplicar la petición de productos.

<<<<<<< HEAD
**Propósito:** Mostrar lista de productos en tabla (Admin)

```typescript
@Input({ required: true }) productos: Producto[];
@Output() editar = new EventEmitter<Producto>();
@Output() eliminar = new EventEmitter<Producto>();
```

**Responsabilidades:**
- Renderiza tabla con datos de productos
- Emite eventos de edición y eliminación
- Optimiza renderizado con `trackBy`
- Muestra mensaje cuando la lista está vacía
=======
## Preguntas de sustentación

### ¿Por qué los servicios devuelven `Observable`?

Porque una petición HTTP es asíncrona. El observable permite que el componente consuma el resultado con `async`, gestione la suscripción automáticamente y mantenga separada la lógica de transporte.

### ¿Dónde se utiliza `async`?
>>>>>>> b46a51e (Fix Angular app compile issues and add project docs)

En [catalogo-page.html](src/app/pages/catalogo-page/catalogo-page.html), sobre `productos$` y `categorias$`. Si el mismo observable HTTP se consumiera varias veces sin compartirlo, podrían generarse varias suscripciones y peticiones. Por eso `productos$` usa `shareReplay`.

<<<<<<< HEAD
**Propósito:** Contenedor principal (Smart Component)

**Responsabilidades:**
- Gestiona el estado de la aplicación
- Administra rol actual (cliente/admin)
- Controla filtros y carrito
- Calcula valores derivados
- Rutas entre vistas

**Estado manejado:**
- `productos`: signal con lista de productos
- `rol`: signal con rol actual
- `categoriaSeleccionada`: signal con categoría activa
- `carrito`: signal con items del carrito
- `productosFiltrados`: computed que filtra por categoría
- `totalCarrito`: computed que suma el total

---

## 🎓 Conceptos Aprendidos

### @Input y @Output
- Paso de datos padre → hijo con `@Input()`
- Comunicación hijo → padre con `@Output()` y `EventEmitter`
- Parámetros requeridos con `required: true`

### Signals y Computed
- Creación de estado reactivo con `signal()`
- Valores derivados sin suscripciones con `computed()`
- Actualizaciones automáticas de vistas

### Componentes Standalone
- Componentes sin módulos
- Importaciones directas de dependencias
- Estructura más limpia

### Patrones de Arquitectura
- Separación de Smart/Presentational components
- Unidireccional data flow
- Componentes reutilizables
=======
### ¿Por qué `productos` es de solo lectura?

`asReadonly()` permite que los componentes observen el carrito sin modificar directamente su signal interno. Los cambios quedan centralizados en `agregar`, `quitar` y `vaciar`.

### ¿Qué ocurre si falla la red?

El servicio devuelve una lista vacía y expone un mensaje de error. La plantilla muestra ese mensaje en pantalla en lugar de dejar una vista en blanco.

### ¿Qué ocurre si el carrito está corrupto?

`cargar()` captura el error de `JSON.parse` y devuelve `[]`, por lo que la aplicación inicia con el carrito vacío.
>>>>>>> b46a51e (Fix Angular app compile issues and add project docs)

---

<<<<<<< HEAD
## 📊 Modelo de Datos

### Interfaz `Producto`

```typescript
interface Producto {
  id: number;
  nombre: string;
  precio: number;
  imagen: string;
  categoria: string;
  stock: number;
}
```

---

## ⚠️ Limitaciones y Alcance

Esta es una **aplicación educativa** con las siguientes limitaciones:

- ❌ **Sin servicios HTTP:** Los datos son simulados
- ❌ **Sin API externa:** No conecta con backend
- ❌ **Sin rutas:** Componente único
- ❌ **Sin persistencia:** No usa localStorage
- ❌ **Sin autenticación:** Cambio de rol sin validación

**Próximas etapas:**
- Integración con API REST
- Implementación de servicios
- Sistema de rutas
- Persistencia de datos
- Autenticación de usuarios

---

## ✅ Estado del Proyecto

- ✔️ Aplicación funcional y compilable
- ✔️ Todos los componentes comunicándose correctamente
- ✔️ Gestión de estado con signals funcionando
- ✔️ Datos simulados para demostración
- ✔️ Build de producción exitoso

---

## 📝 Notas del Desarrollo

- El proyecto utiliza **Angular 22.1.7** con API de signals
- Los productos se encuentran definidos en `src/app/models/producto.ts`
- Los cambios de rol no requieren autenticación (solo para demostración)
- El carrito se reinicia al recargar la página

---

## 📄 Licencia

Este proyecto es parte de material educativo.

---

## 📞 Autor

**TakizawaXD**

---

## 🙏 Agradecimientos

Material educativo del módulo de componentes - Taller 01.

---

<div align="center">

**[⬆ Volver al inicio](#-catálogo-semillero)**

</div>
=======
La integración HTTP está implementada y el proyecto compila con `npm run build`. Las operaciones de creación, actualización y eliminación están disponibles en `ProductoService`; la interfaz actual se concentra en consultar, filtrar, visualizar y gestionar el carrito.
>>>>>>> b46a51e (Fix Angular app compile issues and add project docs)
