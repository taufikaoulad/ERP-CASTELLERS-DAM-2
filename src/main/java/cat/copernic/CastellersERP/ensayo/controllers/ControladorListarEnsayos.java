/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.ensayo.controllers;

import cat.copernic.CastellersERP.DAO.EnsayoDAO;
import cat.copernic.CastellersERP.DAO.EventoDAO;
import cat.copernic.CastellersERP.model.Ensayo;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.Date;
import java.lang.String;
import java.util.List;
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
public class ControladorListarEnsayos {
    
    @Autowired
    private EnsayoDAO ensayoDAO;
    
    @GetMapping("/listarEnsayos")
    public String inicio(Model model) {
        
        //ArrayList de Ensayo creada.
        //List<Ensayo> ensayos = new ArrayList();
        
        //Utilizamos el método findAll desde un objeto de tipo EnsayoDAO,
        //luego en el bucle forEach añadimos a la lista cada objeto con el "add".
        var ensayos = ensayoDAO.findAll();
        
        //Asociamos el nombre del ArrayList puesto en el html, a la List de ensayos creada en el controller.
        model.addAttribute("ensayos", ensayos);
        
        return "ensayo/ListarEnsayos";
    }
}
