package com.example.telegrambotfitnessbuddy;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TelegramBotFitnessBuddyApplication {

    //public static Dotenv dotenv = Dotenv.load();


    public static void main(String[] args) {

        // Друкуємо змінні ДО запуску Spring
        String token = System.getenv("TELEGRAM_TOKEN");
        String hostUrl = System.getenv("HOST_URL");

        System.out.println("🔍 TELEGRAM_TOKEN: " + token);
        System.out.println("🔍 HOST_URL: " + hostUrl);

        SpringApplication.run(TelegramBotFitnessBuddyApplication.class, args);
    }

}
