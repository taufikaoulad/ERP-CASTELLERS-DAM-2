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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author bhugo /** Esta clase ControladorMenuPrincipal es el controlador que
 * maneja la página principal de la aplicación y la página que permite agregar
 * nuevas circulares. Este controlador también maneja la lógica para mostrar el
 * calendario con los eventos del mes actual.
 *
 * @since 1.0
 * @version 1.0
 */
@Controller
public class ControladorMenuPrincipal {

    /**
     * La dependencia del servicio CircularService.
     */
    @Autowired
    private CircularService circularService;

    /**
     * La dependencia del DAO CalendarioDAO.
     */
    @Autowired
    CalendarioDAO calendarioDAO;

    /**
     * Este método es el controlador de la página principal que maneja la lógica
     * para mostrar el calendario con los eventos del mes actual.
     *
     * @param model objeto que representa el modelo en la vista
     * @return devuelve la vista "general/Inicio"
     */
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

        /**
         * establece la fecha actual al primer día del mes actual, y determina
         * en qué día de la semana cae ese primer día. Luego, agrega días
         * adicionales (como días de la semana anteriores al primer día del mes)
         * a la fecha actual para asegurarse de que el primer día del mes
         * aparezca en el día de la semana correcto en el calendario.
         */
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

        /**
         * recorre la matriz bidimensional de ArrayLists "calendario" y agrega
         * fechas al ArrayList correspondiente a cada día en el mes actual. Si
         * el día no está en el mes actual, se agrega el valor 0 en su lugar.
         */
        for (Evento evento : eventos) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    if (!calendario[i][j].get(0).equals("0")) {

                        String diaDeLaFechaQueNosPasan = calendario[i][j].get(0).substring(0, 2);
                        if (diaDeLaFechaQueNosPasan.substring(0, 1).equals("0")) {
                            diaDeLaFechaQueNosPasan = diaDeLaFechaQueNosPasan.substring(1);
                        }
                        if (Integer.parseInt(diaDeLaFechaQueNosPasan) == evento.getFechaEvento().toLocalDate().getDayOfMonth()) {
                            calendario[i][j].add(evento.getNombreEvento());
                        }
                    }
                }
            }
        }

        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean esTipoX = auth.getAuthorities().contains(new SimpleGrantedAuthority("Casteller"));
        if (esTipoX) {
            // Agregar un atributo al modelo para indicar que se debe mostrar la columna X
            model.addAttribute("ocultar", false);
        } else {
            model.addAttribute("ocultar", true);
        }

        model.addAttribute("calendario", calendario);

        model.addAttribute("circulares", circularService.listarCirculars());

        return "general/Inicio";
    }

    /**
     *
     * Retorna la vista "general/AnadirCircular" para crear un formulario de una
     * circular.
     *
     * @param circular la circular a la que se le va a crear el formulario
     * @return una cadena de caracteres que representa la vista
     * "general/AnadirCircular"
     */
    @GetMapping("/formularioCircular")
    public String crearFormularioApunte(Circular circular) {

        return "general/AnadirCircular";
    }

    /**
     *
     * Método para guardar un objeto de tipo Circular en la base de datos.
     *
     * @param circular el objeto de tipo Circular que se desea guardar.
     * @param errors el objeto Errors que contiene los posibles errores de
     * validación del objeto.
     * @return una cadena de texto que indica la vista que se debe mostrar
     * después de realizar la operación.
     */
    @PostMapping("/guardarCircular")
    public String guardarApunte(@Valid Circular circular, Errors errors) {

        if (errors.hasErrors()) {
            return "general/AnadirCircular";
        }
        circularService.afegirCirculars(circular);

        return "redirect:/menuPrincipal";
    }

    /**
     * Mètode que permet editar les dades d'una circular existent en el sistema.
     *
     * Cerca la circular pel seu identificador, i les dades són mostrades en un
     * formulari per a que l'usuari pugui modificar-les.
     *
     * @param circular Objeto Circular que conté l'identificador de la circular
     * a editar.
     *
     * @param model Model de la vista per afegir l'objecte Circular corresponent
     * a la vista.
     *
     * @return Retorna la vista amb el formulari de les dades de la Circular a
     * editar.
     */
    @GetMapping("/editarCircular/{idcircular}")
    public String editar(Circular circular, Model model) {

        /*Cerquem el gos passat per paràmetre, al qual li correspón l'idgos de @GetMapping mitjançant 
         *el mètode cercarGos de la capa de servei.*/
        model.addAttribute("circular", circularService.cercarCirculars(circular));

        return "general/AnadirCircular"; //Retorna la pàgina amb el formulari de les dades del gos
    }

    /**
     * Controlador per a eliminar una circular. Aquest mètode s'encarrega
     * d'eliminar una circular a partir de l'identificador passat per paràmetre.
     *
     * @param circular l'objecte de tipus Circular que conté l'identificador de
     * la circular a eliminar
     * @return una cadena de text que indica la pàgina a la que s'ha de
     * redirigir després d'eliminar la circular
     */
    @GetMapping("/eliminarCircular/{idcircular}")
    public String eliminar(Circular circular) {

        /*Eliminem el gos passat per paràmetre, al qual li correspón l'idgos de @GetMapping mitjançant 
         *el mètode eliminarGos de la capa de servei.*/
        circularService.eliminarCirculars(circular);

        return "redirect:/menuPrincipal"; //Retornem a la pàgina inicial dels gossos mitjançant redirect
    }

}
