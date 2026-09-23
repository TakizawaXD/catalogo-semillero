# Taller 1 - Catálogo inicial y fundamentos de Angular

## Contexto

El taller 1 parte de una aplicación con catálogo de productos en una sola vista. El objetivo es consolidar los fundamentos de Angular, componentes, data binding, estructuras de control, separación de responsabilidades y la base para luego conectarlo con una API.

## Objetivos

- Construir una estructura inicial del catálogo.
- Mostrar productos desde un arreglo local.
- Separar la lógica en componentes reutilizables.
- Implementar comunicación entre componentes con @Input y @Output.
- Entender la diferencia entre datos simulados y datos reales.

## Requerimientos principales

### 1. Estructura del catálogo

- Mostrar productos en tarjetas o tablas.
- Separar visualización, lógica y modelos.
- Usar componentes para representar cada parte de la interfaz.

### 2. Componentes clave

- Tarjeta de producto
- Tabla de productos
- Vista de cliente
- Vista de administrador
- Carrito de compras

### 3. Comunicación entre componentes

- El componente padre envía datos al hijo con @Input.
- El hijo comunica eventos al padre con @Output.
- El flujo de datos debe ser claro y reutilizable.

### 4. Estado inicial del proyecto

- La aplicación debe funcionar sin depender de backend.
- Los productos pueden estar en un arreglo fijo o mock.
- Permite practicar la lógica de UI antes de integrar la API.

### 5. Funcionalidades esperadas

- Visualización de productos.
- Cambio entre vista de cliente y vista de administrador.
- Filtro por categoría.
- Carrito con cantidad y total.
- Interacción básica con botones y eventos.

## Criterios de evaluación

- Los componentes están bien separados.
- La comunicación entre padres e hijos funciona correctamente.
- La interfaz refleja el estado de la aplicación.
- La estructura es entendible y reutilizable.

## Verificación antes de entregar

- La aplicación compila sin errores.
- Los componentes se comunican correctamente.
- No hay lógica de negocio mezclada con la vista.
- El catálogo funciona en cliente y administrador.

## Preguntas de sustentación

### ¿Por qué el taller 1 usa datos simulados?

Porque primero se enseña la estructura de Angular y la comunicación entre componentes sin depender de un backend externo.

### ¿Qué diferencia hay entre vista de cliente y vista de administrador?

La primera se enfoca en consumir productos y agregar al carrito; la segunda se centra en la gestión y visualización de información del catálogo.

### ¿Qué pasa si se modifica la estructura de datos del producto?

Se deben ajustar los modelos, los componentes y la lógica de renderizado para mantener consistencia.

## Conceptos a estudiar

- Angular components
- @Input y @Output
- Templates y directivas estructurales
- TypeScript básico
- modelos e interfaces
- gestión inicial del estado en UI

## Material recomendado

- Documentación oficial de Angular sobre componentes
- Guías de templates y binding
- Fundamentos de TypeScript
