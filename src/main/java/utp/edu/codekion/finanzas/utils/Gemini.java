package utp.edu.codekion.finanzas.utils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.api.GenerationConfig;
import com.google.cloud.vertexai.generativeai.ChatSession;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;
import lombok.Data;
import org.springframework.stereotype.Component;
import utp.edu.codekion.finanzas.model.Transacciones;
import utp.edu.codekion.finanzas.model.dto.CategoriaGastoDto;
import utp.edu.codekion.finanzas.model.dto.IngresoMesDto;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Data
@Component
public class Gemini {

    BigDecimal balanceTotal = BigDecimal.ZERO;
    List<IngresoMesDto> lstIngresos = new ArrayList<>();
    List<IngresoMesDto> lstEgreso = new ArrayList<>();
    List<Transacciones> transacciones = new ArrayList<>();
    List<CategoriaGastoDto> categoriaGastoDtos = new ArrayList<>();
    private static final List<Message> conversationHistory = new ArrayList<>();

    private static final String PROJECT_ID = "proyect-clever-84ccc";
    private static final String LOCATION = "us-central1";
    private static final String MODEL_NAME = "gemini-1.5-flash-001";

    private ChatSession chatSession;

    public void initializeSession() throws IOException {
        // Recuperar las credenciales base64 desde la variable de entorno
        String base64Credenciales = System.getenv("GOOGLE_CREDENTIALS_BASE64");
        if (base64Credenciales == null || base64Credenciales.isEmpty()) {
            throw new IllegalStateException("Las credenciales de Google no están configuradas.");
        }

        // Decodificar las credenciales
        byte[] credencialesDecodificadas = Base64.getDecoder().decode(base64Credenciales);

        // Cargar las credenciales en GoogleCredentials desde un stream
        GoogleCredentials credentials;
        try (ByteArrayInputStream credencialesStream = new ByteArrayInputStream(credencialesDecodificadas)) {
            credentials = GoogleCredentials.fromStream(credencialesStream)
                    .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));
        }

        // Configurar Vertex AI
        VertexAI vertexAI = new VertexAI.Builder()
                .setCredentials(credentials)
                .setProjectId(PROJECT_ID)
                .setLocation(LOCATION)
                .build();

        GenerationConfig generationConfig =
                GenerationConfig.newBuilder()
                        .setMaxOutputTokens(1024)
                        .setTemperature(0.2F)
                        .setTopP(0.8F)
                        .build();

        // Generar las instrucciones dinámicas
        var systemInstruction = ContentMaker.fromMultiModalData(generateJsonBody());
        System.out.println("Instrucciones del sistema: " + systemInstruction);
        // Configurar el modelo y la sesión
        GenerativeModel model = new GenerativeModel.Builder()
                .setModelName(MODEL_NAME)
                .setVertexAi(vertexAI)
                .setGenerationConfig(generationConfig)
                .setSystemInstruction(systemInstruction)
                .build();

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

    private String generateJsonBody() {
        StringBuilder systemInstructionMessage = new StringBuilder();
        systemInstructionMessage.append(
                "Eres CodekionBot, un asistente virtual diseñado para ayudar a los usuarios con consultas relacionadas con sus transacciones financieras. ");
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