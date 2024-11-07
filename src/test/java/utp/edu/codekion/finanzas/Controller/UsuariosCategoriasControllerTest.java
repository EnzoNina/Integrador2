package utp.edu.codekion.finanzas.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import utp.edu.codekion.finanzas.controller.UsuariosCategoriasController;
import utp.edu.codekion.finanzas.model.Categoria;
import utp.edu.codekion.finanzas.model.TipoTransaccion;
import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.model.UsuariosCategoria;
import utp.edu.codekion.finanzas.model.dto.UsuariosCategoriasRequestDto;
import utp.edu.codekion.finanzas.model.dto.UsuariosCategoriasResponseDto;
import utp.edu.codekion.finanzas.service.IService.ICategoriaService;
import utp.edu.codekion.finanzas.service.IService.ITipoTransaccionService;
import utp.edu.codekion.finanzas.service.IService.IUsuarioCategoriaService;
import utp.edu.codekion.finanzas.service.IService.IUsuarioService;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UsuariosCategoriasControllerTest {


    @Mock
    private IUsuarioCategoriaService usuarioCategoriaService;

    @Mock
    private IUsuarioService usuarioService;

    @Mock
    private ITipoTransaccionService tipoTransaccionService;

    @Mock
    private ICategoriaService categoriaService;

    @InjectMocks
    private UsuariosCategoriasController usuariosCategoriasController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_ReturnsCategoria_WhenIdExists() {
        UsuariosCategoria categoria = new UsuariosCategoria();
        categoria.setId(1);
        categoria.setDescripcion("Test Description");
        when(usuarioCategoriaService.findById(1)).thenReturn(categoria);

        ResponseEntity<UsuariosCategoriasResponseDto> response = usuariosCategoriasController.findById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test Description", response.getBody().getDescripcion());
    }

    @Test
    void findById_ReturnsNotFound_WhenIdDoesNotExist() {
        when(usuarioCategoriaService.findById(1)).thenReturn(null);

        ResponseEntity<UsuariosCategoriasResponseDto> response = usuariosCategoriasController.findById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void listAll_ReturnsListOfCategorias_WhenUserIdExists() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        UsuariosCategoria categoria1 = new UsuariosCategoria();
        UsuariosCategoria categoria2 = new UsuariosCategoria();
        when(usuarioService.findById(1)).thenReturn(usuario);
        when(usuarioCategoriaService.findByUsuario(usuario)).thenReturn(List.of(categoria1, categoria2));

        List<UsuariosCategoriasResponseDto> response = usuariosCategoriasController.listAll(1);

        assertEquals(2, response.size());
    }

    @Test
    void save_ReturnsSavedCategoria_WhenDataIsValid() {
        UsuariosCategoriasRequestDto dto = new UsuariosCategoriasRequestDto();
        dto.setId_usuario("1");
        dto.setId_tipo_transaccion("1");
        dto.setId_categoria("1");
        dto.setDescripcion("Test Description");

        Usuario usuario = new Usuario();
        TipoTransaccion tipoTransaccion = new TipoTransaccion();
        Categoria categoria = new Categoria();
        UsuariosCategoria savedCategoria = new UsuariosCategoria();
        savedCategoria.setId(1);
        savedCategoria.setDescripcion("Test Description");

        when(usuarioService.findById(1)).thenReturn(usuario);
        when(tipoTransaccionService.findById(1)).thenReturn(tipoTransaccion);
        when(categoriaService.findById(1)).thenReturn(categoria);
        when(usuarioCategoriaService.save(savedCategoria)).thenReturn(savedCategoria);

        UsuariosCategoriasResponseDto response = usuariosCategoriasController.save(dto);

        assertEquals("Test Description", response.getDescripcion());
    }

    @Test
    void update_ReturnsUpdatedCategoria_WhenDataIsValid() {
        UsuariosCategoriasRequestDto dto = new UsuariosCategoriasRequestDto();
        dto.setId_usuario("1");
        dto.setId_tipo_transaccion("1");
        dto.setId_categoria("1");
        dto.setDescripcion("Updated Description");

        Usuario usuario = new Usuario();
        TipoTransaccion tipoTransaccion = new TipoTransaccion();
        Categoria categoria = new Categoria();
        UsuariosCategoria existingCategoria = new UsuariosCategoria();
        existingCategoria.setId(1);
        existingCategoria.setDescripcion("Old Description");

        when(usuarioService.findById(1)).thenReturn(usuario);
        when(tipoTransaccionService.findById(1)).thenReturn(tipoTransaccion);
        when(categoriaService.findById(1)).thenReturn(categoria);
        when(usuarioCategoriaService.findById(1)).thenReturn(existingCategoria);
        when(usuarioCategoriaService.update(existingCategoria)).thenReturn(existingCategoria);

        UsuariosCategoria response = usuariosCategoriasController.update(1, dto);

        assertEquals("Updated Description", response.getDescripcion());
    }

    @Test
    void delete_ReturnsOk_WhenCategoriaIsDeleted() {
        ResponseEntity<?> response = usuariosCategoriasController.delete(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Categoria eliminada correctamente", ((Map<String, Object>) response.getBody()).get("mensaje"));
    }

}
