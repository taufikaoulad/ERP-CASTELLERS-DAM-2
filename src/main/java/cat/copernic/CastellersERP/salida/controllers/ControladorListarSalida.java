/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.salida.controllers;


import cat.copernic.CastellersERP.castillo.serveis.CastilloService;
import cat.copernic.CastellersERP.model.Castillo;
import cat.copernic.CastellersERP.model.Salida;
import cat.copernic.CastellersERP.salida.serveis.SalidaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Taufik
 */

@Controller
public class ControladorListarSalida {
    
    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de SalidaDAO al controlador
    private SalidaService salidaService; //Atribut per poder utilitzar les funcions CRUD de la interfície SalidaDAO
    
    @Autowired
    private CastilloService castilloService;
    //(localhost:8080/paginalistarSalidas)
    @GetMapping("/paginalistarSalidas")
    public String inici(Model model){
        
        model.addAttribute("salidas", salidaService.llistarSalidas());
        
        
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
    
    @GetMapping("/detalleSalida/{idevento}")
    public String detalleSalida(Model model, Salida salida) {

        //Guardamos el objeto que tiene la misma id de la base de datos en el objeto pasado por parámetro "ensayo".
        salida = salidaService.cercarSalida(salida);

        model.addAttribute("salida", salida);

        List<Castillo> castillosAsignados = salida.getCastillosAsignados();

        model.addAttribute("castillosAsignados", castillosAsignados);

        return "salida/detalleCastilloSalida";
    }
    
    @GetMapping("/formularioSalida")
    public String crearFormulariSalida(Salida salida) {
        
        return "salida/anadirSalida"; //Retorna la pàgina on es mostrarà el formulari de les dades dels gos
    }
    
    @PostMapping("/guardarSalida") //action=guardarGos
    public String guardarSalida(Salida salida) {

        salidaService.afegirSalida(salida); //Afegim la salida passat per paràmetre a la base de dades

        return "redirect:/paginalistarSalidas"; //Retornem a la pàgina inicial de les sortides mitjançant redirect
    }
    
    @GetMapping("/editarSalida/{idevento}")
    public String editar(Salida salida, Model model) {

        /*Cerquem el gos passat per paràmetre, al qual li correspón l'idgos de @GetMapping mitjançant 
         *el mètode cercarGos de la capa de servei.*/
        model.addAttribute("salida", salidaService.cercarSalida(salida));

        return "salida/anadirSalida"; //Retorna la pàgina amb el formulari de les dades del gos
    }
    
    @GetMapping("/editarAsistencia/{idevento}")
    public String editarAsistencia(Salida salida, Model model) {

        /*Cerquem el gos passat per paràmetre, al qual li correspón l'idgos de @GetMapping mitjançant 
         *el mètode cercarGos de la capa de servei.*/
        model.addAttribute("salida", salidaService.cercarSalida(salida));

        return "salida/anadirAsistencia"; //Retorna la pàgina amb el formulari de les dades del gos
    }
    
    @GetMapping("/pasarIDaCastillo/{idevento}")
    public String pasarIDaCastillo(Salida salida, Model model) {

        /*Cerquem el gos passat per paràmetre, al qual li correspón l'idgos de @GetMapping mitjançant 
         *el mètode cercarGos de la capa de servei.*/
        model.addAttribute("salida", salidaService.cercarSalida(salida));
        model.addAttribute("castillos", castilloService.listarCastillos());

        return "castillo/vistaCastillos"; //Retorna la pàgina amb el formulari de les dades del gos
    }
    
    @GetMapping("/eliminarSalida/{idevento}") 
    public String eliminar(Salida salida) {

        /*Eliminem el gos passat per paràmetre, al qual li correspón l'idgos de @GetMapping mitjançant 
         *el mètode eliminarGos de la capa de servei.*/
        salidaService.eliminarSalida(salida);
        
        return "redirect:/paginalistarSalidas"; //Retornem a la pàgina inicial dels gossos mitjançant redirect
    }
}
