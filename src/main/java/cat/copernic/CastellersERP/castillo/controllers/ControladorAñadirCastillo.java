/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.castillo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author bhugo
 */
@Controller
//@Slf4j //Anotació de Lombok que en permet accedir directament a la variable log
public class ControladorAñadirCastillo {
    @GetMapping("/añadirCastillo")
    public String inici(Model model){ 
        //log.info("Executant el controlador Spring MVC"); //Afegeix al log el missatge passat com a paràmetre.
        model.addAttribute("titulo", "Formulario castillo");
        model.addAttribute("pisos", "Pisos: ");
        model.addAttribute("personaspisos", "Personas por piso: ");
        model.addAttribute("ncpinya", "NCPinya: ");
        model.addAttribute("nctronc", "NCTronc: ");
        model.addAttribute("aixecat", "Aixecat per sota: ");
        model.addAttribute("agulla", "Agulla: ");
        
        return "castillo/añadirCastillo";
    }
}
