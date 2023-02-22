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
public class ControladorDetalleCastilloSalida {
    //(localhost:8080/paginadetalleCastilloSalida)
    @GetMapping("/paginadetalleCastilloSalida")
    public String inici(){
        return "salida/detalleCastilloSalida"; //Retorna la pàgina iniciDinamic
    }
}
