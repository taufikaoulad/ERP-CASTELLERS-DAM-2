package cat.copernic.CastellersERP;

import cat.copernic.CastellersERP.DAO.UsuarioDAO;
import cat.copernic.CastellersERP.general.controllers.ControladorListarUsuario;
import cat.copernic.CastellersERP.general.serveis.UsuarioService;
import cat.copernic.CastellersERP.model.Usuario;
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
public class UserControllerTest {

    @Mock
    private UsuarioDAO usuarioDAO;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    public void testAfegirUsuario() {
        // Crea un objeto usuario de prueba
        Usuario usuario = new Usuario();
        usuario.setUsername("Juan Perez");
        usuario.setPassword("1234");

        // Llama al método "afegirUsuario" del servicio
        usuarioService.afegirUsuario(usuario);

        // Verifica que se llama al método "save" del DAO con el objeto de usuario correcto
        verify(usuarioDAO, times(1)).save(usuario);
    }

}
