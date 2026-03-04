package mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inv_inventario_producto")
public class InventarioProducto {

    @Id
    @Column(name = "inv_id_producto", length = 50)
    private String id;

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "inv_id_producto", nullable = false)
    private Producto producto;

    @Column(name = "inv_existencia", nullable = false)
    private Integer existencia;

    @Column(name = "inv_existencia_minima", nullable = false)
    private Integer existenciaMinima;

    @Column(name = "inv_existencia_maxima", nullable = false)
    private Integer existenciaMaxima;

    @Column(name = "inv_punto_reorden", nullable = false)
    private Integer puntoReorden;

    @Column(name = "inv_fecha_actualizacion", nullable = false)
    private LocalDate fechaActualizacion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getExistencia() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }

    public Integer getExistenciaMinima() {
        return existenciaMinima;
    }

    public void setExistenciaMinima(Integer existenciaMinima) {
        this.existenciaMinima = existenciaMinima;
    }

    public Integer getExistenciaMaxima() {
        return existenciaMaxima;
    }

    public void setExistenciaMaxima(Integer existenciaMaxima) {
        this.existenciaMaxima = existenciaMaxima;
    }

    public Integer getPuntoReorden() {
        return puntoReorden;
    }

    public void setPuntoReorden(Integer puntoReorden) {
        this.puntoReorden = puntoReorden;
    }

    public LocalDate getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDate fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @Override
    public String toString() {
        return "InventarioProducto [id=" + id + ", existencia=" + existencia + ", existenciaMinima=" + existenciaMinima
                + ", existenciaMaxima=" + existenciaMaxima + ", puntoReorden=" + puntoReorden
                + ", fechaActualizacion=" + fechaActualizacion + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode() + "]";
    }
}
