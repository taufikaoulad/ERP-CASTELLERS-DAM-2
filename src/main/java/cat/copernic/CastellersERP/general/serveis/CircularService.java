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
 * @author pablogomez Clase que implementa la interfaz CircularServiceInterface
 * y que define los métodos para interactuar con
 *
 * la capa de persistencia de las circulares de la aplicación.
 */
@Service
public class CircularService implements CircularServiceInterface {

    /**
     * Inyección de dependencias del DAO de circulares.
     */
    @Autowired
    private CircularDAO circular;

    /**
     *
     * Método que devuelve una lista con todas las circulares guardadas en la
     * base de datos.
     *
     * @return lista de objetos Circular con todas las circulares guardadas en
     * la base de datos.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Circular> listarCirculars() {
        return (List<Circular>) circular.findAll();
    }

    /**
     *
     * Método que añade una nueva circular a la base de datos.
     *
     * @param circular objeto Circular que se desea añadir a la base de datos.
     */
    @Override
    @Transactional
    public void afegirCirculars(Circular circular) {
        this.circular.save(circular);
    }

    /**
     * Método que elimina una circular de la base de datos.
     *
     * @param circular objeto Circular que se desea eliminar de la base de
     * datos.
     */
    @Override
    @Transactional
    public void eliminarCirculars(Circular circular) {
        this.circular.delete(circular);
    }

    /**
     * Método que busca una circular en la base de datos a partir de su
     * identificador.
     *
     * @param circular objeto Circular que contiene el identificador de la
     * circular que se desea buscar.
     * @return objeto Circular con la circular encontrada en la base de datos.
     * Si no se encuentra, devuelve null.
     */
    @Override
    @Transactional(readOnly = true)
    public Circular cercarCirculars(Circular circular) {
        return this.circular.findById(circular.getIdcircular()).orElse(null);

    }

}
