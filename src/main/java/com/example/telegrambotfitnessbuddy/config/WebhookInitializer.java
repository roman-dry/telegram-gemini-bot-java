package com.example.telegrambotfitnessbuddy.config;

import com.example.telegrambotfitnessbuddy.TelegramBotFitnessBuddyApplication;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WebhookInitializer {

    private static final String WEBHOOK_URL = TelegramBotFitnessBuddyApplication.dotenv.get("HOST_URL") + "/webhook"; // 🔁 Заміни на свій URL

    @PostConstruct
    public void registerWebhook() {
        String botToken = TelegramBotFitnessBuddyApplication.dotenv.get("TELEGRAM_TOKEN");
        String telegramUrl = "https://api.telegram.org/bot" + botToken + "/setWebhook?url=" + WEBHOOK_URL;

        RestTemplate restTemplate = new RestTemplate();
        try {
            String response = restTemplate.getForObject(telegramUrl, String.class);
            System.out.println("✅ Webhook registered: " + response);
        } catch (Exception e) {
            System.err.println("❌ Failed to register webhook: " + e.getMessage());
        }
    }
}

