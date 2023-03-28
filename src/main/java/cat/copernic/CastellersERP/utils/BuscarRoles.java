/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.utils;

import cat.copernic.CastellersERP.ensayo.services.EnsayoService;
import cat.copernic.CastellersERP.general.serveis.ModuloService;
import cat.copernic.CastellersERP.general.serveis.ModuloTipoUsuarioService;
import cat.copernic.CastellersERP.general.serveis.TipoUsuarioService;
import cat.copernic.CastellersERP.model.Modulo;
import cat.copernic.CastellersERP.model.ModuloTipoUsuario;
import cat.copernic.CastellersERP.model.TipoUsuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

/**
 *
 * @author capy
 */
public class BuscarRoles {
    
    @Autowired
    private ModuloService moduloService;
    
    @Autowired
    private ModuloTipoUsuarioService moduloTipoUsuarioService;
    
    @Autowired
    private TipoUsuarioService tipoUsuarioService;
    
    public TipoUsuario buscarRol(Modulo modulo, Authentication auth){
        TipoUsuario x = new TipoUsuario();
        
        List<Modulo> modulos = moduloService.llistarModulo();
        List<TipoUsuario> tipoUsuarios = tipoUsuarioService.llistarTipoUsuarios();
        List<ModuloTipoUsuario> moduloTipoUsuarios = moduloTipoUsuarioService.llistarModuloTipoUsuarios();
        
        
        
        return x;
    }
}
