/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.util.ArrayList;
import lombok.Data;

/**
 *
 * @author bhugo
 */
@Data
@Entity
public class Ensayo extends Evento {
    
    @Column(name = "duracion")
    private String duracion;
}
