package mx.com.qtx.dipArq07m05appWebSB.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.qtx.dipArq07m05appWebSB.corenegocio.IGestorSeleccionProductos;
import mx.com.qtx.dipArq07m05appWebSB.persistencia.PersistenciaException;
import mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.entidades.Categoria;
import mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.entidades.Producto;
import mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.reposJpa.RepositorioCategorias;
import mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.reposJpa.RepositorioProductos;

@Service
public class GestorSeleccionProductos implements IGestorSeleccionProductos {

    private final RepositorioProductos repoProductos;
    private final RepositorioCategorias repoCategorias;

    public GestorSeleccionProductos(RepositorioProductos repoProductos, RepositorioCategorias repoCategorias) {
        this.repoProductos = repoProductos;
        this.repoCategorias = repoCategorias;
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

    @Override
    public List<Categoria> getCategorias() {
        try {
            return repoCategorias.findAll();
        } catch (Exception e) {
            PersistenciaException persistenciaEx = new PersistenciaException("Error al acceder a los datos de categorías.", e);
            throw new ServiciosException("Fallo en el servicio de obtención de categorías.", persistenciaEx);
        }
    }

}
