package mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.entidades;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProveedorPrimarioProdId implements Serializable {

    @Column(name = "ppp_id_producto")
    private String idProducto;

    @Column(name = "ppp_id_proveedor")
    private Long idProveedor;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idProducto == null) ? 0 : idProducto.hashCode());
        result = prime * result + ((idProveedor == null) ? 0 : idProveedor.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProveedorPrimarioProdId other = (ProveedorPrimarioProdId) obj;
        if (idProducto == null) {
            if (other.idProducto != null)
                return false;
        } else if (!idProducto.equals(other.idProducto))
            return false;
        if (idProveedor == null) {
            if (other.idProveedor != null)
                return false;
        } else if (!idProveedor.equals(other.idProveedor))
            return false;
        return true;
    }

}
