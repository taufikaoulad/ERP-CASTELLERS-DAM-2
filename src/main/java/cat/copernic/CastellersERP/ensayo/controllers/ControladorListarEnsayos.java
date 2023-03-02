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
    
    //Instanciamos un objeto de tipo EnsayoDAO para utilizar los métodos que hereda del CrudRepository.
    @Autowired
    private EnsayoDAO ensayoDAO;
    
    @GetMapping("/listarEnsayos")
    public String inicio(Model model) {
        
        //Creamos las variables necesarias para los titulos.
        var nEnsayo = "Nombre Ensayo";
        var fecha = "Fecha";
        var ubicacion = "Ubicación";
        var duracion = "Duración";
        var opciones = "Opciones";
        
        //Utilizamos el método findAll() desde un objeto de tipo EnsayoDAO.
        //Almacenamos en una variable la lista que devuelve el mñetodo findAll().
        var ensayos = ensayoDAO.findAll();
        
        //Asociamos los elementos del controlador a los elementos del html, para poder mostrar sus valores.
        model.addAttribute("ensayos", ensayos);
        model.addAttribute("nEnsayo", nEnsayo);
        model.addAttribute("fecha", fecha);
        model.addAttribute("ubicacion", ubicacion);
        model.addAttribute("duracion", duracion);
        model.addAttribute("opciones", opciones);
        
        //De volvemos el html para referenciarlo y que lo muestre en la web.
        return "ensayo/ListarEnsayos";
    }
}
