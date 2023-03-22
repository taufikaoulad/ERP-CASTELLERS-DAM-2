/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.salida.controllers;


import cat.copernic.CastellersERP.DAO.EventoDAO;
import cat.copernic.CastellersERP.DAO.SalidaDAO;
import cat.copernic.CastellersERP.DAO.UsuarioDAO;
import cat.copernic.CastellersERP.general.serveis.UsuarioService;
import cat.copernic.CastellersERP.model.Ensayo;
import cat.copernic.CastellersERP.model.Evento;
import cat.copernic.CastellersERP.model.Salida;
import cat.copernic.CastellersERP.model.TipoUsuario;
import cat.copernic.CastellersERP.model.Usuario;
import cat.copernic.CastellersERP.model.UsuarioEvento;
import cat.copernic.CastellersERP.salida.serveis.SalidaService;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Taufik
 */

@Controller
public class ControladorSalidas {
    
    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de SalidaDAO al controlador
    private SalidaService salidaService; //Atribut per poder utilitzar les funcions CRUD de la interfície SalidaDAO
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioDAO UsuarioDAO;
    
    //(localhost:8080/paginalistarSalidas)
    @GetMapping("/paginalistarSalidas")
    public String inici(Model model){
        
        model.addAttribute("salidas", salidaService.llistarSalidas());
        
        return "salida/listarSalidas"; //Retorna la pàgina iniciDinamic
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
    
    @GetMapping("/eliminarSalida/{idevento}") 
    public String eliminar(Salida salida) {

        /*Eliminem el gos passat per paràmetre, al qual li correspón l'idgos de @GetMapping mitjançant 
         *el mètode eliminarGos de la capa de servei.*/
        salidaService.eliminarSalida(salida);
        
        return "redirect:/paginalistarSalidas"; //Retornem a la pàgina inicial dels gossos mitjançant redirect
    }
    
    @GetMapping("/editarAsistencia/{idevento}")
    public String editarAsistencia(Salida salida, Model model) {

        salida = salidaService.cercarSalida(salida);
        
        model.addAttribute("ensayo", salida);
        
        List<Usuario> usuarios = usuarioService.llistarUsuarios();

        List<Usuario> usuariosAsignados = salida.getUsuariosAsignados();

        for (int i = 0; i < usuarios.size(); ++i) {
            Usuario usuario = usuarios.get(i);for (int j = 0; j < usuariosAsignados.size(); j++) {
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
        
        return "salida/anadirAsistencia"; //Retorna la pàgina amb el formulari de les dades del gos
    }
    
    @PostMapping("anadirUsarioSalida")
    public RedirectView anadirUsarioSalida(@RequestParam List<Integer> usuariosId, Salida salida, Model model){
        salida = salidaService.cercarSalida(salida);
        
        List<Usuario> asignarUsuarios = usuarioService.llistarUsuarios();

        for (Integer usuarioId : usuariosId) {

            for (Usuario asignarUsuario : asignarUsuarios) {

                if (usuarioId.equals(asignarUsuario.getIdusuario())) {
                    salida.getUsuariosAsignados().add(asignarUsuario);
                }
            }
        }
        
        salidaService.afegirSalida(salida);
        
        return new RedirectView("/editarAsistencia/" + salida.getIdevento());
    }
    
    @PostMapping("/eliminarAsistentes")
    public RedirectView eliminarAsistentes(@RequestParam List<Integer> usuariosId, Salida salida, Model model) {

        //Guardamos el objeto que tiene la misma id de la base de datos en el objeto pasado por parámetro "ensayo".
        salida = salidaService.cercarSalida(salida);

        List<Usuario> usuariosAsignados = salida.getUsuariosAsignados();

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

        salida.setUsuariosAsignados(usuariosAsignados); // Actualizar la lista de usuarios asignados en el ensayo

        salidaService.afegirSalida(salida);

        //return detalleEnsayo(model, ensayo);
        return new RedirectView("/editarAsistencia/" + salida.getIdevento());
    }
    
    
    @GetMapping("/pasarIDaCastillo/{idevento}")
    public String pasarIDaCastillo(Salida salida, Model model) {

        /*Cerquem el gos passat per paràmetre, al qual li correspón l'idgos de @GetMapping mitjançant 
         *el mètode cercarGos de la capa de servei.*/
        model.addAttribute("salida", salidaService.cercarSalida(salida));

        return "castillo/vistaCastillos"; //Retorna la pàgina amb el formulari de les dades del gos
    }
    
    @GetMapping("/inscribirseSalidaITransporte/{idevento}")
    public String inscribirseSalidaITransporte(Model model, Salida salida) {

        //Salida salida = salidaService.carcarSalidaPorId(idSalida);
        salida = salidaService.cercarSalida(salida);
        
        // Obtener el usuario autenticado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //Usuario usuario = usuarioService.buscarUsuarioPorMail(auth.getName());
        
        Usuario usuario = new Usuario();
        
        boolean inscrito = salida.getUsuariosAsignados().contains(usuario);
        
        Date date = new Date(2023-12-12);
        TipoUsuario tipoUsuario = new TipoUsuario(); // create a new instance of TipoUsuario
        tipoUsuario.setIdtipousuario(1);
        
        usuario.setNombre("Taufik4");
        usuario.setEdat(date);
        usuario.setMail("taufik4@gmail.com");
        usuario.setContrasena("mFDsJGU");
        usuario.setTelefono("894599999");
        usuario.setPeso(82.23f);
        usuario.setAltura(1.85f);
        usuario.setActivo(true);
        usuario.setPosicio("tierra");
        usuario.setTipousuario_idtipousuario(tipoUsuario);
        
        if (!inscrito) {
            salida.getUsuariosAsignados().add(usuario);
            salidaService.afegirSalida(salida);
            inscrito = true; // Asignar valor true al booleano
        }
        
        model.addAttribute("inscrito", inscrito); // Añadir el booleano al modelo

        return "redirect:/paginalistarSalidas";
    }

}
