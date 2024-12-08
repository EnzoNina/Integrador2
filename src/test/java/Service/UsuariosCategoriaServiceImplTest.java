package Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.model.UsuariosCategoria;
import utp.edu.codekion.finanzas.repository.UsuariosCategoriaRepository;
import utp.edu.codekion.finanzas.service.impl.UsuariosCategoriaServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuariosCategoriaServiceImplTest {

    @Mock
    private UsuariosCategoriaRepository usuariosCategoriaRepository;

    @InjectMocks
    private UsuariosCategoriaServiceImpl usuariosCategoriaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByUsuario() {
        Usuario usuario = new Usuario();
        UsuariosCategoria categoria1 = new UsuariosCategoria();
        UsuariosCategoria categoria2 = new UsuariosCategoria();
        List<UsuariosCategoria> categorias = Arrays.asList(categoria1, categoria2);

        when(usuariosCategoriaRepository.findByUsuario(usuario)).thenReturn(categorias);

        List<UsuariosCategoria> result = usuariosCategoriaService.findByUsuario(usuario);

        assertEquals(2, result.size());
        verify(usuariosCategoriaRepository, times(1)).findByUsuario(usuario);
    }

    @Test
    void testFindById() {
        UsuariosCategoria categoria = new UsuariosCategoria();
        when(usuariosCategoriaRepository.findById(1)).thenReturn(Optional.of(categoria));

        UsuariosCategoria result = usuariosCategoriaService.findById(1);

        assertNotNull(result);
        verify(usuariosCategoriaRepository, times(1)).findById(1);
    }

    @Test
    void testSave() {
        UsuariosCategoria categoria = new UsuariosCategoria();
        when(usuariosCategoriaRepository.save(categoria)).thenReturn(categoria);

        UsuariosCategoria result = usuariosCategoriaService.save(categoria);

        assertNotNull(result);
        verify(usuariosCategoriaRepository, times(1)).save(categoria);
    }

    @Test
    void testDelete() {
        doNothing().when(usuariosCategoriaRepository).deleteById(1);

        usuariosCategoriaService.delete(1);

        verify(usuariosCategoriaRepository, times(1)).deleteById(1);
    }

    @Test
    void testUpdate() {
        UsuariosCategoria categoria = new UsuariosCategoria();
        when(usuariosCategoriaRepository.save(categoria)).thenReturn(categoria);

        UsuariosCategoria result = usuariosCategoriaService.update(categoria);

        assertNotNull(result);
        verify(usuariosCategoriaRepository, times(1)).save(categoria);
    }
}