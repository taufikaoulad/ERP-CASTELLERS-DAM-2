/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.DAO;


import cat.copernic.CastellersERP.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Taufik
 */

/**
 * Esta es una interfaz Java llamada UsuarioDAO.
 * Define una interfaz para la persistencia de objetos de tipo Usuario.
 * La interfaz extiende la interfaz JpaRepository con un tipo genérico Usuario y un tipo de identificador Integer.
 * También define un método personalizado "findByUsername" que busca un Usuario por nombre de usuario.
 */
public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {
    
    /**
     * Busca un Usuario por nombre de usuario.
     * @param nombre El nombre de usuario a buscar.
     * @return El objeto Usuario correspondiente al nombre de usuario proporcionado, o null si no se encuentra ningún usuario con ese nombre.
     */
    Usuario findByUsername(String nombre);
    
    
}
