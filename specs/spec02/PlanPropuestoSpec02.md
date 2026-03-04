# Plan de Implementación - Desacoplamiento de Menú de Categorías (Spec02)

Este plan detalla los cambios necesarios para cargar dinámicamente las categorías en el menú inicial, centralizar la navegación en `SeleccionProductosController` y eliminar componentes redundantes.

## Cambios Propuestos

### Persistencia
---
#### [NEW] [RepositorioCategorias.java](file:///c:/qtx/workspacesAntigravity/2026/dipArq07m05appWebSB/src/main/java/mx/com/qtx/dipArq07m05appWebSB/persistencia/jpa/reposJpa/RepositorioCategorias.java)
- Crear interfaz `RepositorioCategorias` que extienda `JpaRepository<Categoria, Integer>`.

### Servicios
---
#### [MODIFY] [IGestorSeleccionProductos.java](file:///c:/qtx/workspacesAntigravity/2026/dipArq07m05appWebSB/src/main/java/mx/com/qtx/dipArq07m05appWebSB/corenegocio/IGestorSeleccionProductos.java)
- Agregar método `List<Categoria> getCategorias();`.

#### [MODIFY] [GestorSeleccionProductos.java](file:///c:/qtx/workspacesAntigravity/2026/dipArq07m05appWebSB/src/main/java/mx/com/qtx/dipArq07m05appWebSB/servicios/GestorSeleccionProductos.java)
- Inyectar `RepositorioCategorias`.
- Implementar `getCategorias()` llamando a `repoCategorias.findAll()`.

### Web
---
#### [MODIFY] [SeleccionProductosController.java](file:///c:/qtx/workspacesAntigravity/2026/dipArq07m05appWebSB/src/main/java/mx/com/qtx/dipArq07m05appWebSB/web/SeleccionProductosController.java)
- Agregar método para manejar `@GetMapping("/MenuCategorias.html")`.
- Cargar la lista de categorías usando `gestor.getCategorias()` y añadirla al modelo como `categorias`.

#### [DELETE] [MenuController.java](file:///c:/qtx/workspacesAntigravity/2026/dipArq07m05appWebSB/src/main/java/mx/com/qtx/dipArq07m05appWebSB/web/MenuController.java)
- Eliminar este archivo ya que su funcionalidad ha sido absorbida por `SeleccionProductosController`.

#### [MODIFY] [MenuCategorias.html](file:///c:/qtx/workspacesAntigravity/2026/dipArq07m05appWebSB/src/main/resources/templates/MenuCategorias.html)
- Reemplazar los enlaces hardcoded por un bucle `th:each="cat : ${categorias}"`.
- Generar dinámicamente el texto y el enlace `th:href="@{/categoria/{id}/productos(id=${cat.id})}"`.

## Plan de Verificación

### Pruebas Automatizadas
- Ejecutar `./mvnw clean compile` para asegurar que no hay errores de sintaxis o dependencias.

### Verificación Manual
1. Iniciar la aplicación: `./mvnw spring-boot:run`.
2. Acceder a `http://localhost:8080/MenuCategorias.html`.
3. Verificar que las categorías se muestran correctamente desde la base de datos.
4. Hacer clic en una categoría y verificar que redirige correctamente a la lista de productos.
