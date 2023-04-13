package cat.copernic.CastellersERP.general.serveis;

import cat.copernic.CastellersERP.DAO.ModuloDAO;
import cat.copernic.CastellersERP.model.Modulo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ModuloService implements ModuloServiceInterface {

    @Autowired
    private ModuloDAO moduloDAO;

    /**
     * Este método devuelve una lista con todos los módulos registrados en la
     * base de datos
     *
     * @return una lista de objetos Modulo
     */
    @Override
    @Transactional(readOnly = true)
    public List<Modulo> llistarModulo() {
        return (List<Modulo>) moduloDAO.findAll();
    }

    /**
     * Este método añade un nuevo módulo a la base de datos
     *
     * @param modulo el objeto Modulo a añadir
     */
    @Override
    @Transactional
    public void afegirModulo(Modulo modulo) {
        this.moduloDAO.save(modulo);
    }

    /**
     * Este método elimina un módulo de la base de datos
     *
     * @param modulo el objeto Modulo a eliminar
     */
    @Override
    @Transactional
    public void eliminarModulo(Modulo modulo) {
        this.moduloDAO.delete(modulo);
    }

    /**
     * Este método busca un módulo en la base de datos a partir de su ID
     *
     * @param modulo el objeto Modulo que contiene el ID a buscar
     * @return el objeto Modulo correspondiente al ID buscado, o null si no se
     * encuentra
     */
    @Override
    @Transactional(readOnly = true)
    public Modulo cercarModulos(Modulo modulo) {
        return this.moduloDAO.findById(modulo.getIdModulo()).orElse(null);
    }

}
