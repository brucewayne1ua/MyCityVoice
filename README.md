# MyCityVoice

Приложение Spring Boot для публикации и чтения отзывов/опытов о городах, местах и достопримечательностях. Реализовано как REST API с использованием Spring Web + Spring Data JPA. Предназначено для разработчиков, тестировщиков и для развёртывания в контейнерах.

---

# MyCityVoice (English)

A Spring Boot application for posting and reading reviews/experiences about cities, places, and attractions. Implemented as a REST API using Spring Web + Spring Data JPA. Intended for developers, testers, and for containerized deployment.

## Стек / Stack
- Язык / Language: Java 17
- Фреймворк / Framework: Spring Boot 4 (spring-boot-starter-parent 4.0.2)
- Основные зависимости / Notable dependencies:
  - spring-boot-starter-web (HTTP/REST)
  - spring-boot-starter-data-jpa (JPA / persistence)
  - springdoc-openapi-starter-webmvc-ui (Swagger UI)
  - postgresql (runtime) — recommended DB for production
  - h2 (test scope) — for tests
  - lombok (annotations to reduce boilerplate)

## Структура проекта (аннотированное дерево) / Project structure (annotated)
```
pom.xml                 # Maven build, dependencies, Java 17
Dockerfile              # Docker image build
docker-compose.yml      # Compose for running app + DB (if configured)
src/
  main/
    java/
      com/MyCityVoice/proj/
        ProjApplication.java   # Spring Boot entrypoint
        controller/            # HTTP controllers (REST endpoints)
        service/               # Service layer (business logic)
        repository/            # Spring Data repositories (DB access)
        model/                 # JPA entities / DTOs
    resources/
      application.properties|yml  # Configuration (check repository)
tests/
  test/                    # unit / integration tests
```

Как это работает (кратко) / How it works (short):
Приложение стартует через `ProjApplication`, принимает HTTP-запросы в контроллерах, пересылает в сервисы, которые работают с репозиториями (Spring Data JPA). Данные в продакшн обычно хранить в PostgreSQL, для тестов используется H2.

The app starts via `ProjApplication`, accepts HTTP requests in controllers, passes them to services which use repositories (Spring Data JPA). Production data is typically stored in PostgreSQL; H2 is used for tests.

## Требования / Requirements
- Java 17 (JDK 17)
- Maven (or use bundled wrapper `./mvnw`)
- For containerization: Docker and Docker Compose (if you plan to use compose)

## Быстрый запуск (локально) / Quick start (local)
1. Клонируйте репозиторий / Clone the repo:
   ```
   git clone https://github.com/brucewayne1ua/MyCityVoice.git
   cd MyCityVoice
   ```

2. Сборка и запуск через Maven wrapper / Build and run via Maven wrapper:
   - Запустить в режиме разработки / Run in dev mode:
     ```
     ./mvnw spring-boot:run
     ```
   - Собрать JAR / Build JAR:
     ```
     ./mvnw clean package
     ```
     затем запустить / then run:
     ```
     java -jar target/proj-0.0.1-SNAPSHOT.jar
     ```

Приложение по умолчанию запускается на порту 8080, если не переопределено в `application.properties` / `application.yml`.

By default the app runs on port 8080 unless overridden in `application.properties` / `application.yml`.

## Запуск с PostgreSQL (локально) / Running with PostgreSQL (local)
Пример переменных окружения / Example environment variables (Spring Boot datasource):

```
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/mycityvoice
SPRING_DATASOURCE_USERNAME=myuser
SPRING_DATASOURCE_PASSWORD=mypassword
SPRING_JPA_HIBERNATE_DDL_AUTO=update   # or validate / none / create
```

If datasource is not provided the app may fail when JPA attempts to access a missing DB — check config in src/main/resources.

## Запуск через Docker / Run with Docker
- Собрать образ / Build image:
  ```
  docker build -t mycityvoice:latest .
  ```
- Запустить контейнер / Run container:
  ```
  docker run -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/mycityvoice \
             -e SPRING_DATASOURCE_USERNAME=myuser \
             -e SPRING_DATASOURCE_PASSWORD=mypassword \
             -p 8080:8080 mycityvoice:latest
  ```

## Запуск через Docker Compose / Run with Docker Compose
Если в репозитории есть `docker-compose.yml`, обычно достаточно / If `docker-compose.yml` exists:
```
docker-compose up --build
```
Перед запуском проверьте `docker-compose.yml` — убедитесь, что в нём настроены сервисы БД и переменные среды (или поправьте их под свои значения).

Before running, check `docker-compose.yml` to ensure DB service and env vars are configured (or adjust them).

## Тесты / Tests
Запустить тесты / Run tests:
```
./mvnw test
```
Тестовая зависимость H2 указана со scope test — тесты должны работать без внешней БД.

The H2 test dependency is declared with test scope — tests should run without an external DB.

## Документация API (Swagger / OpenAPI)
Проект использует springdoc-openapi starter. После запуска API-документация обычно доступна по одному из путей:
- /v3/api-docs  — raw OpenAPI JSON
- /swagger-ui.html или /swagger-ui/index.html — Swagger UI

Open `http://localhost:8080/swagger-ui.html` or `http://localhost:8080/swagger-ui/index.html`.

## Полезные советы для разработчика / Developer notes
- Lombok: в IDE (IntelliJ IDEA / Eclipse) включите плагин Lombok или аннотационный процессор, иначе IDE не распознает сгенерированные методы.
- Конфигурация: см. `src/main/resources/application.properties` или `application.yml` для текущих значений. Если файла нет — приложение использует Spring Boot defaults.
- Логи/уровни логирования настраиваются через properties (например `logging.level.root=INFO`).

- Lombok: enable Lombok plugin/annotation processing in your IDE (IntelliJ/Eclipse) or generated methods may not be recognized.
- Configuration: check `src/main/resources/application.properties` or `application.yml` for current values. If missing, Spring Boot defaults apply.
- Logging: configure levels via properties (e.g. `logging.level.root=INFO`).

## Частые проблемы и решения / Troubleshooting
- Проблема: приложение не стартует из-за отсутствия БД.
  Решение: либо запустите локальную PostgreSQL и задайте SPRING_DATASOURCE_*, либо переключитесь на in-memory H2 профиль (требует настройки).

- Issue: application fails to start because DB is missing.
  Fix: either run a local PostgreSQL and set SPRING_DATASOURCE_*, or configure an in-memory H2 profile for development.

- Проблема: IDE не видит геттеры/сеттеры (Lombok).
  Решение: установите Lombok plugin и включите annotation processing.

- Issue: IDE doesn't see getters/setters (Lombok).
  Fix: install Lombok plugin and enable annotation processing.

## Контакты / Author
Repo: brucewayne1ua/MyCityVoice

---

Попробуйте спросить / Try asking:
- "Где находятся REST-контроллеры и какие эндпоинты они предоставляют? (проверь `src/main/java/com/MyCityVoice/proj/controller`)"
- "Как настроен docker-compose.yml для БД и приложения — можешь показать содержимое и предложить улучшения?"
- "Можешь добавить пример .env и конфигурацию для локального запуска с PostgreSQL?"
