package cat.copernic.CastellersERP.general.serveis;

import cat.copernic.CastellersERP.model.Modulo;
import java.util.List;

public interface ModuloServiceInterface {

    /**
     * Método que implementaremos para listar los módulos.
     *
     * @return Una lista de objetos Modulo.
     */
    public List<Modulo> llistarModulo();

    /**
     * Método que implementaremos para agregar un módulo.
     *
     * @param modulo Objeto Modulo a agregar.
     */
    public void afegirModulo(Modulo modulo);

    /**
     * Método que implementaremos para eliminar un módulo.
     *
     * @param modulo Objeto Modulo a eliminar.
     */
    public void eliminarModulo(Modulo modulo);

    /**
     * Método que implementaremos para buscar un módulo.
     *
     * @param modulo Objeto Modulo a buscar.
     * @return El objeto Modulo si se encuentra, null en caso contrario.
     */
    public Modulo cercarModulos(Modulo modulo);
}
