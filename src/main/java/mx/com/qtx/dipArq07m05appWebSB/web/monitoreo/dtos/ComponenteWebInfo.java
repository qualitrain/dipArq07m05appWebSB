package mx.com.qtx.dipArq07m05appWebSB.web.monitoreo.dtos;

public class ComponenteWebInfo {
    private String nombre;
    private String clase;
    private String mappings;
    private Integer orden;
    private String tipo; // "Servlet" o "Filtro"

    public ComponenteWebInfo(String nombre, String clase, String mappings, Integer orden, String tipo) {
        this.nombre = nombre;
        this.clase = clase;
        this.mappings = mappings;
        this.orden = orden;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getMappings() {
        return mappings;
    }

    public void setMappings(String mappings) {
        this.mappings = mappings;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
