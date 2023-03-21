/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
    
    @ManyToOne
    @JoinColumn(name = "tipousuario_idtipousuario")
    private TipoUsuario tipousuario_idtipousuario;
    
    //@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "usuariosAsignados")
    //@JoinColumn(name = "usuario_idusuario")
    //@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "evento_idevento")
    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "castillosAsignados")
    private List<Evento> eventos = new ArrayList<>();
}

    
