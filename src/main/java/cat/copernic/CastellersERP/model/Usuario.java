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
import java.util.Date;
import lombok.Data;

/**
 *
 * @author Taufik
 */

@Data
@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AutoIncrement
    @Column(name = "idusaurio", nullable = false, unique = true)
    private int idusaurio;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "edat", nullable = false)
    private Date edat;
    @Column(name = "mail", nullable = false)
    private String mail;
    @Column(name = "telefono", nullable = false)
    private String telefono;
    @Column(name = "peso", nullable = false)
    private float peso;
    @Column(name = "altura", nullable = false)
    private float altura;
    @Column(name = "activo", nullable = false)
    private boolean activo;
    @Column(name = "pocisio", nullable = false)
    private String posicion;
    @Column(name = "tipousuario_idtipousuario", nullable = false)
    private int tipousuario_idtipousuario;
}

