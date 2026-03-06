package mx.com.qtx.dipArq07m05appWebSB.web;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FiltroMonitoreoWeb extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(FiltroMonitoreoWeb.class);

    public FiltroMonitoreoWeb() {
        logger.info("FiltroMonitoreoWeb constructor");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (!logger.isDebugEnabled()) {
            logger.info("No se esta monitoreando");
            filterChain.doFilter(request, response);
            return;
        }

        // T1: Inicio de la petición
        long t1 = System.nanoTime();

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request, 10240);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        try {
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } 
        finally {
            // T2: Fin de procesamiento de lógica de negocio (antes de cerrar respuesta)
            long t2 = System.nanoTime();

            byte[] requestBody = wrappedRequest.getContentAsByteArray();
            byte[] responseBody = wrappedResponse.getContentAsByteArray();

            // T3: Fin de procesamiento de respuesta (simulado al terminar de obtener el contenido)
            long t3 = System.nanoTime();

            logDetails(wrappedRequest, wrappedResponse, requestBody, responseBody, t1, t2, t3);
            
            wrappedResponse.copyBodyToResponse();
        }
    }

    private void logDetails(HttpServletRequest request, HttpServletResponse response, 
                            byte[] reqBody, byte[] respBody, 
                            long t1, long t2, long t3) {
        
        String path = request.getRequestURI();
        String method = request.getMethod();
        int status = response.getStatus();

        logger.debug("==========================================================================");
        logger.debug("MONITOREO WEB - Petición: {} {}", method, path);
        logger.debug("Cuerpo Petición: {}", new String(reqBody, StandardCharsets.UTF_8));
        logger.debug("--------------------------------------------------------------------------");
        logger.debug("Estado Respuesta: {}", status);
        logger.debug("Cuerpo Respuesta (Bytes): {} bytes", respBody.length);
        // Nota: No logueamos el cuerpo completo si es HTML muy grande para no saturar el log
        if (respBody.length < 2048) {
            logger.debug("Contenido Respuesta: {}", new String(respBody, StandardCharsets.UTF_8));
        }
        logger.debug("--------------------------------------------------------------------------");
        logger.debug("TIEMPOS DE PROCESAMIENTO:");
        logger.debug("T1 (Inicio): {} ns", t1);
        logger.debug("T2 (Fin Lógica): {} ns", t2);
        logger.debug("T3 (Fin Ciclo): {} ns", t3);
        logger.debug("a. Tiempo procesar peticion (T2-T1): {} ms", (t2 - t1) / 1_000_000.0);
        logger.debug("b. Tiempo procesar respuesta (T3-T2): {} ms", (t3 - t2) / 1_000_000.0);
        logger.debug("c. Tiempo ciclo completo (T3-T1): {} ms", (t3 - t1) / 1_000_000.0);
        logger.debug("==========================================================================");
    }
}
