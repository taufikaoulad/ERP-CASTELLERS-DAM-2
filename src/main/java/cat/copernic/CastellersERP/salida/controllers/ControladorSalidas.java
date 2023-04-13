/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.salida.controllers;

import cat.copernic.CastellersERP.DAO.EventoDAO;
import cat.copernic.CastellersERP.DAO.SalidaDAO;
import cat.copernic.CastellersERP.DAO.UsuarioDAO;
import cat.copernic.CastellersERP.castillo.serveis.CastilloService;
import cat.copernic.CastellersERP.general.serveis.UsuarioEventoService;
import cat.copernic.CastellersERP.general.serveis.UsuarioService;
import cat.copernic.CastellersERP.model.Castillo;
import cat.copernic.CastellersERP.model.Ensayo;
import cat.copernic.CastellersERP.model.Evento;
import cat.copernic.CastellersERP.model.Salida;
import cat.copernic.CastellersERP.model.TipoUsuario;
import cat.copernic.CastellersERP.model.Usuario;
import cat.copernic.CastellersERP.model.UsuarioEvento;
import cat.copernic.CastellersERP.salida.serveis.SalidaService;
import jakarta.validation.Valid;
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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;

/**
 *
 * @author Taufik
 */

/*
 * Esta es una clase de controlador de Spring MVC llamada ControladorSalidas.
 * La anotación @Controller se utiliza para indicar que esta clase es un controlador de Spring MVC.
 * El controlador utiliza la inyección de dependencias de Spring para obtener instancias de los servicios y DAO necesarios para manejar las solicitudes entrantes.
 */
@Controller
public class ControladorSalidas {

    /*
     * Atributo para utilizar las funciones CRUD de la interfaz SalidaDAO.
     * La anotación @Autowired se utiliza para inyectar una instancia de la clase SalidaService en este atributo.
     */
    @Autowired
    private SalidaService salidaService; //Atribut per poder utilitzar les funcions CRUD de la interfície SalidaDAO

    @Autowired
    private CastilloService castilloService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioEventoService usuarioEventoService;

    @Autowired
    private UsuarioDAO UsuarioDAO;

    //(localhost:8080/paginalistarSalidas)
    @GetMapping("/paginalistarSalidas")
    public String inici(Model model) {

        // Obtener la autenticación del usuario actual
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Inicializar variables booleanas para verificar los roles del usuario actual
        boolean esTipoX = false;
        boolean esTipoY = false;

        // Verificar si el usuario actual tiene el rol CapDeColla
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("CapDeColla"))) {
            esTipoX = true;
            // Agregar atributos al modelo para mostrar/ocultar secciones en la vista
            model.addAttribute("ocultar", false);
            model.addAttribute("ocultar2", true);
        } else if (auth.getAuthorities().contains(new SimpleGrantedAuthority("Casteller"))) {// Verificar si el usuario actual tiene el rol Casteller
            esTipoY = true;
            // Agregar atributos al modelo para mostrar/ocultar secciones en la vista
            model.addAttribute("ocultar", true);
            model.addAttribute("ocultar2", false);
        } else { // Si el usuario actual no tiene los roles anteriores, ocultar ambas secciones
            model.addAttribute("ocultar", true);
            model.addAttribute("ocultar2", true);
        }

        // Agregar al modelo la lista de salidas obtenida del servicio SalidaService
        model.addAttribute("salidas", salidaService.llistarSalidas());

        return "salida/listarSalidas"; //Retorna la pàgina iniciDinamic
    }

    @GetMapping("/formularioSalida")
    public String crearFormulariSalida(Salida salida) {

        return "salida/anadirSalida"; //Retorna la pàgina on es mostrarà el formulari de les dades dels gos
    }

    @PostMapping("/guardarSalida") //action=guardarGos
    public String guardarSalida(@Valid Salida salida, Errors errors) {
        if (errors.hasErrors()) {
            return "salida/anadirSalida"; //Afegim la salida passat per paràmetre a la base de dades
        }
        salidaService.afegirSalida(salida);//guarda la salida en la base de datos

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

        // Cerca la sortida en la base de dades per obtenir-ne les dades
        salida = salidaService.cercarSalida(salida);

        // Afegir la sortida al model per mostrar les seves dades al formulari
        model.addAttribute("ensayo", salida);

        // Llista tots els usuaris de la base de dades per poder-los assignar a la sortida
        List<Usuario> usuarios = usuarioService.llistarUsuarios();

        // Obtenir la llista d'usuaris ja assignats a la sortida
        List<Usuario> usuariosAsignados = salida.getUsuariosAsignados();

        // Eliminar de la llista d'usuaris els usuaris ja assignats a la sortida per evitar la seva duplicació
        for (int i = 0; i < usuarios.size(); ++i) {
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

        // Afegir les llistes d'usuaris i usuaris assignats al model per mostrar-les al formulari
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuariosAsignados", usuariosAsignados);

        // Retorna la pàgina amb el formulari de les dades de la sortida
        return "salida/anadirAsistencia"; //Retorna la pàgina amb el formulari de les dades del gos
    }

    @PostMapping("anadirUsarioSalida")
    public RedirectView anadirUsarioSalida(@RequestParam List<Integer> usuariosId, Salida salida, Model model, UsuarioEvento usuarioEvento) {
        // Cerca la sortida en la base de dades per obtenir-ne les dades
        Salida salidaActualizada = salidaService.cercarSalida(salida);
        int salidaId = salidaActualizada.getIdevento(); //obtenemos el id de la salida

        // Obtenemos la lista de usuarios asignados
        List<Usuario> usuariosAsignados = salidaActualizada.getUsuariosAsignados();

        //bucle for-each que recorre ids de usuarios
        for (Integer idUsuario : usuariosId) {
            Usuario usuarioAAgregar = usuarioService.cercarUsuarioPorId(idUsuario); //Cercem l'usuari per l'id
            Salida salida1 = salidaService.cercarSalidaPorId(salidaId); 
            // Verificamos si el usuario ya está asignado a la salida
            if (!usuariosAsignados.contains(usuarioAAgregar)) {
                // Creamos una nueva instancia de UsuarioEvento
                UsuarioEvento nuevoUsuarioEvento = new UsuarioEvento(); //se crea una instancia de UsuarioEvento
                // Realizamos el set de lo valores
                nuevoUsuarioEvento.setUsuario(usuarioAAgregar);
                nuevoUsuarioEvento.setEvento(salida1);

                // Agregamos el nuevo usuario evento
                usuarioEventoService.afegirUsuarioEvento(nuevoUsuarioEvento);
            }
        }

        // Redireccionamos al detalle de la salida
        return new RedirectView("/editarAsistencia/" + salida.getIdevento());
    }
    
    @PostMapping("/eliminarAsistentes")
    public RedirectView eliminarAsistentes(@RequestParam List<Integer> usuariosId, Salida salida, Model model, UsuarioEvento usuarioEvento) {
        // Obtenemos la salida desde la base de datos
        Salida salidaActualizada = salidaService.cercarSalida(salida);
        int salidaId = salidaActualizada.getIdevento();

        // Obtenemos la lista de usuarios asignados
        List<Usuario> usuariosAsignados = salidaActualizada.getUsuariosAsignados();

        UsuarioEvento usuarioEvento1 = new UsuarioEvento();

        // Recorremos la lista de usuarios asignados a la salida y comprobamos si el usuario debe ser eliminado
        for (Usuario usuario : usuariosAsignados) {
            if (usuariosId.contains(usuario.getIdusuario())) {
                Usuario usuarioAEliminar = usuarioService.cercarUsuario(usuario);
                int usuarioId = usuarioAEliminar.getIdusuario();
                int idusuarioEvento = usuarioEventoService.obtenerIdUsuarioEvento(usuarioId, salidaId);
                usuarioEvento1.setIdusuarioevento(idusuarioEvento);
                usuarioEvento = usuarioEventoService.cercarUsuarioEvento(usuarioEvento1);
                // Eliminamos el usuario de la salida si existe un UsuarioEvento para esa salida y ese usuario
                if (usuarioEvento != null) {
                    usuarioEventoService.eliminarUsuarioEvento(usuarioEvento);
                }
            }
        }

        // Redireccionamos al detalle de la salida
        return new RedirectView("/editarAsistencia/" + salidaActualizada.getIdevento());
    }

    @GetMapping("/pasarIDaCastillo/{idevento}")
    public String pasarIDaCastillo(Salida salida, Model model) {

        model.addAttribute("salida", salidaService.cercarSalida(salida));
        model.addAttribute("castillos", castilloService.listarCastillos());

        return "castillo/vistaCastillos"; //Retorna la pàgina amb el formulari de les dades del gos
    }

    @GetMapping("/inscribirseSalidaITransporte/{idevento}")
    public String inscribirseSalidaITransporte(Model model, Salida salida) {

        //Salida salida = salidaService.carcarSalidaPorId(idSalida);
        salida = salidaService.cercarSalida(salida);

        // Obtener el usuario autenticado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioService.buscarUsuarioPorMail(auth.getName());

        //Usuario usuario = new Usuario();
        boolean inscrito = salida.getUsuariosAsignados().contains(usuario);
        
        if (!inscrito) {
            salida.getUsuariosAsignados().add(usuario);
            salidaService.afegirSalida(salida);
            inscrito = true; // Asignar valor true al booleano
            model.addAttribute("mensaje", "Te has inscrito satisfactoriamente en esta salida.");
        }else {
            model.addAttribute("mensaje", "Ya estás inscrito en esta salida.");
        }

        model.addAttribute("inscrito", inscrito); // Añadir el booleano al modelo

        return "redirect:/paginalistarSalidas";
    }

    @GetMapping("/inscribirseTransporte/{idevento}")
    public String inscribirseTransporte(Model model, Salida salida, UsuarioEvento usuarioEvento) {

        //Salida salida = salidaService.carcarSalidaPorId(idSalida);
        salida = salidaService.cercarSalida(salida);
        //Obtenemos el id del evento
        int salidaId = salida.getIdevento();

        // Obtener el usuario autenticado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioService.buscarUsuarioPorMail(auth.getName());
        //Obtenemos el id del usuario
        int usuarioId = usuario.getIdusuario();

        boolean inscrito = false;

        // Comprobar si el usuario ya está inscrito en el evento
        for (Usuario u : salida.getUsuariosAsignados()) {
            if (u.getIdusuario() == usuario.getIdusuario()) {
                inscrito = true;
                break;
            }
        }

        UsuarioEvento usuarioEvento1 = null;

        if (inscrito) {
            int idusuarioEvento = usuarioEventoService.obtenerIdUsuarioEvento(usuarioId, salidaId);

            usuarioEvento1 = new UsuarioEvento();
            usuarioEvento1.setIdusuarioevento(idusuarioEvento);

            usuarioEvento = usuarioEventoService.cercarUsuarioEvento(usuarioEvento1);

            // Marcar la asistencia al transporte como true
            usuarioEvento.setAsistenciaTransporte(true);

            // Guardar el objeto UsuarioEvento
            usuarioEventoService.afegirUsuarioEvento(usuarioEvento);
            inscrito = true;
        }

        model.addAttribute("inscrito", inscrito);

        return "redirect:/paginalistarSalidas";
    }

    
    @GetMapping("/detalleSalida/{idevento}")
    public String detalleSalida(Model model, Salida salida) {

        //Guardamos el objeto que tiene la misma id de la base de datos en el objeto pasado por parámetro "salida".
        salida = salidaService.cercarSalida(salida);

        //Agregamos el objeto "salida" al modelo de datos para que esté disponible en la vista.
        model.addAttribute("salida", salida);

        //Obtenemos una lista de castillos asignados a esta salida.
        List<Castillo> castillosAsignados = salida.getCastillosAsignados();
        
        //Agregamos la lista de castillos asignados al modelo de datos para que esté disponible en la vista.
        model.addAttribute("castillosAsignados", castillosAsignados);

        //Retornamos el nombre de la vista que se debe mostrar para mostrar los detalles de la salida de castillo.
        return "salida/detalleCastilloSalida";
    }

    @GetMapping("/detalleAsistentesSalida/{idevento}")
    public String detalleAsistentesSalida(Model model, Salida salida, UsuarioEvento usuarioEvento) {

        //Salida salida = salidaService.carcarSalidaPorId(idSalida);
        // Buscamos la salida por su id en la base de datos.
        salida = salidaService.cercarSalida(salida);
        //Obtenemos el id del evento
        int salidaId = salida.getIdevento();
// Creamos una lista de usuarios a partir de los usuarios asignados a esta salida.
        List<Usuario> usuarios = new ArrayList<>(salida.getUsuariosAsignados());
        // Creamos una lista vacía para almacenar a los usuarios que han indicado que necesitan transporte.
        List<Usuario> usuariosTransporte = new ArrayList<>();
        // Creamos un objeto UsuarioEvento para buscar los detalles de asistencia y transporte de cada usuario.
        UsuarioEvento usuarioEvento1 = new UsuarioEvento();
// Recorremos la lista de usuarios para buscar sus detalles de asistencia y transporte.
        for (Usuario u : salida.getUsuariosAsignados()) {
            // Obtenemos el id del UsuarioEvento correspondiente al usuario y a esta salida.
            int idusuarioEvento = usuarioEventoService.obtenerIdUsuarioEvento(u.getIdusuario(), salidaId);
            usuarioEvento1.setIdusuarioevento(idusuarioEvento);
            // Buscamos los detalles del UsuarioEvento correspondiente.
            usuarioEvento = usuarioEventoService.cercarUsuarioEvento(usuarioEvento1);
            // Verificamos si el usuario indicó que necesita transporte.
            Boolean asistenciaTransporte = usuarioEvento.getAsistenciaTransporte();
            if (asistenciaTransporte != null && asistenciaTransporte.booleanValue()) {
                usuariosTransporte.add(u);
            }

        }
        
        // Agregamos la cantidad de usuarios que necesitan transporte y la lista de usuarios que necesitan transporte al modelo de datos.
        model.addAttribute("numeroDeAsistentesTransporte", usuariosTransporte.size());
        model.addAttribute("usuariosTransporte", usuariosTransporte);

        // Agregamos la cantidad total de usuarios y la lista de usuarios al modelo de datos.
        model.addAttribute("numeroDeAsistentes", usuarios.size());
        model.addAttribute("usuarios", usuarios);

        // Retornamos el nombre de la vista que se debe mostrar para mostrar los detalles de asistencia y transporte de los usuarios.
        return "salida/assistenciaYTransporteSalida";
    }
}
