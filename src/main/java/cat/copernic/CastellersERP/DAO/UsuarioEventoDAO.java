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
public interface UsuarioEventoDAO extends JpaRepository<UsuarioEvento, Integer>{

    //public UsuarioEvento findByUsuarioAndEvento(Usuario usuario, Evento evento);
    @Query("SELECT ue.idusuarioevento FROM UsuarioEvento ue WHERE ue.usuario = :idUsuario AND ue.evento = :idEvento")
    int findIdUsuarioEvento(@Param("idUsuario") int idUsuario, @Param("idEvento") int idEvento);
    
}