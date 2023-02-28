/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.general.controllers;

import cat.copernic.CastellersERP.DAO.ModuloDAO;
import cat.copernic.CastellersERP.model.Modulo;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author bhugo
 */
@Controller
public class ControladorVistaModulos {
    
    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de GosDAO al controlador
    private ModuloDAO moduloDAO; //Atribut per poder utilitzar les funcions CRUD de la interfície GosDAO
    
    @GetMapping("/vistaModulos")
    public String inici(Model model){
    
        var modulos = new ArrayList<Modulo>();
        moduloDAO.findAll().forEach(modulos::add);
        
        //Enviamos el ArrayList
        model.addAttribute("modulos", modulos);
        
        return "general/vistaModulos";
    }
}
