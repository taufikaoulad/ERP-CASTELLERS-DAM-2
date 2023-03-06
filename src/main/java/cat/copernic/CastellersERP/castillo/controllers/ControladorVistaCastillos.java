/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.castillo.controllers;

import cat.copernic.CastellersERP.DAO.CastilloDAO;
import cat.copernic.CastellersERP.model.Castillo;
import cat.copernic.CastellersERP.castillo.serveis.CastilloService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author bhugo
 */
@Controller
public class ControladorVistaCastillos {

    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de GosService al controlador    
    private CastilloService castilloService;

    @GetMapping("/vistaCastillos")
    public String inici(Model model) {
        //listarCastillos() devuelve el listado de objetos castillo guardados en la tabla de castillos de la BBDD 
        model.addAttribute("castillos", castilloService.listarCastillos());
        
        return "castillo/vistaCastillos";
    }
    
    @GetMapping("/modificarCastillo") //URL a la pàgina amb el formulari de les dades del gos
    public String crearFormularioCastillo(Castillo castillo) {

        return "castillo/modificarCastillo"; //Retorna la pàgina on es mostrarà el formulari de les dades dels gos
    }
    
    @PostMapping("/guardarCastillo") //action=guardarGos
    public String guardarGos(Castillo castillo) {

        castilloService.agregarCastillo(castillo); //Afegim el gos passat per paràmetre a la base de dades

        return "redirect:/castillos"; //Retornem a la pàgina inicial dels gossos mitjançant redirect
    }
    
    @GetMapping("/editar/{idcastillo}")
    public String editar(Castillo castillo, Model model) {

        /*Cerquem el gos passat per paràmetre, al qual li correspón l'idgos de @GetMapping mitjançant 
         *el mètode cercarGos de la capa de servei.*/
        model.addAttribute("castillo", castilloService.buscarCastillo(castillo));

        return "castillo/modificarCastillo"; //Retorna la pàgina amb el formulari de les dades del gos
    }
    
    @GetMapping("/eliminar/{idcastillo}") 
    public String eliminar(Castillo castillo) {

        /*Eliminem el gos passat per paràmetre, al qual li correspón l'idgos de @GetMapping mitjançant 
         *el mètode eliminarGos de la capa de servei.*/
        castilloService.eliminarCastillo(castillo);
        
        return "redirect:/castillos"; //Retornem a la pàgina inicial dels gossos mitjançant redirect
    }
    
    
}
