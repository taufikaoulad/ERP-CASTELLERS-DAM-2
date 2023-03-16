/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author bhugo
 */
/*@Entity converteix la classe en una entitat per poder treballar amb els seus objectes
 *com entitats de la base de dades
 */

/*@Table avisa al sistema que l'entitat Gos és l'entitat relacionada amb la taula gossos de la BBDD.
 * Aquesta anotació és obligatòria si el nom de l'entitat i la taual és diferent, com és el nostre cas,
 * i altament recomanable, però no obligatòria, si fem servir un S.O. Linux i MAC i el nom de la classe i 
 * la taula de la BBDD són el mateix, i en el cas de la taula el nom comença en minúscula i el de la classe
 * amb majúscula.
 */

/*Implementem la interfície Serializable perquè els objectes de gos es puguin guardar
 *com un objecte en la base de dades.
 */

@Data
@Entity
@Table(name = "apuntecontable")
public class Apunte implements Serializable {
    
    //Identificació de la classe per poder deserialitzar de manera correcta
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Indica al sistema com generem l'id, en el nostre cas autoincremental, per això fem servir IDENTITY
    private int idapuntecontable;
    
    @NotEmpty
    private String concepto;
    
    
    @DecimalMin("0")
    private float precio;
    
    @NotEmpty
    private String tipo;
}
