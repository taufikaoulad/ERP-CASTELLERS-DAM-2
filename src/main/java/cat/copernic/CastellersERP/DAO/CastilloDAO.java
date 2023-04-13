/**
 *
 * Interfaz que extiende de JpaRepository para acceder a las operaciones CRUD sobre la entidad Castillo en la base de datos.
 */
package cat.copernic.CastellersERP.DAO;

import cat.copernic.CastellersERP.model.Castillo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CastilloDAO extends JpaRepository<Castillo, Integer> {

}
