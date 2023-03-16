/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.ensayo.controllers;

import cat.copernic.CastellersERP.DAO.UsuarioDAO;
import cat.copernic.CastellersERP.ensayo.services.EnsayoService;
import cat.copernic.CastellersERP.general.serveis.UsuarioService;
import cat.copernic.CastellersERP.model.Ensayo;
import cat.copernic.CastellersERP.model.Usuario;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.lang.String;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioDAO UsuarioDAO;

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
    public String editarEnsayo(Ensayo ensayo, Model model) {

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
    public String detalleEnsayo(Model model, Ensayo ensayo) {

        model.addAttribute("ensayo", ensayoService.buscarEnsayo(ensayo));

        model.addAttribute("usuarios", usuarioService.llistarUsuarios());

        model.addAttribute("usuarioAsignados", ensayo.getUsuariosAsignados());
        return "ensayo/DetalleEnsayo";
    }

    //MIRAR MÉTODO
    @PostMapping("/seleccionarUsuarios/{idevento}")
    public String seleccionarUsuarios(@RequestParam(value = "seleccionados", required = false)List<Integer> seleccionados, Ensayo ensayo/*, Model model*/) {
        List<Usuario> asistentes = new ArrayList<Usuario>();

        for (Integer seleccionado : seleccionados) {
            Usuario usuario = UsuarioDAO.findById(seleccionado).orElse(null);

            if (usuario != null) {
                
                asistentes.add(usuario);
            }
        }

        ensayo.setUsuariosAsignados(asistentes);
        /*
        model.addAttribute("ensayo", ensayoService.buscarEnsayo(ensayo));
        model.addAttribute("usuarios", usuarioService.llistarUsuarios());
        model.addAttribute("usuarioAsignados", ensayo.getUsuariosAsignados());
*/
        return "redirect:/detalleEnsayo/{idevento}";
    }
}
