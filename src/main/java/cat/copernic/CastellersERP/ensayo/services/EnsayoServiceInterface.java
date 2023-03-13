/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.ensayo.services;

import cat.copernic.CastellersERP.model.Ensayo;
import java.util.List;

/**
 *
 * @author capy
 */
public interface EnsayoServiceInterface {
    
    public List<Ensayo> listarEnsayos();
    
    public void añadirEnsayo(Ensayo ensayo);
    
    public void eliminarEnsayo(Ensayo ensayo);
    
    public Ensayo buscarEnsayo(Ensayo ensayo);
}
