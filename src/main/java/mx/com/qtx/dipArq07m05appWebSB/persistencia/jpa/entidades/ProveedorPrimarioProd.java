package mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "ppp_proveedor_primario_prod")
public class ProveedorPrimarioProd {

    @EmbeddedId
    private ProveedorPrimarioProdId id;

    @ManyToOne
    @MapsId("idProducto")
    @JoinColumn(name = "ppp_id_producto")
    private Producto producto;

    @ManyToOne
    @MapsId("idProveedor")
    @JoinColumn(name = "ppp_id_proveedor")
    private Proveedor proveedor;

    @Column(name = "ppp_prioridad", nullable = false)
    private Integer prioridad;

    @Column(name = "ppp_condiciones")
    private String condiciones;
}
