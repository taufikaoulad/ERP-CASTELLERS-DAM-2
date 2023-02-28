/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.castillo.controllers;

import cat.copernic.CastellersERP.DAO.CastilloDAO;
import cat.copernic.CastellersERP.model.Castillo;
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
public class ControladorVistaCastillos {

    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de GosDAO al controlador
    private CastilloDAO castilloDAO; //Atribut per poder utilitzar les funcions CRUD de la interfície GosDAO

    @GetMapping("/vistaCastillos")
    public String inici(Model model) {
        
        var castillos = new ArrayList<Castillo>();
        
        castilloDAO.findAll().forEach(castillos::add);

        //Enviamos el ArrayList
        model.addAttribute("castillos", castillos);

        return "castillo/vistaCastillos";
    }
}
