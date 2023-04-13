/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * Esta clase proporciona la implementación concreta de los servicios definidos en la interfaz CastilloServiceInterface.
 * Utiliza un objeto CastilloDAO para acceder a la base de datos y realizar las operaciones necesarias.
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
 * Esta clase proporciona la implementación concreta de los servicios definidos
 * en la interfaz CastilloServiceInterface.
 *
 * Utiliza un objeto CastilloDAO para acceder a la base de datos y realizar las
 * operaciones necesarias.
 *
 * Los métodos de esta clase están anotados con la anotación @Transactional para
 * asegurar la integridad de la transacción
 *
 * y evitar errores en caso de fallos en las operaciones de la base de datos.
 *
 * La clase está anotada con @Service para que pueda ser detectada por el
 * contexto de Spring y usada como un bean de servicio.
 *
 * @author bhugo
 */
@Service
public class CastilloService implements CastilloServiceInterface {

    @Autowired
    private CastilloDAO castillo;

    /**
     *
     * Devuelve una lista de todos los castillos almacenados en la base de
     * datos.
     *
     * @return una lista de objetos Castillo
     */
    @Override
    @Transactional(readOnly = true)
    public List<Castillo> listarCastillos() {
        return (List<Castillo>) castillo.findAll();
    }

    /**
     *
     * Agrega un nuevo castillo a la base de datos.
     *
     * @param castillo el objeto Castillo a agregar
     */
    @Override
    @Transactional
    public void agregarCastillo(Castillo castillo) {
        this.castillo.save(castillo);
    }

    /**
     *
     * Elimina un castillo de la base de datos.
     *
     * @param castillo el objeto Castillo a eliminar
     */
    @Override
    @Transactional
    public void eliminarCastillo(Castillo castillo) {
        this.castillo.delete(castillo);
    }

    /**
     *
     * Busca un castillo en la base de datos en función de sus propiedades.
     *
     * @param castillo el objeto Castillo con las propiedades a buscar
     * @return el objeto Castillo que cumple con los criterios de búsqueda
     */
    @Override
    @Transactional
    public Castillo buscarCastillo(Castillo castillo) {
        return this.castillo.findById(castillo.getIdCastillo()).orElse(null);
    }

    /**
     *
     * Busca un castillo en la base de datos en función de su ID.
     *
     * @param idCastillo el ID del castillo a buscar
     * @return el objeto Castillo con el ID proporcionado
     */
    @Override
    public Castillo buscarCastilloPorId(int idCastillo) {
        return this.castillo.findById(idCastillo).orElse(null);
    }
}
