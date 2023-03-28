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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Usuario usuario = usuarioDAO.findByUsername(username);
        
        var roles = new ArrayList<GrantedAuthority>();
        
        roles.add(new SimpleGrantedAuthority(usuario.getTipousuario_idtipousuario().getNombretipousuario()));
        
        User user = new User(usuario.getUsername(), usuario.getPassword(), roles);
        
        System.out.println(user.getPassword() + " " + user.getUsername());
        
        return user;
        
        
    }
    
}
