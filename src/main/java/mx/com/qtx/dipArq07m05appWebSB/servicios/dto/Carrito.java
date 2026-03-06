package mx.com.qtx.dipArq07m05appWebSB.servicios.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.entidades.Producto;

public class Carrito {
    private final List<ElementoCarrito> elementos = new ArrayList<>();

    public void agregarProducto(Producto producto, int cantidad) {
        Optional<ElementoCarrito> elementoExistente = elementos.stream()
                .filter(e -> e.getProducto().getId().equals(producto.getId()))
                .findFirst();

        if (elementoExistente.isPresent()) {
            ElementoCarrito elemento = elementoExistente.get();
            elemento.setCantidad(elemento.getCantidad() + cantidad);
        } else {
            elementos.add(new ElementoCarrito(producto, cantidad));
        }
    }

    public void eliminarProducto(String idProducto) {
        elementos.removeIf(e -> e.getProducto().getId().equals(idProducto));
    }

    public void vaciar() {
        elementos.clear();
    }

    public List<ElementoCarrito> getElementos() {
        return elementos;
    }

    public java.math.BigDecimal getTotal() {
        return elementos.stream()
                .map(ElementoCarrito::getSubtotal)
                .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);
    }

    public int getNumElementosActual() {
        return elementos.stream()
                .mapToInt(ElementoCarrito::getCantidad)
                .sum();
    }
}
