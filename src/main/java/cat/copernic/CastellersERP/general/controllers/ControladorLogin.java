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
public class ControladorLogin {
    //(localhost:8080/paginaLogin)
    @GetMapping("/paginaLogin")
    public String inici(Model model){
        
        model.addAttribute("login", "Login");
        model.addAttribute("email", "Email");
        model.addAttribute("contrasena", "Contraseña");
        model.addAttribute("olvidasteLaContrasena", "¿Olvidaste la contraseña?");
        
        return "general/Login"; //Retorna la pàgina iniciDinamic
    }
}
