package mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.reposJpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.entidades.Producto;

@Repository
public interface RepositorioProductos extends JpaRepository<Producto, String> {

    List<Producto> findByCategoriaId(Integer id);

}
