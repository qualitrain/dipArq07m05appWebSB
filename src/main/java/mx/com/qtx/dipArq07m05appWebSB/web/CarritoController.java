package mx.com.qtx.dipArq07m05appWebSB.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mx.com.qtx.dipArq07m05appWebSB.corenegocio.IGestorCarrito;

@Controller
public class CarritoController {

    private final IGestorCarrito gestorCarrito;

    public CarritoController(IGestorCarrito gestorCarrito) {
        this.gestorCarrito = gestorCarrito;
    }

    @GetMapping("/carrito")
    public String verCarrito(Model model) {
        model.addAttribute("carrito", gestorCarrito.getCarrito());
        return "VerCarrito";
    }

    @PostMapping("/carrito/agregar")
    public String agregarProducto(@RequestParam String idProducto, @RequestParam(defaultValue = "1") int cantidad) {
        gestorCarrito.agregarProducto(idProducto, cantidad);
        return "redirect:/carrito";
    }

    @PostMapping("/carrito/eliminar/{id}")
    public String eliminarProducto(@PathVariable String id) {
        gestorCarrito.eliminarProducto(id);
        return "redirect:/carrito";
    }

    @PostMapping("/carrito/vaciar")
    public String vaciarCarrito() {
        gestorCarrito.vaciarCarrito();
        return "redirect:/carrito";
    }
}
