package utp.edu.codekion.finanzas.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import utp.edu.codekion.finanzas.controller.DashboardController;
import utp.edu.codekion.finanzas.model.Transacciones;
import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.model.dto.IngresoMesDto;
import utp.edu.codekion.finanzas.model.dto.TransaccionResponseDto;
import utp.edu.codekion.finanzas.service.IService.ITransaccionService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DashboardControllerTest {


    @Mock
    private ITransaccionService transaccionService;

    @InjectMocks
    private DashboardController dashboardController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void balanceTotal_ReturnsCorrectBalance() {
        when(transaccionService.balanceTotal(1)).thenReturn(new BigDecimal("1000.00"));

        BigDecimal result = dashboardController.balanceTotal(1);

        assertEquals(new BigDecimal("1000.00"), result);
    }

    @Test
    void ingresosPorMes_ReturnsIngresosList() {
        List<IngresoMesDto> ingresos = List.of(new IngresoMesDto(), new IngresoMesDto());
        when(transaccionService.ingresosPorMes(1)).thenReturn(ingresos);

        ResponseEntity<?> response = dashboardController.ingresosPorMes(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ingresos, ((Map<String, Object>) response.getBody()).get("ingresos"));
    }

    @Test
    void gastosPorMes_ReturnsGastosList() {
        List<IngresoMesDto> gastos = List.of(new IngresoMesDto(), new IngresoMesDto());
        when(transaccionService.gastosPorMes(1)).thenReturn(gastos);

        ResponseEntity<?> response = dashboardController.gastosPorMes(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(gastos, ((Map<String, Object>) response.getBody()).get("egresos"));
    }

    @Test
    void transaccionesRecientes_ReturnsTransaccionesList() {
        Usuario usuarioMock = new Usuario();
        usuarioMock.setNombres("Nombre Usuario");

        Transacciones transaccion1 = new Transacciones();
        transaccion1.setIdUsuario(usuarioMock);

        Transacciones transaccion2 = new Transacciones();
        transaccion2.setIdUsuario(usuarioMock);

        List<Transacciones> transacciones = List.of(transaccion1, transaccion2);
        when(transaccionService.transaccionesRecientes(1)).thenReturn(transacciones);

        List<TransaccionResponseDto> result = dashboardController.transaccionesRecientes(1);

        assertEquals(2, result.size());  // Cambié a 2 ya que la lista contiene dos transacciones
    }

}