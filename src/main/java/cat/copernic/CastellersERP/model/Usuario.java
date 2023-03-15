/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Taufik
 */

@Data
@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
    
    //Identificació de la classe per poder deserialitzar de manera correcta
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AutoIncrement
    private int idusuario;

    @NotEmpty
    @Size(min = 3)
    private String nombre;
 
    @NotNull
    @Past
    private Date edat;

    @NotEmpty
    @Email
    private String mail;
    
    @NotEmpty
    private String contrasena;

    @NotEmpty
    @Pattern(regexp = "\\d{9}")
    private String telefono;

    @DecimalMin(value = "20")
    private float peso;

    @DecimalMin(value = "0.75")
    private float altura;

    @NotNull //No se ha crado un campo para este boolean
    private boolean activo;
    
    @NotEmpty
    @Size(min = 1)
    private String posicio;
    
    
    @NotNull
    @Min(value = 1)
    @Max(value = 3)
    private int tipousuario_idtipousuario;
    
    
    
    
    
    @OneToMany
    @JoinColumn(name ="idusuario")
    private List<TipoUsuario> tipoUsuario;
}

