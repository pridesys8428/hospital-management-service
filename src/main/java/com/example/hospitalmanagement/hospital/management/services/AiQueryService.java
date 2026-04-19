package com.example.hospitalmanagement.hospital.management.services;

import com.example.hospitalmanagement.hospital.management.utils.Constants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//bill(id, patientId, amount, status)
//patient(id, name, gender, age)
//doctor(id, name, gender, speciality, shiftTime, email, phone, age, experience, education)

@Service
public class AiQueryService {

    private static final String OLLAMA_API_URL = "http://localhost:11434/api/generate";
    private final ObjectMapper mapper = new ObjectMapper();

    public String generateSQLFromPrompt(String userPrompt) throws Exception {
        // ✅ 1. Build the final instruction
        String finalPrompt = Constants.BASE_PROMPT
                + userPrompt
                + "\nWrite only a valid SQL SELECT query, no explanation or extra text.";

        // ✅ 2. Build JSON body safely
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "orca-mini");
        requestBody.put("prompt", finalPrompt);

        String json = mapper.writeValueAsString(requestBody);

        // ✅ 3. Open HTTP connection
        URL url = new URL(OLLAMA_API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");

        // ✅ 4. Send JSON body
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = json.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // ✅ 5. Read streaming response
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder fullResponse = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) continue;
            JsonNode node = mapper.readTree(line);
            if (node.has("response")) {
                fullResponse.append(node.get("response").asText());
            }
        }

        reader.close();
        conn.disconnect();

        return fullResponse.toString().replaceAll("[\\n\\r]+", " ").trim();
    }

}
