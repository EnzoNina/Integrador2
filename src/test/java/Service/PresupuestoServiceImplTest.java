package Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utp.edu.codekion.finanzas.model.Cuenta;
import utp.edu.codekion.finanzas.model.Presupuesto;
import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.model.UsuariosCategoria;
import utp.edu.codekion.finanzas.repository.PresupuestoRepository;
import utp.edu.codekion.finanzas.service.impl.PresupuestoServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;





class PresupuestoServiceImplTest {

    @Mock
    private PresupuestoRepository presupuestoRepository;

    @InjectMocks
    private PresupuestoServiceImpl presupuestoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarPresupuestosByUsuario() {
        Usuario usuario = new Usuario();
        Cuenta cuenta = new Cuenta();
        List<Presupuesto> presupuestos = Arrays.asList(new Presupuesto(), new Presupuesto());

        when(presupuestoRepository.listarPresupuestosByCategoriaAndUsuario(usuario, cuenta)).thenReturn(presupuestos);

        List<Presupuesto> result = presupuestoService.listarPresupuestosByUsuario(usuario, cuenta);

        assertEquals(presupuestos, result);
        verify(presupuestoRepository, times(1)).listarPresupuestosByCategoriaAndUsuario(usuario, cuenta);
    }

    @Test
    void testFindById() {
        Integer id = 1;
        Presupuesto presupuesto = new Presupuesto();

        when(presupuestoRepository.findById(id)).thenReturn(Optional.of(presupuesto));

        Presupuesto result = presupuestoService.findById(id);

        assertEquals(presupuesto, result);
        verify(presupuestoRepository, times(1)).findById(id);
    }

    @Test
    void testFindByIdNotFound() {
        Integer id = 1;

        when(presupuestoRepository.findById(id)).thenReturn(Optional.empty());

        Presupuesto result = presupuestoService.findById(id);

        assertNull(result);
        verify(presupuestoRepository, times(1)).findById(id);
    }

    @Test
    void testFindByUsuarioCategoria() {
        UsuariosCategoria usuariosCategoria = new UsuariosCategoria();
        Presupuesto presupuesto = new Presupuesto();

        when(presupuestoRepository.findByUsuarioCategoria(usuariosCategoria)).thenReturn(presupuesto);

        Presupuesto result = presupuestoService.findByUsuarioCategoria(usuariosCategoria);

        assertEquals(presupuesto, result);
        verify(presupuestoRepository, times(1)).findByUsuarioCategoria(usuariosCategoria);
    }

    @Test
    void testSave() {
        Presupuesto presupuesto = new Presupuesto();

        when(presupuestoRepository.save(presupuesto)).thenReturn(presupuesto);

        Presupuesto result = presupuestoService.save(presupuesto);

        assertEquals(presupuesto, result);
        verify(presupuestoRepository, times(1)).save(presupuesto);
    }
}