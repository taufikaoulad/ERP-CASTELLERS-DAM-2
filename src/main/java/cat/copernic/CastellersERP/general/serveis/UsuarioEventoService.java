/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.general.serveis;

import cat.copernic.CastellersERP.DAO.UsuarioDAO;
import cat.copernic.CastellersERP.DAO.UsuarioEventoDAO;
import cat.copernic.CastellersERP.model.Evento;
import cat.copernic.CastellersERP.model.Usuario;
import cat.copernic.CastellersERP.model.UsuarioEvento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Taufik
 */
@Service
public class UsuarioEventoService implements UsuarioEventoServiceInterface{

    @Autowired
    private UsuarioEventoDAO usuarioeventoDAO;
    
    private EntityManagerFactory entityManagerFactory;
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

    /*@Override
    @Transactional(readOnly = true)
    public UsuarioEvento buscarUsuarioEventoPorUsuarioYEvento(Usuario usuario, Evento evento) {
       return this.usuarioeventoDAO.findByUsuarioAndEvento(usuario, evento);
    }*/
    
    /*@Override
    @Transactional(readOnly = true)
    public Integer buscarUsuarioEventoPorUsuarioOEvento(int idUsuario, int idEvento) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT idusuarioevento FROM usuarioevento WHERE usuario_idusuario = :idUsuario AND evento_idevento = :idEvento");
        query.setParameter("idUsuario", idUsuario);
        query.setParameter("idEvento", idEvento);
        UsuarioEvento usuarioEvento = (UsuarioEvento) query.getSingleResult();
        return usuarioEvento.getIdusuarioevento();
    }*/

    @Override
    @Transactional(readOnly = true)
    public Integer obtenerIdUsuarioEvento(int idUsuario, int idEvento) {
        return usuarioeventoDAO.findIdUsuarioEvento(idUsuario, idEvento);
    } 
 
}
