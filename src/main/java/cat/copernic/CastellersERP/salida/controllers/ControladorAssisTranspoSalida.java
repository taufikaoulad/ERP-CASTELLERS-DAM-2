/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.salida.controllers;

import cat.copernic.CastellersERP.model.Usuario;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Taufik
 */

@Controller
public class ControladorAssisTranspoSalida {
    //(localhost:8080/paginaassistenciaYTransporteSalida)
    @GetMapping("/paginaassistenciaYTransporteSalida")
    public String inici(Model model){
        

        var usuario = new Usuario();
        usuario.setNombre("Nombre1");
        usuario.setEdat(24);
        usuario.setPeso(75.30f);
        usuario.setAltura(1.82f);
        
        var usuario1 = new Usuario();
        usuario1.setNombre("Nombre2");
        usuario1.setEdat(30);
        usuario1.setPeso(80.27f);
        usuario1.setAltura(1.79f);
        
        var usuarios = new ArrayList<Usuario>();
        usuarios.add(usuario);
        usuarios.add(usuario1);
        
        model.addAttribute("usuarios", usuarios);
        
        return "salida/assistenciaYTransporteSalida"; //Retorna la pàgina iniciDinamic
    }
}
