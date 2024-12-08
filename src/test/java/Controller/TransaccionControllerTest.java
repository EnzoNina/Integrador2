package Controller;

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
import utp.edu.codekion.finanzas.model.dto.TransaccionResponseDto;
import utp.edu.codekion.finanzas.model.dto.UsuarioDto;
import utp.edu.codekion.finanzas.service.IService.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;





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

    @Mock
    private ICuentasBancariasService cuentaService;

    @InjectMocks
    private TransaccionController transaccionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testBuscarTransaccionNotFound() {
        when(transaccionService.findById(1)).thenReturn(null);

        ResponseEntity<?> response = transaccionController.buscarTransaccion(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(transaccionService, times(1)).findById(1);
    }

}