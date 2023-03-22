/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.general.serveis;

import cat.copernic.CastellersERP.DAO.UsuarioDAO;
import cat.copernic.CastellersERP.DAO.UsuarioEventoDAO;
import cat.copernic.CastellersERP.model.Usuario;
import cat.copernic.CastellersERP.model.UsuarioEvento;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Taufik
 */
public class UsuarioEventoService implements UsuarioEventoServiceInterface{

    @Autowired
    private UsuarioEventoDAO usuarioeventoDAO;
    
    @Override
    @Transactional(readOnly = true)
    public List<UsuarioEvento> llistarUsuariosEventos() {
        return (List<UsuarioEvento>) usuarioeventoDAO.findAll();
    }

    @Override
    @Transactional
    public void afegirUsuarioEvento(UsuarioEvento usuarioevento) {
        this.usuarioeventoDAO.save(usuarioevento);
    }

    @Override
    @Transactional
    public void eliminarUsuarioEvento(UsuarioEvento usuarioevento) {
        this.usuarioeventoDAO.delete(usuarioevento);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioEvento cercarUsuarioEvento(UsuarioEvento usuarioevento) {
       return this.usuarioeventoDAO.findById(usuarioevento.getIdusuarioevento()).orElse(null);
    }

   
    
}
