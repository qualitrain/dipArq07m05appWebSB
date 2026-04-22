package mx.com.qtx.dipArq07m05appWebSB.webSeguridad.servicios;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import mx.com.qtx.dipArq07m05appWebSB.webSeguridad.core.IResultadoOperacion;
import mx.com.qtx.dipArq07m05appWebSB.webSeguridad.core.IServicioAutenticacionJWT;
import mx.com.qtx.dipArq07m05appWebSB.webSeguridad.entidades.Autenticacion;
import mx.com.qtx.dipArq07m05appWebSB.webSeguridad.entidades.TokenJWT;

@Service
public class ServicioAutenticacionSS implements IServicioAutenticacionJWT {

    private static final Logger log = LoggerFactory.getLogger(ServicioAutenticacionSS.class);

    private final IGeneradorTokensJWT generadorTokensJWT;
    private final UserDetailsService userDetailService;
    private final AuthenticationManager authenticationManager;

    public ServicioAutenticacionSS(IGeneradorTokensJWT generadorTokensJWT, UserDetailsService userDetailService,
            AuthenticationManager authenticationManager) {
        this.generadorTokensJWT = generadorTokensJWT;
        this.userDetailService = userDetailService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public IResultadoOperacion registrarAutenticación(Autenticacion aut) {
        IResultadoOperacion resultadoAutenticacion = new ResultadoAutenticacion();
        try {
            Authentication autSS = new UsernamePasswordAuthenticationToken(aut.getNombreUsuario(), aut.getPassword());
            autSS = authenticationManager.authenticate(autSS);

            UserDetails usuario = userDetailService.loadUserByUsername(aut.getNombreUsuario());
            Map<String, Object> mapClaims = new HashMap<>();
            mapClaims.put("autoridades", usuario.getAuthorities());

            String token = generadorTokensJWT.generarToken(aut.getNombreUsuario(), mapClaims);

            resultadoAutenticacion.setObjResultadoOk(new TokenJWT(token));

        } catch (DisabledException dex) {
            // Cuenta deshabilitada
            resultadoAutenticacion.agregarError(ResultadoAutenticacion.ERR_USUARIO_INHABILITADO, dex.getMessage());
        } catch (LockedException lex) {
            // Cuenta bloqueda
            resultadoAutenticacion.agregarError(ResultadoAutenticacion.ERR_CTA_BLOQUEDA, lex.getMessage());
        } catch (BadCredentialsException bce) {
            // Credenciales equivocadas
            resultadoAutenticacion.agregarError(ResultadoAutenticacion.ERR_CREDENCIALES_EQUIVOCADAS, bce.getMessage());
        } catch (Throwable e) {
            resultadoAutenticacion.agregarError(ResultadoAutenticacion.ERR_GENERICO, e.getMessage());

            while (e.getCause() != null) {
                e = e.getCause();
                resultadoAutenticacion.agregarError(ResultadoAutenticacion.ERR_GENERICO, e.getClass().getName()
                        + ":" + e.getMessage());
            }

        }

        return resultadoAutenticacion;

    }

}
