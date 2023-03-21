/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "tipousuario")
public class TipoUsuario implements Serializable {

    @Id //Indica al sistema que l'atribut idgos és la clau primària de la BBDD
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Indica al sistema com generem l'id, en el nostre cas autoincremental, per això fem servir IDENTITY
    private int idtipousuario;

    @NotEmpty
    private String nombretipousuario;

    
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "tipousuario_idtipousuario")
    private List<Usuario> usuarios = new ArrayList<>();
    
    @OneToMany(mappedBy = "rol")
    private List<ModuloTipoUsuario> tiposUsuariosModulos = new ArrayList<>();
    
}
