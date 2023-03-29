/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.general.config;

import cat.copernic.CastellersERP.DAO.UsuarioDAO;
import cat.copernic.CastellersERP.model.Usuario;
import java.util.ArrayList;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pablogomez
 */

@Service ("userDetailsService")
public class AutentificacionService implements UserDetailsService{
    
    @Autowired
    private UsuarioDAO usuarioDAO;
    
    
    @Override
    @Transactional(readOnly = true)
    
    /*
    Este método carga los detalles de un usuario específico basado 
    en su nombre de usuario y devuelve un objeto UserDetails. El sistema de autenticación de Spring Security 
    utiliza este objeto UserDetails para realizar la autenticación y la autorización de este usuario en 
    particular.
    */
    
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Usuario usuario = usuarioDAO.findByUsername(username);
        
        //GrantedAuthority representa un rol o permiso que se le ha concedido a un usuario. 
        var roles = new ArrayList<GrantedAuthority>();
        
        roles.add(new SimpleGrantedAuthority(usuario.getTipousuario_idtipousuario().getNombretipousuario()));
        
        User user = new User(usuario.getUsername(), usuario.getPassword(), roles);
        
        return user;
    }
    
}
