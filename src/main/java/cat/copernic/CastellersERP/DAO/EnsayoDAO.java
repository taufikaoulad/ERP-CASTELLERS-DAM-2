/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.DAO;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import cat.copernic.CastellersERP.model.Ensayo;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author capy
 * 
 * Esta interfaz representa el Data Access Object (DAO) para la entidad Ensayo.
 * Extiende la interfaz EventoDAO con el tipo de entidad Ensayo como parámetro genérico.
 * 
 * @param <T> el tipo de entidad que maneja este DAO (Ensayo en este caso)
 */
public interface EnsayoDAO extends EventoDAO<Ensayo> {
    
    /**
     * Esta interfaz no define nuevos métodos, pero hereda los métodos de
     * EventoDAO y CrudRepository para realizar operaciones CRUD en la base de datos.
     * Los métodos de EventoDAO son genéricos y se adaptan automáticamente al tipo
     * de entidad Ensayo.
     */

} 
