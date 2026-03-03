package mx.com.qtx.dipArq07m03explorCapaPer.servicios.dto;

import java.math.BigDecimal;

public record CategoriaProductoDTO(int catIdCategoria, String catNombre, String catDescripcion, 
						String prdNombre, String prdDescripcion, BigDecimal prdPrecio) {

}
