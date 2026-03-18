# Verificación y Construcción de API REST (Spec 07)

## Cambios Implementados Según Plan
Hemos completado exitosamente las tareas encomendadas:
1. **Renombrado del Gestor**: Se migró de `IGestorSeleccionProductos` a `IGestorProductos` y se extendieron por completo con las operaciones CRUD de 8 métodos (GET, GETxID, POST, PUT, DELETE) para dar a la API lógica pura de negocio. Las referencias huérfanas (ej. `SeleccionProductosController`) fueron actualizadas para compilar.
2. **Validaciones Empresariales JSR-380**: 
   - Se inyectó `<artifactId>spring-boot-starter-validation</artifactId>` al `pom.xml`.
   - Se aplicaron etiquetas `@NotBlank`, `@Size`, `@DecimalMin`, y `@NotNull` a los campos de base de datos en `Categoria` y `Producto`.
3. **Controladores REST API**:
   - `CategoriasRestController` mapeado a `/api/v1/categorias`.
   - `ProductosRestController` mapeado a `/api/v1/productos`.
   - Implementaron rigurosamente la directiva `@Valid` para bloquear cuerpos JSON corruptos y el estandarizado wrapper `ResponseEntity<T>` para estatus precisos HTTP (201 CREATED, 204 NO CONTENT, 200 OK).
4. **Manejador Global Interceptor**:
   - `ManejadorExcepcionesGlobal` dotado con `@RestControllerAdvice` capturando validaciones fallidas y errores internos, para responder siempre con JSON amigable en vez de volcar una traza de error de Java (`stacktrace`).

## Pruebas Superadas
La re-compilación de dependencias mediante `mvn clean compile` pasó en limpio (Exit code: 0).

## Plan de Verificación Manual (Sugerido para el Usuario)
1. **Ejecutar Aplicación**: Reinicia tu programa de Spring Boot desde el IDE o comando.
2. **Prueba Endpoints (Postman/Curl)**:
   - Haz un **GET** a `http://localhost:8080/api/v1/categorias`.
   - Haz un **POST** a `http://localhost:8080/api/v1/productos` violando intencionalmente propiedades (ej. con nombre o precio en nulo) y verifica que la API responda con **400 Bad Request** y el mensaje configurado de `@Valid`.
   - Haz un **DELETE** contra un producto correcto y asegura que retorne el estado **204 No Content**.
