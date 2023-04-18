/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.ensayo.controllers;

import cat.copernic.CastellersERP.castillo.serveis.CastilloService;
import cat.copernic.CastellersERP.ensayo.services.EnsayoService;
import cat.copernic.CastellersERP.general.config.AutentificacionService;
import cat.copernic.CastellersERP.general.serveis.UsuarioService;
import cat.copernic.CastellersERP.model.Castillo;
import cat.copernic.CastellersERP.model.Ensayo;
import cat.copernic.CastellersERP.model.Usuario;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import java.lang.String;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
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

    /**
     * La anotación @Autowired es una forma de inyección de dependencias que
     * permite a Spring buscar e inyectar un objeto del tipo EnsayoService en
     * esta variable.
     *
     */
    @Autowired
    private EnsayoService ensayoService;

    /**
     * La anotación @Autowired es una forma de inyección de dependencias que
     * permite a Spring buscar e inyectar un objeto del tipo UsuarioService en
     * esta variable.
     *
     */
    @Autowired
    private UsuarioService usuarioService;

    /**
     * La anotación @Autowired es una forma de inyección de dependencias que
     * permite a Spring buscar e inyectar un objeto del tipo CastilloService en
     * esta variable.
     *
     */
    @Autowired
    private CastilloService csastilloService;

    /**
     * El método inicio es un método de petición GET que responde a la URL
     * "/ensayos". Retorna la vista "ensayo/ListarEnsayos".
     *
     * @param model el modelo que se utiliza para enviar información a la vista.
     * @return String la vista "ensayo/ListarEnsayos".
     */
    @GetMapping("/ensayos")
    public String inicio(Model model) {

        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean esTipoX = auth.getAuthorities().contains(new SimpleGrantedAuthority("CapDeColla"));
        if (esTipoX) {
            // Agregar un atributo al modelo para indicar que se debe mostrar la columna X
            model.addAttribute("ocultar", true);
        } else {
            model.addAttribute("ocultar", false);
        }

        model.addAttribute("Evento", ensayoService.listarEnsayos());

        return "ensayo/ListarEnsayos";
    }

    /**
     * El método crearFormularioEnsayo es un método de petición GET que responde
     * a la URL "/FormularioEnsayo". Retorna la vista "ensayo/FormularioEnsayo".
     *
     * @param ensayo el objeto Ensayo que se utiliza para enviar información a
     * la vista.
     * @return String la vista "ensayo/FormularioEnsayo".
     */
    @GetMapping("/FormularioEnsayo")
    public String crearFormularioEnsayo(Ensayo ensayo) {

        return "ensayo/FormularioEnsayo";
    }

    /**
     *
     * Método que se ejecuta al realizar una petición POST al endpoint
     * "/guardarEnsayo"
     *
     * Este método se encarga de guardar un objeto Ensayo en la base de datos,
     * si se valida correctamente
     *
     * el objeto pasado por parámetro. En caso contrario, se redirige al
     * formulario de creación de ensayos.
     *
     * @param ensayo objeto Ensayo a guardar
     *
     * @param errors objeto que contiene los errores de validación del objeto
     * ensayo
     *
     * @return cadena con la vista a mostrar después de guardar el ensayo en la
     * base de datos
     */
    @PostMapping("/guardarEnsayo")
    public String guardarEnsayo(@Valid Ensayo ensayo, Errors errors) {

        if (errors.hasErrors()) {
            return "ensayo/FormularioEnsayo";
        }

        ensayoService.añadirEnsayo(ensayo); //Se añade el objeto ensayo pasado por parámetro a la base de datos

        return "redirect:/ensayos"; //Se redirige a la página inicial de ensayos mediante redirect
    }

    /**
     *
     * Método que se ejecuta al realizar una petición GET al endpoint
     * "/editarEnsayo/{idevento}"
     *
     * Este método se encarga de buscar en la base de datos un objeto Ensayo con
     * la misma id del objeto pasado
     *
     * por parámetro, para luego mostrar la vista del formulario de edición de
     * ensayos con los datos del objeto
     *
     * encontrado.
     *
     * @param ensayo objeto Ensayo a buscar en la base de datos
     *
     * @param model objeto que contiene los datos a mostrar en la vista
     *
     * @return cadena con la vista del formulario de edición de ensayos
     */
    @GetMapping("/editarEnsayo/{idevento}")
    public String editarEnsayo(Ensayo ensayo, Model model) {

        model.addAttribute("ensayo", ensayoService.buscarEnsayo(ensayo));

        return "ensayo/FormularioEnsayo";
    }

    /**
     *
     * Método que se ejecuta al realizar una petición GET al endpoint
     * "/eliminarEnsayo/{idevento}"
     *
     * Este método se encarga de eliminar de la base de datos un objeto Ensayo
     * con la misma id del objeto pasado
     *
     * por parámetro.
     *
     * @param ensayo objeto Ensayo a eliminar de la base de datos
     *
     * @return cadena con la vista de la página inicial de ensayos
     */
    @GetMapping("/eliminarEnsayo/{idevento}")
    public String eliminarEnsayo(Ensayo ensayo) {
        /*Se elimina el objeto ensayo pasado por parámetro, cuyo id coincide con el de @GetMapping,
        mediante el método eliminarEnsayo de la capa de servicio.*/
        ensayoService.eliminarEnsayo(ensayo);

        return "redirect:/ensayos"; //Se redirige a la página inicial de ensayos mediante redirect
    }

    /**
     *
     * Método que maneja una petición GET para obtener el detalle de un ensayo a
     * través de su ID de evento.
     *
     * @param model el modelo utilizado para agregar atributos a la vista.
     *
     * @param ensayo el objeto ensayo que contiene la información del ensayo.
     *
     * @return la vista "ensayo/DetalleEnsayo" con los datos del ensayo y la
     * lista de usuarios y castillos asignados.
     */
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

    /**
     *
     * Método que maneja una petición GET para obtener la lista de castillos y
     * mostrarla en la vista.
     *
     * @param ensayo el objeto ensayo que contiene la información del ensayo.
     *
     * @param model el modelo utilizado para agregar atributos a la vista.
     *
     * @return la vista "castillo/vistaCastillos" con la lista de castillos
     * disponibles.
     */
    @GetMapping("/consultar-castillos-ensayo/{idevento}")
    public String consultarCastillosEnsayo(Ensayo ensayo, Model model) {

        model.addAttribute("ensayo", ensayoService.buscarEnsayo(ensayo));
        model.addAttribute("castillos", csastilloService.listarCastillos());

        return "castillo/vistaCastillos";
    }

    /**
     *
     * Método que se utiliza para agregar usuarios a un ensayo en particular.
     *
     * @param usuariosId Lista de identificadores de usuarios a agregar.
     * @param ensayo Objeto Ensayo al que se agregarán los usuarios.
     * @param model Modelo utilizado para almacenar los datos que se mostrarán
     * en la vista.
     * @return Devuelve una vista de redireccionamiento al detalle del evento al
     * que pertenece el ensayo.
     */
    @PostMapping("/anadir-usuarios")
    public RedirectView anadirUsuarios(@RequestParam(required = false) List<Integer> usuariosId, Ensayo ensayo, Model model) {

        //Guardamos el objeto que tiene la misma id de la base de datos en el objeto pasado por parámetro "ensayo".
        ensayo = ensayoService.buscarEnsayo(ensayo);

        if (usuariosId == null) {
            return new RedirectView("/detalleEnsayo/" + ensayo.getIdevento());
        } else {

            List<Usuario> asignarUsuarios = usuarioService.llistarUsuarios();

            for (Integer usuarioId : usuariosId) {

                for (Usuario asignarUsuario : asignarUsuarios) {

                    if (usuarioId.equals(asignarUsuario.getIdusuario())) {
                        ensayo.getUsuariosAsignados().add(asignarUsuario);
                    }
                }
            }

            ensayoService.añadirEnsayo(ensayo);

            return new RedirectView("/detalleEnsayo/" + ensayo.getIdevento());
        }
    }

    /**
     *
     * Método que se utiliza para eliminar usuarios asignados a un ensayo en
     * particular.
     *
     * @param usuariosAsignadosId Lista de identificadores de usuarios a
     * eliminar.
     * @param ensayo Objeto Ensayo al que se eliminarán los usuarios asignados.
     * @param model Modelo utilizado para almacenar los datos que se mostrarán
     * en la vista.
     * @return Devuelve una vista de redireccionamiento al detalle del evento al
     * que pertenece el ensayo.
     */
    @PostMapping("/eliminar-asistentes")
    public RedirectView eliminarAsistentes(@RequestParam(required = false) List<Integer> usuariosAsignadosId, Ensayo ensayo, Model model) {

        //Guardamos el objeto que tiene la misma id de la base de datos en el objeto pasado por parámetro "ensayo".
        ensayo = ensayoService.buscarEnsayo(ensayo);

        if (usuariosAsignadosId == null) {
            return new RedirectView("/detalleEnsayo/" + ensayo.getIdevento());
        } else {

            List<Usuario> usuariosAsignados = ensayo.getUsuariosAsignados();

            for (int i = 0; i < usuariosAsignadosId.size(); i++) {
                Integer usuarioId = usuariosAsignadosId.get(i);

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
    }

    /**
     *
     * Maneja la solicitud POST para eliminar los castillos asignados de un
     * ensayo específico. Si no se proporcionan los identificadores de los
     * castillos, redirige a la página de detalles del ensayo. De lo contrario,
     * se eliminan los castillos correspondientes de la lista de castillos
     * asignados del ensayo. Finalmente, se actualiza el ensayo en la base de
     * datos y se redirige a la página de detalles del ensayo.
     *
     * @param castillosId una lista de identificadores de los castillos a
     * eliminar
     * @param ensayo el objeto ensayo que contiene los castillos asignados a
     * eliminar
     * @param model el modelo para la vista
     * @return una vista de redirección a la página de detalles del ensayo
     */
    @PostMapping("/eliminar-castillos-asignados")
    public RedirectView eliminarCastillosAsignados(@RequestParam(required = false) List<Integer> castillosId, Ensayo ensayo, Model model) {
        //Guardamos el objeto que tiene la misma id de la base de datos en el objeto pasado por parámetro "ensayo".
        ensayo = ensayoService.buscarEnsayo(ensayo);

        if (castillosId == null) {
            return new RedirectView("/detalleEnsayo/" + ensayo.getIdevento());
        } else {

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

            return new RedirectView("/detalleEnsayo/" + ensayo.getIdevento());
        }
    }

}
