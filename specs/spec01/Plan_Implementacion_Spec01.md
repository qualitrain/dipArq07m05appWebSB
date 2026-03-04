# Plan de Implementación - Spec01: Selección de Productos

Este plan describe la arquitectura y los cambios técnicos necesarios para cumplir con el diagrama de secuencia `1_1_SeleccionProducto.jpg` y el de clases `1_1_ClasesParticipantes.jpg`, utilizando Spring Boot, Data JPA y Thymeleaf/Bootstrap 5.

## Proposed Changes

### Archivo de Configuración (Maven)
Dado que es una aplicación web con vistas dinámicas, necesitamos incluir las capacidades de Spring MVC y del motor de plantillas.
#### [MODIFY] pom.xml
- **Ruta:** `c:/qtx/workspacesAntigravity/2026/dipArq07m05appWebSB/pom.xml`
- **Cambios:**
  - Agregar dependencia `spring-boot-starter-web` para habilitar el uso de anotaciones `@Controller` y configuración MVC.
  - Agregar dependencia `spring-boot-starter-thymeleaf` para procesar las vistas HTML.

---

### Capa de Datos (Repository Layer)
Se requiere una interfaz de repositorio que consulte en la base de datos la entidad `Producto` usando el ID de la relación de su `Categoria`.
#### [NEW] RepositorioProductos.java
- **Ruta:** `c:/qtx/workspacesAntigravity/2026/dipArq07m05appWebSB/src/main/java/mx/com/qtx/dipArq07m03explorCapaPer/persistencia/RepositorioProductos.java`
- **Cambios:**
  - Crear interfaz extendiendo de `JpaRepository<Producto, String>`
  - Añadir el método de consulta derivado: `List<Producto> findByCategoriaId(Integer id);`

---

### Capa de Servicio (Service Layer)
Para evitar acoplar los controladores a los repositorios, crearemos el gestor de lógica de negocio o modelo.
#### [NEW] IGestorSeleccionProductos.java
- **Ruta:** `c:/qtx/workspacesAntigravity/2026/dipArq07m05appWebSB/src/main/java/mx/com/qtx/dipArq07m03explorCapaPer/servicios/IGestorSeleccionProductos.java`
- **Cambios:**
  - Declarar método `List<Producto> getProductosXcat(int idCategoria);`
  - *Nota sobre los diagramas:* En `1_1_SeleccionProducto.jpg` la firma dice que regresa `List<Categoria>`, pero es claro que lógicamente y por el parámetro se requiere una `List<Producto>`.

#### [NEW] GestorSeleccionProductos.java
- **Ruta:** `c:/qtx/workspacesAntigravity/2026/dipArq07m05appWebSB/src/main/java/mx/com/qtx/dipArq07m03explorCapaPer/servicios/GestorSeleccionProductos.java`
- **Cambios:**
  - Implementación con la anotación `@Service`.
  - Inyectar el `RepositorioProductos` y delegar la llamada a `findByCategoriaId()`.

---

### Capa de Controlador (Controller Layer)
Un controlador de Spring MVC para capturar las peticiones GET e inyectar atributos al objeto o modelo `Model` para la vista.
#### [NEW] SeleccionProductosController.java
- **Ruta:** `c:/qtx/workspacesAntigravity/2026/dipArq07m05appWebSB/src/main/java/mx/com/qtx/dipArq07m03explorCapaPer/controladores/SeleccionProductosController.java`
- **Cambios:**
  - Archivo anotado con `@Controller`.
  - Función `obtenerProdsXCategoria(@PathVariable int id, Model model)` mapeada a la ruta HTTP `GET /categoria/{id}/productos`.
  - Invocará al `IGestorSeleccionProductos` y retornará la cadena `"IUselecProds"` para indicar a Spring que busque la vista por ese nombre.

---

### Capa de Presentación (Views Layer)
Se generarán las vistas HTML integradas con Thymeleaf y basadas en la hoja de estilos de Bootstrap 5 desde un CDN, proporcionando una estructura de grillas para los productos y un catálogo inicial (opcional) para lanzar el flujo.
#### [NEW] MenuCategorias.html
- **Ruta:** `c:/qtx/workspacesAntigravity/2026/dipArq07m05appWebSB/src/main/resources/templates/MenuCategorias.html`
- **Cambios:**
  - Una vista semilla para simular el inicio del diagrama de secuencia, con enlaces fijos a un par de categorías de ejemplo: `/categoria/1/productos`, `/categoria/2/productos`, etc.

#### [NEW] IUselecProds.html
- **Ruta:** `c:/qtx/workspacesAntigravity/2026/dipArq07m05appWebSB/src/main/resources/templates/IUselecProds.html`
- **Cambios:**
  - Vista final que recibe la colección `productos` en el subyacente `Model`.
  - Usaremos un `th:each="producto : ${productos}"` para iterar mediante una grilla/tarjetas de producto (Card de Bootstrap 5) mostrando "Nombre", "Descripción" y "Precio".


## Verification Plan

### Automated Tests
* No se han proveído tests unitarios todavía. 
* Compilación: Ejecutaré Maven (`mvn clean compile`) para asegurar que el código generado no rompe debido a referencias faltantes.
* Despliegue de aplicación: Arrancaremos el servidor embebido `mvn spring-boot:run` y comprobaremos que las tablas y mapeos JPA arranquen limpiamente (la base de datos se requiere funcionando).

### Manual Verification
1. Abriré la página principal generada de `MenuCategorias.html` usando el navegador web integrado (herramienta del entorno de IA) o accediendo a tu `http://localhost:8080/MenuCategorias.html` (o un path equivalente).
2. Simularé un clic en el enlace de la categoría deseada, observando si la terminal arroja una ruta procesada `GET /categoria/{id}/productos`.
3. Validaremos visualmente en `IUselecProds.html` si la interfaz se renderizó con objetos (en caso de que la BD tenga registros de Categoría 1 y 2, de lo contrario se vería vacía la grilla).
