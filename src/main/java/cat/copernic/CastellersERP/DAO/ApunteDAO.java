/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.DAO;

import cat.copernic.CastellersERP.model.Apunte;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author pablogomez
 */

/*Los tipos de clases que en nuestro caso utiliza CrudRepository son Apunte e integer, Apunte hace referencia a
  *la clase que define la entidad con la que trabajaremos e Integer el tipo del atributo que define la clave
  *primaría de esta entidad.
*/
public interface ApunteDAO extends CrudRepository<Apunte, Integer>{
    
}
