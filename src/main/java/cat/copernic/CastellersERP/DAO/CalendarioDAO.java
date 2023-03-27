/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.DAO;

import cat.copernic.CastellersERP.model.Evento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author pablogomez
 */
public interface CalendarioDAO extends JpaRepository<Evento, Integer>{
    
    //Esta es una consulta SQL que selecciona todas las filas de la tabla "evento" que tienen una fecha con 
    //el mes igual al valor del primer parámetro y el año igual al valor del segundo parámetro.
    //La opción "nativeQuery = true" indica que esta consulta es una consulta SQL nativa, lo que significa 
    //que se utiliza el dialecto SQL de la base de datos subyacente en lugar del lenguaje de consulta de JPA.
    @Query(value = "SELECT * FROM evento WHERE YEAR(fecha) = ?2 AND MONTH(fecha) = ?1", nativeQuery = true)
    List<Evento> findTareasDelMesActual(int mesActual, int anyoActual);
    
}
