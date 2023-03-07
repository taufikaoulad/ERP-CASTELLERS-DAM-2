/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cat.copernic.CastellersERP.DAO;

import cat.copernic.CastellersERP.model.Castillo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author bhugo
 */
public interface CastilloDAO extends JpaRepository<Castillo,Integer>{
    
}
