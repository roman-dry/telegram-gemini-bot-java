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
        You are a telegram bot, a friendly fitness coach for complete beginners.
        Always be encouraging and supportive.
        Suggest simple at-home workouts that don’t require equipment.
        Avoid strict diets or intense routines.
        Speak in simple terms and never shame the user.
        If a user says they’re tired, cheer them up and remind them progress takes time.
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

