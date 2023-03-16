/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.model;

/**
 *
 * @author bhugo
 */

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
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "modulotipousuario")
public class ModuloTipoUsuario implements Serializable{
    
    
    @Id //Indica al sistema que l'atribut idgos és la clau primària de la BBDD
    @GeneratedValue(strategy=GenerationType.IDENTITY) //Indica al sistema com generem l'id, en el nostre cas autoincremental, per això fem servir IDENTITY
    int idmtu;
    
    boolean activo;
    
    @ManyToOne
    @JoinColumn(name = "modulo_idmodulo")
    private Modulo modulo;
    
    @ManyToOne
    @JoinColumn(name = "tipousuario_idtipousuario")
    private TipoUsuario rol;
}
