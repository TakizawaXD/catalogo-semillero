# Catálogo Semillero

Aplicación web de catálogo de productos desarrollada con Angular 22, TypeScript y consumo de API REST. El proyecto está pensado como una práctica de frontend moderna donde se combinan componentes, servicios, routing, guards, formularios reactivos, validaciones, signals y manejo de estado del carrito en una interfaz funcional y escalable.

> Repositorio: https://github.com/TakizawaXD/catalogo-semillero

## Descripción

Catálogo Semillero es una aplicación de e-commerce académico que permite explorar productos, filtrarlos por categoría, ver detalles, gestionar un carrito de compras y alternar entre una vista de cliente y una vista de administrador. La app se construyó como un proyecto de aprendizaje dentro del semillero WPOSS, integrando buenas prácticas de Angular y patrones de desarrollo frontend.

El proyecto evoluciona en varios talleres, cada uno enfocado en un tema específico: servicios y HttpClient, routing y guards, formularios reactivos y validaciones, autenticación, Angular Material y despliegue.

## Objetivo del proyecto

El objetivo principal es consolidar conocimientos de Angular y desarrollo frontend aplicándolos a una aplicación realista de catálogo de productos. Se busca que el proyecto demuestre:

- separación de responsabilidades por capas y componentes
- consumo de APIs con Angular
- gestión reactiva del estado
- navegación protegida y modular
- validación de formularios con buenas prácticas UX
- autenticación y autorización basadas en roles
- despliegue de una app SPA en entorno real

## Problemática

Muchas aplicaciones de catálogo se vuelven difíciles de mantener cuando la lógica de negocio, el consumo de datos y la visualización están mezclados en los componentes. Además, desarrollar interfaces comerciales sin aplicar patrones adecuados genera errores frecuentes en validación, navegación y manejo de estado.

Este proyecto resuelve esa problemática aplicando una estructura modular y moderna en Angular para separar responsabilidades, manejar estado de forma reactiva y ofrecer una experiencia de usuario clara y consistente.

## Tecnologías utilizadas

- Angular 22
- TypeScript
- HTML
- CSS
- RxJS
- Signals
- Angular Router
- HttpClient
- Angular Reactive Forms
- LocalStorage
- Node.js y npm
- Git y GitHub

## Funcionalidades principales

- Visualización de productos desde una API real
- Filtro por categoría
- Vista de cliente y vista de administrador
- Carrito reactivo con cálculo dinámico de cantidad y total
- Persistencia del carrito en LocalStorage
- detalle de cada producto con navegación por rutas
- carga diferida de componentes y módulos
- rutas protegidas por autenticación y roles
- creación y edición de productos en formularios reactivos
- validación de formularios con mensajes orientados a la acción
- registro de usuarios con validación cruzada y asíncrona
- manejo de estados de carga, error y vacío
- autenticación simulada y luego real en el taller final

## Arquitectura del proyecto

La aplicación sigue una arquitectura basada en componentes y servicios, con una separación clara entre:

- componentes visuales
- páginas y vistas
- servicios de negocio
- modelos de datos
- guards y rutas
- validadores personalizados
- entorno de configuración

### Estructura principal

```text
src/
├── app/
│   ├── components/
│   ├── guards/
│   ├── models/
│   ├── pages/
│   ├── services/
│   ├── validators/
│   ├── app.config.ts
│   ├── app.routes.ts
│   ├── app.ts
│   └── app.html
├── environments/
├── main.ts
├── styles.css
└── index.html
```

### Flujo de la aplicación

1. La app carga los productos desde la API externa.
2. Los servicios encapsulan las llamadas HTTP y manejan errores.
3. Los componentes consumen observables y signals para renderizar datos.
4. El router controla navegación entre páginas y rutas protegidas.
5. Los guards impiden acceso a vistas sin permisos adecuados.
6. Los formularios validan la entrada del usuario antes de guardar cambios.
7. El carrito y la sesión persistente se mantienen en LocalStorage.

## Base de datos y persistencia

Este proyecto no usa una base de datos relacional en el frontend. La persistencia se realiza principalmente en LocalStorage para:

- guardar el carrito de compras
- mantener la sesión de usuario o token
- recordar estados de navegación y preferencias del usuario

El contenido real de productos y categorías proviene de una API REST externa, por lo que la fuente de datos principal es el backend remoto, no una base local.

## Documentación por taller

El proyecto se desarrolló en varias etapas, cada una registrada en su documentación propia:

- [Taller 1 - Conceptos iniciales y requisitos](./README-TALLER-1.md)
- [Taller 2 - Conectar el catálogo a la API real](./README-TALLER-2.md)
- [Taller 3 - Navegación, protección de rutas y carga diferida](./README-TALLER-3.md)
- [Taller 4 - Formularios reactivos y validaciones](./readmeTaller4.md)
- [Taller 5 - Autenticación JWT, Angular Material y despliegue](./README-TALLER-5.md)

## Requisitos previos

Asegúrate de tener instalado:

- Node.js 18 o superior
- npm 9 o superior
- Angular CLI compatible con la versión del proyecto

## Instalación y ejecución local

Clonar el repositorio:

```bash
git clone https://github.com/TakizawaXD/catalogo-semillero.git
cd catalogo-semillero
```

Instalar dependencias:

```bash
npm install
```

Ejecutar la aplicación en modo desarrollo:

```bash
npm start
```

La aplicación estará disponible en:

```text
http://localhost:4200/
```

## Scripts disponibles

```bash
npm start
npm run build
npm test
npm run watch
```

## Resultados obtenidos

Con este proyecto se logró construir una aplicación Angular funcional con una estructura sólida y enfocada en buenas prácticas, incluyendo:

- consumo de una API real
- separación clara de responsabilidades
- gestión reactiva del estado
- navegación y protección de rutas
- formularios reactivos con validación profesional
- experiencia de usuario más clara y robusta
- base para una aplicación full-stack frontend más completa

Además, el proyecto demuestra la evolución natural de una app frontend desde una versión básica hasta una estructura más avanzada y preparada para producción.

## Capturas

Se recomienda incluir aquí capturas del catálogo, detalle de producto, carrito, formulario de producto y vista de administración.

## Mejoras futuras

- integración con backend real y autenticación JWT completa
- gestión de usuarios con roles persistentes
- paginación avanzada y búsqueda por texto
- dashboard administrativo con métricas
- optimización de imágenes y rendimiento
- internacionalización (i18n)
- pruebas E2E con Playwright o Cypress
- despliegue automático y pipeline CI/CD

## Autores y contexto

- Autor principal: TakizawaXD
- Proyecto desarrollado dentro del semillero WPOSS
- Enfoque: aprendizaje y aplicación práctica de Angular moderno

## Información académica

- Asignatura: Desarrollo Frontend / Semillero WPOSS
- Profesor: A definir según la instancia académica
- Institución: Proyecto académico del semillero
- Periodo: 2026

## Estado del proyecto

- Estado: en desarrollo y evolución continua
- Enfoque principal: aprendizaje práctico de Angular y buenas prácticas de frontend
- Objetivo final: entregar una aplicación funcional, modular y desplegable

---

Este proyecto sirve como evidencia del progreso en Angular, arquitectura frontend, UX y desarrollo de una aplicación realista basada en consumo de API y manejo de estado reactivo.

## Licencia

El proyecto se comparte como material académico y de aprendizaje dentro del contexto del semillero. Mantener referencia al repositorio y a la autoría correspondiente.

