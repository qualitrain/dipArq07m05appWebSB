# Plan de Implementación: Ajuste 02 (Spec 06)

## Análisis de Requerimientos
1. **Reducción del aspecto "Infantil"**: Se deben reducir los tamaños exagerados de los títulos (H1, H2, etc.) que Bootstrap trae por defecto, usando clases utilitarias (`fs-4`, `fs-5`) o sobreescribiendo H1-H6 en CSS.
2. **Tipografía de Tablas**: Reemplazar la apariencia "burda" de las tablas. Aplicaremos una fuente más compacta y moderna (ej. `Roboto`, `Inter`, `Helvetica Neue`), bordes más tenues, cabeceras ligeras y un espaciado (padding) controlado en las celdas.
3. **Navbar - Optimización de Logo y Nombre**: Se eliminará el texto redundante "Qualitrain" de la marca en la barra de navegación, dejando exclusivamente la imagen del logo, ajustando su tamaño para que se aprecie sin cajas blancas chapuceras.
4. **Footer - Limpieza y Datos Corporativos**:
   - Se borrará la repetición del logo inferior.
   - Se actualizarán los datos de contacto con los valores proporcionados:
     - **Sitio**: qualitrain.com.mx
     - **Dirección**: Av. Patriotismo 878 Col. Mixcoac, CDMX, C.P. 03910
     - **Teléfono**: 55 5511 8121
     - **Correo**: acruz@qualitrain.com.mx
5. **Esquema de Colores Limpio (Estilo Corporativo Real)**: Cambiaremos la paleta del CSS. Eliminaremos el color "Cyan neón" y pasaremos a usar un esquema de azules marinos clásicos, blancos rotos para los fondos, y textos sobrios. 

## Pasos Técnicos para la Solución

1. **Modificación de `estilos.css`**:
   - Cambiar variables de paleta a: `--corp-blue: #0f2c59`, `--corp-light: #f8f9fa`, `--corp-text: #333333`.
   - Redefinir `h1, h2, h3` para tener `font-weight: 500; font-size: 1.5rem` (escalados dinámicamente).
   - Aplicar `font-family` corporativa global e inyectar un estilo `.table-corporativa` para limpiar las tablas (quitar fondos chillantes).

2. **Modificación del fragmento `layout.html`**:
   - En `<nav>`: Solo dejar `<img th:src="@{/images/logoQtx.png}" alt="Qualitrain Logo" height="45">` quitando el `span` "Qualitrain". 
   - En `<footer>`: Eliminar la etiqueta `<img>` y colocar los datos correctos utilizando las fuentes elegidas.

3. **Limpieza en Vistas de Módulo (`peticiones.html`, `componentes.html`, `VerCarrito.html`, etc.)**:
   - Reducir `h1, h2` asignándoles clases de tipografía elegantes.
   - Limpiar clases `table-dark` o `table-primary` de las tablas y cambiarlas por algo más limpio y ejecutivo (`table-hover border-top`).

## Aprobación
¿Estás de acuerdo con aplicar estos pasos para aterrizar el diseño de Spec 06 a un entorno completamente limpio y profesional?
