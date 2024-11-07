package utp.edu.codekion.finanzas.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import utp.edu.codekion.finanzas.controller.PresupuestoController;
import utp.edu.codekion.finanzas.model.Presupuesto;
import utp.edu.codekion.finanzas.model.TipoTransaccion;
import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.model.UsuariosCategoria;
import utp.edu.codekion.finanzas.model.dto.FindPresupuestoDto;
import utp.edu.codekion.finanzas.model.dto.PresupuestoDto;
import utp.edu.codekion.finanzas.model.dto.PresupuestoUpdateDto;
import utp.edu.codekion.finanzas.model.dto.UsuarioDto;
import utp.edu.codekion.finanzas.service.IService.IPresupuestoService;
import utp.edu.codekion.finanzas.service.IService.IUsuarioCategoriaService;
import utp.edu.codekion.finanzas.service.IService.IUsuarioService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PresupuestoControllerTest {


    @Mock
    private IPresupuestoService presupuestoService;

    @Mock
    private IUsuarioService usuarioService;

    @Mock
    private IUsuarioCategoriaService usuariosCategoriaService;

    @InjectMocks
    private PresupuestoController presupuestoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarPresupuestosPorUsuario_ReturnsListOfPresupuestos() {
        UsuarioDto dto = new UsuarioDto();
        dto.setId_usuario("1");
        Usuario usuario = new Usuario();
        List<Presupuesto> presupuestos = List.of(new Presupuesto(), new Presupuesto());
        when(usuarioService.findById(1)).thenReturn(usuario);
        when(presupuestoService.listarPresupuestosByUsuario(usuario)).thenReturn(presupuestos);

        ResponseEntity<?> response = presupuestoController.listarPresupuestosPorUsuario(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, ((List<?>) ((Map<String, Object>) response.getBody()).get("presupuestos")).size());
    }

    @Test
    void mostrarPresupuesto_ReturnsPresupuesto_WhenIdExists() {
        FindPresupuestoDto dto = new FindPresupuestoDto();
        dto.setId_categoria_usuario("1");
        UsuariosCategoria categoria = new UsuariosCategoria();
        Presupuesto presupuesto = new Presupuesto();
        when(usuariosCategoriaService.findById(1)).thenReturn(categoria);
        when(presupuestoService.findByUsuarioCategoria(categoria)).thenReturn(presupuesto);

        ResponseEntity<?> response = presupuestoController.mostrarPresupuesto(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Presupuesto encontrado", ((Map<String, Object>) response.getBody()).get("mensaje"));
    }

    @Test
    void mostrarPresupuesto_ReturnsNotFound_WhenIdDoesNotExist() {
        FindPresupuestoDto dto = new FindPresupuestoDto();
        dto.setId_categoria_usuario("1");
        UsuariosCategoria categoria = new UsuariosCategoria();
        when(usuariosCategoriaService.findById(1)).thenReturn(categoria);
        when(presupuestoService.findByUsuarioCategoria(categoria)).thenReturn(null);

        ResponseEntity<?> response = presupuestoController.mostrarPresupuesto(dto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Presupuesto no encontrado", ((Map<String, Object>) response.getBody()).get("mensaje"));
    }

    @Test
    void guardarPresupuesto_ReturnsOk_WhenPresupuestoIsSaved() {
        PresupuestoDto dto = new PresupuestoDto();
        dto.setId_categoria_usuario(1);
        dto.setId_usuario(1);
        UsuariosCategoria categoria = new UsuariosCategoria();
        Usuario usuario = new Usuario();
        when(usuariosCategoriaService.findById(1)).thenReturn(categoria);
        when(usuarioService.findById(1)).thenReturn(usuario);
        when(presupuestoService.findByUsuarioCategoria(categoria)).thenReturn(null);

        ResponseEntity<?> response = presupuestoController.guardarPresupuesto(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Presupuesto guardado correctamente", ((Map<String, Object>) response.getBody()).get("mensaje"));
    }

    @Test
    void guardarPresupuesto_ReturnsBadRequest_WhenCategoriaIsNotEgreso() {
        PresupuestoDto dto = new PresupuestoDto();
        dto.setId_categoria_usuario(1);
        UsuariosCategoria categoria = new UsuariosCategoria();
        categoria.setIdTipoTra(new TipoTransaccion(1,"asdasdasdasd")); // Not an egreso
        when(usuariosCategoriaService.findById(1)).thenReturn(categoria);

        ResponseEntity<?> response = presupuestoController.guardarPresupuesto(dto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("La categoría no es de tipo egreso", ((Map<String, Object>) response.getBody()).get("mensaje"));
    }

    @Test
    void actualizarPresupuesto_ReturnsOk_WhenPresupuestoIsUpdated() {
        PresupuestoUpdateDto dto = new PresupuestoUpdateDto();
        dto.setNombre("Updated Name");
        dto.setDescripcion("Updated Description");
        dto.setMonto(BigDecimal.valueOf(1000.0));
        Presupuesto presupuesto = new Presupuesto();
        when(presupuestoService.findById(1)).thenReturn(presupuesto);

        ResponseEntity<?> response = presupuestoController.actualizarPresupuesto(dto, "1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Presupuesto actualizado correctamente", ((Map<String, Object>) response.getBody()).get("mensaje"));
    }

    @Test
    void actualizarPresupuesto_ReturnsNotFound_WhenPresupuestoDoesNotExist() {
        PresupuestoUpdateDto dto = new PresupuestoUpdateDto();
        when(presupuestoService.findById(1)).thenReturn(null);

        ResponseEntity<?> response = presupuestoController.actualizarPresupuesto(dto, "1");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Presupuesto no existe.", ((Map<String, Object>) response.getBody()).get("mensaje"));
    }

}
