package Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import utp.edu.codekion.finanzas.controller.DashboardController;
import utp.edu.codekion.finanzas.model.Transacciones;
import utp.edu.codekion.finanzas.model.dto.CategoriaGastoDto;
import utp.edu.codekion.finanzas.model.dto.IngresoMesDto;
import utp.edu.codekion.finanzas.model.dto.TransaccionResponseDto;
import utp.edu.codekion.finanzas.service.IService.ITransaccionService;
import java.math.BigDecimal;
import java.util.Arrays;
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
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBalanceTotal() {
        Integer id = 1;
        Integer idCuenta = 1;
        BigDecimal expectedBalance = BigDecimal.valueOf(1000);

        when(transaccionService.balanceTotal(id, idCuenta)).thenReturn(expectedBalance);

        BigDecimal actualBalance = dashboardController.balanceTotal(id, idCuenta);

        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void testIngresosPorMes() {
        Integer id = 1;
        List<IngresoMesDto> expectedIngresos = Arrays.asList(new IngresoMesDto(), new IngresoMesDto());

        when(transaccionService.ingresosPorMes(id)).thenReturn(expectedIngresos);

        ResponseEntity<?> response = dashboardController.ingresosPorMes(id);

        assertEquals(expectedIngresos, ((List<?>) ((Map<String, Object>) response.getBody()).get("ingresos")));
        assertEquals("success", ((Map<String, Object>) response.getBody()).get("status"));
    }

    @Test
    public void testIngresosPorMesAndCuenta() {
        Integer id = 1;
        Integer idCuenta = 1;
        List<IngresoMesDto> expectedIngresos = Arrays.asList(new IngresoMesDto(), new IngresoMesDto());

        when(transaccionService.ingresosPorMesAndCuenta(id, idCuenta)).thenReturn(expectedIngresos);

        ResponseEntity<?> response = dashboardController.ingresosPorMesAndCuenta(id, idCuenta);

        assertEquals(expectedIngresos, ((List<?>) ((Map<String, Object>) response.getBody()).get("ingresos")));
        assertEquals("success", ((Map<String, Object>) response.getBody()).get("status"));
    }

    @Test
    public void testGastosPorMes() {
        Integer id = 1;
        List<IngresoMesDto> expectedGastos = Arrays.asList(new IngresoMesDto(), new IngresoMesDto());

        when(transaccionService.gastosPorMes(id)).thenReturn(expectedGastos);

        ResponseEntity<?> response = dashboardController.gastosPorMes(id);

        assertEquals(expectedGastos, ((List<?>) ((Map<String, Object>) response.getBody()).get("egresos")));
        assertEquals("success", ((Map<String, Object>) response.getBody()).get("status"));
    }

    @Test
    public void testGastosPorMesAndCuenta() {
        Integer id = 1;
        Integer idCuenta = 1;
        List<IngresoMesDto> expectedGastos = Arrays.asList(new IngresoMesDto(), new IngresoMesDto());

        when(transaccionService.gastosPorMesAndCuenta(id, idCuenta)).thenReturn(expectedGastos);

        ResponseEntity<?> response = dashboardController.gastosPorMes(id, idCuenta);

        assertEquals(expectedGastos, ((List<?>) ((Map<String, Object>) response.getBody()).get("egresos")));
        assertEquals("success", ((Map<String, Object>) response.getBody()).get("status"));
    }

    @Test
    public void testGastosPorCategoria() {
        Integer id = 1;
        List<?> expectedGastos = Arrays.asList(new Object(), new Object());

        when(transaccionService.gastosPorCategoria(id)).thenReturn((List<CategoriaGastoDto>) expectedGastos);

        ResponseEntity<?> response = dashboardController.gastosPorCategoria(id);

        assertEquals(expectedGastos, ((Map<String, Object>) response.getBody()).get("gastos"));
        assertEquals("success", ((Map<String, Object>) response.getBody()).get("status"));
    }

    @Test
    public void testGastosPorCategoriaAndCuenta() {
        Integer id = 1;
        Integer idCuenta = 1;
        List<?> expectedGastos = Arrays.asList(new Object(), new Object());

        when(transaccionService.gastosPorCategoriaAndCuenta(id, idCuenta))
                .thenReturn((List<CategoriaGastoDto>) expectedGastos);

        ResponseEntity<?> response = dashboardController.gastosPorCategoria(id, idCuenta);

        assertEquals(expectedGastos, ((Map<String, Object>) response.getBody()).get("gastos"));
        assertEquals("success", ((Map<String, Object>) response.getBody()).get("status"));
    }
}