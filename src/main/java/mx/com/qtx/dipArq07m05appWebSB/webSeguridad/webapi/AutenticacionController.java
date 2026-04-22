package mx.com.qtx.dipArq07m05appWebSB.webSeguridad.webapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.com.qtx.dipArq07m05appWebSB.webSeguridad.core.IResultadoOperacion;
import mx.com.qtx.dipArq07m05appWebSB.webSeguridad.core.IServicioAutenticacionJWT;
import mx.com.qtx.dipArq07m05appWebSB.webSeguridad.entidades.Autenticacion;
import mx.com.qtx.dipArq07m05appWebSB.webSeguridad.entidades.TokenJWT;

@RestController
public class AutenticacionController {

    private static final Logger log = LoggerFactory.getLogger(AutenticacionController.class);
    private final IServicioAutenticacionJWT servicioAutenticacionJWT;

    public AutenticacionController(IServicioAutenticacionJWT servicioAutenticacionJWT) {
        this.servicioAutenticacionJWT = servicioAutenticacionJWT;
    }

    @PostMapping(value = "/autenticar", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> autenticar(@RequestBody Autenticacion aut) {
        log.info("AutenticacionController.autenticar({})", aut.toString());
        IResultadoOperacion resultado = servicioAutenticacionJWT.registrarAutenticación(aut);
        if (resultado.todoOk()) {
            log.info("TokenJWT:{} {}", resultado.getClass().getName(), resultado.getObjResultadoOk());
            return ResponseEntity.ok((TokenJWT) resultado.getObjResultadoOk());
        } else {
            log.error("Error al autenticar: {}", resultado.getResumenErrores());
            return ErrorRest.getError(resultado.getResumenErrores(), ErrorRest.ERR_AUTENTICACION_FALLIDA,
                    HttpStatus.UNAUTHORIZED);
        }
    }

}
