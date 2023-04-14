/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.CastellersERP;

import cat.copernic.CastellersERP.general.serveis.UsuarioService;
import cat.copernic.CastellersERP.model.Salida;
import cat.copernic.CastellersERP.model.Usuario;
import cat.copernic.CastellersERP.salida.controllers.ControladorSalidas;
import cat.copernic.CastellersERP.salida.serveis.SalidaService;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Assert;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ExtendedModelMap;

/**
 *
 * @author Taufik
 */

@SpringBootTest // Indica que se ejecutará la prueba con un contexto de Spring Boot cargado.
@RunWith(MockitoJUnitRunner.class) // Indica que se usará el runner "MockitoJUnitRunner" para ejecutar la prueba.
public class SalidaControllerTest {
    @Mock // Indica que se usará un objeto "mock" de SalidaService.
    private SalidaService salidaServiceMock;

    @Mock // Indica que se usará un objeto "mock" de Model.
    private Model modelMock;

    @InjectMocks // Indica que se inyectarán los mocks creados en el controlador de Salidas.
    private ControladorSalidas controlador;

    @Test // Indica que el siguiente método es un test.
    public void editarSalidaTest() {
        // Crear una salida de prueba
        Salida salida = new Salida();
        salida.setIdevento(1);
        salida.setNombreEvento("Salida de prueba");
        salida.setFechaEvento(java.sql.Date.valueOf(LocalDate.now()));
        salida.setHorario("12:12:12");
        salida.setUbicacionEvento("Barcelona");
        salida.setParadita(false);

        // Simular el comportamiento del servicio de salida al buscar una salida por su ID
        Mockito.when(salidaServiceMock.cercarSalida(Mockito.any(Salida.class)))
                .thenReturn(salida);

        // Llamar al método de editar del controlador con una salida de prueba
        String resultado = controlador.editar(salida, modelMock);

        // Verificar que el modelo tiene una salida con los mismos valores que la de prueba
        Mockito.verify(modelMock).addAttribute("salida", salida);

        // Verificar que el resultado es la página de formulario de salida
        Assert.assertEquals("salida/anadirSalida", resultado);
    }
}
