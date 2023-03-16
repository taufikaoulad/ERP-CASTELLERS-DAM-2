/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.castillo.controllers;

import cat.copernic.CastellersERP.model.Castillo;
import cat.copernic.CastellersERP.castillo.serveis.CastilloService;
import cat.copernic.CastellersERP.model.Salida;
import cat.copernic.CastellersERP.salida.serveis.SalidaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author bhugo
 */
@Controller
public class ControladorVistaCastillos {

    @Autowired    
    private CastilloService castilloService;
    
    @Autowired    
    private SalidaService salidaService;

    @GetMapping("/vistaCastillos")
    public String inici(Model model) {

        model.addAttribute("castillos", castilloService.listarCastillos());
        
        return "castillo/vistaCastillos";
    }
    
    @GetMapping("/formularioCastillo")
    public String crearFormularioCastillo(Castillo castillo) {

        return "castillo/modificarCastillo";
    }
    
    @PostMapping("/guardarCastillo")
    public String guardarCastillo(@Valid Castillo castillo, Errors errors) {

        if(errors.hasErrors()){ //Si s'han produït errors...
             return "castillo/modificarCastillo"; //Mostrem la pàgina del formulari
        }
        
        castilloService.agregarCastillo(castillo);

        return "redirect:/vistaCastillos";
    }
      
    @GetMapping("/editarCastillo/{idCastillo}")
    public String editar(Castillo castillo, Model model) {

        model.addAttribute("castillo", castilloService.buscarCastillo(castillo));

        return "castillo/modificarCastillo";
    }
    
    @GetMapping("/eliminarCastillo/{idCastillo}") 
    public String eliminar(Castillo castillo) {

        castilloService.eliminarCastillo(castillo);
        
        return "redirect:/vistaCastillos";
    }
    
    
}
