# Taller 4 - Formularios reactivos y validaciones

## Contexto

El taller 4 lleva la aplicación de lectura a una aplicación que puede crear, editar y registrar usuarios, con validaciones que no molestan al usuario y que se aplican correctamente.

## Rutas del taller

- /admin/productos/nuevo: alta de producto
- /admin/productos/:id/editar: edición de producto
- /registro: registro de usuario

## Requerimientos

### 1. Formulario de producto

Campos y reglas:

- titulo: obligatorio, mínimo 3, máximo 100, no puede ser solo espacios
- precio: obligatorio, numérico, mayor a 0
- descripcion: obligatoria, mínimo 20, máximo 500
- categoriaId: obligatoria, seleccionada de la lista real de la API
- imagenes: FormArray, mínimo una, cada URL obligatoria

Reglas técnicas:

- Construir con FormBuilder y nonNullable.
- No usar new FormGroup(...) manual.
- Debe haber un contador de caracteres restantes para descripcion, alimentado por valueChanges.
- Debe haber botón para agregar imagen.
- El botón de quitar debe deshabilitarse cuando queda una sola imagen.

### 2. Mostrar errores correctamente

- Ningún mensaje rojo visible antes de tocar el campo.
- Cada regla tiene su propio mensaje.
- No usar mensajes genéricos como "campo inválido".
- Al presionar Guardar con el formulario incompleto, todos los errores aparecen a la vez.
- Debe hacer scroll hasta el primer campo con error.

### 3. Envío del formulario

- El botón no se deshabilita por formulario inválido.
- Sí se deshabilita mientras se guarda.
- El texto del botón cambia a "Guardando...".
- Si el backend falla, se muestra el error en pantalla y el formulario conserva los datos escritos.
- Si el backend responde bien, navega al listado.

### 4. Edición

- Precargar los datos del producto con patchValue.
- El campo codigo debe mostrarse lleno y deshabilitado.
- Al guardar, el codigo debe llegar al backend.
- Usar getRawValue() para incluir controles deshabilitados.
- Si el usuario sale con cambios sin guardar, el CanDeactivate debe avisar.
- La condición real es formulario.dirty.

### 5. Registro

Campos y reglas:

- nombre: obligatorio, mínimo 3, no solo espacios
- correo: obligatorio, formato válido, no debe estar ya registrado
- clave: obligatoria, mínimo 8, al menos una letra y un número
- confirmacion: obligatoria, igual a clave

Reglas técnicas:

- La validación de claves iguales debe ser un validador a nivel de grupo.
- El mensaje aparece solo cuando confirmacion ya fue tocado.
- La verificación del correo es un validador asíncrono contra la API.
- Mientras verifica, mostrar indicador y bloquear el botón con formulario.pending.
- El validador asíncrono no debe disparar una petición por cada letra escrita.

### 6. Validadores propios

Como mínimo en src/app/validators/:

- noSoloEspacios
- clavesIguales
- correoDisponible

Cada uno con el tipo correcto y sin any.

## Criterios de evaluación

- Todos los formularios son reactivos y tipados.
- Sin any ni ngModel.
- Errores visibles solo tras touched.
- markAllAsTouched() y scroll al primer error.
- Campo deshabilitado llega al backend con getRawValue().
- FormArray de imágenes funcionando correctamente.
- Validación cruzada correcta con error del grupo.
- Validador asíncrono sin petición por tecla.

## Verificación antes de entregar

- ng build sin errores ni advertencias.
- No existe la palabra ngModel en el proyecto.
- No existe la palabra any en formularios ni validadores.
- Al abrir cualquier formulario, no hay mensajes rojos visibles.
- Al presionar Guardar con todo vacío, aparecen todos los errores y la pantalla salta al primero.
- En edición, con Network abierto, el codigo viaja en el cuerpo de la petición.
- Escribir un correo completo genera una sola petición de verificación.
- Poner claves distintas muestra el mensaje; corregirlas lo hace desaparecer.
- reset() deja los campos en su valor inicial, no en null.
- Con la red caída, guardar muestra un error y no borra lo que el usuario escribió.

## Preguntas de sustentación

### ¿Por qué no se ve ningún error al abrir un formulario vacío?

Porque los mensajes están ligados a touched. El formulario se abre limpio y el usuario aún no interactuó con los controles. Al hacer click en Guardar, se marca todo como tocado y aparecen los errores.

### ¿Dónde está el validador de claves iguales y por qué se lee del grupo?

Se define en el grupo completo porque compara dos campos relacionados: clave y confirmacion. Por eso el error se lee desde el formulario y no desde una sola propiedad.

### ¿Qué pasa si se usa value en vez de getRawValue() en edición?

Los controles deshabilitados no se incluirían en el payload, por lo que el codigo podría llegar ausente al backend.

### ¿Por qué no hay una petición por tecla en el correo?

Porque el validador asíncrono se dispara solo cuando el campo pierde el foco o cuando la lógica de validación lo indica, no al escribir cada letra.

### Si se borra la validación del frontend y se envía un precio negativo, ¿qué pasa?

La validación del cliente no protege de forma real. La seguridad real debe estar en el backend, donde se deben validar todos los datos antes de guardar.

## Conceptos a estudiar

- Reactive Forms
- FormBuilder y nonNullable
- FormArray y controles dinámicos
- Validators y custom validators
- AsyncValidatorFn
- markAllAsTouched
- CanDeactivate
- valueChanges
- getRawValue
