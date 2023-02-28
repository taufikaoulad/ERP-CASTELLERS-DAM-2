/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.DAO;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import cat.copernic.CastellersERP.model.Ensayo;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author capy
 */

//EnsayoDAO extenderá de EventoDAO, pasandole por parámetro la entidad ensayo que es a la que pertenece el EnsayoDAO.
//A su vez, EventoDAO extenderá de CrudRepository.
@EnableJpaRepositories
public interface EnsayoDAO  extends EventoDAO<Ensayo>{}