/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.DAO;

import cat.copernic.CastellersERP.model.Evento;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author capy
 * @param <T>
 */


//EventoDAO tiene un parametro que extiende de Evento, esto lo necesitamos para hacer el extends en el EnsayoDAO.
public interface EventoDAO<T extends Evento> extends CrudRepository<T, Integer>{

}
