package mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ven_venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ven_num_venta")
    private Long id;

    @Column(name = "ven_fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "ven_direccion_entrega", length = 255)
    private String direccionEntrega;

    @Column(name = "ven_telefono", length = 20)
    private String telefono;

    @Column(name = "ven_saldo", nullable = false, precision = 12, scale = 2)
    private BigDecimal saldo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ven_id_empleado_vendedor", nullable = false)
    private Empleado vendedor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ven_id_cliente", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> detalles = new ArrayList<>();
}
