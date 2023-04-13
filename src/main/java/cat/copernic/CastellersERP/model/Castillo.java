package cat.copernic.CastellersERP.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "castillo")
public class Castillo implements Serializable {

    @Id //Indica al sistema que l'atribut idgos és la clau primària de la BBDD
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Indica al sistema com generem l'id, en el nostre cas autoincremental, per això fem servir IDENTITY
    @Column(name = "idcastillo")
    private int idCastillo;

    /**
     * El nombre del castillo.
     */
    @NotEmpty
    @Column(name = "nombre")
    private String nombre;

    /**
     * El número de pisos del castillo.
     */
    @Min(value = 0)
    @Column(name = "pisos")
    private int pisos;

    /**
     * El número de personas por piso del castillo.
     */
    @Min(value = 0)
    @Column(name = "personaspisos")
    private int personasPisos;

    /**
     * El número de piñas del castillo.
     */
    @Min(value = 0)
    @Column(name = "ncpina")
    private int ncPina;

    /**
     * El número de troncos del castillo.
     */
    @Min(value = 0)
    @Column(name = "nctronc")
    private int ncTronc;

    /**
     * Indica si el castillo está levantado o no.
     */
    @Column(name = "aixecat")
    private boolean aixecat;

    /**
     * Indica si el castillo tiene aguja o no.
     */
    @Column(name = "agulla")
    private boolean agulla;

    /**
     * Lista de eventos a los que está asignado el castillo.
     */
    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "castillosAsignados")
    private List<Evento> eventos = new ArrayList<>();
}
