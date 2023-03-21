/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.ensayo.controllers;

import cat.copernic.CastellersERP.DAO.UsuarioDAO;
import cat.copernic.CastellersERP.castillo.serveis.CastilloService;
import cat.copernic.CastellersERP.ensayo.services.EnsayoService;
import cat.copernic.CastellersERP.general.serveis.UsuarioService;
import cat.copernic.CastellersERP.model.Castillo;
import cat.copernic.CastellersERP.model.Ensayo;
import cat.copernic.CastellersERP.model.Usuario;
import lombok.extern.slf4j.Slf4j;
import java.lang.String;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

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
    
    @Autowired
    private CastilloService csastilloService;

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

        //Guardamos el objeto que tiene la misma id de la base de datos en el objeto pasado por parámetro "ensayo".
        ensayo = ensayoService.buscarEnsayo(ensayo);

        model.addAttribute("ensayo", ensayo);

        List<Usuario> usuarios = usuarioService.llistarUsuarios();

        List<Usuario> usuariosAsignados = ensayo.getUsuariosAsignados();

        List<Castillo> castillosAsignados = ensayo.getCastillosAsignados();

        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);

            for (int j = 0; j < usuariosAsignados.size(); j++) {
                Usuario usuarioAsignado = usuariosAsignados.get(j);

                if (usuario.equals(usuarioAsignado)) {
                    usuarios.remove(usuario);
                    i--; // Disminuir el índice ya que el tamaño de la lista ha disminuido
                    break; // Salir del bucle interior si se encuentra una coincidencia
                }
            }
        }

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuariosAsignados", usuariosAsignados);
        model.addAttribute("castillosAsignados", castillosAsignados);

        return "ensayo/DetalleEnsayo";
    }
    
    @GetMapping("/consultar-castillos-ensayo/{idevento}")
    public String consultarCastillosEnsayo(Ensayo ensayo, Model model) {

        model.addAttribute("ensayo", ensayoService.buscarEnsayo(ensayo));
        model.addAttribute("castillos", csastilloService.listarCastillos());
        
        return "castillo/vistaCastillos";
    }

    @PostMapping("/anadir-usuarios")
    public RedirectView anadirUsuarios(@RequestParam List<Integer> usuariosId, Ensayo ensayo, Model model) {

        //Guardamos el objeto que tiene la misma id de la base de datos en el objeto pasado por parámetro "ensayo".
        ensayo = ensayoService.buscarEnsayo(ensayo);

        List<Usuario> asignarUsuarios = usuarioService.llistarUsuarios();

        for (Integer usuarioId : usuariosId) {

            for (Usuario asignarUsuario : asignarUsuarios) {

                if (usuarioId.equals(asignarUsuario.getIdusuario())) {
                    ensayo.getUsuariosAsignados().add(asignarUsuario);
                }
            }
        }

        ensayoService.añadirEnsayo(ensayo);

        //return detalleEnsayo(model, ensayo);
        return new RedirectView("/detalleEnsayo/" + ensayo.getIdevento());
    }

    @PostMapping("/eliminar-asistentes")
    public RedirectView eliminarAsistentes(@RequestParam List<Integer> usuariosId, Ensayo ensayo, Model model) {

        //Guardamos el objeto que tiene la misma id de la base de datos en el objeto pasado por parámetro "ensayo".
        ensayo = ensayoService.buscarEnsayo(ensayo);

        List<Usuario> usuariosAsignados = ensayo.getUsuariosAsignados();

        for (int i = 0; i < usuariosId.size(); i++) {
            Integer usuarioId = usuariosId.get(i);

            for (int j = 0; j < usuariosAsignados.size(); j++) {
                Usuario eliminarUsuario = usuariosAsignados.get(j);

                if (usuarioId.equals(eliminarUsuario.getIdusuario())) {
                    usuariosAsignados.remove(j);
                    j--; // Disminuir el índice ya que el tamaño de la lista ha disminuido
                    break; // Salir del bucle interior si se encuentra una coincidencia
                }
            }
        }

        ensayo.setUsuariosAsignados(usuariosAsignados); // Actualizar la lista de usuarios asignados en el ensayo

        ensayoService.añadirEnsayo(ensayo);

        //return detalleEnsayo(model, ensayo);
        return new RedirectView("/detalleEnsayo/" + ensayo.getIdevento());
    }

    @PostMapping("/eliminar-castillos-asignados")
    public RedirectView eliminarCastillosAsignados(@RequestParam List<Integer> castillosId, Ensayo ensayo, Model model) {
        //Guardamos el objeto que tiene la misma id de la base de datos en el objeto pasado por parámetro "ensayo".
        ensayo = ensayoService.buscarEnsayo(ensayo);

        List<Castillo> castillosAsignados = ensayo.getCastillosAsignados();

        for (int i = 0; i < castillosId.size(); i++) {
            Integer castilloId = castillosId.get(i);
            
            for (int j = 0; j < castillosAsignados.size(); j++) {
                Castillo castilloAsignado = castillosAsignados.get(j);
                
                if (castilloId.equals(castilloAsignado.getIdCastillo())) {
                    ensayo.getCastillosAsignados().remove(castilloAsignado);
                }
            }
        }

        ensayoService.añadirEnsayo(ensayo);

        //return detalleEnsayo(model, ensayo);
        return new RedirectView("/detalleEnsayo/" + ensayo.getIdevento());
    }

}
