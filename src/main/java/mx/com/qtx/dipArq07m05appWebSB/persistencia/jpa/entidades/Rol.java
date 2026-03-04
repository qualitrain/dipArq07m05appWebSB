package mx.com.qtx.dipArq07m05appWebSB.persistencia.jpa.entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "rol_rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id_rol")
    private Integer id;

    @Column(name = "rol_nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "rol_descripcion", length = 255)
    private String descripcion;

    @OneToMany(mappedBy = "rol")
    private List<Empleado> empleados = new ArrayList<>();
}
