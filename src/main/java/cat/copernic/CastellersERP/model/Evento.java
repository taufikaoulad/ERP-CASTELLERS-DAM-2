/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
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
public abstract class Evento implements Serializable{
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private int idevento;
    
    @Column(name = "nombre")
    private String nombreEvento;
    
    @Column(name = "fecha")
    private Date fechaEvento;
    
    @Column(name = "ubicacion")
    private String ubicacionEvento;
    
    //private List<Castillo> listaCastillosAsignadosEvento;
}
