# Plan de Implementación: Ajuste 04 (Spec 06)

## Análisis de Requerimientos y Soluciones

1. **Visibilidad del Logo en el Navbar**:
   - **Problema**: El logo es transparente y se funde con el color oscuro del Navbar, perdiendo definición.
   - **Solución**: Reforzaremos explícitamente la clase `.logo-box` en `estilos.css` para darle un fondo blanco sólido (`background-color: hsl(0, 0%, 100%)`), un poco más de padding (`padding: 5px 10px`) y un borde redondeado (`border-radius: 6px`). Esto encapsulará el logo como un letrero luminoso.

2. **Botón de Navegación en Monitoreo**:
   - **Problema**: `componentes.html` carece de un link para saltar a visualizar las peticiones en vivo.
   - **Solución**: Agregaremos un botón Bootstrap elegante (`<a th:href="@{/monitoreo/peticiones}" class="btn btn-outline-primary mb-3">Ver Peticiones / Requests</a>`) justo encima de las tablas de servlets en `monitoreo/componentes.html` para conectar ambas vistas de administración.

3. **Tamaño de los Títulos Principales**:
   - **Problema**: Los títulos siguen percibiéndose muy grandes e infantiles.
   - **Solución**: Aplicaremos un encogimiento más severo en `estilos.css`. Reduciremos `h1` a `1.25rem` (apenas más de 20px), `h2` a `1.15rem`, y emparejaremos los estilos para lograr una interfaz de uso denso y aspecto de control panel profesional.

4. **Footer Demasiado Extenso y Abierto**:
   - **Problema**: Demasiado espacio entre líneas (`line-height`) y un área del footer desproporcionada.
   - **Solución**: En `estilos.css`, reduciremos el margen de los párrafos en el `.footer-corporativo` (ej. `margin-bottom: 0.2rem;`), ajustaremos la fuente (`font-size: 0.85rem`) y fijaremos un interlineado compacto (`line-height: 1.2;`). Además nos aseguraremos de que las clases `pt-3` y `mt-4` que inyectamos antes se mantengan respetadas.

Se procederá a editar `estilos.css` y `componentes.html` para cumplir con esto una vez aprobado el plan.
