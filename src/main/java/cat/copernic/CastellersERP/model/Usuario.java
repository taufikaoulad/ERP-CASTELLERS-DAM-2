/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.model;

import lombok.Data;

/**
 *
 * @author Taufik
 */

@Data
public class Usuario {
    private String nombre;
    private int edat;
    private String mail;
    private int telefono;
    private float peso;
    private float altura;
    private int posicion;
    private boolean activo;
}
