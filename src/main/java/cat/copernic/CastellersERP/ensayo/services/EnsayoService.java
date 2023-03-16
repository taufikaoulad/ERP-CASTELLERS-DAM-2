/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.ensayo.services;

import cat.copernic.CastellersERP.DAO.EnsayoDAO;
import cat.copernic.CastellersERP.model.Ensayo;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author capy
 */
@Service
public class EnsayoService implements EnsayoServiceInterface{
    
    @Autowired
    private EnsayoDAO ensayoDAO;

    @Override
    //Si no se modifica la información de la base de datos,
    //en la anotación Transactional especificaremos que solo se puede leer de ella.
    @Transactional(readOnly = true)
    public List<Ensayo> listarEnsayos() {
        
        return (List<Ensayo>) ensayoDAO.findAll();
    }

    @Override
    @Transactional
    public void añadirEnsayo(Ensayo ensayo) {
        
        this.ensayoDAO.save(ensayo);
    }

    @Override
    @Transactional
    public void eliminarEnsayo(Ensayo ensayo) {
        
        this.ensayoDAO.delete(ensayo);
    }

    @Override
    //Si no se modifica la información de la base de datos,
    //en la anotación Transactional especificaremos que solo se puede leer de ella.
    @Transactional(readOnly = true)
    public Ensayo buscarEnsayo(Ensayo ensayo) {
        
        return this.ensayoDAO.findById(ensayo.getIdevento()).orElse(null);
    }
    
}
