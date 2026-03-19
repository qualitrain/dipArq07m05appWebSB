# Walkthrough - Solución Error 500 en SpringDoc

He diagnosticado y resuelto el error 500 que ocurría al acceder a `/v3/api-docs`. El problema se debía a una incompatibilidad de versiones en el `pom.xml` y a un proceso zombie que bloqueaba el puerto 8080.

## Cambios Realizados

### Configuración del Ajuste (pom.xml)
- **Corrección de Versión de Spring Boot**: Se cambió el `spring-boot-starter-parent` de la versión inválida `4.0.3` a la versión estable `3.3.4`.
- **Alineación de Versión de SpringDoc**: Se estableció `springdoc-openapi-starter-webmvc-ui` en la versión `2.6.0`, totalmente compatible con Spring Boot 3.3.x.
- **Corrección de Dependencia de Test**: Se reemplazó `spring-boot-starter-jdbc-test` por el estándar `spring-boot-starter-test`.

### Limpieza de Entorno
- Se identificó y finalizó un proceso zombie (PID 17712) que mantenía ocupado el puerto 8080 y servía una versión incompatible de la aplicación.

## Resultados de la Verificación

### Definición OpenAPI
He verificado que el endpoint `/v3/api-docs` ahora devuelve la definición JSON correctamente con un estado **200 OK**.

```bash
Invoke-WebRequest -Uri "http://localhost:8080/v3/api-docs" -UseBasicParsing
# Resultado: 200 OK
# Contenido: {"openapi":"3.0.1","info":{"title":"OpenAPI definition","version":"v0"}...}
```

### Arranque de la Aplicación
La aplicación ahora inicia correctamente utilizando **Spring Framework 6.1.13**, según se confirmó en los logs de tiempo de ejecución.

> [!TIP]
> Asegúrate siempre de que la versión del parent de Spring Boot corresponda a una versión estable (ej. 3.x) para evitar errores de tipo `NoSuchMethodError` al añadir starters de terceros como SpringDoc.
