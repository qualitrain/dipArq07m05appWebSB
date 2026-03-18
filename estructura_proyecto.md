# Estructura de Arquitectura del Proyecto Web Spring Boot

A continuación, se detalla la estructura principal del código fuente (ubicado en `src/main/java/mx/com/qtx/dipArq07m05appWebSB/`) y los recursos (`src/main/resources/`) que componen la aplicación, separados por capas lógicas y responsabilidades:

## 1. Capa de Arranque (Raíz del paquete base)
* `DipArq07m05appWebSB.java`: Clase principal que inicializa la aplicación Spring Boot mediante `@SpringBootApplication`.

## 2. Capa de Dominio / Negocio Core (`corenegocio`)
Contiene las interfaces de negocio principales de la aplicación.
* `IGestorSeleccionProductos.java`: Define contrato para operaciones de menú de categorías y visualización de productos.
* `IGestorCarrito.java`: Define contrato para el carrito de compras (agregar, consultar totales).

## 3. Capa de Servicios (`servicios` y `servicios/dto`)
Implementaciones de la lógica de negocio. Esta capa orquesta el acceso a la base de datos y aplica reglas de negocio.
* `GestorSeleccionProductos.java`: Implementa la búsqueda de categorías y productos interactuando con la capa de Persistencia.
* `GestorCarrito.java`: Servicio (`@SessionScope`) que administra el estado del carrito activo del usuario.
* **DTOs (`servicios/dto`)**:
  * `Carrito.java`, `ElementoCarrito.java`: Modelos para el carrito.
  * `CategoriaDTO.java`, `CategoriaProductoDTO.java`: Transferencia de datos ligeros de catálogos hacia la capa Web.

## 4. Capa de Persistencia (`persistencia/jpa`)
Representa el acceso a datos y las entidades de persistencia usando JPA e Hibernate.
* **Repositorios (`reposJpa`)**: 
  * `RepositorioCategorias.java`, `RepositorioProductos.java`: Interfaces `JpaRepository` para consultas a la DB.
* **Entidades (`entidades`)**:
  * `Producto.java`, `Categoria.java`, `InventarioProducto.java`, y más entidades del modelo de ventas.

## 5. Capa Web de Presentación (`web`)
Maneja las interacciones HTTP, Controladores MVC y Utilidades Web.
* **Controladores (`web/controllers`)**:
  * `SeleccionProductosController.java`: Maneja las peticiones para el `<MenuCategorias>` y la lista de `<Productos>`.
  * `CarritoController.java`: Maneja la adición y visualización del carrito (`/carrito/*`).
* **Módulo de Monitoreo Web (`web/monitoreo`)**: Componente completamente autocontenido para diagnóstico en tiempo de ejecución.
  * `FiltroMonitoreoWeb.java`: Filtro interceptor para capturar tiempos y cuerpos de las peticiones HTTP.
  * `OyenteContextoWeb.java`, `OyenteServletsFiltros.java`: Escuchan e inspeccionan el arranque de Spring y el contenedor de Servlets.
  * `ServicioMonitoreo.java`: Retenedor concurrente en memoria para almacenar la telemetría recolectada.
  * **dtos (`web/monitoreo/dtos`)**: `PeticionWebInfo`, `ComponenteWebInfo` para encapsular datos.
  * **controllers (`web/monitoreo/controllers`)**: `MonitoreoController.java` mapeado a las rutas `/monitoreo/*`.

## 6. Recursos y Vistas (`src/main/resources`)
* `application.properties`: Configuración centralizada de Spring Boot (Trazabilidad, conexión a DB, niveles de log).
* **Vistas HTML (Thymeleaf)** en `templates/`:
  * `MenuCategorias.html`, `IUselecProds.html`: Pantallas de presentación orientadas al cliente (Catálogos de productos).
  * `VerCarrito.html`: Pantalla para validar elementos agregados al canasto.
  * `monitoreo/componentes.html`, `monitoreo/peticiones.html`: Dashboard y visualizadores de logs para fines operativos y depuración, alimentados por el módulo autocontenido de monitoreo web.
