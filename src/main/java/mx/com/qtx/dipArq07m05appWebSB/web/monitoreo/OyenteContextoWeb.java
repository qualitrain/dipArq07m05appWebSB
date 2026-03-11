package mx.com.qtx.dipArq07m05appWebSB.web.monitoreo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import mx.com.qtx.dipArq07m05appWebSB.web.util.Util;

@Component
public class OyenteContextoWeb implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(OyenteContextoWeb.class);
    private static final boolean REPORTAR_TODOS_LOS_BEANS = true;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!logger.isDebugEnabled()) {
            return;
        }

        ApplicationContext context = event.getApplicationContext();
        ConfigurableListableBeanFactory factory =
                        (ConfigurableListableBeanFactory) context.getAutowireCapableBeanFactory();

                        
        logger.debug("==========================================================================");
        logger.debug("MONITOREO DE COMPONENTES CARGADOS EN EL CONTEXTO:");
                        
        String[] allBeanNames = context.getBeanDefinitionNames();
        Arrays.sort(allBeanNames);
                        
        Map<String,List<String>> mapClaseVsBeans = new TreeMap<>();
        for (String nombreBeanI : allBeanNames) {
            Class<?> beanClass = obtenerClase(nombreBeanI, factory);
            String nomClaseBean = obtenerNombreClase(nombreBeanI, factory);
            
            if (esDeInteres(beanClass)) {
                logger.debug(String.format("Bean: %30s -> Clase: %s", nombreBeanI, nomClaseBean));
                logger.debug(String.format(" ".repeat(40) + "Scope: %s", obtenerScope(nombreBeanI, factory)));

                List<String> beansXclase = mapClaseVsBeans.getOrDefault(nomClaseBean,new ArrayList<>());
                beansXclase.add(Util.recortarNombreClase(nombreBeanI));
                mapClaseVsBeans.put(Util.recortarNombreClase(nombreBeanI),beansXclase);
            }
        } 
        logger.debug("Beans de cada tipo:" + "-".repeat(50));
        mapClaseVsBeans.forEach((claseI,lstBeans)->logger.debug(claseI + ": " + lstBeans.toString()));
        logger.debug("==========================================================================");
    }

    private String obtenerNombreClase(String nombreBean, BeanFactory factory) {
        if (nombreBean == null || factory == null) {
            logger.error("Parametros incorrectos al obtener el nombre de la clase del bean");
            return null;
        }
        Class<?> beanClass = this.obtenerClase(nombreBean, factory);
        if (beanClass == null) {
            return "No disponible";
        }
        return beanClass.getName();
    }

    private Class<?> obtenerClase(String nombreBean, BeanFactory factory) {
        if (nombreBean == null || factory == null) {
            logger.error("Parametros incorrectos al obtener el nombre de la clase del bean");
            return null;
        }
        try {
            Object bean = factory.getBean(nombreBean);
            return bean.getClass();
        } 
        catch (org.springframework.beans.factory.support.ScopeNotActiveException e) {
            // No se puede obtener la clase del bean porque anida otro(s) bean(s) con scope no activo
            return null;
        }
        catch (Exception e) {
            logger.error("Error al obtener la clase del bean: {}, ex: {} msg: {}", nombreBean, e.getClass().getName(), e.getMessage());
            return null;
        }
    }

    private boolean esDeInteres(Class<?> clazz) {
        if (REPORTAR_TODOS_LOS_BEANS) {
            return true;
        }
        if (clazz != null) {
            return isWebComponent(clazz) || 
                isRepository(clazz) ||
                esDelGrupoMaven(clazz);
        }
        return true;
    }

    private boolean esDelGrupoMaven(Class<?> clazz) {
        return clazz.getName().startsWith("mx.com.qtx.dipArq07m05appWebSB");
    }   

    private boolean isWebComponent(Class<?> clazz) {
        return clazz.isAnnotationPresent(Controller.class) ||
               clazz.isAnnotationPresent(RestController.class) ||
               clazz.isAnnotationPresent(Service.class) ||
               clazz.isAnnotationPresent(Component.class);
    }

    private boolean isRepository(Class<?> clazz) {
        return clazz.isAnnotationPresent(Repository.class);
    }

    private String obtenerScope(String beanName, ConfigurableListableBeanFactory factory) {

        // Obtenemos la definición del bean (metadata del contenedor)
        BeanDefinition definition = factory.getBeanDefinition(beanName);

        // El scope declarado en la definicion
        String scope = definition.getScope();

        // En Spring el scope por defecto es singleton
        if (scope == null || scope.isEmpty()) {
            scope = "singleton";
        }

        return scope;
    }
}
