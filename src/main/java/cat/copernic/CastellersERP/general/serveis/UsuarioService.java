/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.general.serveis;

import cat.copernic.CastellersERP.DAO.UsuarioDAO;
import cat.copernic.CastellersERP.model.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Taufik
 */

/*Anotació que permet al sistema que reconegui aquesta classe com una classe de servei
 *i que permet injectar aquesta classe en el controlador
*/
@Service 
public class UsuarioService implements UsuarioServiceInterface{

    /*Atribut que defineix un UsuarioDAO. Mitjançant aquest atribut el control ja no 
     *accedirà directament a la capa de dades, si no que accedirà mitjançant la capa de servei.
    */
    @Autowired
    private UsuarioDAO usuarioDAO;
    
    /*LListar gossos de la taula gos de la BBDD veterinari*/
    @Override
    @Transactional(readOnly=true)
    public List<Usuario> llistarUsuarios() {
        return (List<Usuario>) usuarioDAO.findAll();
    }

    /*Afegir el gos passat per paràmetre a la taula gos de la BBDD veterinari*/
    @Override
    @Transactional
    public void afegirUsuario(Usuario usuario) {
        this.usuarioDAO.save(usuario);
    }

     /*Eliminar el gos passat per paràmetre de la taula gos de la BBDD veterinari*/
    @Override
    @Transactional 
    public void eliminarUsuario(Usuario usuario) {
       this.usuarioDAO.delete(usuario);
    }

    /*Cercar el gos passat per paràmetre en la taula gos de la BBDD veterinari*/
    @Override
    @Transactional(readOnly=true)
    public Usuario cercarUsuario(Usuario usuario) {
        return this.usuarioDAO.findById(usuario.getIdusuario()).orElse(null);
    }
   
}
