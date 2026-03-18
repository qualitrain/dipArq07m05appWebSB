package mx.com.qtx.dipArq07m05appWebSB.web.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import mx.com.qtx.dipArq07m05appWebSB.corenegocio.IGestorProductos;
import mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.entidades.Categoria;
import mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.entidades.Producto;
import mx.com.qtx.dipArq07m05appWebSB.servicios.ServiciosException;

@Controller
public class SeleccionProductosController {

    private static final Logger logger = LoggerFactory.getLogger(SeleccionProductosController.class);
    
    // Inyección por constructor
    private final IGestorProductos gestorProductos;

    public SeleccionProductosController(IGestorProductos gestorProductos) {
        this.gestorProductos = gestorProductos;
    }

    @GetMapping("/MenuCategorias.html")
    public String mostrarMenuInicio(Model model) {
        try {
            List<Categoria> categorias = gestorProductos.getCategorias();
            model.addAttribute("categorias", categorias);
            return "MenuCategorias";
        } catch (ServiciosException e) {
            logger.error("Error al cargar las categorías: {}", e.getMessage(), e);
            model.addAttribute("error", "No se pudieron cargar las categorías.");
            return "MenuCategorias";
        }
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
