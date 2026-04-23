package mx.com.qtx.dipArq07m05appWebSB.webSeguridad.core;

import jakarta.servlet.http.HttpServletRequest;

public interface IExtractorTokenJwtPeticionHttp {
    boolean peticionTieneTokenValido(HttpServletRequest request);

    String getNombreUsuario(HttpServletRequest request);
}
