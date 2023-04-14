/**

Interface DAO para acceder a los datos de la entidad Modulo en la base de datos.
Extiende de CrudRepository para poder usar métodos CRUD básicos.
*/
package cat.copernic.CastellersERP.DAO;
import cat.copernic.CastellersERP.model.Modulo;
import org.springframework.data.repository.CrudRepository;

public interface ModuloDAO extends CrudRepository<Modulo,Integer>{

}