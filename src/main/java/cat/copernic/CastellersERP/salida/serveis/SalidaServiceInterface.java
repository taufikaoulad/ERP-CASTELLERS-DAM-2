/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.salida.serveis;

import cat.copernic.CastellersERP.model.Salida;
import cat.copernic.CastellersERP.model.Usuario;
import java.util.List;

/**
 *
 * @author Taufik
 */

//Interface on definirem els mètodes CRUD personalitzats per la nostra aplicació
public interface SalidaServiceInterface {
    
    public List<Salida> llistarSalidas();//Mètode que implementarem per llistar salidas
    
    public void afegirSalida(Salida salida); //Mètode que implementarem per afegir un salida
    
    public void eliminarSalida(Salida salida); //Mètode que implementarem per eliminar un salida
    
    public Salida cercarSalida(Salida salida); //Mètode que implementarem per cercar un salida
    
    public Salida carcarSalidaPorId(int idevento);
}
