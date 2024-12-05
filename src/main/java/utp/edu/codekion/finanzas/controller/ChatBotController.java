package utp.edu.codekion.finanzas.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.codekion.finanzas.model.Transacciones;
import utp.edu.codekion.finanzas.model.dto.CategoriaGastoDto;
import utp.edu.codekion.finanzas.model.dto.IngresoMesDto;
import utp.edu.codekion.finanzas.model.dto.UserMessage;
import utp.edu.codekion.finanzas.service.IService.ITransaccionService;
import utp.edu.codekion.finanzas.utils.Gemini;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chatbot")
@RequiredArgsConstructor
@Log
public class ChatBotController {

    private final Gemini gemini;
    private final ITransaccionService transaccionService;

    @PostMapping("/enviarMensaje")
    public ResponseEntity<?> enviarMensaje(@RequestBody UserMessage userMessage) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Obtener datos dinámicos
            BigDecimal balanceTotal = transaccionService.balanceTotal(
                    Integer.valueOf(userMessage.getId_usuario()),
                    Integer.valueOf(userMessage.getId_cuenta())
            );
            List<IngresoMesDto> lstIngresos = transaccionService.ingresosPorMesAndCuenta(
                    Integer.valueOf(userMessage.getId_usuario()),
                    Integer.valueOf(userMessage.getId_cuenta())
            );
            List<IngresoMesDto> lstEgreso = transaccionService.gastosPorMesAndCuenta(
                    Integer.valueOf(userMessage.getId_usuario()),
                    Integer.valueOf(userMessage.getId_cuenta())
            );
            List<Transacciones> transacciones = transaccionService.transaccionesRecientesAndCuenta(
                    Integer.valueOf(userMessage.getId_usuario()),
                    Integer.valueOf(userMessage.getId_cuenta())
            );
            List<CategoriaGastoDto> categoriaGastoDtos = transaccionService.gastosPorCategoria(
                    Integer.valueOf(userMessage.getId_usuario())
            );

            // Establecer valores en Gemini
            gemini.setBalanceTotal(balanceTotal);
            gemini.setLstIngresos(lstIngresos);
            gemini.setLstEgreso(lstEgreso);
            gemini.setTransacciones(transacciones);
            gemini.setCategoriaGastoDtos(categoriaGastoDtos);

            // Inicializar la sesión con datos dinámicos
            gemini.initializeSession();

            // Obtener respuesta del bot
            String respuesta = gemini.obtenerRespuesta(userMessage.getMessage());
            response.put("respuesta", respuesta);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("error", "Error al procesar la solicitud: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}