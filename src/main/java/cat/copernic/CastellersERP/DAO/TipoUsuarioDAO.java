/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cat.copernic.CastellersERP.DAO;

import cat.copernic.CastellersERP.model.TipoUsuario;
import cat.copernic.CastellersERP.model.Usuario;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author bhugo
 */
public interface TipoUsuarioDAO extends CrudRepository<TipoUsuario,Integer>{
    
    
}
