package mx.com.qtx.dipArq07m05appWebSB.webSeguridad.webapi;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.qtx.dipArq07m05appWebSB.webSeguridad.core.IResultadoOperacion;
import mx.com.qtx.dipArq07m05appWebSB.webSeguridad.core.IServicioAutenticacionJWT;
import mx.com.qtx.dipArq07m05appWebSB.webSeguridad.entidades.Autenticacion;

@RestController
public class AutenticacionController {

    private final IServicioAutenticacionJWT servicioAutenticacionJWT;

    public AutenticacionController(IServicioAutenticacionJWT servicioAutenticacionJWT) {
        this.servicioAutenticacionJWT = servicioAutenticacionJWT;
    }

    @PostMapping("/autenticar")
    public IResultadoOperacion autenticar(@RequestBody Autenticacion aut) {
        return servicioAutenticacionJWT.registrarAutenticación(aut);
    }

}
