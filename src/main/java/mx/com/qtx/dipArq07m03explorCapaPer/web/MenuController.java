package mx.com.qtx.dipArq07m03explorCapaPer.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @GetMapping("/MenuCategorias.html")
    public String mostrarMenuInicio() {
        return "MenuCategorias"; // Redirige a src/main/resources/templates/MenuCategorias.html
    }
}
