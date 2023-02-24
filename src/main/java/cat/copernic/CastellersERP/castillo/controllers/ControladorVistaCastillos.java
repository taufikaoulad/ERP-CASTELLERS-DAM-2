/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.castillo.controllers;

import cat.copernic.CastellersERP.model.Castillo;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author bhugo
 */
@Controller
public class ControladorVistaCastillos {
    @GetMapping("/vistaCastillos")
    public String inici(Model model){ 
        
        //Creem la variable gos de tipus Gos.
        var castillo1 = new Castillo();
        castillo1.setPisos(8);
        castillo1.setPersonesXPiso(2);
        castillo1.setNombre(castillo1.getPisos() + "x" + castillo1.getPersonesXPiso());
        castillo1.setNcPinya(2);
        castillo1.setNcTronc(2);
        castillo1.setAixecat(true);
        castillo1.setAgulla(true);
        
        //Crear castillo2.
        var castillo2 = new Castillo();
        castillo2.setPisos(3);
        castillo2.setPersonesXPiso(4);
        castillo2.setNombre(castillo2.getPisos() + "x" + castillo2.getPersonesXPiso());
        castillo2.setNcPinya(2);
        castillo2.setNcTronc(2);
        castillo2.setAixecat(true);
        castillo2.setAgulla(true);
        
        //Guardamos los castillos en un ArraayList 
        var castillos = new ArrayList<Castillo>();
        castillos.add(castillo1);
        castillos.add(castillo2);
        
        //Enviamos el ArrayList
        model.addAttribute("castillos", castillos);
        
        
        return "castillo/vistaCastillos";
    }
}
