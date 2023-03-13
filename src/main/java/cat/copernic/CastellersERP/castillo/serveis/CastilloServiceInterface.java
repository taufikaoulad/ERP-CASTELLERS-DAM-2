/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cat.copernic.CastellersERP.castillo.serveis;

import cat.copernic.CastellersERP.model.Castillo;
import java.util.List;

/**
 *
 * @author bhugo
 */
public interface CastilloServiceInterface {
    
    public List<Castillo> listarCastillos();
    
    public void agregarCastillo(Castillo castillo);
    
    public void eliminarCastillo(Castillo castillo);
    
    public Castillo buscarCastillo(Castillo castillo);
    
}
