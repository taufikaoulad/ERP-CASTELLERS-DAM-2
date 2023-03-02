/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.general.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author bhugo
 */
@Controller
public class ControladorModificarModulo {
    @GetMapping("/modificarModulo")
    public String inici(Model model){ 
        //log.info("Executant el controlador Spring MVC"); //Afegeix al log el missatge passat com a paràmetre.
        model.addAttribute("titulo", "Formulario Módulos");
        model.addAttribute("nombre", "Nombre");
        model.addAttribute("tipouser", "Tipo de usuario");
        model.addAttribute("activo", "Activo");
        
        return "general/modificarModulo";
    }
}
