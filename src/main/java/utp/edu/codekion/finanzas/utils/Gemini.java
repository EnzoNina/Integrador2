package utp.edu.codekion.finanzas.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Data;
import org.springframework.stereotype.Component;
import utp.edu.codekion.finanzas.model.Transacciones;
import utp.edu.codekion.finanzas.model.dto.CategoriaGastoDto;
import utp.edu.codekion.finanzas.model.dto.IngresoMesDto;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Data
@Component
public class Gemini {

    BigDecimal balanceTotal = new BigDecimal(0);
    List<IngresoMesDto> lstIngresos = new ArrayList<>();
    List<IngresoMesDto> lstEgreso = new ArrayList<>();
    List<Transacciones> transacciones = new ArrayList<>();
    //Creamos una lista de transacciones de respuesta
    List<CategoriaGastoDto> categoriaGastoDtos = new ArrayList<>();

    private static final String API_KEY = "AIzaSyBXbORN8NDbfO1yjc-1W9OpYOYLBRgATBM";
    private static final String URL_STRING = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + API_KEY;

    // List to keep track of the conversation history
    private static final List<Message> conversationHistory = new ArrayList<>();

    public String obtenerRespuesta(String userMessage) {
        // Agregar el mensaje del usuario al historial de la conversación
        conversationHistory.add(new Message("user", userMessage));

        // Obtener la respuesta del bot
        String botResponse = getBotResponse();

        if (botResponse != null) {
            // Agregar la respuesta del bot al historial
            conversationHistory.add(new Message("model", botResponse));
            return botResponse;
        } else {
            return "Error en la respuesta del bot.";
        }
    }

    private String getBotResponse() {
        try {
            URL url = new URL(URL_STRING);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Create JSON body from conversation history
            String jsonInputString = generateJsonBody(conversationHistory);
            System.out.println(jsonInputString);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Check for response
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (Scanner responseScanner = new Scanner(conn.getInputStream(), StandardCharsets.UTF_8.name())) {
                    String response = responseScanner.useDelimiter("\\A").next();
                    System.out.println(response);
                    return parseResponse(response);
                }
            } else {
                System.out.println("Error en la solicitud HTTP: Código " + responseCode);
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String generateJsonBody(List<Message> history) {

        StringBuilder  systemInstructionMessage = new StringBuilder();
        systemInstructionMessage.append("Eres CodekionBot, un asistente virtual diseñado para ayudar a los usuarios con consultas relacionadas con sus transacciones financieras. Puedes proporcionar información actualizada sobre las últimas transacciones, categorías con mayores gastos, presupuestos, y responder a cualquier otra consulta general relacionada con la gestión financiera. Además, podrás ofrecer recomendaciones y sugerencias basadas en los datos disponibles, ya sea según tu análisis personal o según información relevante que encuentres en Internet. Tu objetivo es proporcionar respuestas claras, útiles y completas, adaptándote al tono y contexto de cada usuario.");
        systemInstructionMessage.append("Tu balance total es: ").append(balanceTotal).append(". ");
        systemInstructionMessage.append("Ingresos mensuales:\n");
        for (IngresoMesDto ingreso : lstIngresos) {
            systemInstructionMessage.append(" - Mes ").append(ingreso.getMes()).append(": ").append(ingreso.getTotal()).append("\n");
        }
        systemInstructionMessage.append("Gastos mensuales:\n");
        for (IngresoMesDto egreso : lstEgreso) {
            systemInstructionMessage.append(" - Mes ").append(egreso.getMes()).append(": ").append(egreso.getTotal()).append("\n");
        }
        systemInstructionMessage.append("Transacciones recientes:\n");
        for (Transacciones transaccion : transacciones) {
            systemInstructionMessage.append(" - ").append(transaccion.getDescripcion()).append(": ").append(transaccion.getMonto()).append("\n");
        }
        systemInstructionMessage.append("Gastos por categoría:\n");
        for (CategoriaGastoDto categoria : categoriaGastoDtos) {
            systemInstructionMessage.append(" - ").append(categoria.getDescripcion()).append(": ").append(categoria.getMonto()).append("\n");
        }

        System.out.println(systemInstructionMessage);

        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{\n");
        jsonBuilder.append("  \"contents\": [\n");

        for (Message message : history) {
            jsonBuilder.append("    {\n");
            jsonBuilder.append("      \"role\": \"").append(message.role).append("\",\n");
            jsonBuilder.append("      \"parts\": [{ \"text\": \"").append(message.text).append("\" }]\n");
            jsonBuilder.append("    },\n");
        }

        jsonBuilder.append("  ],\n");

        jsonBuilder.append("  \"systemInstruction\": { \"role\": \"user\", \"parts\": [{ \"text\": \"").append(systemInstructionMessage).append("\" }] },\n");
        jsonBuilder.append("  \"generationConfig\": {\n");
        jsonBuilder.append("    \"temperature\": 1,\n");
        jsonBuilder.append("    \"topK\": 40,\n");
        jsonBuilder.append("    \"topP\": 0.95,\n");
        jsonBuilder.append("    \"maxOutputTokens\": 400,\n");
        jsonBuilder.append("    \"responseMimeType\": \"text/plain\"\n");
        jsonBuilder.append("  }\n");
        jsonBuilder.append("}");

        return jsonBuilder.toString();
    }

    private static String parseResponse(String jsonResponse) {
        try {
            // Parsear el JSON completo de la respuesta
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

            // Acceder al array de candidatos
            JsonArray candidatesArray = jsonObject.getAsJsonArray("candidates");
            if (candidatesArray != null && candidatesArray.size() > 0) {
                JsonObject contentObject = candidatesArray.get(0).getAsJsonObject().getAsJsonObject("content");

                // Acceder al texto en "parts[0].text"
                JsonArray partsArray = contentObject.getAsJsonArray("parts");
                if (partsArray != null && partsArray.size() > 0) {
                    return partsArray.get(0).getAsJsonObject().get("text").getAsString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error al parsear la respuesta.";
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