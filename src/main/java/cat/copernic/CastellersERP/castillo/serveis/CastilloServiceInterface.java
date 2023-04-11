/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cat.copernic.CastellersERP.castillo.serveis;

import cat.copernic.CastellersERP.model.Castillo;
import java.util.List;

/**
 * @author bhugo
 */
public interface CastilloServiceInterface {

    /**
     *
     * Devuelve una lista de todos los castillos almacenados en la base de
     * datos.
     *
     * @return una lista de objetos Castillo
     */
    public List<Castillo> listarCastillos();

    /**
     *
     * Agrega un nuevo castillo a la base de datos.
     *
     * @param castillo el objeto Castillo a agregar
     */
    public void agregarCastillo(Castillo castillo);

    /**
     *
     * Elimina un castillo de la base de datos.
     *
     * @param castillo el objeto Castillo a eliminar
     */
    public void eliminarCastillo(Castillo castillo);

    /**
     *
     * Busca un castillo en la base de datos en función de sus propiedades.
     *
     * @param castillo el objeto Castillo con las propiedades a buscar
     * @return el objeto Castillo que cumple con los criterios de búsqueda
     */
    public Castillo buscarCastillo(Castillo castillo);

    /**
     *
     * Busca un castillo en la base de datos en función de su ID.
     *
     * @param idCastillo el ID del castillo a buscar
     * @return el objeto Castillo con el ID proporcionado
     */
    public Castillo buscarCastilloPorId(int idCastillo);
}
