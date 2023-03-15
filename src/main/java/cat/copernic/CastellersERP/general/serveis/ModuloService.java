/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.general.serveis;

import cat.copernic.CastellersERP.DAO.ModuloDAO;
import cat.copernic.CastellersERP.model.Modulo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bhugo
 */
@Service
public class ModuloService implements ModuloServiceInterface{

    @Autowired
    private ModuloDAO moduloDAO;
    
    @Override
    @Transactional(readOnly=true)
    public List<Modulo> llistarModulo() {
        return (List<Modulo>) moduloDAO.findAll();
    }

    @Override
    @Transactional
    public void afegirModulo(Modulo modulo) {
        this.moduloDAO.save(modulo);
    }

    @Override
    @Transactional
    public void eliminarModulo(Modulo modulo) {
        this.moduloDAO.delete(modulo);
    }

    @Override
    @Transactional(readOnly=true)
    public Modulo cercarModulos(Modulo modulo) {
        return this.moduloDAO.findById(modulo.getIdModulo()).orElse(null);
    }
    
}
