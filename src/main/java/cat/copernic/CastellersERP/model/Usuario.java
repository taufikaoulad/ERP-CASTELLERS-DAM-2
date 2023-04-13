/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.hibernate.annotations.Check;

/**
 *
 * @author Taufik
 */

@Data // Genera automáticamente los métodos "getter", "setter" y otros métodos útiles.
@Entity // Indica que la clase es una entidad que se almacenará en la base de datos y se mapeará a una tabla de la base de datos.
@Table(name="usuario") // Indica que la tabla de la base de datos que se asociará con la entidad es "usuario".
public class Usuario implements Serializable {
    
    //Identificació de la classe per poder deserialitzar de manera correcta
    private static final long serialVersionUID = 1L;
    
    @Id // Indica que el campo "idusuario" es la clave primaria de la tabla.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que el valor del campo "idusuario" será generado automáticamente por la base de datos y que se utilizará un esquema de autoincremento.
    private int idusuario;

    @NotEmpty // Indica que el campo "username" no puede ser nulo ni vacío.
    @Size(min = 3) // Indica que el campo "username" debe tener al menos 3 caracteres.
    private String username;
 
    @NotNull // Indica que el campo "edat" no puede ser nulo.
    @Past // Indica que el campo "edat" debe ser una fecha anterior a la fecha actual.
    private Date edat;

    @NotEmpty // Indica que el campo "mail" no puede ser nulo ni vacío.
    @Email // Indica que el campo "mail" debe ser una dirección de correo electrónico válida.
    private String mail;
    
    @NotEmpty // Indica que el campo "password" no puede ser nulo ni vacío.
    private String password;

    @NotEmpty // Indica que el campo "telefono" no puede ser nulo ni vacío.
    @Pattern(regexp = "\\d{9}") // Indica que el campo "telefono" debe ser un número de teléfono de 9 dígitos.
    private String telefono;

    @DecimalMin(value = "20") // Indica que el campo "peso" debe tener un valor mínimo de 20.
    private float peso;

    @DecimalMin(value = "0.75") // Indica que el campo "altura" debe tener un valor mínimo de 0.75.
    private float altura;

    @NotNull // Indica que el campo "activo" no puede ser nulo.
    private boolean activo;
    
    @NotEmpty // Indica que el campo "posicio" no puede ser nulo ni vacío.
    @Size(min = 1) // Indica que el campo "posicio" debe tener al menos un caracter.
    private String posicio;
    
    @ManyToOne // Indica que la relación entre la entidad "Usuario" y la entidad "TipoUsuario" es de muchos a uno.
    @JoinColumn(name = "tipousuario_idtipousuario") // Indica que la clave externa en la tabla "usuario" que se utilizará para mapear la relación es "tipousuario_idtipousuario".
    private TipoUsuario tipousuario_idtipousuario;
    
    @ManyToMany(mappedBy = "castillosAsignados") // Indica que la relación entre la entidad "Usuario" y la entidad "Evento" es de muchos a muchos y que el campo "castillosAsignados" en la entidad "Evento" se utilizará para mapear la relación.
    private List<Evento> eventos = new ArrayList<>(); // Lista de eventos a los que está asignado el usuario.
}

    
