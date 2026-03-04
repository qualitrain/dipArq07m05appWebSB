package mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.reposJpa;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.entidades.Categoria;

@Repository
public interface RepositorioCategorias extends JpaRepository<Categoria, Integer> {
    List<Categoria> findAllByOrderByNombreAsc();

    @Query("SELECT DISTINCT c FROM Categoria c JOIN c.productos p")
    List<Categoria> findAllWithProducts();
}
