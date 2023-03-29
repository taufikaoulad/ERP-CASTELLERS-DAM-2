/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 * @author pablogomez
 */
@Configuration
@EnableWebSecurity
public class ConfiguracionAutentificacion {

    @Autowired
    private AutentificacionService autentificacionService;
    /*
    configura el AuthenticationManagerBuilder para utilizar el servicio AutentificacionService para 
    autenticar a los usuarios y el BCryptPasswordEncoder para codificar las contraseñas.
    */
    @Autowired
    public void autenticacio(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autentificacionService).passwordEncoder(new BCryptPasswordEncoder());
    }
    /*
    proteger las rutas especificadas en la aplicación web y configurar la autenticación y 
    autorización correspondientes.
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
