package mx.com.qtx.dipArq07m05appWebSB.servicios.dto;

import mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.entidades.Producto;

public class ElementoCarrito {
    private Producto producto;
    private int cantidad;

    public ElementoCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public java.math.BigDecimal getSubtotal() {
        return producto.getPrecio().multiply(java.math.BigDecimal.valueOf(cantidad));
    }

    @Override
    public String toString() {
        return "ElementoCarrito [producto=" + producto.getNombre() + ", cantidad=" + cantidad + "]";
    }
}
