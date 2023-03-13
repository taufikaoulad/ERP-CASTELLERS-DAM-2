/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.general.serveis;

import cat.copernic.CastellersERP.DAO.UsuarioEventoCastilloDAO;
import cat.copernic.CastellersERP.model.EventoCastillo;
import cat.copernic.CastellersERP.model.UsuarioEventoCastillo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author capy
 */
public class UsuarioEventoCastilloService implements UsuarioEventoCastilloServiceInterface {

    @Autowired
    private UsuarioEventoCastilloDAO usuarioEventoCastilloDAO;

    @Override
    public List<UsuarioEventoCastillo> listarEventoCastillo() {
        return (List<UsuarioEventoCastillo>) usuarioEventoCastilloDAO.findAll();
    }

    @Override
    public void añadirEventoCastillo(UsuarioEventoCastillo usuarioEventoCastillo) {
        this.usuarioEventoCastilloDAO.save(usuarioEventoCastillo);
    }

    @Override
    public void eliminarEventoCastillo(UsuarioEventoCastillo usuarioEventoCastillo) {
        this.usuarioEventoCastilloDAO.delete(usuarioEventoCastillo);
    }

    @Override
    public UsuarioEventoCastillo buscarEventoCastillo(UsuarioEventoCastillo usuarioEventoCastillo) {
        return this.usuarioEventoCastilloDAO.findById(usuarioEventoCastillo.getIdusuarioeventocastillo()).orElse(null);
    }
    
    
}
