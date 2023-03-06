/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.castillo.serveis;

import cat.copernic.CastellersERP.model.Castillo;
import java.util.List;
import cat.copernic.CastellersERP.DAO.CastilloDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bhugo
 */
@Service 
public class CastilloService implements CastilloServiceInterface{

    @Autowired
    private CastilloDAO castillo;


    @Override
    @Transactional(readOnly = true)
    public List<Castillo> listarCastillos() {
        return (List<Castillo>) castillo.findAll();
    }

    @Override
    @Transactional
    public void agregarCastillo(Castillo castillo) {
        this.castillo.save(castillo); 
    }

    @Override
    @Transactional
    public void eliminarCastillo(Castillo castillo) {
        this.castillo.delete(castillo);
    }

    @Override
    @Transactional
    public Castillo buscarCastillo(Castillo castillo) {
        return this.castillo.findById(castillo.getIdCastillo()).orElse(null);
    }
}
