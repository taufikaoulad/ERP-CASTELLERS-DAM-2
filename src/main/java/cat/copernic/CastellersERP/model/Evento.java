/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.model;

import java.util.ArrayList;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author capy
 */
@Data
public class Evento {

    private String nombreEvento;
    private Date fechaEvento;
    private String ubicacionEvento;
    private ArrayList<Castillo> listaCastillosAsignadosEvento;
}
