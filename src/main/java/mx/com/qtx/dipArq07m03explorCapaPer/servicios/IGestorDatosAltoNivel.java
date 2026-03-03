package mx.com.qtx.dipArq07m03explorCapaPer.servicios;

import mx.com.qtx.dipArq07m03explorCapaPer.persistencia.jpa.Categoria;

public interface IGestorDatosAltoNivel {

	Categoria leerCategoriaXID(int id);

	Categoria leerCategoriaXID_conProductos(int id);

}
