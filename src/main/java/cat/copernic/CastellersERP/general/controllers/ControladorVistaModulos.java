/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.general.controllers;

import cat.copernic.CastellersERP.model.Modulo;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author bhugo
 */
@Controller
public class ControladorVistaModulos {
    @GetMapping("/vistaModulos")
    public String inici(Model model){ 
        
        //Modulo1
        var modulo1 = new Modulo();
        modulo1.setNombre("Salidas");
        modulo1.setActivo(true);
        
        //Modulo2
        var modulo2 = new Modulo();
        modulo2.setNombre("Ensayos");
        modulo2.setActivo(true);
        
        //Modulo3
        var modulo3 = new Modulo();
        modulo3.setNombre("Gestion Economica");
        modulo3.setActivo(true);
        
        //Modulo4
        var modulo4 = new Modulo();
        modulo4.setNombre("Castillos");
        modulo4.setActivo(true);
        
        //Guardamos los modulos en un ArraayList
        var modulos = new ArrayList<Modulo>();
        modulos.add(modulo1);
        modulos.add(modulo2);
        modulos.add(modulo3);
        modulos.add(modulo4);
        
        //Enviamos el ArrayList
        model.addAttribute("modulos", modulos);
        
        return "general/vistaModulos";
    }
}
