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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import lombok.Data;

/**
 *
 * @author capy
 */
@Data
@Entity
@Table(name = "usuarioevento")
public class UsuarioEvento implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idusuarioevento;
    
    @Column(name = "asistenciatransporte")
    private Boolean asistenciaTransporte;
    /*
    @ManyToOne
    @JoinColumn(name = "evento_idevento")
    private Evento evento_idevento;
    
    @ManyToOne
    @JoinColumn(name = "usuario_idusuario")
    private Usuario usuario_idusuario;
         */   
    
    
    
}
