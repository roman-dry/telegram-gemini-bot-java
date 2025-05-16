package com.example.telegrambotfitnessbuddy;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class TelegramBotFitnessBuddyApplication {

    //public static Dotenv dotenv = Dotenv.load();


    public static void main(String[] args) {

        Map<String, String> env = System.getenv();
        System.out.println("🌍 ВСІ ЗМІННІ СЕРЕДОВИЩА:");
        env.forEach((key, value) -> System.out.println(key + " = " + value));


        SpringApplication.run(TelegramBotFitnessBuddyApplication.class, args);
    }

}
