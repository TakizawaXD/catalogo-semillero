# Catalogo de productos

Aplicacion desarrollada en Angular para construir la primera version de un catalogo de productos con datos simulados.

Este proyecto corresponde al Taller 01 del modulo de componentes. El objetivo es practicar la comunicacion entre componentes mediante `@Input()` y `@Output()`, antes de conectar la aplicacion con una API.

## Funcionalidades

- Visualizacion de productos en tarjetas para el rol cliente.
- Visualizacion de productos en una tabla para el rol administrador.
- Cambio de rol desde la interfaz, sin autenticacion.
- Filtro de productos por categoria.
- Carrito simulado con cantidad de items y total.
- Mensaje de estado vacio cuando no hay productos.
- Indicador de productos agotados cuando el stock es cero.
- Acciones de editar y eliminar disponibles en la tabla.

## Tecnologias utilizadas

- Angular 22.1.7
- TypeScript
- HTML
- CSS
- Componentes standalone
- `signal()` para el estado de la aplicacion
- `computed()` para valores derivados
- `@Input()` y `@Output()` con `EventEmitter`

## Requisitos previos

Antes de ejecutar el proyecto es necesario tener instalado:

- Node.js
- npm
- Angular CLI

Puedes comprobar las versiones instaladas con:

```bash
node --version
npm --version
ng version
```

## Instalacion

Clona el repositorio y entra en la carpeta del proyecto:

```bash
git clone URL_DEL_REPOSITORIO
cd catalogo-semillero
```

Instala las dependencias:

```bash
npm install
```

## Ejecucion del proyecto

Inicia el servidor de desarrollo con:

```bash
ng serve
```

Luego abre la siguiente direccion en el navegador:

```text
http://localhost:4200/
```

La aplicacion se actualiza automaticamente cuando se modifican los archivos.

## Verificacion

Para compilar el proyecto en modo produccion:

```bash
ng build
```

Para ejecutar las pruebas unitarias:

```bash
ng test
```

## Uso de la aplicacion

La aplicacion comienza en la vista de cliente. En esta vista se muestran los productos como tarjetas.

Cada tarjeta permite:

- Consultar la imagen, titulo, precio y categoria.
- Identificar si un producto esta agotado.
- Emitir el evento para agregar un producto al carrito.

El boton de cambio de rol permite pasar a la vista de administrador. En esta vista los productos se muestran en una tabla con sus datos y acciones.

El selector de categoria actualiza la lista mostrada. Si no existen productos para la categoria seleccionada, se muestra un mensaje de estado vacio.

## Estructura del proyecto

```text
src/
├── app/
│   ├── components/
│   │   ├── targeta-producto/
│   │   │   ├── targeta-producto.ts
│   │   │   ├── targeta-producto.html
│   │   │   └── targeta-producto.css
│   │   └── tabla.productos/
│   │       ├── tabla.productos.ts
│   │       ├── tabla.productos.html
│   │       └── tabla.productos.css
│   ├── models/
│   │   └── producto.ts
│   ├── pages/
│   │   └── catalogo-page/
│   │       ├── catalogo-page.ts
│   │       ├── catalogo-page.html
│   │       └── catalogo-page.css
│   ├── app.ts
│   └── app.html
├── styles.css
└── main.ts
```

## Responsabilidad de los componentes

### `TargetaProductoComponent`

Recibe un producto mediante `@Input({ required: true })` y muestra su informacion. Cuando el usuario presiona el boton del carrito, emite el producto mediante un `EventEmitter<Producto>`.

Este componente no conoce el arreglo completo de productos, no utiliza servicios y no modifica el carrito directamente.

### `TablaProductosComponent`

Recibe una lista mediante `@Input({ required: true }) productos` y la muestra en una tabla.

Emite dos eventos mediante `@Output()`:

- `editar`, con el producto seleccionado.
- `eliminar`, con el producto seleccionado.

La tabla utiliza `trackBy` con el `id` del producto y muestra un mensaje cuando la lista esta vacia.

### `CatalogoPageComponent`

Es el componente contenedor y el unico que conoce el arreglo inicial de productos.

Administra:

- El listado de productos mediante un `signal`.
- El rol actual mediante un `signal`.
- La categoria seleccionada mediante un `signal`.
- El carrito mediante un `signal`.
- La lista filtrada mediante `computed()`.
- La cantidad y el total del carrito mediante `computed()`.

La vista se selecciona con `*ngSwitch`: la tabla se muestra para el rol administrador y las tarjetas para el rol cliente.

## Alcance del taller

Los productos se encuentran definidos localmente en `src/app/models/producto.ts`.

En esta etapa no se utilizan:

- Servicios.
- `HttpClient`.
- Rutas.
- `localStorage`.
- Conexion con una API externa.

La integracion con la API se realizara en un modulo posterior.

## Estado del proyecto

Proyecto funcional correspondiente al primer taller de componentes. La aplicacion compila con `ng build` y utiliza datos simulados para demostrar la comunicacion entre componentes.
