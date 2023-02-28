/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.ensayo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author capy
 */
@Controller
public class ControladorCrearEnsayo {
    @GetMapping("/crearEnsayo")
    public String inicio(){
        
        return "ensayo/CrearEnsayo";
    }
}
