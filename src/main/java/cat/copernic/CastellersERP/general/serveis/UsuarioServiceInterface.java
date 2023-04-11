/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.general.serveis;

import cat.copernic.CastellersERP.model.Usuario;
import java.util.List;

/**
 *
 * @author Taufik
 */
public interface UsuarioServiceInterface {
    public List<Usuario> llistarUsuarios();//Mètode que implementarem per llistar usuarios
    
    public void afegirUsuario(Usuario usuario); //Mètode que implementarem per afegir un usuario
    
    public void eliminarUsuario(Usuario usuario); //Mètode que implementarem per eliminar un usuario
    
    public Usuario cercarUsuario(Usuario usuario); //Mètode que implementarem per cercar un usuario
    
    public Usuario cercarUsuarioPorId(int usuarioId); //Mètode que implementarem per cercar un usuario
    
    public Usuario buscarUsuarioPorMail(String email);
}
