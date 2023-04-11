package cat.copernic.CastellersERP.gestionEconomica.controllers;

import cat.copernic.CastellersERP.gestionEconomica.serveis.ApunteService;
import cat.copernic.CastellersERP.model.Apunte;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

/**
 *
 * @author bhugo Controlador encargado de manejar las operaciones relacionadas
 * con la gestión económica del sistema.
 *
 */
@Controller
public class ControladorListarApunte {

    double dinero = 0;
    /**
     * si una clase necesita una instancia de otro componente, simplemente se
     * puede declarar un campo con la anotación "@Autowired" y Spring Boot se
     * encargará de crear una instancia y asignarla al campo automáticamente.
     */
    @Autowired
    private ApunteService apunteService;

    /**
     * Método encargado de mostrar la lista de apuntes contables del sistema.
     *
     * @param model Modelo utilizado para transmitir información a la vista.
     * @return Devuelve la vista de la lista de apuntes contables.
     */
    @GetMapping("/gestionEconomica")
    public String inicio(Model model) {

        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean esTipoX = auth.getAuthorities().contains(new SimpleGrantedAuthority("Tresorer"));
        if (esTipoX) {
            // Agregar un atributo al modelo para indicar que se debe mostrar la columna X
            model.addAttribute("ocultar", true);
        } else {
            model.addAttribute("ocultar", false);
        }

        model.addAttribute("apuntes", apunteService.listarApuntes());

        model.addAttribute("Dinero", dinero);

        return "gestionEconomica/ListarApuntes";
    }

    /**
     * Método encargado de mostrar el formulario para añadir un nuevo apunte
     * contable.
     *
     * @param apunte Objeto apunte contable utilizado para recoger los datos del
     * formulario.
     * @return Devuelve la vista del formulario para añadir un nuevo apunte
     * contable.
     */
    @GetMapping("/formularioApunte")
    public String crearFormularioApunte(Apunte apunte) {

        return "gestionEconomica/AnadirApunte";
    }

    /**
     * Método encargado de guardar un nuevo apunte contable en el sistema.
     *
     * @param apunte Objeto apunte contable que contiene los datos a guardar.
     * @param errors Errores que se han producido durante la validación del
     * objeto apunte.
     * @return Devuelve la vista de la lista de apuntes contables.
     */
    @PostMapping("/guardarApunte")
    public String guardarApunte(@Valid Apunte apunte, Errors errors) {

        if (errors.hasErrors()) {
            return "gestionEconomica/AnadirApunte";
        }
        apunteService.afegirApuntes(apunte);

//        if (apunte.getTipo().equals("Ingreso")) {
//            dinero += apunte.getPrecio();
//        } else if (apunte.getTipo().equals("Gasto")) {
//            dinero -= apunte.getPrecio();
//        }
        return "redirect:/gestionEconomica";
    }

    /**
     * Método que se encarga de editar un Apunte existente en la lista de
     * apuntes de la aplicación.
     *
     * Recibe un Apunte y un Model como parámetros para la vista.
     *
     * @param apunte El Apunte a editar.
     *
     * @param model El Model utilizado para enviar datos a la vista.
     *
     * @return Retorna la vista "gestionEconomica/AnadirApunte" que muestra el
     * formulario para editar el Apunte.
     */
    @GetMapping("/editarApunte/{idapuntecontable}")
    public String editar(Apunte apunte, Model model) {

        /*Cerquem el gos passat per paràmetre, al qual li correspón l'idgos de @GetMapping mitjançant 
         *el mètode cercarGos de la capa de servei.*/
        model.addAttribute("apunte", apunteService.cercarApunte(apunte));

        return "gestionEconomica/AnadirApunte"; //Retorna la pàgina amb el formulari de les dades del gos
    }

    @GetMapping("/eliminarApunte/{idapuntecontable}")
    public String eliminar(Apunte apunte) {

        /*Eliminem el gos passat per paràmetre, al qual li correspón l'idgos de @GetMapping mitjançant 
         *el mètode eliminarGos de la capa de servei.*/
        apunteService.eliminarApunte(apunte);

        return "redirect:/gestionEconomica"; //Retornem a la pàgina inicial dels gossos mitjançant redirect
    }

    /**
     *
     * Método que devuelve una instancia del dialecto SpringSecurityDialect para
     * ser utilizado en las vistas de Thymeleaf. Este dialecto proporciona
     * herramientas para trabajar con seguridad en Spring Security.
     *
     * @return instancia de SpringSecurityDialect
     */
    @ModelAttribute("SpringSecurity")
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }
}
