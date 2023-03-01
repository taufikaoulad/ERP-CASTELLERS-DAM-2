/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.gestionEconomica.controllers;

import cat.copernic.CastellersERP.DAO.ApunteDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author bhugo
 */

@Controller
@Slf4j
public class ControladorListarApunte {
    
    @Autowired
    private ApunteDAO apunteDAO;
    
    @GetMapping("/gestionEconomica")
    public String inicio(Model model){ 
        
        model.addAttribute("apuntes", apunteDAO.findAll());
        
        model.addAttribute("titulo", "Listar Apuntes");
        
        model.addAttribute("Menu", "Menu");
        model.addAttribute("Ensayo", "Ensayo");
        model.addAttribute("Salidas", "Salidas");
        model.addAttribute("Castillos", "Castillos");
        model.addAttribute("Administracion", "Administracion");
        
        model.addAttribute("Concepto", "Concepto");
        model.addAttribute("Precio", "Precio");
        model.addAttribute("Tipo", "Tipo");
        model.addAttribute("Acciones", "Acciones");
        
        

        
        return "gestionEconomica/ListarApuntes"; 
    }
    
}
