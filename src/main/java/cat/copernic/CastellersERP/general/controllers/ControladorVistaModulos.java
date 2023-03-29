/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.general.controllers;

import cat.copernic.CastellersERP.general.serveis.ModuloService;
import cat.copernic.CastellersERP.general.serveis.ModuloTipoUsuarioService;
import cat.copernic.CastellersERP.general.serveis.TipoUsuarioService;
import cat.copernic.CastellersERP.model.ModuloTipoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author bhugo
 */
@Controller
public class ControladorVistaModulos {

    @Autowired
    private ModuloService moduloService;
    @Autowired
    private ModuloTipoUsuarioService modulotipousuarioService;
    @Autowired
    private TipoUsuarioService tipousuarioService;

    @GetMapping("/vistaModulos")
    public String inici(Model model) {

        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean esTipoX = auth.getAuthorities().contains(new SimpleGrantedAuthority("CapDeColla"));
        if (esTipoX) {
            // Agregar un atributo al modelo para indicar que se debe mostrar la columna X
            model.addAttribute("ocultar", true);
        } else {
            model.addAttribute("ocultar", false);
        }

        model.addAttribute("modulos", moduloService.llistarModulo());
        model.addAttribute("modulotipousuarios", modulotipousuarioService.llistarModuloTipoUsuarios());

        return "general/vistaModulos";
    }

    @GetMapping("/formularioModulo")
    public String crearFormularioModulo(ModuloTipoUsuario modulotipousuario) {

        return "general/modificarModulo";
    }

    @PostMapping("/guardarModulo")
    public String guardarModulo(ModuloTipoUsuario modulotipousuario) {

        modulotipousuarioService.afegirModuloTipoUsuario(modulotipousuario);

        return "redirect:/vistaModulos";
    }

    @GetMapping("/editarModulo/{idmtu}")
    public String editar(ModuloTipoUsuario modulotipousuario, Model model) {

        model.addAttribute("modulotipousuario", modulotipousuarioService.cercarModuloTipoUsuario(modulotipousuario));

        return "general/modificarModulo";
    }

    @GetMapping("/eliminarModulo/{idmtu}")
    public String eliminar(ModuloTipoUsuario modulotipousuario) {

        modulotipousuarioService.eliminarModuloTipoUsuario(modulotipousuario);

        return "redirect:/vistaModulos";
    }

}
