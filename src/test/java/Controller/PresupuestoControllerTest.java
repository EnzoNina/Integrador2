package Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import utp.edu.codekion.finanzas.controller.PresupuestoController;
import utp.edu.codekion.finanzas.model.Cuenta;
import utp.edu.codekion.finanzas.model.Presupuesto;
import utp.edu.codekion.finanzas.model.TipoTransaccion;
import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.model.UsuariosCategoria;
import utp.edu.codekion.finanzas.model.dto.*;
import utp.edu.codekion.finanzas.service.IService.ICuentasBancariasService;
import utp.edu.codekion.finanzas.service.IService.IPresupuestoService;
import utp.edu.codekion.finanzas.service.IService.IUsuarioCategoriaService;
import utp.edu.codekion.finanzas.service.IService.IUsuarioService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;





public class PresupuestoControllerTest {

    @InjectMocks
    private PresupuestoController presupuestoController;

    @Mock
    private IPresupuestoService presupuestoService;

    @Mock
    private IUsuarioService usuarioService;

    @Mock
    private IUsuarioCategoriaService usuariosCategoriaService;

    @Mock
    private ICuentasBancariasService cuentaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarPresupuesto() {
        PresupuestoDto dto = new PresupuestoDto();
        dto.setId_categoria_usuario(1);
        dto.setId_usuario(1);
        dto.setId_cuenta(1);
        dto.setNombre("Test");
        dto.setDescripcion("Test");
        dto.setMonto(new BigDecimal(100.0));

        UsuariosCategoria categoria = new UsuariosCategoria();
        categoria.setIdTipoTra(new TipoTransaccion(2,"a"));
        Usuario usuario = new Usuario();
        Cuenta cuenta = new Cuenta();

        when(usuariosCategoriaService.findById(1)).thenReturn(categoria);
        when(usuarioService.findById(1)).thenReturn(usuario);
        when(cuentaService.obtenerCuentaPorId(1)).thenReturn(cuenta);
        when(presupuestoService.findByUsuarioCategoria(categoria)).thenReturn(null);

        ResponseEntity<?> response = presupuestoController.guardarPresupuesto(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(usuariosCategoriaService, times(1)).findById(1);
        verify(usuarioService, times(1)).findById(1);
        verify(cuentaService, times(1)).obtenerCuentaPorId(1);
        verify(presupuestoService, times(1)).save(any(Presupuesto.class));
    }

    @Test
    public void testActualizarPresupuesto() {
        PresupuestoUpdateDto dto = new PresupuestoUpdateDto();
        dto.setNombre("Updated");
        dto.setDescripcion("Updated");
        dto.setMonto(new BigDecimal(0));
        dto.setId_cuenta("1");

        Presupuesto presupuesto = new Presupuesto();

        when(presupuestoService.findById(1)).thenReturn(presupuesto);
        when(cuentaService.obtenerCuentaPorId(1)).thenReturn(new Cuenta());

        ResponseEntity<?> response = presupuestoController.actualizarPresupuesto(dto, "1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(presupuestoService, times(1)).findById(1);
        verify(cuentaService, times(1)).obtenerCuentaPorId(1);
        verify(presupuestoService, times(1)).save(presupuesto);
    }
}