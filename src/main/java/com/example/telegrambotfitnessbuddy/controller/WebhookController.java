package com.example.telegrambotfitnessbuddy.controller;

import com.example.telegrambotfitnessbuddy.service.GeminiService;
import com.example.telegrambotfitnessbuddy.service.TelegramService;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private final TelegramService telegramService;

    private static final String INSTRUCTIONS = """
        You are a Telegram chatbot acting as a beginner-friendly fitness coach.
        
        Your role:
        - Guide complete beginners who want to start exercising at home.
        - Offer simple workouts that require no equipment.
        - Help users build routines with small, achievable steps.
        
        Your tone:
        - Always be friendly, supportive, and motivational.
        - Use simple, clear language — no jargon.
        - Never criticize, shame, or pressure the user.
        - Celebrate small wins and encourage consistency.
        
        What to avoid:
        - Do not suggest strict diets, advanced workouts, or intense training.
        - Do not use complicated fitness terms.
        
        Special case:
        - If the user feels tired, discouraged, or says they’re not making progress — respond with empathy, encouragement, and remind them that small steps lead to long-term change.
        """;

    @Autowired
    public WebhookController(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        System.out.println("✅ Ping received");
        return ResponseEntity.ok("pong");
    }


    @GetMapping
    public ResponseEntity<String> checkWebhook() {
        return ResponseEntity.ok("Webhook endpoint is up");
    }

    /*@PostMapping
    public ResponseEntity<String> onUpdateReceived(@RequestBody Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String input = message.getText();
            String chatId = message.getChatId().toString();

            System.out.println("Received message: " + input + " from chatId: " + chatId);

            String responseText;
            if ("/start".equals(input)) {
                responseText = "Hi there! I’m your friendly fitness buddy. " +
                        "Ready to start your journey with some simple and fun workouts? " +
                        "Just ask me anything — I’m here to help!";
            } else {
                String prompt = INSTRUCTIONS + "\n\nUser query: " + input;
                responseText = GeminiService.sendPrompt(prompt);  // Перевір, чи цей метод статичний!
            }

            telegramService.sendMessage(chatId, responseText);
            System.out.println("Sent response: " + responseText);
        }*/

        @PostMapping
        public ResponseEntity<String> onUpdateReceived(@RequestBody Update update) {
            System.out.println("📥 Incoming update: " + update);

            try {
                if (update.hasMessage() && update.getMessage().hasText()) {
                    Message message = update.getMessage();
                    String input = message.getText();
                    String chatId = message.getChatId().toString();

                    String responseText;
                    if ("/start".equals(input)) {
                        responseText = "Hi there! I’m your friendly fitness buddy. " +
                                "Ready to start your journey with some simple and fun workouts? " +
                                "Just ask me anything — I’m here to help!";
                    } else {
                        String prompt = INSTRUCTIONS + "\n\nUser query: " + input;
                        responseText = GeminiService.sendPrompt(prompt);  // Перевір, чи цей метод статичний!
                    }

                    telegramService.sendMessage(chatId, responseText);
                }
            } catch (Exception e) {
                e.printStackTrace(); // <-- обов'язково
            }

            return ResponseEntity.ok("OK");
        }



        /*return ResponseEntity.ok("OK");
    }
*/

}

