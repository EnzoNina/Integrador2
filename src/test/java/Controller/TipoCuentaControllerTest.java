package Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import utp.edu.codekion.finanzas.controller.TipoCuentaController;
import utp.edu.codekion.finanzas.model.TipoCuenta;
import utp.edu.codekion.finanzas.service.IService.ITipoCuentaService;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;





public class TipoCuentaControllerTest {

    @Mock
    private ITipoCuentaService tipoCuentaService;

    @InjectMocks
    private TipoCuentaController tipoCuentaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarTipoCuenta() {
        // Arrange
        TipoCuenta tipoCuenta1 = new TipoCuenta();
        TipoCuenta tipoCuenta2 = new TipoCuenta();
        List<TipoCuenta> listaTipoCuenta = Arrays.asList(tipoCuenta1, tipoCuenta2);
        when(tipoCuentaService.findAll()).thenReturn(listaTipoCuenta);

        // Act
        ResponseEntity<?> responseEntity = tipoCuentaController.listarTipoCuenta();

        // Assert
        assertEquals(ResponseEntity.ok(listaTipoCuenta), responseEntity);
    }
}