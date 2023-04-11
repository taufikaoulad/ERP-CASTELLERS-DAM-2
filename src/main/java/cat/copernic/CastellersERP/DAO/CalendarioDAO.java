package cat.copernic.CastellersERP.DAO;

import cat.copernic.CastellersERP.model.Evento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author pablogomez La interfaz CalendarioDAO define un repositorio de acceso
 * a datos que maneja objetos de tipo Evento. Extiende JpaRepository, lo que
 * proporciona métodos CRUD (crear, leer, actualizar y eliminar) para Evento sin
 * necesidad de implementarlos manualmente. También incluye un método
 * personalizado "findTareasDelMesActual" que ejecuta una consulta SQL nativa
 * para seleccionar todos los eventos cuya fecha tiene el mes igual al valor del
 * primer parámetro y el año igual al valor del segundo parámetro.
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see org.springframework.data.jpa.repository.Query
 * @author pablogomez
 * @param <Evento> la clase que define la entidad con la que trabajaremos
 * @param <Integer> el tipo del atributo que define la clave primaria de esta
 * entidad
 */
public interface CalendarioDAO extends JpaRepository<Evento, Integer> {

    //Esta es una consulta SQL que selecciona todas las filas de la tabla "evento" que tienen una fecha con 
    //el mes igual al valor del primer parámetro y el año igual al valor del segundo parámetro.
    //La opción "nativeQuery = true" indica que esta consulta es una consulta SQL nativa, lo que significa 
    //que se utiliza el dialecto SQL de la base de datos subyacente en lugar del lenguaje de consulta de JPA.
    @Query(value = "SELECT * FROM evento WHERE YEAR(fecha) = ?2 AND MONTH(fecha) = ?1", nativeQuery = true)
    List<Evento> findTareasDelMesActual(int mesActual, int anyoActual);

}
