/**
 *
 * Clase que representa la relación entre un módulo y un tipo de usuario en el sistema.
 * Esta clase se utiliza para asignar permisos a los usuarios según el módulo al que tengan acceso.
 */
package cat.copernic.CastellersERP.model;

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
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "modulotipousuario")
public class ModuloTipoUsuario implements Serializable {

    /**
     * Identificador único de la relación módulo-tipo de usuario.
     */
    @Id //Indica al sistema que el atributo idmtu es la clave primaria de la BBDD
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Indica al sistema cómo generar el id, en nuestro caso autoincremental, por eso usamos IDENTITY
    int idmtu;

    /**
     * Indica si la relación está activa o no en el sistema.
     */
    boolean activo;

    /**
     * Módulo al que se le asigna el permiso.
     */
    @ManyToOne
    @JoinColumn(name = "modulo_idmodulo")
    private Modulo modulo;

    /**
     * Tipo de usuario al que se le asigna el permiso.
     */
    @ManyToOne
    @JoinColumn(name = "tipousuario_idtipousuario")
    private TipoUsuario rol;
}
