/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.gestionEconomica.controllers;

import cat.copernic.CastellersERP.model.Apunte;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author bhugo
 */

@Controller
public class ControladorListarApunte {
    
    @GetMapping("/gestionEconomica")
    public String inicio(Model model){ 
        
        var apunte = new Apunte();
        apunte.setConcepto("Camisetas");
        apunte.setPrecio(500);
        apunte.setTipo("Gasto");
        
        var apunte1 = new Apunte();
        apunte1.setConcepto("Mensualidad");
        apunte1.setPrecio(1000);
        apunte1.setTipo("Ingreso");
        
        var apuntes = new ArrayList<Apunte>();
        apuntes.add(apunte);
        apuntes.add(apunte1);
        
        model.addAttribute("apuntes",apuntes);
        
        
        return "gestionEconomica/ListarApuntes"; 
    }
    
}
