/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.gestionEconomica.controllers;

import cat.copernic.CastellersERP.gestionEconomica.serveis.ApunteService;
import cat.copernic.CastellersERP.model.Apunte;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author bhugo
 */

@Controller
public class ControladorListarApunte {
    
    /*
    si una clase necesita una instancia de otro componente, simplemente se puede declarar un campo con la anotación 
    "@Autowired" y Spring Boot se encargará de crear una instancia y asignarla al campo automáticamente.
    */
    @Autowired
    private ApunteService apunteService;
    
    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User username){ 
        
        model.addAttribute("apuntes", apunteService.listarApuntes());
        
        return "gestionEconomica/ListarApuntes"; 
    }
    
    @GetMapping("/formularioApunte")
    public String crearFormularioApunte(Apunte apunte){ 
        
        return "gestionEconomica/AnadirApunte"; 
    }
    
    @PostMapping("/guardarApunte")
    public String guardarApunte(@Valid Apunte apunte, Errors errors) {
        
        if (errors.hasErrors()) {
            return "gestionEconomica/AnadirApunte";
        }
        
        apunteService.afegirApuntes(apunte);
        
        return "redirect:/gestionEconomica";
    }
    
    @GetMapping("/editarApunte/{idapuntecontable}")
    public String editar(Apunte apunte, Model model) {

        /*Cerquem el gos passat per paràmetre, al qual li correspón l'idgos de @GetMapping mitjançant 
         *el mètode cercarGos de la capa de servei.*/
        model.addAttribute("apunte", apunteService.cercarApunte(apunte));

        return "gestionEconomica/AnadirApunte"; //Retorna la pàgina amb el formulari de les dades del gos
    }
    
    @GetMapping("/eliminarApunte/{idapuntecontable}") 
    public String eliminar(Apunte apunte) {

        /*Eliminem el gos passat per paràmetre, al qual li correspón l'idgos de @GetMapping mitjançant 
         *el mètode eliminarGos de la capa de servei.*/
        apunteService.eliminarApunte(apunte);
        
        return "redirect:/gestionEconomica"; //Retornem a la pàgina inicial dels gossos mitjançant redirect
    }  
}
