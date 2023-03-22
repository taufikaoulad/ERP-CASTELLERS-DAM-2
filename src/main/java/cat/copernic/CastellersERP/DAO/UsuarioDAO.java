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
public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {
    
    Usuario findByUsername(String nombre);
    
    
}
