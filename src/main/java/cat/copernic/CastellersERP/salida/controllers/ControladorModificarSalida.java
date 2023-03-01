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
public class ControladorModificarSalida {
    //(localhost:8080/paginamodificarSalida)
    @GetMapping("/paginamodificarSalida")
    public String inici(Model model){
        
        model.addAttribute("ModificarSalida", "Modificar Salida");
        model.addAttribute("Nombre", "Nombre");
        model.addAttribute("Fecha", "Fecha");
        model.addAttribute("Ubicacion", "Ubicación");
        model.addAttribute("Paradita", "Paradita");
        model.addAttribute("Cancelar", "Cancelar");
        model.addAttribute("ConfirmarModificacion", "Confirmar Modificación");
        
        return "salida/modificarSalida"; //Retorna la pàgina iniciDinamic
    }
}
