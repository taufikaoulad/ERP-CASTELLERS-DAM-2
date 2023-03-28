/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.general.controllers;

import cat.copernic.CastellersERP.DAO.CalendarioDAO;
import cat.copernic.CastellersERP.DAO.EventoDAO;
import cat.copernic.CastellersERP.general.serveis.CircularService;
import cat.copernic.CastellersERP.model.Circular;
import cat.copernic.CastellersERP.model.Evento;
import jakarta.validation.Valid;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author bhugo
 */
@Controller
public class ControladorMenuPrincipal {

    @Autowired
    private CircularService circularService;
    
    @Autowired
    CalendarioDAO calendarioDAO;

    @GetMapping("/menuPrincipal")
    public String inicio(Model model) {

        ArrayList<String>[][] calendario = new ArrayList[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                calendario[i][j] = new ArrayList<String>();
            }
        }

        LocalDate fechaActual = LocalDate.now();
        int mesActual = fechaActual.getMonthValue();
        int anyoActual = fechaActual.getYear();
        List<Evento> eventos = calendarioDAO.findTareasDelMesActual(mesActual, anyoActual);
        
        LocalDate fecha = LocalDate.now().withDayOfMonth(1);
        
        DayOfWeek diaInicio = fecha.getDayOfWeek();
        int desplazamiento = diaInicio.getValue() - DayOfWeek.MONDAY.getValue();
        fecha = fecha.minusDays(desplazamiento);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (fecha.getMonthValue() == LocalDate.now().getMonthValue()) {
                    String diaConCeros = String.format("%02d", fecha.getDayOfMonth());
                    String mesConCeros = String.format("%02d", fecha.getMonthValue());
                    String fechaString = diaConCeros /*+ "-" + mesConCeros + "-" + fecha.getYear()*/;
                    calendario[i][j].add(fechaString);

                } else {
                    calendario[i][j].add(Integer.toString(0));
                }
                fecha = fecha.plusDays(1);
            }
        }
       
        for (Evento evento : eventos) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    if(!calendario[i][j].get(0).equals("0")){
                       
                        String diaDeLaFechaQueNosPasan = calendario[i][j].get(0).substring(0,2);
                        if(diaDeLaFechaQueNosPasan.substring(0,1).equals("0")){
                            diaDeLaFechaQueNosPasan = diaDeLaFechaQueNosPasan.substring(1);
                        }
                        if(Integer.parseInt(diaDeLaFechaQueNosPasan) == evento.getFechaEvento().toLocalDate().getDayOfMonth()) 
                        {
                            calendario[i][j].add(evento.getNombreEvento());
                        }
                    }
                }
            }
        }
        
        model.addAttribute("calendario",calendario);
        
        model.addAttribute("circulares", circularService.listarCirculars());

        return "general/Inicio";
    }

    @GetMapping("/formularioCircular")
    public String crearFormularioApunte(Circular circular) {

        return "general/AnadirCircular";
    }

    @PostMapping("/guardarCircular")
    public String guardarApunte(@Valid Circular circular, Errors errors) {

        if (errors.hasErrors()) {
            return "general/AnadirCircular";
        }
        circularService.afegirCirculars(circular);

        return "redirect:/menuPrincipal";
    }

    @GetMapping("/editarCircular/{idcircular}")
    public String editar(Circular circular, Model model) {

        /*Cerquem el gos passat per paràmetre, al qual li correspón l'idgos de @GetMapping mitjançant 
         *el mètode cercarGos de la capa de servei.*/
        model.addAttribute("circular", circularService.cercarCirculars(circular));

        return "general/AnadirCircular"; //Retorna la pàgina amb el formulari de les dades del gos
    }

    @GetMapping("/eliminarCircular/{idcircular}")
    public String eliminar(Circular circular) {

        /*Eliminem el gos passat per paràmetre, al qual li correspón l'idgos de @GetMapping mitjançant 
         *el mètode eliminarGos de la capa de servei.*/
        circularService.eliminarCirculars(circular);

        return "redirect:/menuPrincipal"; //Retornem a la pàgina inicial dels gossos mitjançant redirect
    }

}
