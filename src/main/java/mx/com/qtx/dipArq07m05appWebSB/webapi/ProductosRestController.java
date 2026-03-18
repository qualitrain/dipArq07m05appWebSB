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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import mx.com.qtx.dipArq07m05appWebSB.corenegocio.IGestorProductos;
import mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.entidades.Producto;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductosRestController {

    private final IGestorProductos gestor;

    public ProductosRestController(IGestorProductos gestor) {
        this.gestor = gestor;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> getProductos(
            @RequestParam(name = "idCategoria", required = false) Integer idCategoria) {
        
        List<Producto> listado;
        if (idCategoria != null) {
            listado = gestor.getProductosXcat(idCategoria);
        } 
        else {
            listado = gestor.getProductos();
        }
        return new ResponseEntity<>(listado, HttpStatus.OK);
    }

    @GetMapping("/{clave}")
    public ResponseEntity<Producto> getProducto(@PathVariable String clave) {
        Producto producto = gestor.getProducto(clave);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody Producto producto) {
        Producto nuevo = gestor.insertarProducto(producto);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{clave}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable String clave, @Valid @RequestBody Producto producto) {
        producto.setId(clave);
        Producto actualizado = gestor.actualizarProducto(producto);
        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{clave}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable String clave) {
        gestor.eliminarProducto(clave);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
