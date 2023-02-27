/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 *
 * @author capy
 */
@Data
@Inheritance
@Entity
@Table(name = "evento")
public abstract class Evento {
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    @Column(name = "idevento")
    private int idEvento;
    
    @Column(name = "nombre")
    private String nombreEvento;
    
    @Column(name = "fecha")
    private Date fechaEvento;
    
    @Column(name = "ubicacion")
    private String ubicacionEvento;
    
    //private ArrayList<Castillo> listaCastillosAsignadosEvento;
}
