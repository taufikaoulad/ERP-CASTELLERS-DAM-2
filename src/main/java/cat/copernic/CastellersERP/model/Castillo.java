/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author bhugo
 */

@Data
@Entity
@Table(name = "castillo")
public class Castillo implements Serializable{
    @Id //Indica al sistema que l'atribut idgos és la clau primària de la BBDD
    @GeneratedValue(strategy=GenerationType.IDENTITY) //Indica al sistema com generem l'id, en el nostre cas autoincremental, per això fem servir IDENTITY
    @Column(name="idcastillo")
    private int idCastillo; 
    
    @NotEmpty
    @Column(name="nombre")
    private String nombre;
    
    @Min(value = 0)
    @Column(name="pisos")
    private int pisos;
    
    @Min(value = 0)
    @Column(name="personaspisos")
    private int personasPisos;
    
    @Min(value = 0)
    @Column(name="ncpina")
    private int ncPina;
    
    @Min(value = 0)
    @Column(name="nctronc")
    private int ncTronc;
    
    @Column(name="aixecat")
    private boolean aixecat;
    @Column(name="agulla")
    private boolean agulla;
    
    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "castillosAsignados")
    private List<Evento> eventos = new ArrayList<>();
}
