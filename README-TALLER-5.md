# Taller 5 - Autenticación JWT, Angular Material y despliegue

## Contexto

Este taller cierra la ruta de frontend y convierte la aplicación en algo que puede presentarse como proyecto del semillero. Es la entrega que se evalúa como proyecto completo.

## Requerimientos

### 1. Autenticación

- AuthService con iniciarSesion, cerrarSesion, obtenerToken, estaAutenticado y tieneRol.
- El token se guarda en localStorage y sobrevive a recargar la página.
- estaAutenticado verifica la expiración del token, no solo su existencia.
- Todas las lecturas de localStorage y decodificación del token deben ir en try/catch.
- La pantalla de login usa formulario reactivo con validaciones.
- El mensaje de error del login debe ser general: "Correo o contraseña incorrectos".

### 2. Interceptores

- authInterceptor agrega Authorization: Bearer <token> a cada petición con clone().
- No agrega el header si no hay token.
- errorAuthInterceptor, ante un 401, cierra la sesión, redirige a login y vuelve a lanzar el error.
- Registro con withInterceptors([...]).
- No usar HTTP_INTERCEPTORS.

### 3. Guards conectados

- El authGuard del taller 3 usa el AuthService real.
- Después del login, la aplicación vuelve a la URL guardada en volverA.
- El adminGuard sigue impidiendo la descarga del chunk de /admin para usuarios comunes.
- El menú oculta opciones sin permiso, pero sin quitar los guards.

### 4. Angular Material

Debe pasar la interfaz a Material. Como mínimo:

- Barra superior con menú y sesión: mat-toolbar
- Tarjetas del catálogo: mat-card
- Formularios: mat-form-field + matInput + mat-error
- Botones: mat-button / mat-flat-button / mat-icon-button
- Tabla de administración con paginación: mat-table + mat-paginator
- Confirmación antes de eliminar: mat-dialog
- Mensajes de éxito o error: mat-snack-bar
- Indicador de carga: mat-progress-spinner

Reglas:

- Solo importar módulos de Material que realmente se usen.
- Todo botón de solo icono debe tener aria-label.
- Toda imagen debe tener alt.

### 5. Producción

- environment.ts y environment.production.ts.
- Sin URLs quemadas en el código.
- Sin console.log.
- La app debe verse bien en pantalla de 375px.

### 6. Despliegue

- La app debe estar desplegada en Vercel o Netlify.
- Despliegue automático desde la rama principal.
- Directorio de publicación: dist/<nombre-app>/browser.
- Configurar vercel.json o netlify.toml con fallback a index.html.
- Recargar /productos/42 directamente en la barra de direcciones debe funcionar.
- El README.md del repositorio debe incluir la URL desplegada, cómo levantarlo localmente y un usuario de prueba.

## Criterios de evaluación

- AuthService completo con expiración verificada y try/catch.
- Interceptores funcionales con clone() y manejo de 401.
- Guards conectados y volverA respetado.
- Interfaz completa en Material.
- Sin secretos, URLs quemadas ni console.log.
- Desplegada y funcionando con rutas profundas.

## Verificación antes de entregar

- ng build sin errores ni advertencias.
- grep -r "console.log" src/ no devuelve nada.
- grep -ri "localhost" src/ solo aparece en environment.ts.
- Iniciar sesión y recargar con F5: la sesión sigue activa.
- Con Network abierto, toda petición autenticada lleva Authorization.
- Cambiar manualmente el exp del token en LocalStorage a una fecha pasada: la app lo trata como no autenticado.
- Poner basura en el token: la app arranca sin sesión y sin pantalla blanca.
- Abrir la aplicación en modo privado: arranca.
- Como usuario común, entrar a /admin no descarga ningún chunk nuevo.
- Entrar a /carrito sin sesión lleva a /login?volverA=%2Fcarrito, y tras el login vuelve al carrito.
- Recargar la ruta profunda en la app desplegada no da 404.
- Navegar con teclado (Tab y Enter) es posible.
- En 375px no hay scroll horizontal.

## Preguntas de sustentación

### ¿Por qué el interceptor usa clone()?

Porque debe crear una copia de la request original para agregar el encabezado Authorization sin mutar la request original ni afectar otros puntos del flujo de requests.

### ¿Qué pasa si editas el exp del token en localStorage?

La aplicación debe tratar al usuario como no autenticado si el token ya expiró.

### ¿Qué pasa si el token tiene basura?

La app debe manejarlo con try/catch y arrancar sin romper la UI.

### ¿Por qué el token va en localStorage y no en cookie HttpOnly?

Se usa localStorage para facilitar el manejo del token desde Angular. La cookie HttpOnly mejora la seguridad, pero deja menos control en el frontend y requiere manejo del backend más específico.

### ¿Qué se puede saltar en el frontend?

Las validaciones del cliente pueden evitarse con herramientas del navegador. Lo realmente crítico se corrige en el backend, donde debe validarse todo lo que entra y sale.

## Conceptos a estudiar

- JWT y expiración
- Interceptors en Angular
- Guards con autenticación real
- Angular Material
- despliegue de SPAs en Vercel o Netlify
- seguridad frontend vs backend
- routing y fallback para SPA
