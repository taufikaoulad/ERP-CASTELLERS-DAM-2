/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.salida.controllers;

import cat.copernic.CastellersERP.model.Castillo;
import cat.copernic.CastellersERP.model.Salida;
import cat.copernic.CastellersERP.model.Usuario;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Taufik
 */

@Controller
public class ControladorDetalleCastilloSalida {
    //(localhost:8080/paginadetalleCastilloSalida)
    @GetMapping("/paginadetalleCastilloSalida")
    public String inici(Model model){
        
        var castillo = new Castillo();
        castillo.setNombre("4x4");
        var castillo1 = new Castillo();
        castillo1.setNombre("3x4");
        var castillo2 = new Castillo();
        castillo2.setNombre("5x6");
        
        var castillos = new ArrayList<Castillo>();
        castillos.add(castillo);
        castillos.add(castillo1);
        castillos.add(castillo2);
        
        model.addAttribute("castillos", castillos);
        
        
        model.addAttribute("DetalleCastillo", "Detalle Castillo");
        model.addAttribute("Castillo", "Castillo");
        model.addAttribute("Volver", "Volver");
                
        return "salida/detalleCastilloSalida"; //Retorna la pàgina iniciDinamic
    }
}
