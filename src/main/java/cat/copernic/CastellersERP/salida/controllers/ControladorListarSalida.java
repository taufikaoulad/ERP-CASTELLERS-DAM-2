/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.salida.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Taufik
 */

@Controller
public class ControladorListarSalida {
    
    //(localhost:8080/paginalistarSalidas)
    @GetMapping("/paginalistarSalidas")
    public String inici(){
        return "salida/listarSalidas"; //Retorna la pàgina iniciDinamic
    }
}
