/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.gestionEconomica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private UserDetailsService userDetailsService;
    
    
    @Autowired
    public void autenticacio(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        return http.authorizeHttpRequests((requests) -> 
                requests.requestMatchers("gestionEconomica/ListarApuntes/**", "/enviar/**", "/eliminar" , "guardarApunte/**").hasAuthority("Tresorer")
                .anyRequest().authenticated()
        )
        .formLogin((form) -> form
        .loginPage("/login")
        .permitAll()
        ).build();
        
    }
    
}
