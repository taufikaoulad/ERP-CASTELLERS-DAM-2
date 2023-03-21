/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.model;


import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;
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
    private Date fechaEvento;

    @Column(name = "ubicacion")
    private String ubicacionEvento;
    
    /*
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
        name = "usuarioeventocastillo",
        joinColumns = @JoinColumn(name = "evento_idevento"),
        inverseJoinColumns = @JoinColumn(name = "usuario_idusuario")
    )
    */

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "evento")
    private List<UsuarioEvento> usuariosAsignados = new ArrayList();
    
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
        name = "eventocastillo",
        joinColumns = @JoinColumn(name = "evento_idevento"),
        inverseJoinColumns = @JoinColumn(name = "castillo_idcastillo")
    )
    private List<Castillo> castillosAsignados = new ArrayList();
    
    
   
}
