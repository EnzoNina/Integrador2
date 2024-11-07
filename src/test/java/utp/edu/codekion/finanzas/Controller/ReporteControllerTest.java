package utp.edu.codekion.finanzas.Controller;

import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utp.edu.codekion.finanzas.controller.ReporteController;
import utp.edu.codekion.finanzas.model.ResumenTransacciones;
import utp.edu.codekion.finanzas.model.Transacciones;
import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.model.dto.FechasAndUsuarioDto;
import utp.edu.codekion.finanzas.service.IService.IResumenTransaccionService;
import utp.edu.codekion.finanzas.service.IService.ITransaccionService;
import utp.edu.codekion.finanzas.service.IService.IUsuarioService;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ReporteControllerTest {


    @Mock
    private ITransaccionService transaccionService;

    @Mock
    private IResumenTransaccionService resumenTransaccionService;

    @Mock
    private IUsuarioService usuarioService;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private ReporteController reporteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generarReporte_ReturnsPdfResponse_WhenDataIsValid() throws IOException {
        FechasAndUsuarioDto fechasDto = new FechasAndUsuarioDto();
        fechasDto.setFechaInicio(LocalDate.parse("2023-01-01"));
        fechasDto.setFechaFin(LocalDate.parse("2023-01-31"));
        fechasDto.setId_usuario("1");

        List<Transacciones> transaccionesLst = List.of(new Transacciones(), new Transacciones());
        BigDecimal ingresos = BigDecimal.valueOf(1000);
        BigDecimal egresos = BigDecimal.valueOf(500);
        Usuario usuario = new Usuario();
        ResumenTransacciones resumenTransacciones = new ResumenTransacciones();
        resumenTransacciones.setIdUsuario(usuario);
        resumenTransacciones.setPeriodo("2023-01-01 - 2023-01-31");
        resumenTransacciones.setTransacciones(Map.of("1", new Transacciones()));
        resumenTransacciones.setTotalIngresos(ingresos);
        resumenTransacciones.setTotalEgresos(egresos);

        LocalDate fechaInicio = LocalDate.parse("2023-01-01");
        LocalDate fechaFin = LocalDate.parse("2023-01-31");

        when(transaccionService.findByFechaTransaccionBetween(fechaInicio, fechaFin)).thenReturn(transaccionesLst);
        when(transaccionService.totalIngresosEntreFechas(fechaInicio, fechaFin,1)).thenReturn(ingresos);
        when(transaccionService.totalEgresosEntreFechas(fechaInicio, fechaFin,1)).thenReturn(egresos);
        when(usuarioService.findById(1)).thenReturn(usuario);
        when(resumenTransaccionService.save(resumenTransacciones)).thenReturn(resumenTransacciones);

        reporteController.generarReporte(fechasDto, response);

        assertEquals("application/pdf", response.getContentType());
        assertEquals("attachment; filename=reporte.pdf", response.getHeader("Content-Disposition"));
    }

    @Test
    void generarReporte_ThrowsIOException_WhenResponseFails() throws IOException {
        LocalDate fechaInicio = LocalDate.parse("2023-01-01");
        LocalDate fechaFin = LocalDate.parse("2023-01-31");
        FechasAndUsuarioDto fechasDto = new FechasAndUsuarioDto();
        fechasDto.setFechaInicio(fechaInicio);
        fechasDto.setFechaFin(fechaFin);
        fechasDto.setId_usuario("1");

        List<Transacciones> transaccionesLst = List.of(new Transacciones(), new Transacciones());
        BigDecimal ingresos = BigDecimal.valueOf(1000);
        BigDecimal egresos = BigDecimal.valueOf(500);
        Usuario usuario = new Usuario();
        ResumenTransacciones resumenTransacciones = new ResumenTransacciones();
        resumenTransacciones.setIdUsuario(usuario);
        resumenTransacciones.setPeriodo("2023-01-01 - 2023-01-31");
        resumenTransacciones.setTransacciones(Map.of("1", new Transacciones()));
        resumenTransacciones.setTotalIngresos(ingresos);
        resumenTransacciones.setTotalEgresos(egresos);

        when(transaccionService.findByFechaTransaccionBetween(fechaInicio, fechaFin)).thenReturn(transaccionesLst);
        when(transaccionService.totalIngresosEntreFechas(fechaInicio, fechaFin,1)).thenReturn(ingresos);
        when(transaccionService.totalEgresosEntreFechas(fechaInicio, fechaFin,1)).thenReturn(egresos);
        when(usuarioService.findById(1)).thenReturn(usuario);
        when(resumenTransaccionService.save(resumenTransacciones)).thenReturn(resumenTransacciones);

        when(response.getOutputStream()).thenThrow(new IOException());

        reporteController.generarReporte(fechasDto, response);

        // No assertions needed as we are testing exception handling
    }

}