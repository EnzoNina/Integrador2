package Controller;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;





public class RegistrarControllerTest {

    @Mock
    private IUsuarioService usuarioService;

    @InjectMocks
    private RegistrarController registrarController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegistrar_Success() {
        RegistrarDto dto = new RegistrarDto();
        dto.setNombres("John");
        dto.setApellidop("Doe");
        dto.setApellidom("Smith");
        dto.setEmail("john.doe@example.com");
        dto.setPassword("password");

        when(usuarioService.findByUsername(dto.getEmail())).thenReturn(Optional.empty());
        when(usuarioService.save(any(Usuario.class))).thenReturn(new Usuario());

        ResponseEntity<?> response = registrarController.registrar(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuario creado correctamente", ((Map<String, Object>) response.getBody()).get("message"));
    }

    @Test
    public void testRegistrar_EmailAlreadyExists() {
        RegistrarDto dto = new RegistrarDto();
        dto.setNombres("John");
        dto.setApellidop("Doe");
        dto.setApellidom("Smith");
        dto.setEmail("john.doe@example.com");
        dto.setPassword("password");

        when(usuarioService.findByUsername(dto.getEmail())).thenReturn(Optional.of(new Usuario()));

        ResponseEntity<?> response = registrarController.registrar(dto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("El correo ya existe", response.getBody());
    }

    @Test
    public void testRegistrar_Exception() {
        RegistrarDto dto = new RegistrarDto();
        dto.setNombres("John");
        dto.setApellidop("Doe");
        dto.setApellidom("Smith");
        dto.setEmail("john.doe@example.com");
        dto.setPassword("password");

        when(usuarioService.findByUsername(dto.getEmail())).thenReturn(Optional.empty());
        when(usuarioService.save(any(Usuario.class))).thenThrow(new RuntimeException("Database error"));

        ResponseEntity<?> response = registrarController.registrar(dto);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error al crear el usuario: Database error", ((Map<String, Object>) response.getBody()).get("message"));
    }
}