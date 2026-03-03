package mx.com.qtx.dipArq07m03explorCapaPer.persistencia.jpa;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "prd_producto")
public class Producto {

    @Id
    @Column(name = "prd_id_producto", length = 50)
    private String id;

    @Column(name = "prd_nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "prd_descripcion", length = 255)
    private String descripcion;

    @Column(name = "prd_precio", nullable = false, precision = 12, scale = 2)
    private BigDecimal precio;

    @ManyToOne(optional = false)
    @JoinColumn(name = "prd_id_categoria", nullable = false)
    private Categoria categoria;

    @OneToOne(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private InventarioProducto inventario;

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio
				+ ", categoria=" + categoria.getId() + ", inventario=" + inventario + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
    
    
}
