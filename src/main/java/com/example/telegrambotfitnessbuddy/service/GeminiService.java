package com.example.telegrambotfitnessbuddy.service;

import com.example.telegrambotfitnessbuddy.TelegramBotFitnessBuddyApplication;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class GeminiService {

private static final String GEMINI_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=";

    public static String sendPrompt(String userPrompt) {
        RestTemplate restTemplate = new RestTemplate();

        //String apiKey = TelegramBotFitnessBuddyApplication.dotenv.get("GEMINI_API_KEY");
        String apiKey = System.getenv("GEMINI_API_KEY");


        Map<String, Object> payload = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text", userPrompt)
                        })
                }
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);
        String url = GEMINI_URL + apiKey;

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);
            Map content = (Map) ((Map)((java.util.List) response.getBody().get("candidates")).get(0)).get("content");
            java.util.List parts = (java.util.List) content.get("parts");
            return (String) ((Map) parts.get(0)).get("text");
        } catch (Exception e) {
            e.printStackTrace();
            return "Sorry, I encountered an error.";
        }
    }
}

