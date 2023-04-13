/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP;

import cat.copernic.CastellersERP.DAO.EnsayoDAO;
import cat.copernic.CastellersERP.DAO.UsuarioDAO;
import cat.copernic.CastellersERP.ensayo.controllers.ControladorEnsayos;
import cat.copernic.CastellersERP.ensayo.services.EnsayoService;
import cat.copernic.CastellersERP.general.serveis.UsuarioService;
import cat.copernic.CastellersERP.model.Ensayo;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.runner.RunWith;

/**
 *
 * @author capy
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class EnsayoControllerTest {

    @Autowired
    @Mock
    private EnsayoDAO ensayoDAO;

    @Autowired
    @InjectMocks
    private EnsayoService ensayoService;

    @Test
    public void eliminarEnsayoTest() {

        // Obtener la lista de ensayos
        List<Ensayo> ensayos = ensayoService.listarEnsayos();

        // Verificar que hay ensayos en la lista
        assertTrue(!ensayos.isEmpty());
        /*
        // Obtener el índice de un ensayo aleatorio
        Random rand = new Random();
        int randomIndex = rand.nextInt(ensayos.size());

        // Obtener el id del ensayo aleatorio
        int idEnsayoAEliminar = ensayos.get(randomIndex).getIdevento();
         */

        // Obtener un ensayo aleatorio
        Ensayo ensayoAEliminar = ensayos.get(new Random().nextInt(ensayos.size()));

        // Obtener el id del ensayo aleatorio
        int idEnsayoAEliminar = ensayoAEliminar.getIdevento();

        // Eliminar el ensayo aleatorio
        Ensayo ensayo = ensayoService.buscarEnsayoPorId(idEnsayoAEliminar);
        ensayoService.eliminarEnsayo(ensayo);

        // Verificar que el ensayo fue eliminado correctamente
        assertEquals(ensayos.size() - 1, ensayoService.listarEnsayos().size());

    }
}
