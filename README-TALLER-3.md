# Taller 3 - Navegación, protección de rutas y carga diferida

## Contexto

El taller 3 transforma el catálogo en una aplicación con rutas navegables, permisos y carga diferida para optimizar la entrega.

## Rutas a construir

- / redirige a /productos
- /productos con filtros por query params
- /productos/:id detalle de producto
- /carrito con acceso protegido
- /login con autenticación simulada
- /admin con rutas hijas y protección por rol
- ** redirige a página no encontrada

## Requerimientos

### 1. Configuración base

- Usar provideRouter(routes) en app.config.ts.
- Tener <router-outlet> en AppComponent.
- Cada ruta debe definir title.

### 2. Menú de navegación

- Encabezado fijo fuera del router-outlet.
- Usar routerLink, no href internos.
- Marcar la pestaña activa con routerLinkActive.

### 3. Detalle de producto

- La ruta recibe el id por parámetro.
- El componente debe reaccionar a cambios usando paramMap.
- Debe mostrar productos relacionados que enlazan a otros detalles.
- Si el producto no existe, mostrar un mensaje y no una pantalla en blanco.

### 4. Filtros en la URL

- El filtro por categoría se guarda como query param.
- Al recargar con F5, el filtro debe mantenerse.
- Copiar la URL y abrirla en otra pestaña debe mostrar el mismo estado.

### 5. Carga diferida

- /productos/:id y /carrito con loadComponent.
- /admin con loadChildren usando admin.routes.ts.
- Debe haber evidencia de chunks separados en ng build y Network.

### 6. Autenticación simulada

- AuthService mínimo:
  - iniciarSesion(usuario, clave)
  - cerrarSesion()
  - estaAutenticado()
  - tieneRol(rol)
- Si la clave es wposs123, guardar usuario en LocalStorage.
- rol admin para administrador; cualquier otro para usuario.

### 7. Guards

- authGuard sobre /carrito.
- Si no hay sesión, devolver UrlTree a /login con ?volverA=.
- adminGuard sobre /admin.
- El menú debe ocultar links sin acceso; esto no reemplaza los guards.

### 8. CanDeactivate

- Al editar un formulario del panel de administración, si hay cambios sin guardar, mostrar advertencia antes de salir.

## Criterios de evaluación

- Todas las rutas funcionan y el 404 está bien ordenado.
- El detalle se actualiza al cambiar de producto relacionado.
- Los filtros sobrevive a F5 y se comparten por URL.
- Hay carga diferida real con chunks separados.
- authGuard devuelve UrlTree y respeta volverA.
- canMatch impide descargar el chunk de /admin.
- CanDeactivate está implementado.

## Verificación antes de entregar

- ng build sin errores y con al menos tres chunks.
- Ningún href apuntando a rutas internas.
- Ningún snapshot.paramMap en componentes reutilizables.
- Sin sesión, entrar a /carrito lleva a /login?volverA=%2Fcarrito.
- Tras el login, vuelve a /carrito.
- Como usuario común, entrar a /admin no descarga ningún chunk nuevo.
- Filtrar y luego copiar la URL mantiene el filtro en otra pestaña.

## Preguntas de sustentación

### ¿Por qué el detalle cambia al navegar entre productos relacionados?

Porque el componente se vuelve a instanciar o se re-suscribe a cambios de parámetros, no porque se use snapshot.

### ¿Qué evidencia muestra que hay carga diferida?

La salida de ng build con chunks separados y la descarga del bundle al entrar a /admin.

### ¿Por qué authGuard devuelve un UrlTree?

Porque el guard debe redirigir al login sin hacer navegación imperativa desde el componente.

### ¿Qué pasa si alguien edita LocalStorage para poner rol admin?

Puede engañar al frontend, pero eso es un problema de seguridad del cliente. La validación real debe estar en el backend.

## Conceptos a estudiar

- Routes y RouterOutlet
- route params y query params
- lazy loading con loadComponent y loadChildren
- Guards de autenticación y acceso
- redirecciones con UrlTree
- navegación por URL compartible
