/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.general.serveis;

import cat.copernic.CastellersERP.DAO.EventoCastilloDAO;
import cat.copernic.CastellersERP.model.Ensayo;
import cat.copernic.CastellersERP.model.EventoCastillo;
import java.util.List;

/**
 *
 * @author capy
 */
public interface EventoCastilloServiceInterface {
    
    public List<EventoCastillo> listarEventoCastillo();
    
    public void añadirEventoCastillo(EventoCastillo eventoCastillo);
    
    public void eliminarEventoCastillo(EventoCastillo eventoCastillo);
    
    public EventoCastillo buscarEventoCastillo(EventoCastillo eventoCastillo);
}
