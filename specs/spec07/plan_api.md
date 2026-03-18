# Plan de Implementación API REST (Spec 07)

## 1. Análisis de Requerimientos
La especificación solicita la creación de un API REST para **agregar, eliminar y modificar** tanto Categorías como Productos. 

**Consideraciones principales:**
1. Los controladores vivirán bajo el paquete `mx.com.qtx.dipArq07m05appWebSB.webapi`.
2. Validaciones estrictas de datos de entrada desde los controladores (JSR-380: Bean Validation).
3. Renombrado del archivo vital `GestorSeleccionProductos.java` (y su interfaz) a `GestorProductos.java` para reflejar que dejará de ser exclusivo para lectura/selección y adquirirá poderes globales de negocio (CRUD completo).
4. Proponer e incluir mejores prácticas modernas de diseño API REST. 

## 2. Mejores Prácticas Propuestas a Implementar
Para garantizar una arquitectura profesional en nuestra API, adoptaremos los siguientes estándares en este desarrollo:

- **Estandarización de Respuestas HTTP**: En lugar de regresar simplemente los objetos o booleanos, enrutaremos todo mediante wrappers formales `ResponseEntity<T>`, utilizando el código de estatus correcto (`200 OK` en lectura, `201 CREATED` al insertar, `204 NO CONTENT` al eliminar de forma exitosa, `404 NOT FOUND` si la entidad no existe).
- **Manejo Centralizado de Excepciones**: Usaremos una clase marcada con `@RestControllerAdvice` como sumidero universal capaz de transformar automáticamente nuestras custom `ServiciosException` o instancias de `MethodArgumentNotValidException` (fallos de validación) en un JSON uniforme de error estándar hacia el consumidor del API, sin reventar el servidor ni fugar trazas de tipo stack trace.
- **Validaciones Automáticas**: Añadiremos (si no existe ya en el pom) la dependencia de `spring-boot-starter-validation`  y utilizaremos la anotación `@Valid` emparejada con restricciones lógicas (`@NotNull`, `@NotBlank`, `@Size`, `@Min`) dentro de las entidades provistas.

## 3. Guía de Ejecución

**Paso 1: Renombrado y Expansión del Servicio**
- Renombrar la interfaz `IGestorSeleccionProductos` a `IGestorProductos`.
- Renombrar `GestorSeleccionProductos` a `GestorProductos`.
- En esta misma interfaz añadir las operaciones faltantes del CRUD (Create, Update, Delete, Get-Unico) para ambos repositorios.

**Paso 2: Incorporar Dependencia de Validación**
- Revisar `pom.xml` para constatar o agregar el Starter Validation de Spring Boot. 
- Incrustar anotaciones a los campos requeridos de `Categoria` y `Producto`.

**Paso 3: Construcción de la Capa REST**
- Crear el paquete `mx.com.qtx.dipArq07m05appWebSB.webapi`.
- Crear `CategoriasRestController.java`.
- Crear `ProductosRestController.java`.
- Mapear las URIs correctamente (e.g. `/api/v1/categorias`, `/api/v1/productos`).

**Paso 4: Gestor Universal de Errores**
- Construir en `mx.com.qtx.dipArq07m05appWebSB.webapi.excepciones` un interceptor universal para asegurar nuestra promesa de Mejor Práctica REST. Todo esto se implementará a nivel Controlador.
