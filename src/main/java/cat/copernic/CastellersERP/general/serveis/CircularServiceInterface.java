/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.general.serveis;

import cat.copernic.CastellersERP.model.Circular;
import java.util.List;

/**
 *
 * @author pablogomez
 */
public interface CircularServiceInterface {
    
    public List<Circular> listarCirculars();
    
    public void afegirCirculars(Circular circular);
    
    public void eliminarCirculars(Circular circular);   
    
    public Circular cercarCirculars(Circular circular);
}
