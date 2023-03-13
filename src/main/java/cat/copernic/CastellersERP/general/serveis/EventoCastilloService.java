/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP.general.serveis;

import cat.copernic.CastellersERP.DAO.EventoCastilloDAO;
import cat.copernic.CastellersERP.model.Ensayo;
import cat.copernic.CastellersERP.model.EventoCastillo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author capy
 */
public class EventoCastilloService implements EventoCastilloServiceInterface {

    @Autowired
    private EventoCastilloDAO eventoCastilloDAO;

    @Override
    public List<EventoCastillo> listarEventoCastillo() {
        return (List<EventoCastillo>) eventoCastilloDAO.findAll();
    }

    @Override
    public void añadirEventoCastillo(EventoCastillo eventoCastillo) {
        this.eventoCastilloDAO.save(eventoCastillo);
    }

    @Override
    public void eliminarEventoCastillo(EventoCastillo eventoCastillo) {
        this.eventoCastilloDAO.delete(eventoCastillo);
    }

    @Override
    public EventoCastillo buscarEventoCastillo(EventoCastillo eventoCastillo) {
        return this.eventoCastilloDAO.findById(eventoCastillo.getIdeventocastillo()).orElse(null);
    }

}
