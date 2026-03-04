package mx.com.qtx.dipArq07m05appWebSB.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.Producto;
import mx.com.qtx.dipArq07m05appWebSB.servicios.IGestorSeleccionProductos;
import mx.com.qtx.dipArq07m05appWebSB.servicios.ServiciosException;

@Controller
public class SeleccionProductosController {

    private static final Logger logger = LoggerFactory.getLogger(SeleccionProductosController.class);
    
    // Inyección por constructor
    private final IGestorSeleccionProductos gestorProductos;

    public SeleccionProductosController(IGestorSeleccionProductos gestorProductos) {
        this.gestorProductos = gestorProductos;
    }

    @GetMapping("/categoria/{id}/productos")
    public String obtenerProdsXCategoria(@PathVariable int id, Model model) {
        try {
            List<Producto> lstProds = gestorProductos.getProductosXcat(id);
            model.addAttribute("productos", lstProds);
            return "IUselecProds"; // Devuelve la vista Thymeleaf
        } catch (ServiciosException e) {
            logger.error("Error al obtener productos para la categoría {}: {}", id, e.getMessage(), e);
            model.addAttribute("error", "Ha ocurrido un error al cargar los productos. Por favor intentar de nuevo.");
            return "MenuCategorias"; // Redirigimos al menú si hay error
        }
    }
}
