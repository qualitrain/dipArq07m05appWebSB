# Plan de Implementación de Interfaz Corporativa (Spec 06)

El objetivo de esta iteración es proveer una imagen corporativa unificada y profesional en toda la aplicación. Para lograr esto de forma escalable (sin repetir código en cada pantalla), utilizaremos una característica clave de Thymeleaf: **Fragmentos (Fragments)**.

## Archivos y Cambios Propuestos

### 1. Activos Estáticos (Imágenes y CSS)
- **Copia del Logo**: El archivo `specs/spec06/logoQtx.png` será copiado a la carpeta pública del servidor web: `src/main/resources/static/images/logoQtx.png`.
- *(Opcional)* Un pequeño archivo `style.css` en la carpeta estática para los ajustes menores del pie de página.

### 2. Creación de Plantillas Maestras (Fragments)
Se creará un nuevo archivo maestro para contener los elementos comunes de la interfaz:
#### [NUEVO] `src/main/resources/templates/fragments/layout.html`
Este archivo contendrá dos secciones principales (usando Bootstrap 5):
1. `<nav th:fragment="navbar">`:
    * Contendrá el `logoQtx.png` en la esquina superior izquierda.
    * Menú de enlaces: `Inicio` (`/MenuCategorias.html`), `Productos` (`/MenuCategorias.html`), `Carrito` (`/carrito`), `Monitoreo` (`/monitoreo/componentes`), `Salir` (`/logout` o `#`).
2. `<footer th:fragment="footer">`:
    * Un pie de página elegante (fondo oscuro, texto claro) dividido en columnas.
    * **Información**: Nombre de la empresa, Dirección, Teléfono, Correo, Sitio web, Copyright y el logo repetido.

### 3. Refactorización de las Vistas Existentes
Reemplazaremos todos los menús de navegación sueltos que existen actualmente e inyectaremos las nuevas plantillas fragmentadas usando `th:replace="~{fragments/layout :: navbar}"` y `th:replace="~{fragments/layout :: footer}"`.

Las vistas que serán actualizadas son:
* `src/main/resources/templates/MenuCategorias.html`
* `src/main/resources/templates/IUselecProds.html`
* `src/main/resources/templates/VerCarrito.html`
* `src/main/resources/templates/monitoreo/componentes.html`
* `src/main/resources/templates/monitoreo/peticiones.html`

## Plan de Ejecución
1. Crear el directorio `static/images` y copiar el logo.
2. Crear `templates/fragments/layout.html` y maquetar el navbar y footer usando Bootstrap 5.
3. Actualizar una por una las 5 vistas `.html` para eliminar sus `<nav>` redundantes e importar los fragmentos estandarizados.
4. Compilar y probar visualmente navegando por los flujos.
