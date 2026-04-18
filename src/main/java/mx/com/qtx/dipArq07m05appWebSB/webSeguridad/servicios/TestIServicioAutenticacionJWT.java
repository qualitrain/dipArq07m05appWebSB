package mx.com.qtx.dipArq07m05appWebSB.webSeguridad.servicios;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import mx.com.qtx.dipArq07m05appWebSB.webSeguridad.core.IResultadoOperacion;
import mx.com.qtx.dipArq07m05appWebSB.webSeguridad.core.IServicioAutenticacionJWT;
import mx.com.qtx.dipArq07m05appWebSB.webSeguridad.entidades.Autenticacion;

@Component
public class TestIServicioAutenticacionJWT {

    private static final Logger log = LoggerFactory.getLogger(TestIServicioAutenticacionJWT.class);

    @Autowired
    private IServicioAutenticacionJWT servicioAutenticacionJWT;

    @PostConstruct
    public void correrTests() {
        testUsuarioInexistente();
        testUsuarioExistente();
    }

    public void testUsuarioInexistente() {
        log.info("Test Usuario Inexistente");
        Autenticacion aut = new Autenticacion();
        aut.setNombreUsuario("admin");
        aut.setPassword("admin");
        IResultadoOperacion resultado = servicioAutenticacionJWT.registrarAutenticación(aut);
        if (resultado.todoOk()) {
            log.info("Token: " + resultado.getObjResultadoOk());
        } else {
            log.info("Error: " + resultado.getResumenErrores());
        }
    }

    public void testUsuarioExistente() {
        log.info("Test Usuario Existente");
        Autenticacion aut = new Autenticacion();
        aut.setNombreUsuario("alex");
        aut.setPassword("tekamachalko");
        IResultadoOperacion resultado = servicioAutenticacionJWT.registrarAutenticación(aut);
        if (resultado.todoOk()) {
            log.info("Token: " + resultado.getObjResultadoOk());
        } else {
            log.info("Error: " + resultado.getResumenErrores());
        }
    }

}
