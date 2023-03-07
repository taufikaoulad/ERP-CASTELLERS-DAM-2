/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.ensayo.controllers;

import cat.copernic.CastellersERP.DAO.EnsayoDAO;
import cat.copernic.CastellersERP.DAO.EventoDAO;
import cat.copernic.CastellersERP.ensayo.services.EnsayoService;
import cat.copernic.CastellersERP.model.Castillo;
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
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author bhugo
 */
@Controller
@Slf4j
public class ControladorEnsayos {
    
    //Instanciamos un objeto de tipo EnsayoDAO para utilizar los métodos que hereda del CrudRepository.
    @Autowired
    private EnsayoService ensayoService;
    
    @GetMapping("/ensayos")
    public String inicio(Model model) {
        
        model.addAttribute("Evento", ensayoService.listarEnsayos());
        
        return "ensayo/ListarEnsayos";
    }
    
    @GetMapping("/FormularioEnsayo") //URL a la pàgina amb el formulari de les dades del gos
    public String crearFormularioEnsayo(Ensayo ensayo) {

        return "ensayo/FormularioEnsayo"; //Retorna la pàgina on es mostrarà el formulari de les dades dels gos
    }
    
    @PostMapping("/guardarEnsayo") //action=guardarGos
    public String guardarEnsayo(Ensayo ensayo) {

        ensayoService.añadirEnsayo(ensayo); //Afegim el gos passat per paràmetre a la base de dades

        return "redirect:/ensayos"; //Retornem a la pàgina inicial dels gossos mitjançant redirect
    }
    
    @GetMapping("/editarEnsayo/{idevento}")
    public String editarEnsayo(Ensayo ensayo, Model model){
        
        model.addAttribute("ensayo", ensayoService.buscarEnsayo(ensayo));
        
        return "ensayo/FormularioEnsayo";
    }
    
    @GetMapping("/eliminarEnsayo/{idevento}")
    public String eliminarEnsayo(Ensayo ensayo) {

        /*Eliminem el gos passat per paràmetre, al qual li correspón l'idgos de @GetMapping mitjançant 
         *el mètode eliminarGos de la capa de servei.*/
        ensayoService.eliminarEnsayo(ensayo);
        
        return "redirect:/ensayos"; //Retornem a la pàgina inicial dels gossos mitjançant redirect
    }
    
    @GetMapping("/detalleEnsayo/{idevento}")
    public String detalleEnsayo(Model model, Ensayo ensayo){
        
        model.addAttribute("ensayo", ensayoService.buscarEnsayo(ensayo));
        
        model.addAttribute("usuarios", ensayoService.listarEnsayos());
        
        /*
        //Realizamos el mismo proceso con variables de tipo Castillo
        var castillo1 = new Castillo();
        castillo1.setNombre("castillo1");
        var castillo2 = new Castillo();
        castillo2.setNombre("castillo2");
        var castillo3 = new Castillo();
        castillo3.setNombre("castillo3");
        
        ArrayList<Castillo> castillosAsignados = new ArrayList();
        castillosAsignados.add(castillo1);
        castillosAsignados.add(castillo2);
        castillosAsignados.add(castillo3);
        */
        return "ensayo/DetalleEnsayo";
    }
}
