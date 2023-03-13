/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
public class Castillo {
    @Id //Indica al sistema que l'atribut idgos és la clau primària de la BBDD
    @GeneratedValue(strategy=GenerationType.IDENTITY) //Indica al sistema com generem l'id, en el nostre cas autoincremental, per això fem servir IDENTITY
    @Column(name="idcastillo")
    private int idCastillo; 
    @Column(name="nombre")
    private String nombre;
    @Column(name="pisos")
    private int pisos;
    @Column(name="personaspisos")
    private int personasPisos;
    @Column(name="ncpina")
    private int ncPina;
    @Column(name="nctronc")
    private int ncTronc;
    @Column(name="aixecat")
    private boolean aixecat;
    @Column(name="agulla")
    private boolean agulla;
    
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "castillo_idcastillo")
    private List<EventoCastillo> castillos = new ArrayList();
}