package mx.com.qtx.dipArq07m05appWebSB.webapi;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import mx.com.qtx.dipArq07m05appWebSB.corenegocio.IGestorProductos;
import mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.entidades.Categoria;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriasRestController {

    private final IGestorProductos gestor;

    public CategoriasRestController(IGestorProductos gestor) {
        this.gestor = gestor;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> getCategorias() {
        List<Categoria> listado = gestor.getCategorias();
        return new ResponseEntity<>(listado, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoria(@PathVariable int id) {
        Categoria cat = gestor.getCategoria(id);
        return new ResponseEntity<>(cat, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(@Valid @RequestBody Categoria categoria) {
        Categoria nueva = gestor.insertarCategoria(categoria);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable int id, @Valid @RequestBody Categoria categoria) {
        categoria.setId(id);
        Categoria actualizada = gestor.actualizarCategoria(categoria);
        return new ResponseEntity<>(actualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable int id) {
        gestor.eliminarCategoria(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
