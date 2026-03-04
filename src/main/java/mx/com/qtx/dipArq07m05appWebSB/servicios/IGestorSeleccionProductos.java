package mx.com.qtx.dipArq07m05appWebSB.servicios;

import java.util.List;

import mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.Producto;

public interface IGestorSeleccionProductos {

    List<Producto> getProductosXcat(int idCategoria);

}
