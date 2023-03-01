/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.gestionEconomica.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author pablogomez
 */
@Controller

public class ControladorModificarApunte {
    
    @GetMapping("/ModificarApunte")
    public String inicio(Model model){ 
        
        model.addAttribute("titulo", "Modificar Apunte");
        
        model.addAttribute("Concepto", "Concepto: ");
        model.addAttribute("Precio", "Precio: ");
        model.addAttribute("Tipo", "Tipo: ");
        
        
        return "gestionEconomica/ModificarApunte"; 
    }
    
}
