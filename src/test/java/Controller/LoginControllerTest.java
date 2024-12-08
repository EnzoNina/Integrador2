package Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import utp.edu.codekion.finanzas.controller.LoginController;
import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.model.dto.LoginDto;
import utp.edu.codekion.finanzas.service.IService.IAuthenticacionService;
import utp.edu.codekion.finanzas.service.IService.IUsuarioService;

import java.util.Map;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;



public class LoginControllerTest {

    @Mock
    private IUsuarioService usuarioService;

    @Mock
    private IAuthenticacionService authenticacionService;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoginSuccess() {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("test@example.com");
        loginDto.setPassword("password");

        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");

        when(usuarioService.findByUsernameAndPassword(anyString(), anyString())).thenReturn(Optional.of(usuario));
        when(authenticacionService.getToken(usuario)).thenReturn("mockToken");

        ResponseEntity<?> response = loginController.login(loginDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuario logeado correctamente", ((Map) response.getBody()).get("message"));
        assertEquals("mockToken", ((Map) response.getBody()).get("token"));
    }

    @Test
    public void testLoginFailure() {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("test@example.com");
        loginDto.setPassword("wrongpassword");

        when(usuarioService.findByUsernameAndPassword(anyString(), anyString())).thenReturn(Optional.empty());

        ResponseEntity<?> response = loginController.login(loginDto);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error al logear el usuario: No value present", ((Map) response.getBody()).get("message"));
    }
}