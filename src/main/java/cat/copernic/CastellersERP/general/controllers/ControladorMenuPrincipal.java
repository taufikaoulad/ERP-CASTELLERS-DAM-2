/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.general.controllers;

import cat.copernic.CastellersERP.model.Apunte;
import cat.copernic.CastellersERP.model.Circular;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 *
 * @author bhugo
 */

@Controller
public class ControladorMenuPrincipal { 
    
    @GetMapping("/menuPrincipal")
    public String inicio(Model model){ 
        
        var circular = new Circular();
        circular.setTitulo("Pagameent");
        circular.setMensaje("Recordad que este viernes es el ultimo dia para realizar el pago de la mensualidad");
        
        var circular1 = new Circular();
        circular1.setTitulo("Camisetas");
        circular1.setMensaje("");
        
        var circulares = new ArrayList<Circular>();
        circulares.add(circular);
        circulares.add(circular1);
        
        model.addAttribute("circulares", circulares);
        
        return "general/Inicio"; 
    }
    
}
