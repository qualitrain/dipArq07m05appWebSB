package mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.entidades;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "dtv_detalle_venta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dtv_num_detalle")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "dtv_num_venta", nullable = false)
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "dtv_id_producto", nullable = false)
    private Producto producto;

    @Column(name = "dtv_cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "dtv_precio_unitario", nullable = false)
    private BigDecimal precioUnitario;

    public BigDecimal calcularImporte() {
        return precioUnitario.multiply(BigDecimal.valueOf(cantidad));
    }
}
