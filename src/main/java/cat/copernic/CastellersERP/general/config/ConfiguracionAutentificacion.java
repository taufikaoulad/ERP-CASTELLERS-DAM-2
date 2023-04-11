package cat.copernic.CastellersERP.general.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author pablogomez Esta clase configura la autenticación y autorización en
 * una aplicación web Spring Security.
 *
 * El AuthenticationManagerBuilder se configura para utilizar el servicio
 * AutentificacionService para
 *
 * autenticar a los usuarios y el BCryptPasswordEncoder para codificar las
 * contraseñas.
 *
 * También se definen las rutas protegidas y se configura la autenticación y
 * autorización correspondientes.
 */
@Configuration
@EnableWebSecurity
public class ConfiguracionAutentificacion {

    @Autowired
    private AutentificacionService autentificacionService;

    /**
     *
     * Configura el AuthenticationManagerBuilder para utilizar el servicio
     * AutentificacionService para autenticar a los usuarios y el
     * BCryptPasswordEncoder para codificar las contraseñas.
     *
     * @param auth AuthenticationManagerBuilder que se configurará.
     * @throws Exception si ocurre un error al configurar el
     * AuthenticationManagerBuilder.
     */
    @Autowired
    public void autenticacio(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autentificacionService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     *
     * Protege las rutas especificadas en la aplicación web y configura la
     * autenticación y autorización correspondientes.
     *
     * @param http HttpSecurity que se configurará.
     *
     * @return SecurityFilterChain que se utilizará para proteger las rutas.
     *
     * @throws Exception si ocurre un error al configurar el HttpSecurity.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //Deshabilita la protección contra CSRF (ataques de falsificación de solicitudes en sitios cruzados).
        return http.csrf().disable().authorizeHttpRequests((requests) -> requests
                .requestMatchers("/menuPrincipal").authenticated()
                .requestMatchers("/gestionEconomica").hasAnyAuthority("Tresorer", "CapDeColla")
                .requestMatchers("/vistaModulos").hasAnyAuthority("CapDeColla")
                .requestMatchers("/paginalistarUsuarios").hasAnyAuthority("CapDeColla")
                .anyRequest().authenticated()
        /*
                .and()
                .authorizeHttpRequests().
                .antMatchers("/ListarEnsayos").authenticated() // Verificación de autenticación para ListarEnsayos
                .antMatchers("/ListarEnsayos/ocultarColumnaX").hasAuthority("TipoUsuarioX") // Verificación de autorización para ocultar la columna X
                .and()
         */
        )
                .formLogin((form) -> form
                .loginPage("/Login")
                .defaultSuccessUrl("/menuPrincipal", true)
                .permitAll()
                )
                .exceptionHandling((exception) -> exception
                .accessDeniedPage("/errors/error403"))
                .build();
    }
}
