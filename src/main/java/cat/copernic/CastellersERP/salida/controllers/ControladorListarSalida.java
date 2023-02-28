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

        /*Date date = new Date();
        
        var salida = new Salida();
        salida.setNombreEvento("Nombre1");
        salida.setFechaEvento(date);
        salida.setUbicacionEvento("Terrassa");
        salida.setParadita(true);
        salida.setAsistenciaSalida(true);
        salida.setAsistenciaTransporte(true);
        
        var salida1 = new Salida();
        salida1.setNombreEvento("Nombre2");
        salida1.setFechaEvento(date);
        salida1.setUbicacionEvento("Les Fonts");
        salida1.setParadita(true);
        salida1.setAsistenciaSalida(true);
        salida1.setAsistenciaTransporte(true);
        
        var salidas = new ArrayList<Salida>();
        salidas.add(salida);
        salidas.add(salida1);
        
        model.addAttribute("salidas", salidas);*/
        
        return "salida/listarSalidas"; //Retorna la pàgina iniciDinamic
    }
}
