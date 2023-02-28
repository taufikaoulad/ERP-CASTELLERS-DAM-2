/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.ensayo.controllers;

import cat.copernic.CastellersERP.model.Ensayo;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author capy
 */
@Controller
public class ControladorModificarEnsayo {
    @GetMapping("/modificarEnsayo")
    public String inicio(Model model){
        
        //Creamos Objeto Ensayo
        var ensayo = new Ensayo();

        //Añadimos valores a los atributos
        ensayo.setNombreEvento("Ensayo_1");

        Date date = new Date();
        ensayo.setFechaEvento(date);

        ensayo.setUbicacionEvento("Terrassa");

        ensayo.setDuracion("4h");

        //Creamos ArrayList de ensayos
        ArrayList<Ensayo> ensayos = new ArrayList();
        //Añadimos el objeto ensayo al ArrayList ensayos
        ensayos.add(ensayo);

        //Asociamos el nombre del ArrayList puesta en el html, con el ArrayList de ensayos creada en el controller
        model.addAttribute("ensayos", ensayos);
        
        return "ensayo/ModificarEnsayo";
    }
}
