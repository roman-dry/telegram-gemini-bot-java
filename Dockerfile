# Базовий образ із Java 17
FROM eclipse-temurin:17-jdk

# Робоча директорія
WORKDIR /app

# Копіюємо jar-файл
COPY target/telegram-bot-fitness-buddy-0.0.1-SNAPSHOT.jar app.jar

# Виставляємо порт (не обов’язково, Railway і так слухає 8080)
EXPOSE 8080

# Запуск додатку
ENTRYPOINT ["java", "-jar", "app.jar"]
