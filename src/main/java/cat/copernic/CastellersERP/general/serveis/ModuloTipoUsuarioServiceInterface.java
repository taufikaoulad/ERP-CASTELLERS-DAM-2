/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cat.copernic.CastellersERP.general.serveis;

import cat.copernic.CastellersERP.model.ModuloTipoUsuario;
import java.util.List;

/**
 *
 * @author bhugo
 */
public interface ModuloTipoUsuarioServiceInterface {
    public List<ModuloTipoUsuario> llistarTipoUsuarios();//Mètode que implementarem per llistar usuarios
    
    public void afegirTipoUsuario(ModuloTipoUsuario modulotipousuario); //Mètode que implementarem per afegir un usuario
    
    public void eliminarTipoUsuario(ModuloTipoUsuario modulotipousuario); //Mètode que implementarem per eliminar un usuario
    
    public ModuloTipoUsuario cercarTipoUsuario(ModuloTipoUsuario modulotipousuario); //Mètode que implementarem per cercar un usuario
}
