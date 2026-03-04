package mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "dtp_detalle_pedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dtp_num_detalle")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "dtp_id_pedido", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "dtp_id_producto", nullable = false)
    private Producto producto;

    @Column(name = "dtp_cantidad", nullable = false)
    private Integer cantidad;
}
