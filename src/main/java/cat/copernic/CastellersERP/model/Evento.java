/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author capy
 */
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
public abstract class Evento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idevento;

    @Column(name = "nombre")
    private String nombreEvento;

    @Column(name = "fecha")
    @Future
    private Date fechaEvento;
    
    @Column(name = "horario")
    private Time horario;

    @Column(name = "ubicacion")
    private String ubicacionEvento;
    
    
    @ManyToMany
    @JoinTable(
        name = "usuarioevento",
        joinColumns = @JoinColumn(name = "evento_idevento"),
        inverseJoinColumns = @JoinColumn(name = "usuario_idusuario")
    )
    //@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "usuario_idusuario")
    private List<Usuario> usuariosAsignados = new ArrayList();
    
    
    
    @ManyToMany
    @JoinTable(
        name = "eventocastillo",
        joinColumns = @JoinColumn(name = "evento_idevento"),
        inverseJoinColumns = @JoinColumn(name = "castillo_idcastillo")
    )
    private List<Castillo> castillosAsignados = new ArrayList();
    
    
   
}
