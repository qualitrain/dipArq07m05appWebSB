package mx.com.qtx.dipArq07m05appWebSB.corenegocio;

import java.util.List;

import mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.entidades.Categoria;
import mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.entidades.Producto;

public interface IGestorProductos {

    List<Producto> getProductosXcat(int idCategoria);
    List<Producto> getProductos();
    List<Categoria> getCategorias();
    
    // CRUD Categoría
    Categoria getCategoria(int id);
    Categoria insertarCategoria(Categoria categoria);
    Categoria actualizarCategoria(Categoria categoria);
    void eliminarCategoria(int id);
    
    // CRUD Producto
    Producto getProducto(String clave);
    Producto insertarProducto(Producto producto);
    Producto actualizarProducto(Producto producto);
    void eliminarProducto(String clave);

}
