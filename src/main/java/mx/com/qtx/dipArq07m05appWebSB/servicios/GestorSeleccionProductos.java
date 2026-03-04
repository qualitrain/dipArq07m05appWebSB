package mx.com.qtx.dipArq07m05appWebSB.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.qtx.dipArq07m05appWebSB.persistencia.PersistenciaException;
import mx.com.qtx.dipArq07m05appWebSB.persistencia.RepositorioProductos;
import mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.Producto;

@Service
public class GestorSeleccionProductos implements IGestorSeleccionProductos {

    private final RepositorioProductos repoProductos;

    public GestorSeleccionProductos(RepositorioProductos repoProductos) {
        this.repoProductos = repoProductos;
    }

    @Override
    public List<Producto> getProductosXcat(int idCategoria) {
        try {
            return repoProductos.findByCategoriaId(idCategoria);
        } catch (Exception e) {
            PersistenciaException persistenciaEx = new PersistenciaException("Error al acceder a los datos de productos.", e);
            throw new ServiciosException("Fallo en el servicio de selección de productos por categoría.", persistenciaEx);
        }
    }

}
