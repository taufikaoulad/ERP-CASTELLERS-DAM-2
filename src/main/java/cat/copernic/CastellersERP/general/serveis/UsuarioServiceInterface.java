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
    
    public void afegirGos(Usuario usuario); //Mètode que implementarem per afegir un usuario
    
    public void eliminarGos(Usuario usuario); //Mètode que implementarem per eliminar un usuario
    
    public Usuario cercarGos(Usuario usuario); //Mètode que implementarem per cercar un usuario
}
