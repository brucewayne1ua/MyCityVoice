# MyCityVoice

Приложение Spring Boot для публикации и чтения отзывов/опытов о городах, местах и достопримечательностях. Реализовано как REST API с использованием Spring Web + Spring Data JPA. Предназначено для разработчиков, тестировщиков и для развёртывания в контейнерах.

## Стек
- Язык: Java 17
- Фреймворк: Spring Boot 4 (spring-boot-starter-parent 4.0.2)
- Основные зависимости:
  - spring-boot-starter-web (HTTP/REST)
  - spring-boot-starter-data-jpa (JPA / persistence)
  - springdoc-openapi-starter-webmvc-ui (Swagger UI)
  - postgresql (runtime) — рекомендованная БД для продакшна
  - h2 (test scope) — для тестов
  - lombok (аннотации для сокращения кода)

## Структура проекта (аннотированное дерево)
```
pom.xml                 # Maven build, зависимости, Java 17
Dockerfile              # Docker image build
docker-compose.yml      # Compose для запуска приложения + БД (если настроено)
src/
  main/
    java/
      com/MyCityVoice/proj/
        ProjApplication.java   # Точка входа Spring Boot
        controller/            # HTTP контроллеры (REST endpoints)
        service/               # Сервисный слой (бизнес-логика)
        repository/            # Spring Data репозитории (DB access)
        model/                 # JPA entity / DTO
    resources/
      application.properties|yml  # Конфигурация (проверьте файл в репозитории)
tests/
  test/                    # модульные/интеграционные тесты
```

Как это работает (кратко): приложение стартует через `ProjApplication`, принимает HTTP-запросы в контроллерах, пересылает в сервисы, которые работают с репозиториями (Spring Data JPA). Данные в продакшн обычно хранить в PostgreSQL (зависимость в pom.xml со scope runtime), для тестов используется H2.

## Требования
- Java 17 (JDK 17)
- Maven (или используйте встроенный wrapper `./mvnw`)
- Для контейнеризации: Docker и Docker Compose (если используете compose)

## Быстрый запуск (локально)
1. Клонируйте репозиторий:
   ```
   git clone https://github.com/brucewayne1ua/MyCityVoice.git
   cd MyCityVoice
   ```

2. Сборка и запуск через Maven wrapper:
   - Запустить в режиме разработки:
     ```
     ./mvnw spring-boot:run
     ```
   - Собрать JAR:
     ```
     ./mvnw clean package
     ```
     затем запустить:
     ```
     java -jar target/proj-0.0.1-SNAPSHOT.jar
     ```

Приложение по умолчанию запускается на порту 8080 (стандарт для Spring Boot), если не переопределено в `application.properties` / `application.yml`.

## Запуск с PostgreSQL (локально)
Пример переменных окружения (т.н. Spring Boot datasource vars). Можно экспортировать в shell или положить в `docker-compose.yml` / систему окружения:

```
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/mycityvoice
SPRING_DATASOURCE_USERNAME=myuser
SPRING_DATASOURCE_PASSWORD=mypassword
SPRING_JPA_HIBERNATE_DDL_AUTO=update   # или validate / none / create
```

Если вы не укажете datasource, приложение может падать при попытке использования JPA с отсутствующей БД — проверьте config в src/main/resources.

## Запуск через Docker
- Собрать образ:
  ```
  docker build -t mycityvoice:latest .
  ```
- Запустить контейнер:
  ```
  docker run -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/mycityvoice \
             -e SPRING_DATASOURCE_USERNAME=myuser \
             -e SPRING_DATASOURCE_PASSWORD=mypassword \
             -p 8080:8080 mycityvoice:latest
  ```

## Запуск через Docker Compose
Если в репозитории есть `docker-compose.yml`, обычно достаточно:
```
docker-compose up --build
```
Перед запуском проверьте `docker-compose.yml` — убедитесь, что в нём настроены сервисы БД и переменные среды (или поправьте их под свои значения).

## Тесты
Запустить тесты:
```
./mvnw test
```
В pom.xml тестовая зависимость H2 указана со scope test — тесты должны работать без внешней БД.

## Документация API (Swagger / OpenAPI)
Проект использует springdoc-openapi starter. После запуска API-документация обычно доступна по одному из путей:
- /v3/api-docs  — raw OpenAPI JSON
- /swagger-ui.html или /swagger-ui/index.html — Swagger UI в браузере

Откройте `http://localhost:8080/swagger-ui.html` или `http://localhost:8080/swagger-ui/index.html`.

## Полезные советы для разработчика
- Lombok: в IDE (IntelliJ IDEA / Eclipse) включите плагин Lombok или аннотационный процессор, иначе IDE не распознает сгенерированные методы.
- Конфигурация: см. `src/main/resources/application.properties` или `application.yml` для текущих значений. Если файла нет — приложение использует Spring Boot defaults.
- Логи/уровни логирования настраиваются через properties (например `logging.level.root=INFO`).

## Частые проблемы и решения
- Проблема: приложение не стартует из-за отсутствия БД.
  Решение: либо запустите локальную PostgreSQL и задайте SPRING_DATASOURCE_*, либо переключитесь на in-memory H2 профиль (требует настройки).
- Проблема: IDE не видит геттеры/сеттеры (Lombok).
  Решение: установите Lombok plugin и включите annotation processing.

## Контакты / автор
Repo: brucewayne1ua/MyCityVoice

---

Попробуйте спросить:
- "Где находятся REST-контроллеры и какие эндпоинты они предоставляют? (проверь `src/main/java/com/MyCityVoice/proj/controller`)"
- "Как настроен docker-compose.yml для БД и приложения — можешь показать содержимое и предложить улучшения?"
- "Можешь добавить пример .env и конфигурацию для локального запуска с PostgreSQL?"
