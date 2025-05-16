# Етап 1: збірка проєкту
FROM maven:3.9.4-eclipse-temurin-17 AS builder

WORKDIR /app

# Копіюємо всі файли проєкту
COPY . .

# Збираємо jar-файл (без тестів, щоб пришвидшити)
RUN mvn clean package -DskipTests

# Етап 2: запуск готового jar
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Копіюємо готовий jar з попереднього етапу
COPY --from=builder /app/target/telegram-bot-fitness-buddy-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Запуск Spring Boot-додатку
ENTRYPOINT ["java", "-jar", "app.jar"]

