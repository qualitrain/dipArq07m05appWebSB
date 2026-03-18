# Web Monitoring Dashboard Implementation Plan (spec05)

The goal is to provide a web interface (Controller + Views) that allows users to monitor the web application's incoming requests/responses and view the registered Servlets and Filters, including their execution order.

## User Review Required

> [!IMPORTANT]
> Currently, `FiltroMonitoreoWeb` and `OyenteServletsFiltros` only print information to the application logs (`slf4j`).
> To make this data available to a Web Controller, we must store it in memory. I propose creating a `@Service` (`ServicioMonitoreoMemoria`) that acts as an in-memory repository to collect this data during the application's lifecycle.
> To prevent `OutOfMemory` errors, the service will only keep the **last N (e.g., 50) requests**.

Is this architectural approach (using an in-memory service as a bridge between the Filters/Listeners and the Controller) acceptable to you?

## Proposed Changes

### [Core Data Models & Storage]

#### [NEW] `mx/com/qtx/dipArq07m05appWebSB/web/monitoreo/dtos/PeticionWebInfo.java`
A DTO or Record bean to hold the data of a single HTTP request and response (Path, Method, Request Body, Response Status, Response Body, processing times).

#### [NEW] `mx/com/qtx/dipArq07m05appWebSB/web/monitoreo/dtos/ComponenteWebInfo.java`
A DTO to hold data about a Servlet or Filter (Name, Class, Mappings, Order/Position).

#### [NEW] `mx/com/qtx/dipArq07m05appWebSB/web/monitoreo/ServicioMonitoreo.java`
A `@Service` bean with:
- `List<PeticionWebInfo> getUltimasPeticiones()`
- `void registrarPeticion(PeticionWebInfo info)` (evicts oldest entries if limit is reached)
- `List<ComponenteWebInfo> getFiltros()`
- `List<ComponenteWebInfo> getServlets()`
- Setters/adders for the Listeners to populate the web components on context startup.

---

### [Refactoring Existing Listeners and Filters]

#### [MODIFY] `FiltroMonitoreoWeb.java`
- Inject `ServicioMonitoreo`.
- In `logDetails()`, in addition to logging to the console, instantiate a `PeticionWebInfo` object and pass it to `ServicioMonitoreo.registrarPeticion()`.

#### [MODIFY] `OyenteServletsFiltros.java`
- Inject `ServicioMonitoreo`.
- In `reportarServlets()` and `reportarFiltros()`, extract the names, classes, mappings, and calculated order position, and pass them as `ComponenteWebInfo` to the service.

---

### [Web Presentation Layer (MVC)]

#### [NEW] `mx/com/qtx/dipArq07m05appWebSB/web/controllers/MonitoreoController.java`
A new `@Controller` mapped to `/monitoreo`.
- `@GetMapping("/peticiones")`: Injects the service, retrieves `getUltimasPeticiones()`, adds to the Model, and returns `"monitoreo/peticiones"`.
- `@GetMapping("/componentes")`: Retrieves filters and servlets, adds to the Model, and returns `"monitoreo/componentes"`.

#### [NEW] `src/main/resources/templates/monitoreo/peticiones.html`
A Thymeleaf template using Bootstrap to display a table of the recent HTTP requests. Row details (like full body content) will be presented cleanly (e.g., in a modal or `<details>` tag) to keep the UI beautiful.

#### [NEW] `src/main/resources/templates/monitoreo/componentes.html`
A Thymeleaf template to display the list of registered Servlets and Filters and their execution sort order.

## Verification Plan

### Automated Tests
- Ensure `mvnw clean compile` passes cleanly after these changes.

### Manual Verification
1. Start the application locally with `./mvnw spring-boot:run`.
2. Navigate to `http://localhost:8080/monitoreo/componentes`. Verify that the page loads beautifully and lists the loaded Servlets and Filters in order.
3. Navigate to a business page like `http://localhost:8080/MenuCategorias.html` to generate some traffic.
4. Navigate to `http://localhost:8080/monitoreo/peticiones`. Validate that the table shows the traffic we just generated, including the exact JSON or HTML response content and the processing times.
