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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author pablogomez
 */
@Data
@Entity
@Table(name = "modulo")
public class Modulo implements Serializable{
    
    @Id //Indica al sistema que l'atribut idgos és la clau primària de la BBDD
    @GeneratedValue(strategy=GenerationType.IDENTITY) //Indica al sistema com generem l'id, en el nostre cas autoincremental, per això fem servir IDENTITY
    @Column(name="idmodulo")
    private int idModulo; 
    @Column(name="nombre")
    private String nombre;
    
    @OneToMany(mappedBy = "modulo")
    private List<ModuloTipoUsuario> modulosTiposUsuario = new ArrayList<>();
    
    
}
