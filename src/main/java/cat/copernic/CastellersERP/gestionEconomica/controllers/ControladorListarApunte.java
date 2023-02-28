/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.gestionEconomica.controllers;

import cat.copernic.CastellersERP.DAO.ApunteDAO;
import cat.copernic.CastellersERP.model.Apunte;
import java.util.ArrayList;
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
        
        return "gestionEconomica/ListarApuntes"; 
    }
    
}
