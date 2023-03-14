/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cat.copernic.CastellersERP.general.serveis;

import cat.copernic.CastellersERP.model.Modulo;
import java.util.List;

/**
 *
 * @author bhugo
 */
public interface ModuloServiceInterface {
    public List<Modulo> llistarModulo();
    
    public void afegirModulo(Modulo modulo);
    
    public void eliminarModulo(Modulo modulo); 
    
    public Modulo cercarModulos (Modulo modulo);
}
