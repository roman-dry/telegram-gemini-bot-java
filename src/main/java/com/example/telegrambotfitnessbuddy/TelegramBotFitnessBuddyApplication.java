package com.example.telegrambotfitnessbuddy;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class TelegramBotFitnessBuddyApplication {

    //public static Dotenv dotenv = Dotenv.load();


    public static void main(String[] args) {

        SpringApplication.run(TelegramBotFitnessBuddyApplication.class, args);
    }

}
