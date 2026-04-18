package mx.com.qtx.dipArq07m05appWebSB.webSeguridad.core;

import mx.com.qtx.dipArq07m05appWebSB.webSeguridad.entidades.Autenticacion;

public interface IServicioAutenticacionJWT {
    IResultadoOperacion registrarAutenticación(Autenticacion aut);
}
