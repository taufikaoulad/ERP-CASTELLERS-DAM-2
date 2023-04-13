package cat.copernic.CastellersERP.castillo.controllers;

import cat.copernic.CastellersERP.model.Castillo;
import cat.copernic.CastellersERP.castillo.serveis.CastilloService;
import cat.copernic.CastellersERP.ensayo.services.EnsayoService;
import cat.copernic.CastellersERP.general.serveis.UsuarioService;
import cat.copernic.CastellersERP.model.Ensayo;
import cat.copernic.CastellersERP.model.Salida;
import cat.copernic.CastellersERP.salida.serveis.SalidaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ControladorVistaCastillos {

    @Autowired
    private CastilloService castilloService;

    @Autowired
    private SalidaService salidaService;

    @Autowired
    private EnsayoService ensayoService;

    @Autowired
    private UsuarioService usuarioService;

    /**
     *
     * Método encargado de cargar la vista de la lista de castillos.
     *
     * Además, comprueba el tipo de usuario que ha iniciado sesión y agrega un
     * atributo al modelo para indicar
     *
     * si se debe mostrar la columna "X" en la tabla de castillos.
     *
     * @param model objeto de Spring MVC que contiene los atributos que se
     * utilizarán para construir la vista.
     *
     * @return el nombre de la plantilla que se debe utilizar para mostrar la
     * vista.
     */
    @GetMapping("/vistaCastillos")
    public String inici(Model model) {

        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean esTipoX = auth.getAuthorities().contains(new SimpleGrantedAuthority("CapDeColla"));
        if (esTipoX) {
            // Agregar un atributo al modelo para indicar que se debe mostrar la columna X
            model.addAttribute("ocultar", true);
        } else {
            model.addAttribute("ocultar", false);
        }

        model.addAttribute("castillos", castilloService.listarCastillos());

        return "castillo/vistaCastillos";
    }

    /**
     *
     * Método encargado de cargar la vista del formulario de creación de
     * castillos.
     *
     * @param castillo objeto Castillo que se utilizará para crear el
     * formulario.
     *
     * @return el nombre de la plantilla que se debe utilizar para mostrar la
     * vista.
     */
    @GetMapping("/formularioCastillo")
    public String crearFormularioCastillo(Castillo castillo) {

        return "castillo/modificarCastillo";
    }

    /**
     *
     * Método encargado de procesar el formulario de creación de castillos.
     *
     * @param castillo objeto Castillo que se creará a partir de los datos del
     * formulario.
     *
     * @param errors objeto que contendrá los errores de validación que se hayan
     * producido.
     *
     * @return el nombre de la plantilla que se debe utilizar para mostrar la
     * vista.
     */
    @PostMapping("/guardarCastillo")
    public String guardarCastillo(@Valid Castillo castillo, Errors errors) {

        if (errors.hasErrors()) { //Si s'han produït errors...
            return "castillo/modificarCastillo"; //Mostrem la pàgina del formulari
        }

        castilloService.agregarCastillo(castillo);

        return "redirect:/vistaCastillos";
    }

    /**
     *
     * Método encargado de cargar la vista del formulario de edición de
     * castillos.
     *
     * @param castillo objeto Castillo que se utilizará para crear el formulario
     * de edición.
     *
     * @param model objeto de Spring MVC que contiene los atributos que se
     * utilizarán para construir la vista.
     *
     * @return el nombre de la plantilla que se debe utilizar para mostrar la
     * vista.
     */
    @GetMapping("/editarCastillo/{idCastillo}")
    public String editar(Castillo castillo, Model model) {

        model.addAttribute("castillo", castilloService.buscarCastillo(castillo));

        return "castillo/modificarCastillo";
    }

    /**
     *
     * Método que elimina un castillo en base a su id
     *
     * @param castillo el castillo a eliminar
     *
     * @return una cadena de texto que redirige a la página de vista de
     * castillos
     */
    @GetMapping("/eliminarCastillo/{idCastillo}")
    public String eliminar(Castillo castillo) {

        castilloService.eliminarCastillo(castillo);

        return "redirect:/vistaCastillos";
    }

    /**
     *
     * Método que se encarga de asignar un castillo a una salida y redirigir a
     * la página de listado de salidas.
     *
     * @param model Modelo para enviar información a la vista.
     * @param idSalida Identificador de la salida a la que se quiere asignar el
     * castillo.
     * @param idCastillo Identificador del castillo que se quiere asignar a la
     * salida.
     * @return Retorna la página de listado de salidas.
     */
    @PostMapping("/pasarCastilloASalida")
    public String pasarCastilloASalida(Model model, @RequestParam("idSalida") int idEnsayo, @RequestParam("idCastilloToSalida") int idCastillo) {

        Salida salida = salidaService.cercarSalidaPorId(idEnsayo);
        Castillo castillo = castilloService.buscarCastilloPorId(idCastillo);

        if (!salida.getCastillosAsignados().contains(castillo)) {
            salida.getCastillosAsignados().add(castillo);
            salidaService.afegirSalida(salida);
        }

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

    /**
     *
     * Método que asigna un castillo a un ensayo y lo muestra en la lista de
     * castillos asignados
     *
     * @param model el modelo de la vista
     *
     * @param idEnsayo el id del ensayo al que se quiere asignar el castillo
     *
     * @param idCastillo el id del castillo que se quiere asignar
     *
     * @return una cadena de texto que redirige a la página de vista de ensayos
     */
    @PostMapping("/pasarCastilloAEnsayo")
    public String pasarCastilloAEnsayo(Model model, @RequestParam("idEnsayo") int idEnsayo, @RequestParam("idCastilloToEnsayo") int idCastillo) {

        Ensayo ensayo = ensayoService.buscarEnsayoPorId(idEnsayo);
        Castillo castillo = castilloService.buscarCastilloPorId(idCastillo);

        if (!ensayo.getCastillosAsignados().contains(castillo)) {
            ensayo.getCastillosAsignados().add(castillo);

            ensayoService.añadirEnsayo(ensayo);
        }

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

}
