package mx.com.qtx.dipArq07m03explorCapaPer.servicios;

import java.util.List;

import mx.com.qtx.dipArq07m03explorCapaPer.persistencia.jpa.Producto;

public interface IGestorSeleccionProductos {

    List<Producto> getProductosXcat(int idCategoria);

}
