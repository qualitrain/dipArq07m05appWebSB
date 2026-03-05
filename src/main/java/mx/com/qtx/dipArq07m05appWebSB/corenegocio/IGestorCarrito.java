package mx.com.qtx.dipArq07m05appWebSB.corenegocio;

import mx.com.qtx.dipArq07m05appWebSB.servicios.dto.Carrito;

public interface IGestorCarrito {
    void agregarProducto(String idProducto, int cantidad);
    void eliminarProducto(String idProducto);
    void vaciarCarrito();
    Carrito getCarrito();
}
