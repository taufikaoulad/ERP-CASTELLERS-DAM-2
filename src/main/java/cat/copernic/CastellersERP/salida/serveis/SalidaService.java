/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.salida.serveis;


import cat.copernic.CastellersERP.DAO.SalidaDAO;
import cat.copernic.CastellersERP.model.Salida;
import cat.copernic.CastellersERP.model.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Taufik
 */

/*Anotació que permet al sistema que reconegui aquesta classe com una classe de servei
 *i que permet injectar aquesta classe en el controlador
*/
@Service 
public class SalidaService implements SalidaServiceInterface{

    @Autowired
    private SalidaDAO salidaDAO;

    @Override
    @Transactional(readOnly=true)
    public List<Salida> llistarSalidas() {
       return (List<Salida>) salidaDAO.findAll();
    }

    @Override
    @Transactional
    public void afegirSalida(Salida salida) {
        this.salidaDAO.save(salida);
    }

    @Override
    @Transactional 
    public void eliminarSalida(Salida salida) {
        this.salidaDAO.delete(salida);
    }

    @Override
    @Transactional(readOnly=true)
    public Salida cercarSalida(Salida salida) {
       return this.salidaDAO.findById(salida.getIdevento()).orElse(null);
    }

    @Override
    public Salida cercarSalidaPorId(int idevento) {
        return this.salidaDAO.findById(idevento).orElse(null);
    }
 
}
