/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cat.copernic.CastellersERP.general.serveis;

import cat.copernic.CastellersERP.model.TipoUsuario;
import java.util.List;

/**
 *
 * @author bhugo
 */
public interface TipoUsuarioServiceInterface {
    public List<TipoUsuario> llistarTipoUsuarios();//Mètode que implementarem per llistar usuarios
    
    public void afegirTipoUsuario(TipoUsuario tipousuario); //Mètode que implementarem per afegir un usuario
    
    public void eliminarTipoUsuario(TipoUsuario tipousuario); //Mètode que implementarem per eliminar un usuario
    
    public TipoUsuario cercarTipoUsuario(TipoUsuario tipousuario); //Mètode que implementarem per cercar un usuario
}
