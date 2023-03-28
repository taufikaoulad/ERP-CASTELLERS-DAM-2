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

    @Autowired
    public void autenticacio(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autentificacionService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        return http.authorizeHttpRequests((requests) -> requests
//                
//                //.requestMatchers("general/menuPrincipal").authenticated()
//                .requestMatchers("gestionEconomica/ListarApuntes/**").hasAuthority("Tresorer")
//                .requestMatchers("salida/listarSalidas/**").hasAuthority("CapDeColla")
//                //.requestMatchers("/**").permitAll()
//                .anyRequest().authenticated()
//        )
//        .formLogin((form) -> form
//        .loginPage("/Login")
//        .defaultSuccessUrl("/menuPrincipal", true)
//        .permitAll()
//        )
//        .exceptionHandling((exception) -> exception
//        .accessDeniedPage("/errors/error403"))
//        .build();
        /* SecurityFilterChain securityFilterChain = http.csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/menuPrincipal").authenticated()
                .requestMatchers("/gestionEconomica").hasAuthority("Tresorer")
                .anyRequest().authenticated()
                .and()
                .authorizeRequests()
                .mvcMatchers("/ListarEnsayos").authenticated() // Verificación de autenticación para ListarEnsayos
                .mvcMatchers("/ListarEnsayos/ocultarColumnaX").hasAuthority("TipoUsuarioX") // Verificación de autorización para ocultar la columna X
                .and()
                .formLogin(formLogin -> formLogin
                .loginPage("/Login")
                .defaultSuccessUrl("/menuPrincipal", true)
                .permitAll()
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                .accessDeniedPage("/errors/error403")
                )
                .build();*/
        return http.csrf().disable().authorizeHttpRequests((requests) -> requests
                .requestMatchers("/menuPrincipal").authenticated()
                .requestMatchers("/gestionEconomica").hasAnyAuthority("Tresorer")
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
