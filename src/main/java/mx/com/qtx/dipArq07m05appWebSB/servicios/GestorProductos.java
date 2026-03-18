package mx.com.qtx.dipArq07m05appWebSB.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.qtx.dipArq07m05appWebSB.corenegocio.IGestorProductos;
import mx.com.qtx.dipArq07m05appWebSB.persistencia.PersistenciaException;
import mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.entidades.Categoria;
import mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.entidades.Producto;
import mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.reposJpa.RepositorioCategorias;
import mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.reposJpa.RepositorioProductos;

@Service
public class GestorProductos implements IGestorProductos {

    private final RepositorioProductos repoProductos;
    private final RepositorioCategorias repoCategorias;

    public GestorProductos(RepositorioProductos repoProductos, RepositorioCategorias repoCategorias) {
        this.repoProductos = repoProductos;
        this.repoCategorias = repoCategorias;
    }

    @Override
    public List<Producto> getProductosXcat(int idCategoria) {
        try {
            return repoProductos.findByCategoriaId(idCategoria);
        } 
        catch (Exception e) {
            PersistenciaException persistenciaEx = new PersistenciaException("Error al acceder a los datos de productos.", e);
            throw new ServiciosException("Fallo en el servicio de selección de productos por categoría.", persistenciaEx);
        }
    }

    @Override
    public List<Producto> getProductos() {
        try {
            return repoProductos.findAll();
        } 
        catch (Exception e) {
            PersistenciaException persistenciaEx = new PersistenciaException("Error al acceder a los datos de productos.", e);
            throw new ServiciosException("Fallo en el servicio de selección de todos los productos.", persistenciaEx);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> getCategorias() {
        try {
            return repoCategorias.findAllWithProducts();
        } 
        catch (Exception e) {
            PersistenciaException persistenciaEx = new PersistenciaException("Error al acceder a los datos de categorías.", e);
            throw new ServiciosException("Fallo en el servicio de obtención de categorías.", persistenciaEx);
        }
    }

    // ============================================
    // CRUD Categoría
    // ============================================

    @Override
    public Categoria getCategoria(int id) {
        try {
            return repoCategorias.findById(id).orElseThrow(() -> new ServiciosException("La categoría con ID " + id + " no existe."));
        } 
        catch (ServiciosException e) {
            throw e;
        } 
        catch (Exception e) {
            throw new ServiciosException("Fallo al consultar la categoría.", new PersistenciaException(e.getMessage(), e));
        }
    }

    @Override
    public Categoria insertarCategoria(Categoria categoria) {
        try {
            return repoCategorias.save(categoria);
        } 
        catch (Exception e) {
            throw new ServiciosException("Fallo al insertar la categoría.", new PersistenciaException(e.getMessage(), e));
        }
    }

    @Override
    public Categoria actualizarCategoria(Categoria categoria) {
        try {
            if(!repoCategorias.existsById(categoria.getId())) {
                throw new ServiciosException("No se puede actualizar porque no existe la categoría.");
            }
            return repoCategorias.save(categoria);
        } 
        catch (ServiciosException e) {
            throw e;
        } 
        catch (Exception e) {
            throw new ServiciosException("Fallo al actualizar la categoría.", new PersistenciaException(e.getMessage(), e));
        }
    }

    @Override
    public void eliminarCategoria(int id) {
        try {
            if(!repoCategorias.existsById(id)) {
                throw new ServiciosException("No se puede eliminar la categoría con id " + id + " porque no existe.");
            }
            repoCategorias.deleteById(id);
        } 
        catch (ServiciosException e) {
            throw e;
        } 
        catch (Exception e) {
            throw new ServiciosException("Fallo al eliminar la categoría.", new PersistenciaException(e.getMessage(), e));
        }
    }

    // ============================================
    // CRUD Producto
    // ============================================

    @Override
    public Producto getProducto(String clave) {
        try {
            return repoProductos.findById(clave).orElseThrow(() -> new ServiciosException("El producto con ID " + clave + " no existe."));
        } 
        catch (ServiciosException e) {
            throw e;
        } 
        catch (Exception e) {
            throw new ServiciosException("Fallo al consultar el producto.", new PersistenciaException(e.getMessage(), e));
        }
    }

    @Override
    public Producto insertarProducto(Producto producto) {
        try {
            if(repoProductos.existsById(producto.getId())) {
                throw new ServiciosException("El producto con la clave enviada ya existe.");
            }
            return repoProductos.save(producto);
        } 
        catch (ServiciosException e) {
            throw e;
        } 
        catch (Exception e) {
            throw new ServiciosException("Fallo al insertar el producto.", new PersistenciaException(e.getMessage(), e));
        }
    }

    @Override
    public Producto actualizarProducto(Producto producto) {
        try {
            if(!repoProductos.existsById(producto.getId())) {
                throw new ServiciosException("No se puede actualizar el producto porque no existe en la base de datos.");
            }
            return repoProductos.save(producto);
        } 
        catch (ServiciosException e) {
            throw e;
        } 
        catch (Exception e) {
            throw new ServiciosException("Fallo al actualizar el producto.", new PersistenciaException(e.getMessage(), e));
        }
    }

    @Override
    public void eliminarProducto(String clave) {
        try {
            if(!repoProductos.existsById(clave)) {
                throw new ServiciosException("No se encontraron registros del producto a eliminar.");
            }
            repoProductos.deleteById(clave);
        } 
        catch (ServiciosException e) {
            throw e;
        } 
        catch (Exception e) {
            throw new ServiciosException("Fallo al eliminar el producto.", new PersistenciaException(e.getMessage(), e));
        }
    }

}
