package Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import utp.edu.codekion.finanzas.controller.ChatBotController;
import utp.edu.codekion.finanzas.model.Transacciones;
import utp.edu.codekion.finanzas.model.dto.CategoriaGastoDto;
import utp.edu.codekion.finanzas.model.dto.IngresoMesDto;
import utp.edu.codekion.finanzas.model.dto.UserMessage;
import utp.edu.codekion.finanzas.service.IService.ITransaccionService;
import utp.edu.codekion.finanzas.utils.Gemini;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ChatBotControllerTest {

    @Mock
    private Gemini gemini;

    @Mock
    private ITransaccionService transaccionService;

    @InjectMocks
    private ChatBotController chatBotController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testEnviarMensaje_Success() throws Exception {
        UserMessage userMessage = new UserMessage();
        userMessage.setId_usuario("1");
        userMessage.setId_cuenta("1");
        userMessage.setMessage("Hello");

        when(transaccionService.balanceTotal(1, 1)).thenReturn(BigDecimal.valueOf(1000));
        when(transaccionService.ingresosPorMesAndCuenta(1, 1)).thenReturn(Collections.emptyList());
        when(transaccionService.gastosPorMesAndCuenta(1, 1)).thenReturn(Collections.emptyList());
        when(transaccionService.transaccionesRecientesAndCuenta(1, 1)).thenReturn(Collections.emptyList());
        when(transaccionService.gastosPorCategoria(1)).thenReturn(Collections.emptyList());
        when(gemini.obtenerRespuesta("Hello")).thenReturn("Hi there!");

        ResponseEntity<?> responseEntity = chatBotController.enviarMensaje(userMessage);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("Hi there!", ((Map<String, Object>) responseEntity.getBody()).get("respuesta"));

        verify(transaccionService).balanceTotal(1, 1);
        verify(transaccionService).ingresosPorMesAndCuenta(1, 1);
        verify(transaccionService).gastosPorMesAndCuenta(1, 1);
        verify(transaccionService).transaccionesRecientesAndCuenta(1, 1);
        verify(transaccionService).gastosPorCategoria(1);
        verify(gemini).setBalanceTotal(BigDecimal.valueOf(1000));
        verify(gemini).setLstIngresos(Collections.emptyList());
        verify(gemini).setLstEgreso(Collections.emptyList());
        verify(gemini).setTransacciones(Collections.emptyList());
        verify(gemini).setCategoriaGastoDtos(Collections.emptyList());
        verify(gemini).initializeSession();
        verify(gemini).obtenerRespuesta("Hello");
    }

    @Test
    public void testEnviarMensaje_Exception() throws Exception {
        UserMessage userMessage = new UserMessage();
        userMessage.setId_usuario("1");
        userMessage.setId_cuenta("1");
        userMessage.setMessage("Hello");

        when(transaccionService.balanceTotal(1, 1)).thenThrow(new RuntimeException("Database error"));

        ResponseEntity<?> responseEntity = chatBotController.enviarMensaje(userMessage);

        assertEquals(500, responseEntity.getStatusCodeValue());
        assertEquals("Error al procesar la solicitud: Database error",
                ((Map<String, Object>) responseEntity.getBody()).get("error"));

        verify(transaccionService).balanceTotal(1, 1);
        verify(transaccionService, never()).ingresosPorMesAndCuenta(anyInt(), anyInt());
        verify(transaccionService, never()).gastosPorMesAndCuenta(anyInt(), anyInt());
        verify(transaccionService, never()).transaccionesRecientesAndCuenta(anyInt(), anyInt());
        verify(transaccionService, never()).gastosPorCategoria(anyInt());
        verify(gemini, never()).setBalanceTotal(any());
        verify(gemini, never()).setLstIngresos(any());
        verify(gemini, never()).setLstEgreso(any());
        verify(gemini, never()).setTransacciones(any());
        verify(gemini, never()).setCategoriaGastoDtos(any());
        verify(gemini, never()).initializeSession();
        verify(gemini, never()).obtenerRespuesta(anyString());
    }
}