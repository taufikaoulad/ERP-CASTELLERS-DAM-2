/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.general.serveis;

import cat.copernic.CastellersERP.model.EventoCastillo;
import cat.copernic.CastellersERP.model.UsuarioEventoCastillo;
import java.util.List;

/**
 *
 * @author capy
 */
public interface UsuarioEventoCastilloServiceInterface {
    
    public List<UsuarioEventoCastillo> listarEventoCastillo();
    
    public void añadirEventoCastillo(UsuarioEventoCastillo usuarioEventoCastillo);
    
    public void eliminarEventoCastillo(UsuarioEventoCastillo usuarioEventoCastillo);
    
    public UsuarioEventoCastillo buscarEventoCastillo(UsuarioEventoCastillo usuarioEventoCastillo);
}
