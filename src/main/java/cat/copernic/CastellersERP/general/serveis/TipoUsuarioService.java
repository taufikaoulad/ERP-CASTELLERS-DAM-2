/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.general.serveis;

import cat.copernic.CastellersERP.DAO.TipoUsuarioDAO;
import cat.copernic.CastellersERP.model.TipoUsuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bhugo
 */
@Service
public class TipoUsuarioService implements TipoUsuarioServiceInterface{

    @Autowired
    private TipoUsuarioDAO tipousuarioDAO;
    
    @Override
    @Transactional(readOnly=true)
    public List<TipoUsuario> llistarTipoUsuarios() {
        return (List<TipoUsuario>) tipousuarioDAO.findAll();
    }

    @Override
    @Transactional
    public void afegirTipoUsuario(TipoUsuario tipousuario) {
        this.tipousuarioDAO.save(tipousuario);
    }

    @Override
    @Transactional
    public void eliminarTipoUsuario(TipoUsuario tipousuario) {
        this.tipousuarioDAO.delete(tipousuario);
    }

    @Override
    @Transactional(readOnly=true)
    public TipoUsuario cercarTipoUsuario(TipoUsuario tipousuario) {
        return this.tipousuarioDAO.findById(tipousuario.getIdtipousuario()).orElse(null);
    }
    
}
