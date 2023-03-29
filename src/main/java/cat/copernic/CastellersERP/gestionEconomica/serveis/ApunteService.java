/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.gestionEconomica.serveis;

import cat.copernic.CastellersERP.DAO.ApunteDAO;
import cat.copernic.CastellersERP.model.Apunte;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pablogomez
 */

/*Anotació que permet al sistema que reconegui aquesta classe com una classe de servei
 *i que permet injectar aquesta classe en el controlador
*/
@Service
public class ApunteService implements ApunteServiceInterface {

    /*Atribut que defineix un apunteDAO. */
    @Autowired
    private ApunteDAO apunte;
    
    /*Quan treballem en la capa de servei amb classes de tipus DAO, com és el cas, estem
     *treballant amb transaccions SQL, és a dir, quan fem una consulta a la BBDD, si aquesta
     *ha estat un èxit, el sistema ha de fer un COMMIT, en cas contrari un ROLLBACK. Així doncs,
     *mitjançant la notació @Transactional l'indiquem al sistema que el mètode és una transacció.
     *Això permet que no hi hagi problemes si estem fent més d'una transacció al mateix temps.
    */

    /*LListar gossos de la taula gos de la BBDD veterinari*/
    @Override
    /*La notació @Transactional fa referència a la classe Transactional de Spring Framework.
     *En aquest cas no hi haurà ni COMMITS, ni ROLLBACKS, ja que no modifiquem la informació
     *de la BBDD, per tant, utilitzarem aquesta notació passant-li com a paràmetre readOnly=true
     *perquè només hem de llegir de la BBDD.
    */    
    @Transactional(readOnly=true)
    public List<Apunte> listarApuntes() {
        return (List<Apunte>) apunte.findAll();
    }

    @Override
    @Transactional
    public void afegirApuntes(Apunte apunte) {
        
        this.apunte.save(apunte);
    }
    @Override
    @Transactional
    public void eliminarApunte(Apunte apunte) {
        
        this.apunte.delete(apunte);
    }
    @Override
    @Transactional(readOnly=true)
    public Apunte cercarApunte(Apunte apunte) {
        
        return this.apunte.findById(apunte.getIdapuntecontable()).orElse(null);
    }
}
