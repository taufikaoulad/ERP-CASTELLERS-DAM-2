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
/**
 *
 * Esta interfaz representa el Data Access Object (DAO) para la entidad Evento.
 *
 * Se utiliza un parámetro genérico T que extiende la clase Evento para permitir
 *
 * la implementación de diferentes subtipos de Evento.
 *
 * @param <T> el tipo de Evento que se maneja en este DAO
 */
public interface EventoDAO<T extends Evento> extends CrudRepository<T, Integer> {

    /**
     *
     * Esta interfaz extiende la interfaz CrudRepository, por lo que hereda
     * todos sus métodos para realizar operaciones CRUD en la base de datos.
     * Además, se pueden agregar métodos personalizados según las necesidades de
     * la aplicación.
     */
}
