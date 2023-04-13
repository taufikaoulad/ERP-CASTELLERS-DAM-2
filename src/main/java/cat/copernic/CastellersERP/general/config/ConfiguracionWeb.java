package cat.copernic.CastellersERP.general.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author Taufik Esta clase configura la aplicación web, añadiendo
 * controladores de vistas (View Controllers) para las páginas de login y
 * error403. Implementa la interfaz WebMvcConfigurer para poder registrar los
 * controladores de vistas en el registro de vistas (View Controller Registry).
 */
@Configuration
public class ConfiguracionWeb implements WebMvcConfigurer {

    /**
     * Registra los controladores de vistas para la página de login y error403
     * en el registro de vistas.
     *
     * @param registre objeto ViewControllerRegistry que permite registrar los
     * controladores de vistas.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registre) {
        //registre.addViewController("/").setViewName("index"); //Mostrem la pàgina incial que reanomenen com a index, quan encara no ens hem autenticat
        registre.addViewController("/Login"); //Mostrem la pàgina login quan l'usuari no ha pogut autenticar-se
        registre.addViewController("/errors/error403").setViewName("/errors/error403"); //Mostrem la pàgina error403 quan l'usuari no pot accedir a una pàgina determinada.
    }
}
