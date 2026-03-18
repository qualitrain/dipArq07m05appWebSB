# Diagnóstico y Plan de Acción: Error de Sesión en API REST

## 1. El Origen del Error
El error `Cannot lazily initialize collection... (no session)` ocurre porque:
1. **Configuración Lazy**: La relación `@OneToMany` en `Categoria.java` está marcada como `FetchType.LAZY` (por defecto).
2. **Ciclo de Vida de la Sesión**: Aunque agregaste `@Transactional`, la sesión de base de datos se cierra en cuanto el método del servicio (`GestorProductos.getCategorias()`) termina y retorna la lista al controlador.
3. **Serialización de Jackson**: El `RestController` intenta convertir la lista de objetos a JSON. Jackson recorre todos los campos, incluido `productos`. Al intentar leer esa lista, Hibernate intenta cargarla de la base de datos, pero la sesión ya no existe, lanzando la excepción.

## 2. Hipótesis de Solución

### Opción A: Ignorar la colección en el JSON (La más común para Listados)
Normalmente, un endpoint que lista categorías no debería devolver obligatoriamente todos los productos de cada categoría (por rendimiento y tamaño del JSON).
- **Acción**: Agregar `@JsonIgnore` sobre el atributo `productos` en `Categoria.java`.

### Opción B: Carga Forzada (Fetch Join)
Si realmente deseas que el JSON incluya los productos:
1. **Acción en Repo**: Cambiar `JOIN` por `JOIN FETCH` en `RepositorioCategorias.java`.
2. **Acción en Entidad**: Manejar la recursividad infinita (Producto -> Categoria -> Producto...) usando `@JsonIgnore` en el atributo `categoria` de `Producto.java` o usando `@JsonManagedReference` / `@JsonBackReference`.

### Opción C: Uso de DTOs (Mejor Práctica)
Crear clases `CategoriaDTO` y `ProductoDTO` que solo contengan los datos necesarios para la API. Esto elimina el acoplamiento con JPA y evita que Jackson intente tocar proxies de Hibernate.

## 3. Plan Propuesto
1. Aplicar la **Opción A** por ser la más eficiente para un listado general.
2. Aplicar `@JsonIgnore` también en la relación inversa de `Producto -> Categoria` para evitar recursividad en los endpoints de productos.
3. (Opcional pero recomendado) Configurar Jackson para que ignore propiedades no inicializadas de Hibernate.

¿Cómo prefieres proceder? ¿Necesitas que el JSON de categorías incluya su lista de productos?
