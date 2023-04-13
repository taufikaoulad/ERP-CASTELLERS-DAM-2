package cat.copernic.CastellersERP.model;

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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "modulo")
public class Modulo implements Serializable {

    /**
     * Identificador único del módulo en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmodulo")
    private int idModulo;

    /**
     * Nombre del módulo.
     */
    @Column(name = "nombre")
    private String nombre;

    /**
     * Lista de relaciones entre módulos y tipos de usuarios.
     */
    @OneToMany(mappedBy = "modulo")
    private List<ModuloTipoUsuario> modulosTiposUsuario = new ArrayList<>();

}
