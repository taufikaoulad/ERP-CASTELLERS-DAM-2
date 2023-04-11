package cat.copernic.CastellersERP.gestionEconomica.serveis;

import cat.copernic.CastellersERP.model.Apunte;
import java.util.List;

/**
 * Interfície que defineix els mètodes que implementa la capa de servei per a la
 * gestió d'apunts contables.
 *
 * Aquests mètodes defineixen les operacions que es poden realitzar amb els
 * apunts contables.
 *
 * @author pablogomez
 */
public interface ApunteServiceInterface {

    /**
     * Retorna una llista amb tots els apunts contables de la base de dades.
     *
     * @return una llista amb tots els apunts contables de la base de dades.
     */
    public List<Apunte> listarApuntes();

    /**
     * Afegeix un apunt contable a la base de dades.
     *
     * @param apunte l'apunt contable a afegir a la base de dades.
     */
    public void afegirApuntes(Apunte apunte);

    /**
     * Elimina un apunt contable de la base de dades.
     *
     * @param apunte l'apunt contable a eliminar de la base de dades.
     */
    public void eliminarApunte(Apunte apunte);

    /**
     * Cerca un apunt contable a la base de dades a partir del seu id.
     *
     * @param apunte l'apunt contable a cercar a la base de dades.
     * @return l'apunt contable trobat a la base de dades, o null si no s'ha
     * trobat cap apunt amb l'id especificat.
     */
    public Apunte cercarApunte(Apunte apunte);
}
