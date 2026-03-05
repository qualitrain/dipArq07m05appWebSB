package mx.com.qtx.dipArq07m05appWebSB.servicios;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import mx.com.qtx.dipArq07m05appWebSB.corenegocio.IGestorCarrito;
import mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.entidades.Producto;
import mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.reposJpa.RepositorioProductos;
import mx.com.qtx.dipArq07m05appWebSB.servicios.dto.Carrito;

@Service
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@SessionScope
public class GestorCarrito implements IGestorCarrito {

    private final Carrito carrito = new Carrito();
    private final RepositorioProductos repoProductos;

    public GestorCarrito(RepositorioProductos repoProductos) {
        this.repoProductos = repoProductos;
    }

    @Override
    public void agregarProducto(String idProducto, int cantidad) {
        Producto producto = repoProductos.findById(idProducto)
                .orElseThrow(() -> new ServiciosException("Producto no encontrado con ID: " + idProducto));
        carrito.agregarProducto(producto, cantidad);
    }

    @Override
    public void eliminarProducto(String idProducto) {
        carrito.eliminarProducto(idProducto);
    }

    @Override
    public void vaciarCarrito() {
        carrito.vaciar();
    }

    @Override
    public Carrito getCarrito() {
        return carrito;
    }
}
