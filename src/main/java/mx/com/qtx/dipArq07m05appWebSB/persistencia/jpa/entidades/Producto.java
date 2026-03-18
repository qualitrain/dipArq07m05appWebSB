package mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.entidades;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "prd_producto")
public class Producto {

    @NotBlank(message = "El ID del producto (clave) es requerido")
    @Size(max = 50, message = "El ID no puede exceder los 50 caracteres")
    @Id
    @Column(name = "prd_id_producto", length = 50)
    private String id;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Size(min = 3, max = 150, message = "El nombre debe tener entre 3 y 150 caracteres")
    @Column(name = "prd_nombre", nullable = false, length = 150)
    private String nombre;

    @Size(max = 255, message = "La descripción no debe sobrepasar 255 caracteres")
    @Column(name = "prd_descripcion", length = 255)
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a cero")
    @Column(name = "prd_precio", nullable = false, precision = 12, scale = 2)
    private BigDecimal precio;

    @JsonIgnore
    @NotNull(message = "Debe asociar este producto a una categoría")
    @ManyToOne(optional = false)
    @JoinColumn(name = "prd_id_categoria", nullable = false)
    private Categoria categoria;

    @OneToOne(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private InventarioProducto inventario;

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public InventarioProducto getInventario() {
        return inventario;
    }

    public void setInventario(InventarioProducto inventario) {
        this.inventario = inventario;
    }

    @Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio
				+ ", categoria=" + (categoria != null ? categoria.getId() : "null") + "]";
	}
    
    
}
