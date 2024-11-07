package utp.edu.codekion.finanzas.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import utp.edu.codekion.finanzas.controller.RegistrarController;
import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.model.dto.RegistrarDto;
import utp.edu.codekion.finanzas.service.IService.IUsuarioService;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RegistrarControllerTest {


    @Mock
    private IUsuarioService usuarioService;

    @InjectMocks
    private RegistrarController registrarController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrar_ReturnsOkResponse_WhenUserIsCreatedSuccessfully() {
        RegistrarDto dto = new RegistrarDto();
        dto.setNombres("John");
        dto.setApellidop("Doe");
        dto.setApellidom("Smith");
        dto.setEmail("john.doe@example.com");
        dto.setPassword("password");
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        when(usuarioService.findByUsername(dto.getEmail())).thenReturn(Optional.empty());
        when(usuarioService.save(usuario)).thenReturn(usuario);

        ResponseEntity<?> response = registrarController.registrar(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuario creado correctamente", ((Map<String, Object>) response.getBody()).get("message"));
    }

    @Test
    void registrar_ReturnsBadRequest_WhenEmailAlreadyExists() {
        RegistrarDto dto = new RegistrarDto();
        dto.setNombres("John");
        dto.setApellidop("Doe");
        dto.setApellidom("Smith");
        dto.setEmail("john.doe@example.com");
        dto.setPassword("password");
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        when(usuarioService.findByUsername(dto.getEmail())).thenReturn(Optional.of(usuario));

        ResponseEntity<?> response = registrarController.registrar(dto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("El correo ya existe", response.getBody());
    }

    @Test
    void registrar_ReturnsInternalServerError_WhenRequiredFieldsAreMissing() {
        RegistrarDto dto = new RegistrarDto();
        dto.setNombres(null);
        dto.setApellidop("Doe");
        dto.setApellidom("Smith");
        dto.setEmail("john.doe@example.com");
        dto.setPassword("password");

        ResponseEntity<?> response = registrarController.registrar(dto);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error al crear el usuario: El nombre es requerido", ((Map<String, Object>) response.getBody()).get("message"));
    }

}