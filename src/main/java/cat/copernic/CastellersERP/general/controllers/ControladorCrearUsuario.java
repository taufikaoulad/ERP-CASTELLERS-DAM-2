/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.general.controllers;

import cat.copernic.CastellersERP.general.serveis.UsuarioService;
import cat.copernic.CastellersERP.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author bhugo
 */
@Controller
public class ControladorCrearUsuario {
    
    //@Autowired //Anotació que injecta tots els mètodes i possibles dependències de SalidaDAO al controlador
    //private UsuarioService usuarioService;
    //(localhost:8080/paginacrearUsuario)
    
    @GetMapping("/paginacrearUsuario")
    public String inici(Model model){ 
        
        model.addAttribute("Cancelar", "Cancelar");
        model.addAttribute("AnadirUsuario", "Añadir Usuario");
        return "general/crearUsuario";
    }
    
    /*@GetMapping("/crearUsuario")
    public String crearFormulariGos(Usuario usuario) {
        
        return "crearUsuario"; //Retorna la pàgina on es mostrarà el formulari de les dades dels gos
    }*/
    
    
}
