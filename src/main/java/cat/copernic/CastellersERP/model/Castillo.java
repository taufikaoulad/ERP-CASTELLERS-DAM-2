/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.model;

import lombok.Data;

/**
 *
 * @author bhugo
 */

@Data
public class Castillo {
    private String nombre;
    private int pisos;
    private int personesXPiso;
    private int ncPiña;
    private int ncTronc;
    private boolean aixecat;
    private boolean agulla;

}
