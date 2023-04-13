package cat.copernic.CastellersERP;

import cat.copernic.CastellersERP.DAO.CastilloDAO;
import cat.copernic.CastellersERP.castillo.serveis.CastilloService;
import cat.copernic.CastellersERP.model.Castillo;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CastilloControlerTest {

    @Mock
    private CastilloDAO castilloDAO;

    @InjectMocks
    private CastilloService castilloService;

    @Test
    public void testLeerCastillos() {
        // Llama al método "leerCastillos" del servicio
        List<Castillo> castillos = castilloService.listarCastillos();
        
        verify(castilloDAO, times(1)).findAll();
        
        // Verifica que la lista de castillos no este
        assertTrue(castillos.size() > 0);
    }
    
}