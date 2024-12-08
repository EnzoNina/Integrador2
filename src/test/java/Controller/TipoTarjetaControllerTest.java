package Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import utp.edu.codekion.finanzas.controller.TipoTarjetaController;
import utp.edu.codekion.finanzas.service.IService.ITipoTarjetaService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;




public class TipoTarjetaControllerTest {

    @Mock
    private ITipoTarjetaService tipoTarjetaService;

    @InjectMocks
    private TipoTarjetaController tipoTarjetaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarTiposTarjetas() {
        // Arrange
        when(tipoTarjetaService.findAll()).thenReturn(new ArrayList<>());

        // Act
        ResponseEntity<?> response = tipoTarjetaController.listarTiposTarjetas();

        // Assert
        assertEquals(ResponseEntity.ok(new ArrayList<>()), response);
    }
}