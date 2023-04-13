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

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class CastellersErpApplicationTests {
    
    @Mock
    private SalidaService salidaServiceMock;

    @Mock
    private Model modelMock;

    @InjectMocks
    private ControladorSalidas controlador;

    @Test
    public void editarSalidaTest() {
        // Crear una salida de prueba
        Salida salida = new Salida();
        salida.setIdevento(1);
        salida.setNombreEvento("Salida de prueba");
        salida.setFechaEvento(java.sql.Date.valueOf(LocalDate.now()));
        salida.setHorario(Time.valueOf(LocalTime.of(10, 0, 0)));
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
