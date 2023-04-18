/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.lang.String;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
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

    /**
     * Identificador único del evento.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idevento;

    /**
     * Nombre del evento.
     */
    @Column(name = "nombre")
    @NotEmpty
    private String nombreEvento;

    /**
     * Fecha en la que se realizará el evento.
     */
    @Column(name = "fecha")
    @NotNull
    @Future
    private Date fechaEvento;

    /**
     * Horario del evento.
     */
    @Column(name = "horario")
    @NotEmpty
    private String horario;

    /**
     * Ubicación donde se llevará a cabo el evento.
     */
    @Column(name = "ubicacion")
    @NotEmpty
    private String ubicacionEvento;

    /**
     * Lista de usuarios asignados al evento mediante una relación ManyToMany
     * con la clase Usuario.
     */
    @ManyToMany
    @JoinTable(
            name = "usuarioevento",
            joinColumns = @JoinColumn(name = "evento_idevento"),
            inverseJoinColumns = @JoinColumn(name = "usuario_idusuario")
    )
    private List<Usuario> usuariosAsignados = new ArrayList();

    /**
     * Lista de castillos asignados al evento mediante una relación ManyToMany
     * con la clase Castillo.
     */
    @ManyToMany
    @JoinTable(
            name = "eventocastillo",
            joinColumns = @JoinColumn(name = "evento_idevento"),
            inverseJoinColumns = @JoinColumn(name = "castillo_idcastillo")
    )
    private List<Castillo> castillosAsignados = new ArrayList();

}
