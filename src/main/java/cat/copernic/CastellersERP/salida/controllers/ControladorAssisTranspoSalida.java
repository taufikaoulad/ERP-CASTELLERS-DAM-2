/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.salida.controllers;


import cat.copernic.CastellersERP.DAO.UsuarioDAO;
import cat.copernic.CastellersERP.model.Usuario;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Taufik
 */

@Controller
public class ControladorAssisTranspoSalida {
    
    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de SalidaDAO al controlador
    private UsuarioDAO usuarioDAO; //Atribut per poder utilitzar les funcions CRUD de la interfície SalidaDAO
    //(localhost:8080/paginaassistenciaYTransporteSalida)
    @GetMapping("/paginaassistenciaYTransporteSalida")
    public String inici(Model model){
        
        /*var usuarios = new ArrayList<Usuario>();
        usuarioDAO.findAll().forEach(usuarios::add);*/

        model.addAttribute("usuarios", usuarioDAO.findAll());

        model.addAttribute("AsistentesyTransportedelasalida", "Asistentes y Transporte de la salida");
        model.addAttribute("AsistenciaASalida", "Asistentes y Transporte de la salida");
        model.addAttribute("numeroDeAsistentes", "72");
        model.addAttribute("numeroDeAsistentes2", "52");
        model.addAttribute("Nombre", "Nombre");
        model.addAttribute("Edat", "Edat");
        model.addAttribute("Peso", "Peso (kg)");
        model.addAttribute("Altura", "Altura (m)");
        
        model.addAttribute("AsistenciaATransporteGrupal", "Castellers que harán uso del trasporte grupal");
        model.addAttribute("Volver", "Volver");
        
        
        return "salida/assistenciaYTransporteSalida"; //Retorna la pàgina iniciDinamic
    }
}
