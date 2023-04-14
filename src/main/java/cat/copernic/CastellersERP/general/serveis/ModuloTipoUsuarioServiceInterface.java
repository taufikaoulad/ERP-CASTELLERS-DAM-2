package cat.copernic.CastellersERP.general.serveis;

import cat.copernic.CastellersERP.model.ModuloTipoUsuario;
import java.util.List;

public interface ModuloTipoUsuarioServiceInterface {

    /**
     * Método que retorna una lista con todos los registros de la entidad
     * ModuloTipoUsuario en la base de datos.
     *
     * @return Lista de ModuloTipoUsuario.
     */
    public List<ModuloTipoUsuario> llistarModuloTipoUsuarios();

    /**
     * Método que permite agregar un registro de la entidad ModuloTipoUsuario en
     * la base de datos.
     *
     * @param modulotipousuario Registro de ModuloTipoUsuario a agregar.
     */
    public void afegirModuloTipoUsuario(ModuloTipoUsuario modulotipousuario);

    /**
     * Método que permite eliminar un registro de la entidad ModuloTipoUsuario
     * de la base de datos.
     *
     * @param modulotipousuario Registro de ModuloTipoUsuario a eliminar.
     */
    public void eliminarModuloTipoUsuario(ModuloTipoUsuario modulotipousuario);

    /**
     * Método que permite buscar un registro de la entidad ModuloTipoUsuario en
     * la base de datos.
     *
     * @param modulotipousuario Registro de ModuloTipoUsuario a buscar.
     * @return Registro de ModuloTipoUsuario encontrado.
     */
    public ModuloTipoUsuario cercarModuloTipoUsuario(ModuloTipoUsuario modulotipousuario);

}
