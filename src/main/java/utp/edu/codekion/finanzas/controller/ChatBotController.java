package utp.edu.codekion.finanzas.controller;

import lombok.RequiredArgsConstructor;
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
public class ChatBotController {

    private final Gemini gemini;
    private final ITransaccionService transaccionService;

    @PostMapping("/enviarMensaje")
    public ResponseEntity<?> enviarMensaje(@RequestBody UserMessage userMessage) {
        Map<String, Object> response = new HashMap<>();
        BigDecimal balanceTotal = transaccionService.balanceTotal(Integer.valueOf(userMessage.getId_usuario()));
        List<IngresoMesDto> lstIngresos = transaccionService.ingresosPorMes(Integer.valueOf(userMessage.getId_usuario()));
        List<IngresoMesDto> lstEgreso = transaccionService.gastosPorMes(Integer.valueOf(userMessage.getId_usuario()));
        List<Transacciones> transacciones = transaccionService.transaccionesRecientes(Integer.valueOf(userMessage.getId_usuario()));

        List<CategoriaGastoDto> categoriaGastoDtos = transaccionService.gastosPorCategoria(Integer.valueOf(userMessage.getId_usuario()));
        //Establecemos los valores de las variables de la clase Gemini
        gemini.setBalanceTotal(balanceTotal);
        gemini.setLstIngresos(lstIngresos);
        gemini.setLstEgreso(lstEgreso);
        gemini.setTransacciones(transacciones);
        gemini.setCategoriaGastoDtos(categoriaGastoDtos);

        String respuesta = gemini.obtenerRespuesta(userMessage.getMessage());
        response.put("respuesta", respuesta);
        return ResponseEntity.ok(response);
    }

}