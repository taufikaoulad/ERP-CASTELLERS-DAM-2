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
@Controller
public class ControladorSalidas {

    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de SalidaDAO al controlador
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

        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean esTipoX = false;
        boolean esTipoY = false;

        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("CapDeColla"))) {
            esTipoX = true;
            model.addAttribute("ocultar", false);
            model.addAttribute("ocultar2", true);
        } else if (auth.getAuthorities().contains(new SimpleGrantedAuthority("Casteller"))) {
            esTipoY = true;
            model.addAttribute("ocultar", true);
            model.addAttribute("ocultar2", false);
        } else { // si esTipoZ (Tresorer)
            model.addAttribute("ocultar", true);
            model.addAttribute("ocultar2", true);
        }

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
        salidaService.afegirSalida(salida);

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

        return "salida/anadirAsistencia"; //Retorna la pàgina amb el formulari de les dades del gos
    }

    /*@PostMapping("anadirUsarioSalida")
    public RedirectView anadirUsarioSalida(@RequestParam List<Integer> usuariosId, Salida salida) {
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

        // Redireccionamos al detalle de la salida
        return new RedirectView("/editarAsistencia/" + salida.getIdevento());
    }*/
    @PostMapping("anadirUsarioSalida")
    public RedirectView anadirUsarioSalida(@RequestParam(required = false) List<Integer> usuariosId, Salida salida, Model model, UsuarioEvento usuarioEvento) {
        Salida salidaActualizada = salidaService.cercarSalida(salida);
        int salidaId = salidaActualizada.getIdevento();
        
        if (usuariosId == null) {
            return new RedirectView("/editarAsistencia/" + salida.getIdevento());
        } else {
        // Obtenemos la lista de usuarios asignados
        List<Usuario> usuariosAsignados = salidaActualizada.getUsuariosAsignados();

        for (Integer idUsuario : usuariosId) {
            Usuario usuarioAAgregar = usuarioService.cercarUsuarioPorId(idUsuario);
            Salida salida1 = salidaService.cercarSalidaPorId(salidaId);
            // Verificamos si el usuario ya está asignado a la salida
            if (!usuariosAsignados.contains(usuarioAAgregar)) {
                // Creamos una nueva instancia de UsuarioEvento
                UsuarioEvento nuevoUsuarioEvento = new UsuarioEvento();
                nuevoUsuarioEvento.setUsuario(usuarioAAgregar);
                nuevoUsuarioEvento.setEvento(salida1);

                // Agregamos el nuevo usuario evento
                usuarioEventoService.afegirUsuarioEvento(nuevoUsuarioEvento);
            }
        }

        // Redireccionamos al detalle de la salida
        return new RedirectView("/editarAsistencia/" + salida.getIdevento());
        
        }
    }

    /*@PostMapping("/eliminarAsistentes")
    public RedirectView eliminarAsistentes(@RequestParam List<Integer> usuariosId, Salida salida, Model model, Usuario U) {

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
    }*/
 /*@PostMapping("/eliminarAsistentes")
    public RedirectView eliminarAsistentes(@RequestParam List<Integer> usuariosId, Salida salida, Model model) {

        // Obtenemos la salida desde la base de datos
        Salida salidaActualizada = salidaService.cercarSalida(salida);

        // Obtenemos la lista de usuarios asignados de la salida actualizada
        List<Usuario> usuariosAsignados = salidaActualizada.getUsuariosAsignados();

        // Creamos una lista temporal para almacenar los usuarios que no se eliminarán
        List<Usuario> usuariosPermanentes = new ArrayList<>();

        // Recorremos la lista de usuarios asignados y añadimos a la lista temporal aquellos que no se eliminarán
        for (Usuario usuario : usuariosAsignados) {
            if (!usuariosId.contains(usuario.getIdusuario())) {
                usuariosPermanentes.add(usuario);
            }
            
        }

        // Actualizamos la lista de usuarios asignados de la salida actualizada
        salidaActualizada.setUsuariosAsignados(usuariosPermanentes);

        // Guardamos los cambios en la base de datos
        salidaService.afegirSalida(salidaActualizada);

        // Redireccionamos al detalle de la salida
        return new RedirectView("/editarAsistencia/" + salidaActualizada.getIdevento());
    }*/
 /*@PostMapping("/eliminarAsistentes")
    public RedirectView eliminarAsistentes(int a, @RequestParam List<Integer> usuariosId, Salida salida, Model model, UsuarioEvento usuarioEvento) {

        // Obtenemos la salida desde la base de datos
        Salida salidaActualizada = salidaService.cercarSalida(salida);
        int salidaId = salidaActualizada.getIdevento();

        // Obtenemos la lista de usuarios asignados de la salida actualizada
        List<Usuario> usuariosAsignados = salidaActualizada.getUsuariosAsignados();

        // Creamos una lista temporal para almacenar los usuarios que no se eliminarán
        List<Usuario> usuariosPermanentes = new ArrayList<>();
        
        UsuarioEvento usuarioEvento1 = new UsuarioEvento();
        
        
        // Recorremos la lista de usuarios asignados y añadimos a la lista temporal aquellos que no se eliminarán
        for (Usuario usuario : usuariosAsignados) {
            if (!usuariosId.contains(usuario.getIdusuario())) {
                usuariosPermanentes.add(usuario);
            } else {
                int idusuarioEvento = usuarioEventoService.obtenerIdUsuarioEvento(usuario.getIdusuario(), salidaId);
                usuarioEvento1.setIdusuarioevento(idusuarioEvento);
                usuarioEvento = usuarioEventoService.cercarUsuarioEvento(usuarioEvento1);
                // Si el usuario está siendo eliminado, verificamos si tiene asistencia al transporte
                Boolean asistenciaTransporte = null;
                for (Usuario usuario1 : salidaActualizada.getUsuariosAsignados()) {
                    if (usuarioEvento.getUsuario().getIdusuario() == usuario1.getIdusuario()) {
                        asistenciaTransporte = usuarioEvento.getAsistenciaTransporte();
                        break;
                    }
                }
                if (asistenciaTransporte != null) {
                    // Si tiene asistencia al transporte, marcamos la asistencia como falsa
                    for (Usuario usuario2 : salidaActualizada.getUsuariosAsignados()) {
                        if (usuarioEvento.getUsuario().getIdusuario() == usuario2.getIdusuario()) {
                            usuarioEvento.setAsistenciaTransporte(false);
                            break;
                        }
                    }
                }
                
                // Eliminamos el usuario del evento
                usuarioEventoService.eliminarUsuarioEvento(usuarioEvento);
            }

        }

        // Actualizamos la lista de usuarios asignados de la salida actualizada
        salidaActualizada.setUsuariosAsignados(usuariosPermanentes);

        // Guardamos los cambios en la base de datos
        salidaService.afegirSalida(salidaActualizada);

        // Redireccionamos al detalle de la salida
        return new RedirectView("/editarAsistencia/" + salidaActualizada.getIdevento());
    }*/
    @PostMapping("/eliminarAsistentes")
    public RedirectView eliminarAsistentes(@RequestParam(required = false) List<Integer> usuariosId, Salida salida, Model model, UsuarioEvento usuarioEvento) {
        // Obtenemos la salida desde la base de datos
        Salida salidaActualizada = salidaService.cercarSalida(salida);
        int salidaId = salidaActualizada.getIdevento();
        
        if (usuariosId == null) {
            return new RedirectView("/editarAsistencia/" + salidaActualizada.getIdevento());
        } else {
        // Obtenemos la lista de usuarios asignados
        List<Usuario> usuariosAsignados = salidaActualizada.getUsuariosAsignados();

        UsuarioEvento usuarioEvento1 = new UsuarioEvento();

        for (Usuario usuario : usuariosAsignados) {
            if (usuariosId.contains(usuario.getIdusuario())) {
                Usuario usuarioAEliminar = usuarioService.cercarUsuario(usuario);
                int usuarioId = usuarioAEliminar.getIdusuario();
                int idusuarioEvento = usuarioEventoService.obtenerIdUsuarioEvento(usuarioId, salidaId);
                usuarioEvento1.setIdusuarioevento(idusuarioEvento);
                usuarioEvento = usuarioEventoService.cercarUsuarioEvento(usuarioEvento1);
                // Eliminamos el usuario de la salida
                if (usuarioEvento != null) {
                    usuarioEventoService.eliminarUsuarioEvento(usuarioEvento);
                }
            }
        }

        // Redireccionamos al detalle de la salida
        return new RedirectView("/editarAsistencia/" + salidaActualizada.getIdevento());
        }
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

        /*Date date = new Date(2023-12-12);
        TipoUsuario tipoUsuario = new TipoUsuario(); // create a new instance of TipoUsuario
        tipoUsuario.setIdtipousuario(1);
        
        usuario.setUsername("Taufik4"); 
        usuario.setEdat(date);
        usuario.setMail("taufik4@gmail.com");
        usuario.setPassword("mFDsJGU"); 
        usuario.setTelefono("894599999");
        usuario.setPeso(82.23f);
        usuario.setAltura(1.85f);
        usuario.setActivo(true);
        usuario.setPosicio("tierra");
        usuario.setTipousuario_idtipousuario(tipoUsuario);*/
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

    /*@GetMapping("/inscribirseTransporte/{idevento}")
    public String inscribirseTransporte(Model model, Salida salida) {

        //Salida salida = salidaService.carcarSalidaPorId(idSalida);
        salida = salidaService.cercarSalida(salida);
        // Obtener el usuario autenticado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioService.buscarUsuarioPorMail(auth.getName());
        
        UsuarioEvento usuarioEvento = UsuarioEventoService.buscarUsuarioEventoPorUsuarioYEvento(usuario, salida);
        //Usuario usuario = new Usuario();
        
        boolean inscrito = salida.getUsuariosAsignados().contains(usuario);
        
        if (!inscrito) {
            
        }
        
        model.addAttribute("inscrito", inscrito); // Añadir el booleano al modelo

        return "redirect:/paginalistarSalidas";
    }*/
 /*@PostMapping("/inscribirseTransporte/{idevento}")
    public String inscribirseTransporte(Model model, Salida salida, @RequestParam boolean asistenciaTransporte) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioService.buscarUsuarioPorMail(auth.getName());
        salida = salidaService.cercarSalida(salida);

        UsuarioEvento usuarioEvento = new UsuarioEvento();
        usuarioEvento.setAsistenciaTransporte(asistenciaTransporte);
        usuarioEvento.setEvento(salida);
        usuarioEvento.setUsuario(usuario);

        UsuarioEventoService.guardarUsuarioEvento(usuarioEvento);

        return "redirect:/paginalistarSalidas";
    }*/
    @GetMapping("/detalleSalida/{idevento}")
    public String detalleSalida(Model model, Salida salida) {

        //Guardamos el objeto que tiene la misma id de la base de datos en el objeto pasado por parámetro "ensayo".
        salida = salidaService.cercarSalida(salida);

        model.addAttribute("salida", salida);

        List<Castillo> castillosAsignados = salida.getCastillosAsignados();

        model.addAttribute("castillosAsignados", castillosAsignados);

        return "salida/detalleCastilloSalida";
    }

    @GetMapping("/detalleAsistentesSalida/{idevento}")
    public String detalleAsistentesSalida(Model model, Salida salida, UsuarioEvento usuarioEvento) {

        //Salida salida = salidaService.carcarSalidaPorId(idSalida);
        salida = salidaService.cercarSalida(salida);
        //Obtenemos el id del evento
        int salidaId = salida.getIdevento();

        List<Usuario> usuarios = new ArrayList<>(salida.getUsuariosAsignados());

        List<Usuario> usuariosTransporte = new ArrayList<>();

        UsuarioEvento usuarioEvento1 = new UsuarioEvento();

        for (Usuario u : salida.getUsuariosAsignados()) {

            int idusuarioEvento = usuarioEventoService.obtenerIdUsuarioEvento(u.getIdusuario(), salidaId);
            usuarioEvento1.setIdusuarioevento(idusuarioEvento);

            usuarioEvento = usuarioEventoService.cercarUsuarioEvento(usuarioEvento1);

            Boolean asistenciaTransporte = usuarioEvento.getAsistenciaTransporte();
            if (asistenciaTransporte != null && asistenciaTransporte.booleanValue()) {
                usuariosTransporte.add(u);
            }

        }

        model.addAttribute("numeroDeAsistentesTransporte", usuariosTransporte.size());
        model.addAttribute("usuariosTransporte", usuariosTransporte);

        model.addAttribute("numeroDeAsistentes", usuarios.size());
        model.addAttribute("usuarios", usuarios);

        return "salida/assistenciaYTransporteSalida";
    }
}
