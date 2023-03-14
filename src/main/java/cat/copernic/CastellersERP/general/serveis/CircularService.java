/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.general.serveis;

import cat.copernic.CastellersERP.DAO.CircularDAO;
import cat.copernic.CastellersERP.model.Circular;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pablogomez
 */
@Service
public class CircularService implements CircularServiceInterface {

    @Autowired
    private CircularDAO circular;

    @Override
    @Transactional(readOnly = true)
    public List<Circular> listarCirculars() {
        return (List<Circular>) circular.findAll();
    }

    @Override
    @Transactional
    public void afegirCirculars(Circular circular) {
        this.circular.save(circular);
    }

    @Override
    @Transactional
    public void eliminarCirculars(Circular circular) {
        this.circular.delete(circular);
    }

    @Override
    @Transactional(readOnly = true)
    public Circular cercarCirculars(Circular circular) {
        return this.circular.findById(circular.getIdcircular()).orElse(null);

    }

}
