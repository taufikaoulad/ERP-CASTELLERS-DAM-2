package cat.copernic.CastellersERP.general.serveis;

import cat.copernic.CastellersERP.DAO.ModuloTipoUsuarioDAO;
import cat.copernic.CastellersERP.model.ModuloTipoUsuario;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ModuloTipoUsuarioService implements ModuloTipoUsuarioServiceInterface {

    @Autowired
    private ModuloTipoUsuarioDAO modulotipousuarioDAO;

    /**
     * Retorna una lista con todos los usuarios de módulos.
     *
     * @return lista de usuarios de módulos.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ModuloTipoUsuario> llistarModuloTipoUsuarios() {
        return (List<ModuloTipoUsuario>) modulotipousuarioDAO.findAll();
    }

    /**
     * Añade un usuario de módulo a la base de datos.
     *
     * @param modulotipousuario usuario de módulo que se desea añadir.
     */
    @Override
    @Transactional
    public void afegirModuloTipoUsuario(ModuloTipoUsuario modulotipousuario) {
        this.modulotipousuarioDAO.save(modulotipousuario);
    }

    /**
     * Elimina un usuario de módulo de la base de datos.
     *
     * @param modulotipousuario usuario de módulo que se desea eliminar.
     */
    @Override
    @Transactional
    public void eliminarModuloTipoUsuario(ModuloTipoUsuario modulotipousuario) {
        this.modulotipousuarioDAO.delete(modulotipousuario);
    }

    /**
     * Busca un usuario de módulo en la base de datos.
     *
     * @param modulotipousuario usuario de módulo que se desea buscar.
     * @return el usuario de módulo encontrado o null si no existe.
     */
    @Override
    @Transactional(readOnly = true)
    public ModuloTipoUsuario cercarModuloTipoUsuario(ModuloTipoUsuario modulotipousuario) {
        return this.modulotipousuarioDAO.findById(modulotipousuario.getIdmtu()).orElse(null);
    }
}
