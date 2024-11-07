package utp.edu.codekion.finanzas.Controller;

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
import static org.mockito.Mockito.when;

public class LoginControllerTest {


    @Mock
    private IUsuarioService usuarioService;

    @Mock
    private IAuthenticacionService authenticacionService;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_ReturnsOkResponse_WhenCredentialsAreValid() {
        LoginDto dto = new LoginDto();
        dto.setEmail("test@example.com");
        dto.setPassword("password");
        Usuario usuario = new Usuario();
        when(usuarioService.findByUsernameAndPassword(dto.getEmail(), dto.getPassword())).thenReturn(Optional.of(usuario));
        when(authenticacionService.getToken(usuario)).thenReturn("token");

        ResponseEntity<?> response = loginController.login(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuario logeado correctamente", ((Map<String, Object>) response.getBody()).get("message"));
        assertEquals("token", ((Map<String, Object>) response.getBody()).get("token"));
        assertEquals(usuario, ((Map<String, Object>) response.getBody()).get("data"));
    }

    @Test
    void login_ReturnsInternalServerError_WhenCredentialsAreInvalid() {
        LoginDto dto = new LoginDto();
        dto.setEmail("test@example.com");
        dto.setPassword("wrongpassword");
        when(usuarioService.findByUsernameAndPassword(dto.getEmail(), dto.getPassword())).thenReturn(Optional.empty());

        ResponseEntity<?> response = loginController.login(dto);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error al logear el usuario: No value present", ((Map<String, Object>) response.getBody()).get("message"));
    }

    @Test
    void login_ReturnsInternalServerError_WhenEmailIsNull() {
        LoginDto dto = new LoginDto();
        dto.setEmail(null);
        dto.setPassword("password");

        ResponseEntity<?> response = loginController.login(dto);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error al logear el usuario: El correo es requerido", ((Map<String, Object>) response.getBody()).get("message"));
    }

    @Test
    void login_ReturnsInternalServerError_WhenPasswordIsNull() {
        LoginDto dto = new LoginDto();
        dto.setEmail("test@example.com");
        dto.setPassword(null);

        ResponseEntity<?> response = loginController.login(dto);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error al logear el usuario: La contraseña es requerida", ((Map<String, Object>) response.getBody()).get("message"));
    }

}