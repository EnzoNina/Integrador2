package Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;





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
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        UsuariosCategoria usuarioCategoria = new UsuariosCategoria();
        usuarioCategoria.setId(1);
        usuarioCategoria.setDescripcion("Test Description");
        TipoTransaccion tipoTransaccion = new TipoTransaccion();
        tipoTransaccion.setDescripcion("Test TipoTransaccion");
        Categoria categoria = new Categoria();
        categoria.setDescripcion("Test Categoria");
        usuarioCategoria.setIdTipoTra(tipoTransaccion);
        usuarioCategoria.setIdTipoCat(categoria);

        when(usuarioCategoriaService.findById(1)).thenReturn(usuarioCategoria);

        ResponseEntity<UsuariosCategoriasResponseDto> response = usuariosCategoriasController.findById(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("1", response.getBody().getId());
        assertEquals("Test Categoria", response.getBody().getCategoria());
        assertEquals("Test TipoTransaccion", response.getBody().getTipo_transaccion());
        assertEquals("Test Description", response.getBody().getDescripcion());
    }

    @Test
    public void testListAll() {
        Usuario usuario = new Usuario();
        usuario.setId(1);

        UsuariosCategoria usuarioCategoria1 = new UsuariosCategoria();
        usuarioCategoria1.setId(1);
        usuarioCategoria1.setDescripcion("Test Description 1");
        TipoTransaccion tipoTransaccion1 = new TipoTransaccion();
        tipoTransaccion1.setDescripcion("Test TipoTransaccion 1");
        Categoria categoria1 = new Categoria();
        categoria1.setDescripcion("Test Categoria 1");
        usuarioCategoria1.setIdTipoTra(tipoTransaccion1);
        usuarioCategoria1.setIdTipoCat(categoria1);

        UsuariosCategoria usuarioCategoria2 = new UsuariosCategoria();
        usuarioCategoria2.setId(2);
        usuarioCategoria2.setDescripcion("Test Description 2");
        TipoTransaccion tipoTransaccion2 = new TipoTransaccion();
        tipoTransaccion2.setDescripcion("Test TipoTransaccion 2");
        Categoria categoria2 = new Categoria();
        categoria2.setDescripcion("Test Categoria 2");
        usuarioCategoria2.setIdTipoTra(tipoTransaccion2);
        usuarioCategoria2.setIdTipoCat(categoria2);

        when(usuarioService.findById(1)).thenReturn(usuario);
        when(usuarioCategoriaService.findByUsuario(usuario)).thenReturn(Arrays.asList(usuarioCategoria1, usuarioCategoria2));

        List<UsuariosCategoriasResponseDto> responseList = usuariosCategoriasController.listAll(1);

        assertEquals(2, responseList.size());
        assertEquals("1", responseList.get(0).getId());
        assertEquals("Test Categoria 1", responseList.get(0).getCategoria());
        assertEquals("Test TipoTransaccion 1", responseList.get(0).getTipo_transaccion());
        assertEquals("Test Description 1", responseList.get(0).getDescripcion());

        assertEquals("2", responseList.get(1).getId());
        assertEquals("Test Categoria 2", responseList.get(1).getCategoria());
        assertEquals("Test TipoTransaccion 2", responseList.get(1).getTipo_transaccion());
        assertEquals("Test Description 2", responseList.get(1).getDescripcion());
    }

    @Test
    public void testSave() {
        UsuariosCategoriasRequestDto requestDto = new UsuariosCategoriasRequestDto();
        requestDto.setId_usuario("1");
        requestDto.setId_tipo_transaccion("1");
        requestDto.setId_categoria("1");
        requestDto.setDescripcion("Test Description");

        Usuario usuario = new Usuario();
        usuario.setId(1);
        TipoTransaccion tipoTransaccion = new TipoTransaccion();
        tipoTransaccion.setId(1);
        Categoria categoria = new Categoria();
        categoria.setId(1);

        UsuariosCategoria usuarioCategoria = new UsuariosCategoria();
        usuarioCategoria.setId(1);
        usuarioCategoria.setIdUsuario(usuario);
        usuarioCategoria.setIdTipoTra(tipoTransaccion);
        usuarioCategoria.setIdTipoCat(categoria);
        usuarioCategoria.setDescripcion("Test Description");

        when(usuarioService.findById(1)).thenReturn(usuario);
        when(tipoTransaccionService.findById(1)).thenReturn(tipoTransaccion);
        when(categoriaService.findById(1)).thenReturn(categoria);
        when(usuarioCategoriaService.save(any(UsuariosCategoria.class))).thenReturn(usuarioCategoria);

        UsuariosCategoriasResponseDto response = usuariosCategoriasController.save(requestDto);

        assertEquals("1", response.getId());
        assertEquals("Test Categoria", response.getCategoria());
        assertEquals("Test TipoTransaccion", response.getTipo_transaccion());
        assertEquals("Test Description", response.getDescripcion());
    }

    @Test
    public void testUpdate() {
        UsuariosCategoriasRequestDto requestDto = new UsuariosCategoriasRequestDto();
        requestDto.setId_usuario("1");
        requestDto.setId_tipo_transaccion("1");
        requestDto.setId_categoria("1");
        requestDto.setDescripcion("Updated Description");

        Usuario usuario = new Usuario();
        usuario.setId(1);
        TipoTransaccion tipoTransaccion = new TipoTransaccion();
        tipoTransaccion.setId(1);
        Categoria categoria = new Categoria();
        categoria.setId(1);

        UsuariosCategoria usuarioCategoria = new UsuariosCategoria();
        usuarioCategoria.setId(1);
        usuarioCategoria.setIdUsuario(usuario);
        usuarioCategoria.setIdTipoTra(tipoTransaccion);
        usuarioCategoria.setIdTipoCat(categoria);
        usuarioCategoria.setDescripcion("Test Description");

        when(usuarioService.findById(1)).thenReturn(usuario);
        when(tipoTransaccionService.findById(1)).thenReturn(tipoTransaccion);
        when(categoriaService.findById(1)).thenReturn(categoria);
        when(usuarioCategoriaService.findById(1)).thenReturn(usuarioCategoria);
        when(usuarioCategoriaService.update(any(UsuariosCategoria.class))).thenReturn(usuarioCategoria);

        UsuariosCategoria response = usuariosCategoriasController.update(1, requestDto);

        assertEquals(1, response.getId());
        assertEquals("Updated Description", response.getDescripcion());
    }

    @Test
    public void testDelete() {
        doNothing().when(usuarioCategoriaService).delete(1);

        ResponseEntity<?> response = usuariosCategoriasController.delete(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Categoria eliminada correctamente", ((Map<String, Object>) response.getBody()).get("mensaje"));
    }
}