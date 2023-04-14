/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.DAO;

import cat.copernic.CastellersERP.model.Evento;
import cat.copernic.CastellersERP.model.Usuario;
import cat.copernic.CastellersERP.model.UsuarioEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Taufik
 */

/*
 * Esta es una interfaz Java llamada UsuarioEventoDAO que extiende la interfaz JpaRepository.
 * Define un método que utiliza la anotación @Query para definir una consulta personalizada que busca el identificador del objeto UsuarioEvento en función del ID del usuario y del ID del evento proporcionados como parámetros.
 */
public interface UsuarioEventoDAO extends JpaRepository<UsuarioEvento, Integer>{

    /**
     * Este método utiliza la anotación @Query para definir una consulta personalizada que busca el identificador del objeto UsuarioEvento
     * en función del ID del usuario y del ID del evento proporcionados como parámetros.
     * @param idUsuario el ID del usuario
     * @param idEvento el ID del evento
     * @return el identificador del objeto UsuarioEvento en función del ID del usuario y del ID del evento proporcionados como parámetros.
     */
    //public UsuarioEvento findByUsuarioAndEvento(Usuario usuario, Evento evento);
    @Query("SELECT ue.idusuarioevento FROM UsuarioEvento ue WHERE ue.usuario.idusuario = :idUsuario AND ue.evento.idevento = :idEvento")
    int findIdUsuarioEvento(@Param("idUsuario") int idUsuario, @Param("idEvento") int idEvento);
    
}
