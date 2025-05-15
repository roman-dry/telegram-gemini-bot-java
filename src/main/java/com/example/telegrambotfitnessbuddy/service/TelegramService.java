package com.example.telegrambotfitnessbuddy.service;

import com.example.telegrambotfitnessbuddy.TelegramBotFitnessBuddyApplication;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class TelegramService {
    private static final String TELEGRAM_API_URL = "https://api.telegram.org/bot";

    public void sendMessage(String chatId, String text) {
        //String token = TelegramBotFitnessBuddyApplication.dotenv.get("TELEGRAM_TOKEN");
        String token = System.getenv("TELEGRAM_TOKEN");

        String url = TELEGRAM_API_URL + token + "/sendMessage";

        Map<String, String> payload = Map.of(
                "chat_id", chatId,
                "text", text
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(payload, headers);

        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.postForEntity(url, entity, String.class);
        } catch (Exception e) {
            System.err.println("❌ Failed to send message: " + e.getMessage());
        }
    }
}

