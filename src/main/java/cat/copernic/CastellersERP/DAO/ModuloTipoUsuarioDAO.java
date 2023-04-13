/**

Interfaz que define las operaciones de acceso a datos para la entidad ModuloTipoUsuario.
Extiende la interfaz CrudRepository de Spring Data.
*/
package cat.copernic.CastellersERP.DAO;
import cat.copernic.CastellersERP.model.ModuloTipoUsuario;
import org.springframework.data.repository.CrudRepository;

public interface ModuloTipoUsuarioDAO extends CrudRepository<ModuloTipoUsuario,Integer>{

}