package mx.com.qtx.dipArq07m05appWebSB.webSeguridad.servicios;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import mx.com.qtx.dipArq07m05appWebSB.webSeguridad.core.IExtractorTokenJwtPeticionHttp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ExtractorTokenJwtPeticiones implements IExtractorTokenJwtPeticionHttp {

    private static final Logger log = LoggerFactory.getLogger(ExtractorTokenJwtPeticiones.class);

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    private final IGeneradorTokensJWT generadorTokensJWT;

    public ExtractorTokenJwtPeticiones(IGeneradorTokensJWT generadorTokensJWT) {
        this.generadorTokensJWT = generadorTokensJWT;
    }

    @Override
    public boolean peticionTieneTokenValido(HttpServletRequest request) {
        String token = request.getHeader(HEADER_AUTHORIZATION);
        if (token == null || !token.startsWith(BEARER_PREFIX)) {
            return false;
        }
        token = token.substring(BEARER_PREFIX.length());
        return !token.isEmpty();
    }

    @Override
    public String getNombreUsuario(HttpServletRequest request) {
        if (!peticionTieneTokenValido(request)) {
            return null;
        }
        String token = request.getHeader(HEADER_AUTHORIZATION);
        token = token.substring(BEARER_PREFIX.length());
        return this.generadorTokensJWT.extraerUsuario(token);
    }

}
