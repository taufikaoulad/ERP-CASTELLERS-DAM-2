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

/*
 * Esta es una clase Java llamada SalidaService que implementa la interfaz SalidaServiceInterface.
 * La clase está anotada con la anotación @Service de Spring, lo que indica que es un componente de servicio.
 * La clase proporciona implementaciones concretas para los métodos definidos en la interfaz, que interactúan con un objeto SalidaDAO para realizar operaciones CRUD en la base de datos.
 */

/*Anotació que permet al sistema que reconegui aquesta classe com una classe de servei
 *i que permet injectar aquesta classe en el controlador
*/
@Service 
public class SalidaService implements SalidaServiceInterface{

    /*Atribut que defineix un salidaDAO. */
    @Autowired
    private SalidaDAO salidaDAO;
    
    /*Quan treballem en la capa de servei amb classes de tipus DAO, com és el cas, estem
     *treballant amb transaccions SQL, és a dir, quan fem una consulta a la BBDD, si aquesta
     *ha estat un èxit, el sistema ha de fer un COMMIT, en cas contrari un ROLLBACK. Així doncs,
     *mitjançant la notació @Transactional l'indiquem al sistema que el mètode és una transacció.
     *Això permet que no hi hagi problemes si estem fent més d'una transacció al mateix temps.
    */

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
