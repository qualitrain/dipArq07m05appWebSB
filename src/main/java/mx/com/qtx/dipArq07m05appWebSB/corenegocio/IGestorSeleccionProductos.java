package mx.com.qtx.dipArq07m05appWebSB.corenegocio;

import java.util.List;

import mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.entidades.Producto;

public interface IGestorSeleccionProductos {

    List<Producto> getProductosXcat(int idCategoria);

}
