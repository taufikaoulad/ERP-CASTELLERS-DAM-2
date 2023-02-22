/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.general.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Taufik
 */
@Controller
public class ControladorConfirmarEliminacion {
    //(localhost:8080/paginaconfirmarEliminacion)
    @GetMapping("/paginaconfirmarEliminacion")
    public String inici(){ 
        //log.info("Executant el controlador Spring MVC"); //Afegeix al log el missatge passat com a paràmetre.
        return "general/confirmarEliminacion";
    }
}
