package utp.edu.codekion.finanzas.utils;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.ChatSession;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Data;
import org.springframework.stereotype.Component;
import utp.edu.codekion.finanzas.model.Transacciones;
import utp.edu.codekion.finanzas.model.dto.CategoriaGastoDto;
import utp.edu.codekion.finanzas.model.dto.IngresoMesDto;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Data
@Component
public class Gemini {

    BigDecimal balanceTotal = new BigDecimal(0);
    List<IngresoMesDto> lstIngresos = new ArrayList<>();
    List<IngresoMesDto> lstEgreso = new ArrayList<>();
    List<Transacciones> transacciones = new ArrayList<>();
    List<CategoriaGastoDto> categoriaGastoDtos = new ArrayList<>();

    private static final String API_KEY = "AIzaSyBOiRtJ7umQmkJGL3LoNIpube7v6yXkKR4";
    private static final List<Message> conversationHistory = new ArrayList<>();

    private static final String PROJECT_ID = "your-google-cloud-project-id";
    private static final String LOCATION = "us-central1";
    private static final String MODEL_NAME = "gemini-1.5-flash-001";

    private ChatSession chatSession;

    public Gemini() throws IOException {

        // Recuperar las credenciales codificadas desde la variable de entorno
        String base64Credenciales = System.getenv("GOOGLE_CREDENTIALS_BASE64");

        if (base64Credenciales == null || base64Credenciales.isEmpty()) {
            throw new IllegalStateException("Las credenciales de Google no están configuradas.");
        }

        // Decodificar las credenciales
        byte[] credencialesDecodificadas = Base64.getDecoder().decode(base64Credenciales);

        // Guardar las credenciales en un archivo temporal
        Path credencialesPath = Paths.get("credenciales.json");
        Files.write(credencialesPath, credencialesDecodificadas);

        // Establecer la variable de entorno GOOGLE_APPLICATION_CREDENTIALS para Vertex
        // AI
        System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", credencialesPath.toAbsolutePath().toString());

        VertexAI vertexAI = new VertexAI(PROJECT_ID, LOCATION);
        GenerativeModel model = new GenerativeModel(MODEL_NAME, vertexAI)
                .withSystemInstruction(ContentMaker.fromString(generateJsonBody(conversationHistory)));
        this.chatSession = new ChatSession(model);
    }

    public String obtenerRespuesta(String userMessage) {
        conversationHistory.add(new Message("user", userMessage));
        try {
            return getBotResponse(userMessage);
        } catch (Exception e) {
            System.out.println("Error al obtener la respuesta del bot: " + e.getMessage());
        }
        return "Error al obtener la respuesta del bot.";
    }

    private String getBotResponse(String userMessage) throws Exception {
        GenerateContentResponse response = chatSession.sendMessage(userMessage);
        return ResponseHandler.getText(response);
    }

    public static void main(String[] args) throws IOException {
        Gemini gemini = new Gemini();
        System.out.println(gemini.obtenerRespuesta("Hola, ¿cómo puedo gestionar mis finanzas personales?"));
    }

    private String generateJsonBody(List<Message> history) {

        StringBuilder systemInstructionMessage = new StringBuilder();
        systemInstructionMessage.append(
                "Eres CodekionBot, un asistente virtual diseñado para ayudar a los usuarios con consultas relacionadas con sus transacciones financieras. Puedes proporcionar información actualizada sobre las últimas transacciones, categorías con mayores gastos, presupuestos, y responder a cualquier otra consulta general relacionada con la gestión financiera. Además, podrás ofrecer recomendaciones y sugerencias basadas en los datos disponibles, ya sea según tu análisis personal o según información relevante que encuentres en Internet. Tu objetivo es proporcionar respuestas claras, útiles y completas, adaptándote al tono y contexto de cada usuario.");
        systemInstructionMessage.append("Tu balance total es: ").append(balanceTotal).append(". ");
        systemInstructionMessage.append("Ingresos mensuales:\n");
        for (IngresoMesDto ingreso : lstIngresos) {
            systemInstructionMessage.append(" - Mes ").append(ingreso.getMes()).append(": ").append(ingreso.getTotal())
                    .append("\n");
        }
        systemInstructionMessage.append("Gastos mensuales:\n");
        for (IngresoMesDto egreso : lstEgreso) {
            systemInstructionMessage.append(" - Mes ").append(egreso.getMes()).append(": ").append(egreso.getTotal())
                    .append("\n");
        }
        systemInstructionMessage.append("Transacciones recientes:\n");
        for (Transacciones transaccion : transacciones) {
            systemInstructionMessage.append(" - ").append(transaccion.getDescripcion()).append(": ")
                    .append(transaccion.getMonto()).append("\n");
        }
        systemInstructionMessage.append("Gastos por categoría:\n");
        for (CategoriaGastoDto categoria : categoriaGastoDtos) {
            systemInstructionMessage.append(" - ").append(categoria.getDescripcion()).append(": ")
                    .append(categoria.getMonto()).append("\n");
        }

        return systemInstructionMessage.toString();
    }

    // Inner class to represent messages
    static class Message {
        String role;
        String text;

        Message(String role, String text) {
            this.role = role;
            this.text = text;
        }
    }

}