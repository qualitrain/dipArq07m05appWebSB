package mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.reposJpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.entidades.Categoria;

@Repository
public interface RepositorioCategorias extends JpaRepository<Categoria, Integer> {
}
