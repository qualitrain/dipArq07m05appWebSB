# Plan de Implementación: Ajuste 03 (Spec 06)

## Análisis de Requerimientos
1. **Contraste del Logo**: Dado que el logo es transparente, se pierde contra fondos corporativos de tono oscuro en el Navbar. 
   - **Solución**: Crearemos un pequeño marco sutil de contraste para la imagen (`.logo-box`) añadiendo un fondo sólido blanco con ligero espaciado (padding).
2. **Reducción de Títulos**: Los tamaños (`h1`, `h2`, etc.) aún se perciben grandes y fuera del contexto corporativo (look infantil).
   - **Solución**: Refinar el esquema tipográfico en `estilos.css` de manera agresiva para escalar hacia una fuente mucho más compacta (por ejemplo, reducir `h1` de 1.75rem a 1.4rem, y así sucesivamente).
3. **Afinación de Tipografía ("Qualitrain" en Footer)**: La marca luce muy gruesa (bold). Una tipografía demasiado "pesada" daña el aspecto profesional.
   - **Solución**: Remover la clase utilitaria `fw-bold` (Bootstrap) y asignar a la marca corporativa del footer un peso fino o normal (`font-weight: 300` o `400`).
4. **Acortar el Footer**: El pie de página original heredaba paddings muy grandes de Bootstrap (`pt-5`, `pb-4`, `mt-5`) diseñados para diseños "Hero".
   - **Solución**: Reducirlos significativamente a `pt-3`, `pb-2`, `mt-4` y ajustar elementos internos para un aspecto muy condensado.
5. **Nuevo Lema Corporativo**:
   - **Solución**: Se reemplazará la frase *"Soluciones tecnológicas e innovación corporativa."* por el eslogan institucional exacto que indicaste: *"Profesores extraordinarios, estudiantes extraordinarios"*.

## Plan de Ejecución

1. Editar explícitamente `estilos.css` para aplicar fondo (`background-color: hsl(0, 0%, 100%)`) y espaciado (padding/border-radius) al `.logo-box`. Redimensionar agresivamente `h1 - h6`. Asegurar que los colores respeten el CSS en `hsl()`.
2. Editar `layout.html` en la sección del fragmento Navbar para garantizar que la imagen viva dentro de la estructura de fondo blanca.
3. Editar `layout.html` (Footer) para limpiar su altura, márgenes, quitar las etiquetas bold gruesas (`fw-bold`, `font-weight-bold`) e inyectar el nuevo lema especificado.
4. Documentaremos esto en el historial como el **Prompt 67** y **68**.

¿Me das luz verde para inyectar estos 5 refinamientos visuales a nuestra plantilla base?
