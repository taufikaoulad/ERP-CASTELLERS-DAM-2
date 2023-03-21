/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.general.controllers;

import cat.copernic.CastellersERP.general.serveis.UsuarioService;
import cat.copernic.CastellersERP.model.Usuario;
import cat.copernic.CastellersERP.utils.EncriptadorContrasenya;
import jakarta.validation.Valid;
import org.aspectj.weaver.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import static org.thymeleaf.spring6.util.FieldUtils.errors;

/**
 *
 * @author bhugo
 */
@Controller
public class ControladorListarUsuario {
    
    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de SalidaDAO al controlador
    private UsuarioService usuarioService;
    //(localhost:8080/paginalistarUsuarios)
    
    @GetMapping("/paginalistarUsuarios")
    public String inici(Model model){ 
        
        model.addAttribute("usuarios", usuarioService.llistarUsuarios());
        
        model.addAttribute("Menu", "Menu");
        model.addAttribute("Ensayo", "Ensayo");
        model.addAttribute("Salidas", "Salidas");
        model.addAttribute("Castillos", "Castillos");
        model.addAttribute("Administracion", "Administracion");
        
        return "general/listarUsuarios";
    }
    
    @GetMapping("/formularioUsuario")
    public String crearFormulariUsuario(Usuario usuario) {
        
        return "general/crearUsuario"; //Retorna la pàgina on es mostrarà el formulari de les dades dels gos
    }
    
    @PostMapping("/guardarUsuario") //action=guardarGos
    public String guardarUsuario(@Valid Usuario usuario, Errors errors) {
        
        if(errors.hasErrors()){ //Si s'han produït errors...
             return "general/crearUsuario"; //Mostrem la pàgina del formulari
        }
        
        String password = usuario.getContrasena();
        String  a = EncriptadorContrasenya.encriptarContrasenya(password);
        
        usuario.setContrasena(a);
        usuarioService.afegirUsuario(usuario); //Afegim el usuario passat per paràmetre a la base de dades

        return "redirect:/paginalistarUsuarios"; //Retornem a la pàgina inicial dels Usuaris mitjançant redirect
    }
    
    @GetMapping("/editarUsuario/{idusuario}")
    public String editar(Usuario usuario, Model model) {

        /*Cerquem el gos passat per paràmetre, al qual li correspón l'idgos de @GetMapping mitjançant 
         *el mètode cercarGos de la capa de servei.*/
        model.addAttribute("usuario", usuarioService.cercarUsuario(usuario));

        return "general/crearUsuario"; //Retorna la pàgina amb el formulari de les dades del gos
    }
    
     @GetMapping("/eliminarUsuario/{idusuario}") 
    public String eliminar(Usuario usuario) {

        /*Eliminem el gos passat per paràmetre, al qual li correspón l'idgos de @GetMapping mitjançant 
         *el mètode eliminarGos de la capa de servei.*/
        usuarioService.eliminarUsuario(usuario);
        
        return "redirect:/paginalistarUsuarios"; //Retornem a la pàgina inicial dels gossos mitjançant redirect
    }
    
}
