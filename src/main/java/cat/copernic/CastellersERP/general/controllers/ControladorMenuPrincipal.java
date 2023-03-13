/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.general.controllers;

import cat.copernic.CastellersERP.general.serveis.CircularService;
import cat.copernic.CastellersERP.model.Circular;
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
public class ControladorMenuPrincipal { 
    
    @Autowired
    private CircularService circularService;
    
    @GetMapping("/menuPrincipal")
    public String inicio(Model model){ 
        
        model.addAttribute("circulares", circularService.listarCirculars());
        
        return "general/Inicio"; 
    }
    
    @GetMapping("/formularioCircular")
    public String crearFormularioApunte(Circular circular){ 
        
        return "general/AnadirCircular"; 
    }
    
    @PostMapping("/guardarCircular")
    public String guardarApunte(Circular circular) {
        
        circularService.afegirCirculars(circular);
        
        return "redirect:/menuPrincipal";
    }
    
    @GetMapping("/editarCircular/{idcircular}")
    public String editar(Circular circular, Model model) {

        /*Cerquem el gos passat per paràmetre, al qual li correspón l'idgos de @GetMapping mitjançant 
         *el mètode cercarGos de la capa de servei.*/
        model.addAttribute("circular", circularService.cercarCirculars(circular));

        return "general/AnadirCircular"; //Retorna la pàgina amb el formulari de les dades del gos
    }
    
    @GetMapping("/eliminarCircular/{idcircular}") 
    public String eliminar(Circular circular) {

        /*Eliminem el gos passat per paràmetre, al qual li correspón l'idgos de @GetMapping mitjançant 
         *el mètode eliminarGos de la capa de servei.*/
        circularService.eliminarCirculars(circular);
        
        return "redirect:/menuPrincipal"; //Retornem a la pàgina inicial dels gossos mitjançant redirect
    }
}
