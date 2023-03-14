/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.general.serveis;

import cat.copernic.CastellersERP.DAO.ModuloTipoUsuarioDAO;
import cat.copernic.CastellersERP.model.ModuloTipoUsuario;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bhugo
 */
@Service
public class ModuloTipoUsuarioService implements ModuloTipoUsuarioServiceInterface{

    @Autowired
    private ModuloTipoUsuarioDAO modulotipousuarioDAO;
    
    
    @Override
    @Transactional(readOnly=true)
    public List<ModuloTipoUsuario> llistarModuloTipoUsuarios() {
        return (List<ModuloTipoUsuario>) modulotipousuarioDAO.findAll();
    }

    @Override
    @Transactional
    public void afegirModuloTipoUsuario(ModuloTipoUsuario modulotipousuario) {
        this.modulotipousuarioDAO.save(modulotipousuario);
    }

    @Override
    @Transactional
    public void eliminarModuloTipoUsuario(ModuloTipoUsuario modulotipousuario) {
        this.modulotipousuarioDAO.delete(modulotipousuario);
    }

    @Override
    @Transactional(readOnly=true)
    public ModuloTipoUsuario cercarModuloTipoUsuario(ModuloTipoUsuario modulotipousuario) {
        return this.modulotipousuarioDAO.findById(modulotipousuario.getIdmtu()).orElse(null);
    }    
}
