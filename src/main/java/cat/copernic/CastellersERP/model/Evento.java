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
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author capy
 */
@Data
@Entity
@Table(name="evento")
public class Evento implements Serializable {

    //Identificació de la classe per poder deserialitzar de manera correcta
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AutoIncrement
    @Column(name = "idevento", nullable = false, unique = true)
    private int idEvento;
    @Column(name = "nombre", nullable = false)
    private String nombreEvento;
    @Column(name = "fecha", nullable = false)
    private Date fechaEvento;
    @Column(name = "ubicacion", nullable = false)
    private String ubicacionEvento;
    //private ArrayList<Castillo> listaCastillosAsignadosEvento;
}
