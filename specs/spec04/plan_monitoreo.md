# Monitoring and Logging Implementation Plan (spec04)

The goal is to implement monitoring filters and listeners to track request/response content, processing times, and loaded web components, all via logs in DEBUG mode.

## User Review Required

> [!IMPORTANT]
> To log request/response content (body) efficiently, we will use `ContentCachingRequestWrapper` and `ContentCachingResponseWrapper`. This ensures that we can read the stream multiple times without consuming it for the application.

> [!NOTE]
> Performance monitoring will be calculated using `System.nanoTime()` for high precision.

## Proposed Changes

### [Logging & Monitoring]

#### [NEW] [FiltroMonitoreoWeb.java](file:///c:/qtx/workspacesAntigravity/2026/dipArq07m05appWebSB/src/main/java/mx/com/qtx/dipArq07m05appWebSB/web/FiltroMonitoreoWeb.java)
A `OncePerRequestFilter` that will:
- Wrap the request and response for caching.
- Record the start time (`T1`).
- Proceed with the filter chain (request processing).
- Record the time after request processing but before response finalization (`T2`).
- Finalize the response and record end time (`T3`).
- Log:
    - Request: Path, Method, Content.
    - Response: Status, Content.
    - **Times**:
        - Processing Request Time (`T2 - T1`).
        - Processing Response Time (`T3 - T2`).
        - Total Cycle Time (`T3 - T1`).

#### [NEW] [OyenteContextoWeb.java](file:///c:/qtx/workspacesAntigravity/2026/dipArq07m05appWebSB/src/main/java/mx/com/qtx/dipArq07m05appWebSB/web/OyenteContextoWeb.java)
A class implementing `ApplicationListener<ContextRefreshedEvent>` that will:
- Iterate through all beans in the context.
- Filter and log those annotated with `@Controller`, `@RestController`, or `@Service`.

#### [MODIFY] [application.properties](file:///c:/qtx/workspacesAntigravity/2026/dipArq07m05appWebSB/src/main/resources/application.properties)
- Set `logging.level.mx.com.qtx.web=DEBUG` to enable the output of the new monitoring components.

---

## Propuetas Adicionales (Mejora de Monitoreo)

Aparte de los logs, sugiero los siguientes componentes para un monitoreo de nivel profesional:

1.  **Spring Boot Actuator**: Agregar la dependencia `spring-boot-starter-actuator`. Proporciona endpoints como `/actuator/health`, `/actuator/metrics`, y `/actuator/info` para monitorear el estado de la app en tiempo real.
2.  **Micrometer & Prometheus**: Para recolectar métricas de rendimiento y visualizarlas en dashboards (como Grafana).
3.  **MDC (Mapped Diagnostic Context)**: Configurar `MDC` en los logs para incluir un `traceId` único por petición. Esto permite rastrear todos los logs relacionados con una sola transacción de usuario, incluso en sistemas con mucha concurrencia.

---

## Verification Plan

### Automated Tests
- I will verify that the filters are correctly registered by checking the context during startup.

### Manual Verification
1.  Run the application: `./mvnw spring-boot:run`.
2.  Navigate through various pages (`/MenuCategorias.html`, `/carrito`, etc.).
3.  Observe the terminal output (Standard Output/Logs).
4.  Confirm that for each request, there is a block of logs showing:
    - Request content.
    - Response content.
    - Time T1, T2, T3 and their deltas.
5.  Check the logs immediately after startup to see the list of loaded web components.
