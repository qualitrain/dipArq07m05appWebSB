package mx.com.qtx.dipArq07m05appWebSB.servicios;

import mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.Categoria;

public interface IGestorDatosAltoNivel {

	Categoria leerCategoriaXID(int id);

	Categoria leerCategoriaXID_conProductos(int id);

}
