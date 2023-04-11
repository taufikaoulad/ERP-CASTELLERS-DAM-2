package cat.copernic.CastellersERP.gestionEconomica.serveis;

import cat.copernic.CastellersERP.DAO.ApunteDAO;
import cat.copernic.CastellersERP.model.Apunte;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author pablogomez Clase que implementa los métodos definidos en la interfaz
 * ApunteServiceInterface para la gestión de los apuntes contables. Se utiliza
 * la anotación @Service para que Spring reconozca la clase como un servicio y
 * permita la inyección de dependencias. Además, se utiliza la anotación
 * @Transactional para definir que algunos métodos son transacciones SQL.
 */
@Service
public class ApunteService implements ApunteServiceInterface {

    /*Atribut que defineix un apunteDAO. */
    @Autowired
    private ApunteDAO apunte;

    /**
     * Quan treballem en la capa de servei amb classes de tipus DAO, com és el
     * cas, estem treballant amb transaccions SQL, és a dir, quan fem una
     * consulta a la BBDD, si aquesta ha estat un èxit, el sistema ha de fer un
     * COMMIT, en cas contrari un ROLLBACK. Així doncs, mitjançant la notació
     *
     * @Transactional l'indiquem al sistema que el mètode és una transacció.
     * Això permet que no hi hagi problemes si estem fent més d'una transacció
     * al mateix temps.
     */

    /*LListar gossos de la taula gos de la BBDD veterinari*/
    @Override
    /**
     * La notació @Transactional fa referència a la classe Transactional de
     * Spring Framework. En aquest cas no hi haurà ni COMMITS, ni ROLLBACKS, ja
     * que no modifiquem la informació de la BBDD, per tant, utilitzarem aquesta
     * notació passant-li com a paràmetre readOnly=true perquè només hem de
     * llegir de la BBDD.
     */
    /**
     * Método que lista todos los apuntes contables en la tabla apunte de la
     * base de datos. Se utiliza la anotación @Transactional con readOnly=true
     * para definir que se trata de una transacción de solo lectura.
     *
     * @return Lista de Apuntes contables.
     */
    @Transactional(readOnly = true)
    public List<Apunte> listarApuntes() {
        return (List<Apunte>) apunte.findAll();
    }

    /**
     * Método que añade un nuevo apunte contable a la tabla apunte de la base de
     * datos. Se utiliza la anotación @Transactional para definir que se trata
     * de una transacción SQL.
     *
     * @param apunte Apunte contable que se desea añadir a la tabla apunte.
     */
    @Override
    @Transactional
    public void afegirApuntes(Apunte apunte) {

        this.apunte.save(apunte);
    }

    /**
     * Método que elimina un apunte contable de la tabla apunte de la base de datos.
     * Se utiliza la anotación @Transactional para definir que se trata de una transacción SQL.
     * @param apunte Apunte contable que se desea eliminar de la tabla apunte.
     */
    @Override
    @Transactional
    public void eliminarApunte(Apunte apunte) {

        this.apunte.delete(apunte);
    }

    /**
     * Método que busca un apunte contable en la tabla apunte de la base de datos a partir de su id.
     * Se utiliza la anotación @Transactional con readOnly=true para definir que se trata de una transacción de solo lectura.
     * @param apunte Apunte contable con el id que se desea buscar en la tabla apunte.
     * @return Apunte contable encontrado en la tabla apunte de la base de datos.
     */
    @Override
    @Transactional(readOnly = true)
    public Apunte cercarApunte(Apunte apunte) {

        return this.apunte.findById(apunte.getIdapuntecontable()).orElse(null);
    }
}
