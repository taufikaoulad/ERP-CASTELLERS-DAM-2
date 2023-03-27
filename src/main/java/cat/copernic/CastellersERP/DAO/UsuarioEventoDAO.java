/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.DAO;

import cat.copernic.CastellersERP.model.Evento;
import cat.copernic.CastellersERP.model.Usuario;
import cat.copernic.CastellersERP.model.UsuarioEvento;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Taufik
 */
public interface UsuarioEventoDAO extends CrudRepository<UsuarioEvento, Integer>{

    //public UsuarioEvento findByUsuarioAndEvento(Usuario usuario, Evento evento);
    
}