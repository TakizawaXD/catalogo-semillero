# Taller 4 - Formularios reactivos y tipados

Este taller transforma el catálogo de productos, que antes era principalmente de solo lectura, en una aplicación capaz de crear productos, editar productos existentes y registrar usuarios.

Todos los formularios están construidos con Angular Reactive Forms. Se utiliza `FormBuilder` con `nonNullable`, sin `ngModel` y sin `new FormGroup(...)` manuales.

## Objetivos

- Crear y editar productos mediante formularios reactivos.
- Registrar usuarios con validación síncrona, cruzada y asíncrona.
- Mostrar mensajes útiles únicamente cuando corresponde.
- Mantener los datos escritos por el usuario si una petición falla.
- Proteger la salida de la pantalla de edición cuando existen cambios sin guardar.

## Rutas del taller

| Ruta | Funcionalidad | Conceptos principales |
| --- | --- | --- |
| `/admin/productos/nuevo` | Alta de producto | `FormBuilder`, validadores integrados, validadores propios y `FormArray` |
| `/admin/productos/:id/editar` | Edición de producto | Precarga con `patchValue`, campo deshabilitado y `CanDeactivate` |
| `/registro` | Registro de usuario | Validación cruzada y validador asíncrono |

Las rutas de administración están protegidas por el guard de administración. La ruta de edición también utiliza `puedeSalirGuard`.

## Formulario de producto

El formulario se declara con `this.fb.nonNullable.group(...)` y contiene los siguientes campos:

| Campo | Validaciones |
| --- | --- |
| `codigo` | Se muestra en edición, se precarga y permanece deshabilitado. |
| `titulo` | Obligatorio, entre 3 y 100 caracteres y no puede contener solo espacios. |
| `precio` | Obligatorio y mayor que cero. |
| `descripcion` | Obligatoria, entre 20 y 500 caracteres. |
| `categoriaId` | Obligatoria y debe corresponder a una categoría válida. |
| `imagenes` | `FormArray` con al menos una URL obligatoria y con formato `http://` o `https://`. |

### Imágenes dinámicas

El campo `imagenes` es un `FormArray<FormControl<string>>`:

- El formulario comienza con una imagen.
- `Agregar imagen` añade un nuevo control.
- `Quitar` elimina la imagen seleccionada.
- El botón `Quitar` queda deshabilitado cuando solo queda una imagen.
- Cada URL muestra su propio mensaje según la regla que incumple.

### Contador de descripción

La cantidad de caracteres restantes se actualiza mediante `valueChanges` sobre el control `descripcion`. El límite es de 500 caracteres.

## Mensajes y experiencia de validación

Los mensajes de error se muestran mediante el estado `touched` de cada control. Por eso, al abrir un formulario vacío no aparece ningún mensaje rojo.

Al presionar `Guardar` o `Registrarme`:

1. Se ejecuta `markAllAsTouched()`.
2. Todos los errores pasan a ser visibles.
3. Si el formulario es inválido, se busca el primer control inválido.
4. La pantalla hace scroll hasta ese campo y le da el foco.

Cada regla tiene un mensaje específico y orientado a la acción, por ejemplo:

- `Mínimo 3 caracteres.`
- `Máximo 100 caracteres.`
- `El precio debe ser mayor que 0.`
- `Selecciona una categoría.`
- `La URL de la imagen es obligatoria.`

El botón de guardado no se deshabilita solo porque el formulario sea inválido. Únicamente se deshabilita mientras la petición está en curso y cambia su texto a `Guardando...`.

## Creación y edición de productos

En modo creación se envía una petición para crear el producto. En modo edición:

1. Se obtiene el producto por su identificador.
2. Los datos se cargan con `patchValue`.
3. Las imágenes se reconstruyen como un `FormArray` con sus validadores.
4. El código se muestra lleno y deshabilitado.
5. El formulario se marca como pristine después de cargar los datos.

Aunque `codigo` está deshabilitado, se incluye en el cuerpo de la petición utilizando:

```ts
const raw = this.formulario.getRawValue();
```

Usar `formulario.value` omitiría los controles deshabilitados y el código no llegaría al backend.

Si la petición falla, se muestra un mensaje en pantalla y se conserva todo lo que el usuario escribió. Si la petición termina correctamente, la aplicación navega al listado de productos.

### Cambios sin guardar

La edición implementa la interfaz `PuedeSalir` y devuelve `!formulario.dirty` desde `puedeSalir()`.

Si el usuario modifica el formulario y trata de salir, `puedeSalirGuard` detecta que el formulario está dirty y permite conectar el aviso de cambios sin guardar del taller 03 con una condición real.

## Formulario de registro

El formulario de registro contiene estos campos:

| Campo | Validaciones |
| --- | --- |
| `nombre` | Obligatorio, mínimo 3 caracteres y no puede contener solo espacios. |
| `correo` | Obligatorio, formato de correo válido y disponibilidad contra la API. |
| `clave` | Obligatoria, mínimo 8 caracteres, al menos una letra y un número. |
| `confirmacion` | Obligatoria y debe coincidir con `clave`. |

### Validación cruzada

`clavesIguales` se aplica al grupo completo del formulario, porque compara dos controles relacionados: `clave` y `confirmacion`.

El mensaje se lee del grupo:

```ts
formulario.hasError('clavesIguales')
```

Solo aparece cuando `confirmacion` ya fue tocado. Al hacer coincidir ambas claves, el error desaparece.

### Validación asíncrona del correo

`correoDisponible` implementa `AsyncValidatorFn` y consulta el endpoint de verificación de usuarios:

```text
GET /api/usuarios/verificarCorreo?correo=...
```

El control `correo` utiliza `updateOn: 'blur'`, por lo que la verificación ocurre cuando el usuario termina de editar el campo, no en cada tecla.

Mientras la petición está pendiente:

- Se muestra `Verificando disponibilidad...`.
- `formulario.pending` es verdadero.
- El botón de registro permanece deshabilitado.

La API puede producir dos resultados de validación:

- `correoNoDisponible`: el correo ya está registrado.
- `correoNoVerificado`: no fue posible completar la verificación.

## Validadores propios

Los validadores están ubicados en `src/app/validators/` y no utilizan `any`:

| Archivo | Tipo | Responsabilidad |
| --- | --- | --- |
| `no-solo-espacios.validator.ts` | `ValidatorFn` | Rechaza valores vacíos o compuestos únicamente por espacios. |
| `claves-iguales.validator.ts` | `ValidatorFn` | Compara `clave` y `confirmacion` a nivel de grupo. |
| `correo-disponible.validator.ts` | `AsyncValidatorFn` | Consulta la API para verificar la disponibilidad del correo. |

## API y servicios involucrados

- `ProductoService`: consulta, crea y actualiza productos.
- `CategoriaService`: obtiene la lista real de categorías para el selector.
- `AuthService`: verifica la disponibilidad del correo y registra usuarios.
- `environment.apiUrl`: centraliza la URL base de la API.

## Verificación manual

Antes de entregar el taller se deben comprobar estos escenarios:

1. Abrir un formulario vacío y confirmar que no aparecen errores.
2. Presionar `Guardar` con los campos vacíos y verificar que aparecen todos los errores y que la pantalla salta al primero.
3. Agregar y quitar imágenes comprobando que siempre quede al menos una.
4. En edición, modificar el producto y salir para comprobar el aviso de cambios sin guardar.
5. Abrir Network y confirmar que el `codigo` viaja en la actualización.
6. Escribir un correo completo y verificar que se realiza una sola petición al perder el foco.
7. Probar claves diferentes y luego iguales.
8. Simular una falla de red y confirmar que el formulario conserva sus valores.
9. Ejecutar la compilación y las pruebas:

```bash
npm install
npm run build
npm test
```

También se puede iniciar la aplicación con:

```bash
npm start
```

La aplicación queda disponible en `http://localhost:4200/`.

## Preguntas de sustentación

### 1. ¿Por qué no se ve ningún error al abrir un formulario vacío?

Porque los mensajes dependen de `touched`. Al abrirlo, los controles todavía no fueron tocados. Al presionar Guardar se ejecuta `markAllAsTouched()`, se muestran los errores y se desplaza la vista al primer control inválido.

### 2. ¿Dónde está el validador de claves iguales?

Está en `src/app/validators/claves-iguales.validator.ts`. Se aplica al grupo porque necesita leer simultáneamente `clave` y `confirmacion`; por eso el error se consulta en el formulario y no como un error propio de un único campo.

### 3. ¿Cómo viaja el código deshabilitado?

Se obtiene el formulario con `getRawValue()`, que incluye controles deshabilitados. Si se utilizara `value`, Angular excluiría `codigo` del objeto enviado.

### 4. ¿Por qué el correo no genera una petición por tecla?

Porque el control está configurado con `updateOn: 'blur'`. El validador asíncrono se ejecuta cuando el usuario termina de editar y sale del campo.

### 5. ¿Qué ocurre si se elimina la validación del frontend y se envía un precio negativo?

La validación del frontend mejora la experiencia, pero no es una medida de seguridad. La API debe validar nuevamente los datos en el backend y rechazar cualquier precio inválido antes de persistirlo.

## Archivos principales

- [src/app/pages/producto-form-page/producto-form-page.ts](src/app/pages/producto-form-page/producto-form-page.ts)
- [src/app/pages/producto-form-page/producto-form-page.html](src/app/pages/producto-form-page/producto-form-page.html)
- [src/app/pages/registro-page/registro-page.ts](src/app/pages/registro-page/registro-page.ts)
- [src/app/pages/registro-page/registro-page.html](src/app/pages/registro-page/registro-page.html)
- [src/app/validators/no-solo-espacios.validator.ts](src/app/validators/no-solo-espacios.validator.ts)
- [src/app/validators/claves-iguales.validator.ts](src/app/validators/claves-iguales.validator.ts)
- [src/app/validators/correo-disponible.validator.ts](src/app/validators/correo-disponible.validator.ts)
- [src/app/guards/puede-salir.guard.ts](src/app/guards/puede-salir.guard.ts)
