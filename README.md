# 📦 Catálogo Semillero

**Aplicación web de catálogo de productos desarrollada con Angular y TypeScript**

Una aplicación de demostración que implementa patrones de comunicación entre componentes Angular, con roles diferenciados (cliente y administrador) y gestión de estado reactivo.

---

## 🎯 Descripción

Catálogo Semillero es una aplicación educativa que pone en práctica conceptos fundamentales de Angular, específicamente:
- Comunicación entre componentes con `@Input()` y `@Output()`
- Gestión de estado con `signal()` y `computed()`
- Arquitectura de componentes standalone
- Patrones de filtrado y carrito de compras

> **Nota:** Este proyecto corresponde al Taller 01 del módulo de componentes y utiliza datos simulados.

---

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

```bash
node --version
npm --version
ng version
```

---

## 🚀 Instalación y Configuración

### 1. Clonar el repositorio

```bash
git clone https://github.com/TakizawaXD/catalogo-semillero.git
cd catalogo-semillero
```

### 2. Instalar dependencias

```bash
npm install
```

### 3. Ejecutar en desarrollo

```bash
ng serve
```

La aplicación estará disponible en: **`http://localhost:4200/`**

Se recarga automáticamente al realizar cambios en los archivos.

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
│   │   ├── targeta-producto/
│   │   │   ├── targeta-producto.ts
│   │   │   ├── targeta-producto.html
│   │   │   └── targeta-producto.css
│   │   └── tabla-productos/
│   │       ├── tabla-productos.ts
│   │       ├── tabla-productos.html
│   │       └── tabla-productos.css
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

---

## 🧩 Componentes

### `TargetaProductoComponent`

**Propósito:** Mostrar información de un producto individual

```typescript
@Input({ required: true }) producto: Producto;
@Output() agregarAlCarrito = new EventEmitter<Producto>();
```

**Responsabilidades:**
- Recibe un producto y lo visualiza
- Emite evento al agregar al carrito
- No conoce el arreglo completo ni interactúa con servicios

### `TablaProductosComponent`

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

### `CatalogoPageComponent`

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

---

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
