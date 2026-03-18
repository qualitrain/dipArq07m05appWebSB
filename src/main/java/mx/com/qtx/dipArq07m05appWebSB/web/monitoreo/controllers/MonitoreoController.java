package mx.com.qtx.dipArq07m05appWebSB.web.monitoreo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mx.com.qtx.dipArq07m05appWebSB.web.monitoreo.ServicioMonitoreo;

@Controller
@RequestMapping("/monitoreo")
public class MonitoreoController {

    private final ServicioMonitoreo servicioMonitoreo;

    public MonitoreoController(ServicioMonitoreo servicioMonitoreo) {
        this.servicioMonitoreo = servicioMonitoreo;
    }

    @GetMapping("/peticiones")
    public String verPeticiones(Model modelo) {
        modelo.addAttribute("peticiones", servicioMonitoreo.getUltimasPeticiones());
        return "monitoreo/peticiones";
    }

    @GetMapping("/componentes")
    public String verComponentes(Model modelo) {
        modelo.addAttribute("servlets", servicioMonitoreo.getServlets());
        modelo.addAttribute("filtros", servicioMonitoreo.getFiltros());
        return "monitoreo/componentes";
    }
}
