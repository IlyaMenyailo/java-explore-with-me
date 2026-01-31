# Explore With Me (java-explore-with-me)

## Описание
Это сервис-афиша событий, где пользователи могут:
- Создавать события и искать интересные мероприятия
- Подавать заявки на участие в событиях других пользователей
- Создавать и управлять подборками событий
- Подписываться на других пользователей и получать их события
- Администраторы могут модерировать события и управлять категориями

Проект состоит из двух основных модулей:
*   ewm-main-svc — основное приложение с REST API для работы с событиями, пользователями, категориями, запросами и подписками
*   stat-svc — сервис сбора и хранения статистики о просмотрах событий, который включает:
    *   `stats-server` — сервер статистики
    *   `stat-client` — клиент для отправки статистики
    *   `stat-dto` — DTO для работы со статистикой
    
## Технологии
* Java 21
* Spring Boot
* Spring Data JPA
* Maven
* PostgreSQL
* Docker
* Lombok
* Jakarta Validation

## Запуск проекта
1.  Убедитесь, что установлены: JDK 21+, Maven, Docker, PostgreSQL.
2.  Склонируйте репозиторий: `git clone https://github.com/IlyaMenyailo/java-explore-with-me
3.  Соберите проект: `mvn clean package`
4.  Запустите базы данных и окружение: `docker-compose up -d` (файл `docker-compose.yml`)
5.  Запустите модули:
    *   Сначала `ewm-stats` (класс `StatsServerApplication`)
    *   Затем `ewm-main` (класс `MainApp`)

## Сервисы будут доступны по адресам:
* Основное приложение: http://localhost:8080
* Сервис статистики: http://localhost:9090
* База данных основного сервиса: localhost:6542
* База данных статистики: localhost:6541

## API
### Основной сервис (ewm-main-svc)
#### Административные эндпоинты
* Категории (/admin/categories)
  * POST /admin/categories — создание новой категории
  * PATCH /admin/categories/{catId} — обновление категории
  * DELETE /admin/categories/{catId} — удаление категории
* Пользователи (/admin/users)
  * POST /admin/users — создание нового пользователя
  * DELETE /admin/users/{id} — удаление пользователя
  * GET /admin/users — получение списка пользователей с пагинацией
* События (/admin/events)
  * GET /admin/events — поиск событий с фильтрами (для модерации)
  * PATCH /admin/events/{eventId} — публикация/отклонение события
* Подборки (/admin/compilations)
  * POST /admin/compilations — создание новой подборки событий
  * PATCH /admin/compilations/{compId} — обновление подборки
  * DELETE /admin/compilations/{compId} — удаление подборки
#### Публичные эндпоинты
* Категории (/categories)
  * GET /categories — получение всех категорий с пагинацией
  * GET /categories/{catId} — получение категории по ID
* События (/events)
  * GET /events — поиск опубликованных событий с фильтрацией
  * GET /events/{eventId} — получение подробной информации о событии
* Подборки (/compilations)
  * GET /compilations — получение всех подборок (с фильтром по закреплению)
  * GET /compilations/{compId} — получение подборки по ID
#### Приватные эндпоинты (для авторизованных пользователей)
* События пользователя (/users/{userId}/events)
  * POST /users/{userId}/events — создание нового события
  * GET /users/{userId}/events — получение событий пользователя
  * GET /users/{userId}/events/{eventId} — получение конкретного события пользователя
  * PATCH /users/{userId}/events/{eventId} — обновление события пользователя
* Запросы на участие (/users/{userId}/requests)
  * POST /users/{userId}/requests — создание запроса на участие в событии
  * GET /users/{userId}/requests — получение запросов пользователя
  * PATCH /users/{userId}/requests/{requestId}/cancel — отмена запроса
  * GET /users/{userId}/events/{eventId}/requests — получение запросов на участие в событии пользователя
  * PATCH /users/{userId}/events/{eventId}/requests — обновление статуса запросов
* Подписки (/users/{userId}/subscriptions)
  * POST /users/{userId}/subscriptions — подписка на другого пользователя
  * DELETE /users/{userId}/subscriptions/{ownerId} — отписка от пользователя
  * GET /users/{userId}/subscriptions/events — получение событий от подписок
  * GET /users/{userId}/subscriptions/subscribers/count — количество подписчиков
  * GET /users/{userId}/subscriptions/subscribers — список подписчиков
### Сервис статистики (stat-svc)
  * POST /hit — сохранение информации о запросе к эндпоинту
  * GET /stats — получение статистики просмотров за период


## Ссылка на пул реквест: https://github.com/IlyaMenyailo/java-explore-with-me/pull/3
