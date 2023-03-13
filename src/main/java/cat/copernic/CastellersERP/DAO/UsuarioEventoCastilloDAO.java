/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.DAO;

import cat.copernic.CastellersERP.model.EventoCastillo;
import cat.copernic.CastellersERP.model.UsuarioEventoCastillo;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author capy
 */
public interface UsuarioEventoCastilloDAO extends CrudRepository<UsuarioEventoCastillo, Integer>{}