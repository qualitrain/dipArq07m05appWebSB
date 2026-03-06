package mx.com.qtx.dipArq07m05appWebSB.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import mx.com.qtx.dipArq07m05appWebSB.web.util.Util;

@Component
public class OyenteServletsFiltros implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(OyenteServletsFiltros.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!logger.isDebugEnabled()) {
            return;
        }

        ApplicationContext context = event.getApplicationContext();
        
        // Solo procedemos si es un contexto web
        if (!(context instanceof WebApplicationContext)) {
            return;
        }

        WebApplicationContext webContext = (WebApplicationContext) context;
        ServletContext servletContext = webContext.getServletContext();

        if (servletContext == null) {
            logger.debug("ServletContext no disponible todavia.");
            return;
        }

        logger.debug("==========================================================================");
        logger.debug("MONITOREO DE COMPONENTES WEB (SERVLETS Y FILTROS):");

        reportarServlets(servletContext);
        reportarFiltros(servletContext, webContext);

        logger.debug("==========================================================================");
    }

    private void reportarServlets(ServletContext servletContext) {
        logger.debug("--------------------------------------------------------------------------");
        logger.debug("SERVLETS REGISTRADOS:");
        Map<String, ? extends ServletRegistration> servlets = servletContext.getServletRegistrations();
        
        servlets.forEach((nombre, registro) -> {
            logger.debug(String.format("Servlet: %-25s -> Clase: %s", nombre, registro.getClassName()));
            logger.debug(String.format("   Mappings: %s", registro.getMappings()));
            Map<String, String> initParams = registro.getInitParameters();
            if(!initParams.isEmpty()){
                logger.debug("   Parametros init: " + initParams);
            }
        });
    }

    private void reportarFiltros(ServletContext servletContext, WebApplicationContext context) {
        logger.debug("--------------------------------------------------------------------------");
        logger.debug("FILTROS REGISTRADOS Y SU ORDEN (VIA SPRING):");

        Map<String, jakarta.servlet.Filter> filterBeans = context.getBeansOfType(jakarta.servlet.Filter.class);
        logger.debug("Se han encontrado " + filterBeans.size() + " beans de tipo Filter registrados en Spring.");

        List<Map.Entry<String, jakarta.servlet.Filter>> filterList = new ArrayList<>(filterBeans.entrySet());
        AnnotationAwareOrderComparator.sort(filterList);

        Map<String, ? extends FilterRegistration> registrations = servletContext.getFilterRegistrations();

        if (!filterList.isEmpty()) {
            for (int i = 0; i < filterList.size(); i++) {
                Map.Entry<String, jakarta.servlet.Filter> entry = filterList.get(i);
                String beanName = entry.getKey();
                jakarta.servlet.Filter filter = entry.getValue();
                String className = Util.recortarNombreClase(filter.getClass().getName());
                
                FilterRegistration reg = registrations.get(beanName);
                String mappings = (reg != null) ? reg.getUrlPatternMappings().toString() : "N/A";

                logger.debug(String.format("Posición: %2d | Bean: %-25s -> Clase: %s", (i + 1), beanName, className));
                logger.debug(String.format("            Mappings: %s", mappings));
            }
        }

        logger.debug("--- Registros directos en ServletContext (Inconsistencias o Externos) ---");
        registrations.forEach((name, reg) -> {
            boolean esBean = filterBeans.containsKey(name);
            if(!esBean){
               logger.debug(String.format("Filtro SC: %-25s -> Clase: %s", name, Util.recortarNombreClase(reg.getClassName())));
               logger.debug(String.format("            Mappings: %s", reg.getUrlPatternMappings()));
            }
        });
    }
}
