package mx.com.qtx.dipArq07m05appWebSB.web.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class Util {

/*    public static final Map<String,String> mapRemplazos = Map.of(
                "org.springframework.boot.autoconfigure","o.s.b.auto",
                "org.springframework.boot.context","o.s.b.ctx",
                "org.springframework.boot.data","o.s.b.d",
                "org.springframework.boot.hibernate","o.s.b.hib",
                "org.springframework.boot.jackson.autoconfigure","o.s.b.j.auto",
                "org.springframework.boot.servlet.autoconfigure","o.s.b.srvlt.auto",
                "org.springframework.boot.servlet","o.s.b.srvlt",
                "org.springframework.boot.transaction.autoconfigure","o.s.b.trnsac.auto",
                "org.springframework.boot","o.s.b",
                "org.springframework.context","o.s.ctx",
                "org.springframework.data","o.s.d",
                "org.springframework.web.servlet","o.s.w.srvlt",
                "org.springframework","o.s"
    );*/
    public static final Map<String, String> mapRemplazos = createMap();

    private static Map<String, String> createMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("org.springframework.boot.autoconfigure","o.s.b.auto");
        map.put("org.springframework.boot.context","o.s.b.ctx");
        map.put("org.springframework.boot.data","o.s.b.d");
        map.put("org.springframework.boot.hibernate","o.s.b.hib");
        map.put("org.springframework.boot.jackson.autoconfigure","o.s.b.j.auto");
        map.put("org.springframework.boot.servlet.autoconfigure","o.s.b.srvlt.auto");
        map.put("org.springframework.boot.servlet","o.s.b.srvlt");
        map.put("org.springframework.boot.transaction.autoconfigure","o.s.b.trnsac.auto");
        map.put("org.springframework.boot","o.s.b");
        map.put("org.springframework.security.web","o.s.s.w");
        map.put("org.springframework.security","o.s.s");
        map.put("org.springframework.context","o.s.ctx");
        map.put("org.springframework.data","o.s.d");
        map.put("org.springframework.web.servlet","o.s.w.srvlt");
        map.put("org.springframework.web.filter","o.s.w.f");
        map.put("org.springframework.web","o.s.w");
        map.put("org.springframework","o.s");
        map.put("org.apache.catalina.filters","o.a.c.f");
        map.put("org.apache.catalina","o.a.c");
        map.put("org.hibernate.validator","o.h.v");
        map.put("org.hibernate","o.h");
        return map;
    }
    public static String recortarNombreClase(String nombreCalificado){
        for(String cadBuscadaI : mapRemplazos.keySet()){
            String remplazoI = mapRemplazos.get(cadBuscadaI);
            
            nombreCalificado = nombreCalificado.replace(cadBuscadaI, remplazoI);
        }
        return nombreCalificado;
    }
}
