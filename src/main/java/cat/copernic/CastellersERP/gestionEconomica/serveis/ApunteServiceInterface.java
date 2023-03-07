/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.gestionEconomica.serveis;

import cat.copernic.CastellersERP.model.Apunte;
import java.util.List;

/**
 *
 * @author pablogomez
 */
public interface ApunteServiceInterface {
    
    public List<Apunte> listarApuntes();
    
    public void afegirApuntes(Apunte apunte);
    
    public void eliminarApunte(Apunte apunte);   
    
    public Apunte cercarApunte(Apunte apunte);
}
