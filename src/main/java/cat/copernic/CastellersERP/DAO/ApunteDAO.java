/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.DAO;

import cat.copernic.CastellersERP.model.Apunte;
import org.springframework.data.repository.CrudRepository;

/**
 * La interfaz ApunteDAO define un repositorio de acceso a datos que maneja
 * objetos de tipo Apunte. Extiende CrudRepository, lo que proporciona métodos
 * CRUD (crear, leer, actualizar y eliminar) para Apunte sin necesidad de
 * implementarlos manualmente.
 *
 * @author pablogomez
 * @see org.springframework.data.repository.CrudRepository
 * entidad
 * @author pablogomez
 */
public interface ApunteDAO extends CrudRepository<Apunte, Integer> {

}
/*Los tipos de clases que en nuestro caso utiliza CrudRepository son Apunte e integer, Apunte hace referencia a
  *la clase que define la entidad con la que trabajaremos e Integer el tipo del atributo que define la clave
  *primaría de esta entidad.
 */
