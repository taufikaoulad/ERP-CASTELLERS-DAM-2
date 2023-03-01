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
 * @author Taufik
 */

@Controller
public class ControladorRecuperarContrasena {
    //(localhost:8080/paginarecuperarContrasena)
    @GetMapping("/paginarecuperarContrasena")
    public String inici(Model model){
        
        model.addAttribute("recuperarContrasena", "Recuperar Contraseña");
        model.addAttribute("email", "Email");
        model.addAttribute("enviar", "Enviar");
        
        return "general/recuperarContrasena"; //Retorna la pàgina iniciDinamic
    }
}
