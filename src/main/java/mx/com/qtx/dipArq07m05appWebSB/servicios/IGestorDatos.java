package mx.com.qtx.dipArq07m05appWebSB.servicios;

import java.util.List;

import mx.com.qtx.dipArq07m05appWebSB.servicios.dto.CategoriaDTO;
import mx.com.qtx.dipArq07m05appWebSB.servicios.dto.CategoriaProductoDTO;

public interface IGestorDatos {
	CategoriaDTO leerCategoriaXID(int id) throws Exception;
	List<CategoriaProductoDTO> leerProductosConCategorias(int id) throws Exception;
}
