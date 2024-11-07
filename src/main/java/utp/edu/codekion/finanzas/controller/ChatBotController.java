package utp.edu.codekion.finanzas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.codekion.finanzas.model.dto.UserMessage;
import utp.edu.codekion.finanzas.utils.Gemini;

@RestController
@RequestMapping("/chatbot")
@RequiredArgsConstructor
public class ChatBotController {

    private final Gemini gemini;

    @PostMapping("/enviarMensaje")
    public String enviarMensaje(@RequestBody UserMessage userMessage) {
        return gemini.obtenerRespuesta(userMessage.getMessage());
    }

}