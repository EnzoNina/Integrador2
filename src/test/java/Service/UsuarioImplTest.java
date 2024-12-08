package Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.repository.UsuarioRepository;
import utp.edu.codekion.finanzas.service.impl.UsuarioImpl;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioImpl usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByUsernameAndPassword() {
        String username = "testUser";
        String password = "testPass";
        Usuario usuario = new Usuario();
        usuario.setEmail(username);
        usuario.setPassword(password);

        when(usuarioRepository.findByUsernameAndPassword(username, password)).thenReturn(Optional.of(usuario));

        Optional<Usuario> result = usuarioService.findByUsernameAndPassword(username, password);

        assertTrue(result.isPresent());
        assertEquals(username, result.get().getUsername());
        assertEquals(password, result.get().getPassword());
    }

    @Test
    void testFindByUsername() {
        String username = "testUser";
        Usuario usuario = new Usuario();
        usuario.setEmail(username);

        when(usuarioRepository.findByUsername(username)).thenReturn(Optional.of(usuario));

        Optional<Usuario> result = usuarioService.findByUsername(username);

        assertTrue(result.isPresent());
        assertEquals(username, result.get().getUsername());
    }

    @Test
    void testFindById() {
        Integer id = 1;
        Usuario usuario = new Usuario();
        usuario.setId(id);

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        Usuario result = usuarioService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    void testSave() {
        Usuario usuario = new Usuario();
        usuario.setEmail("testUser");
        usuario.setPassword("testPass");

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario result = usuarioService.save(usuario);

        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
        assertEquals("testPass", result.getPassword());
    }
}