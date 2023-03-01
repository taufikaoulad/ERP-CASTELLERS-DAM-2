/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.salida.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Taufik
 */

@Controller
public class ControladorAnadirSalida {
    
    //(localhost:8080/paginaanadirSalida)
    @GetMapping("/paginaanadirSalida")
    public String inici(Model model){
        
        model.addAttribute("CrearSalida", "Crear Salida");
        model.addAttribute("Nombre", "Nombre");
        model.addAttribute("Fecha", "Fecha");
        model.addAttribute("Ubicacion", "Ubicación");
        model.addAttribute("Paradita", "Paradita");
        model.addAttribute("Cancelar", "Cancelar");
        model.addAttribute("AnadirSalida", "Añadir Salida");
        
        return "salida/anadirSalida"; //Retorna la pàgina iniciDinamic
    }
}
