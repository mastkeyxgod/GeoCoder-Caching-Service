# Приложение GeoService

**Описание:** Этот проект представляет собой сервис кеширования запросов к стороннему API геолокации, предоставляющее функциональность геокодирования.

## Структура проекта

### Контроллер

**GeoController:** Контроллер, обрабатывающий HTTP-запросы для геокодирования.

### Сервис

**GeoService:** Сервис, предоставляющий методы для работы с геокодированием.

### Утилиты

**CacheDBConnector:** Интерфейс для взаимодействия с кэшем.

**GeoAPIConnector:** Интерфейс для взаимодействия с API геокодирования.

**RedisUtil:** Реализация `CacheBDConnector` для взаимодействия с Redis.

**YandexAPI:** Реализация `GeoAPIConnector` для взаимодействия с Yandex Geocoding API.

### Приложение

**GeoApplication:** Основной класс, инициализирующий Spring Boot приложение.

## Конфигурация

- **application.properties:** Файл с настройками приложения.

- **yandex.key:** Ключ для доступа к Yandex Geocoding API.

- **yandex.base_url:** Базовый URL для запросов к Yandex Geocoding API.

- **redis.server:** Сервер с Redis.

- **redis.port:** Порт с Redis.

## Взаимодействие с API

- **GET /geocoding/coordinates:** Получение адреса по координатам. Пример запроса:
  ```http
  GET /geocoding/coordinates
  с json с полями latitude и longitude.

- **GET /geocoding/address:** Получение координат по адресу. Пример запроса:
  ```http
  GET /geocoding/address
  с json с полем address.


  
