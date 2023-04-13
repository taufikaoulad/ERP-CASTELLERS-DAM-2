/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author Taufik
 */

@Data // Genera automáticamente los métodos "getter", "setter" y otros métodos útiles.
@Entity // Indica que la clase es una entidad que se almacenará en la base de datos y se mapeará a una tabla de la base de datos.
public class Salida extends Evento implements Serializable {
    
    // Identificación de la clase para poder deserializar de manera correcta
    private static final long serialVersionUID = 1L;
    
    private boolean paradita; // Indica si la salida tiene una parada programada.
}
