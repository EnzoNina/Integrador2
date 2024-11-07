package utp.edu.codekion.finanzas.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import utp.edu.codekion.finanzas.controller.TransaccionController;
import utp.edu.codekion.finanzas.model.*;
import utp.edu.codekion.finanzas.model.dto.TransaccionDto;
import utp.edu.codekion.finanzas.model.dto.UsuarioDto;
import utp.edu.codekion.finanzas.service.IService.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TransaccionControllerTest {


    @Mock
    private ITransaccionService transaccionService;

    @Mock
    private IUsuarioService usuarioService;

    @Mock
    private IUsuarioCategoriaService usuarioCategoriaService;

    @Mock
    private ITipoConceptoService tipoConceptoService;

    @Mock
    private IFrecuenciaService frecuenciaService;

    @Mock
    private IDivisaService divisaService;

    @Mock
    private IPresupuestoService presupuestoService;

    @InjectMocks
    private TransaccionController transaccionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarTransacciones_ReturnsListOfTransacciones() {
        UsuarioDto dto = new UsuarioDto();
        dto.setId_usuario("1");
        Usuario usuario = new Usuario();
        List<Transacciones> transaccionesList = List.of(new Transacciones(), new Transacciones());
        when(usuarioService.findById(1)).thenReturn(usuario);
        when(transaccionService.findByUsuario(usuario)).thenReturn(transaccionesList);

        ResponseEntity<?> response = transaccionController.listarTransacciones(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, ((List<?>) ((Map<String, Object>) response.getBody()).get("transacciones")).size());
    }

    @Test
    void buscarTransaccion_ReturnsTransaccion_WhenIdExists() {
        Transacciones transaccion = new Transacciones();
        when(transaccionService.findById(1)).thenReturn(transaccion);

        ResponseEntity<?> response = transaccionController.buscarTransaccion(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Transacción encontrada", ((Map<String, Object>) response.getBody()).get("mensaje"));
    }

    @Test
    void buscarTransaccion_ReturnsNotFound_WhenIdDoesNotExist() {
        when(transaccionService.findById(1)).thenReturn(null);

        ResponseEntity<?> response = transaccionController.buscarTransaccion(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Transacción no encontrada", ((Map<String, Object>) response.getBody()).get("mensaje"));
    }

    @Test
    void guardarTransaccion_ReturnsOk_WhenTransaccionIsSaved() {
        TransaccionDto dto = new TransaccionDto();
        dto.setId_tipo_categoria("1");
        dto.setId_usuario("1");
        dto.setId_tipo_concepto("1");
        dto.setId_tipo_frecuencia("1");
        dto.setId_tipo_divisa("1");
        dto.setMonto(BigDecimal.valueOf(100));
        UsuariosCategoria categoria = new UsuariosCategoria();
        Usuario usuario = new Usuario();
        TipoConcepto tipoConcepto = new TipoConcepto();
        Frecuencia frecuencia = new Frecuencia();
        Divisa divisa = new Divisa();
        when(usuarioCategoriaService.findById(1)).thenReturn(categoria);
        when(usuarioService.findById(1)).thenReturn(usuario);
        when(tipoConceptoService.findById(1)).thenReturn(tipoConcepto);
        when(frecuenciaService.findById(1)).thenReturn(frecuencia);
        when(divisaService.findById(1)).thenReturn(divisa);

        ResponseEntity<?> response = transaccionController.guardarTransaccion(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Transacción guardada correctamente", ((Map<String, Object>) response.getBody()).get("mensaje"));
    }

    @Test
    void guardarTransaccion_ReturnsBadRequest_WhenMontoExceedsPresupuesto() {
        TransaccionDto dto = new TransaccionDto();
        dto.setId_tipo_categoria("1");
        dto.setId_usuario("1");
        dto.setMonto(BigDecimal.valueOf(1000));
        UsuariosCategoria categoria = new UsuariosCategoria();
        Usuario usuario = new Usuario();
        Presupuesto presupuesto = new Presupuesto();
        presupuesto.setMonto(BigDecimal.valueOf(500));
        when(usuarioCategoriaService.findById(1)).thenReturn(categoria);
        when(usuarioService.findById(1)).thenReturn(usuario);
        when(presupuestoService.findByUsuarioCategoria(categoria)).thenReturn(presupuesto);
        when(transaccionService.sumarTransaccionesPorCategoriaAndUsuario(1, usuario)).thenReturn(BigDecimal.valueOf(400));

        ResponseEntity<?> response = transaccionController.guardarTransaccion(dto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("El monto de la transacción supera el presupuesto", ((Map<String, Object>) response.getBody()).get("mensaje"));
    }

}