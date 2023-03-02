/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.salida.controllers;


import cat.copernic.CastellersERP.DAO.EventoDAO;
import cat.copernic.CastellersERP.DAO.SalidaDAO;
import cat.copernic.CastellersERP.model.Evento;
import cat.copernic.CastellersERP.model.Salida;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Taufik
 */

@Controller
public class ControladorListarSalida {
    
    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de SalidaDAO al controlador
    private SalidaDAO salidaDAO; //Atribut per poder utilitzar les funcions CRUD de la interfície SalidaDAO
    //(localhost:8080/paginalistarSalidas)
    @GetMapping("/paginalistarSalidas")
    public String inici(Model model){
        
        model.addAttribute("salidas", salidaDAO.findAll());
        
        
        model.addAttribute("Menu", "Menu");
        model.addAttribute("Ensayo", "Ensayo");
        model.addAttribute("Salidas", "Salidas");
        model.addAttribute("Castillos", "Castillos");
        model.addAttribute("Administracion", "Administracion");

        model.addAttribute("SALIDAS", "SALIDAS");
        model.addAttribute("Nombre", "Nombre");
        model.addAttribute("Fecha", "Fecha");
        model.addAttribute("Ubicacion", "Ubicación");
        model.addAttribute("Paradita", "Paradita");
        model.addAttribute("Castillo", "Castillo");
        model.addAttribute("Acciones", "Acciones");
        model.addAttribute("Inscribirseasalida", "Inscribirse a salida");
        model.addAttribute("Inscribirseatransporte", "Inscribirse a transporte");
        model.addAttribute("Detalle", "Detalle");
        model.addAttribute("ConsultarCastillo", "Consultar Castillo");
        model.addAttribute("Nparticipantes", "Nºparticipantes");
        model.addAttribute("CrearSalida", "Crear Salida");
        
        
        return "salida/listarSalidas"; //Retorna la pàgina iniciDinamic
    }
}
