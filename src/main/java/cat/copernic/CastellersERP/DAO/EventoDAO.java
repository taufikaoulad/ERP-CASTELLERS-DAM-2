/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.DAO;

import cat.copernic.CastellersERP.model.Evento;
import cat.copernic.CastellersERP.model.Salida;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Taufik
 */

public interface EventoDAO extends CrudRepository<Evento, Integer>{
    
}
