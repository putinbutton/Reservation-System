# Reservation System

Projekt edukacyjny w Spring Boot: system rezerwacji wizyt u fryzjera.

Aplikacja pozwala zarzadzac klientami, fryzjerami, uslugami fryzjerskimi oraz rezerwacjami. Projekt jest budowany krok po kroku, z naciskiem na nauke Spring Boota, JPA/Hibernate, walidacji, REST API oraz testow jednostkowych.

## Technologie

- Java 25
- Spring Boot 4.1.0
- Spring Web MVC
- Spring Data JPA
- Hibernate
- H2 Database
- Jakarta Validation
- Lombok
- JUnit
- Mockito
- Maven Wrapper

## Uruchomienie projektu

Przejdz do katalogu projektu:

```powershell
cd "C:\Users\kamil\OneDrive\Desktop\Java\Projects\Reservation System\Reservation-System"
```

Uruchom aplikacje:

```powershell
.\mvnw.cmd spring-boot:run
```

Aplikacja startuje domyslnie pod adresem:

```text
http://localhost:8080
```

## Testy

Uruchomienie testow:

```powershell
.\mvnw.cmd test
```

Czysty build z testami:

```powershell
.\mvnw.cmd clean test
```

Jesli `clean test` nie moze usunac folderu `target`, zatrzymaj aplikacje Spring Boot, zamknij proces uruchomiony w IntelliJ i usun folder `target`. To sa tylko pliki wygenerowane przez Maven.

## Baza danych H2

Projekt uzywa bazy H2 w pamieci.

Konsola H2:

```text
http://localhost:8080/h2-console
```

Dane logowania:

```text
JDBC URL: jdbc:h2:mem:reservationdb
User Name: sa
Password:
```

Haslo zostaw puste.

Konfiguracja znajduje sie w:

```text
src/main/resources/application.properties
```

## Struktura projektu

```text
src/main/java/com/example/Reservation/System
  Barber
    Barber.java
    BarberController.java
    BarberRepository.java
    BarberService.java

  Client
    Client.java
    ClientController.java
    ClientRepository.java
    ClientService.java

  HairService
    HairService.java
    HairServiceController.java
    HairServiceRepository.java
    HairServiceService.java

  Reservation
    CreateReservationRequest.java
    Reservation.java
    ReservationController.java
    ReservationRepository.java
    ReservationService.java

  common
    ErrorResponse.java
    GlobalExceptionHandler.java
    ReservationsConflictException.java

  ReservationSystemApplication.java
```

## Glowne encje

### Client

Reprezentuje klienta salonu.

Pola:

- `id`
- `firstName`
- `lastName`
- `email`
- `phoneNumber`

### Barber

Reprezentuje fryzjera.

Pola:

- `id`
- `firstName`
- `lastName`
- `phoneNumber`

### HairService

Reprezentuje usluge fryzjerska.

Pola:

- `id`
- `name`
- `price`
- `durationInMinutes`

### Reservation

Reprezentuje rezerwacje wizyty.

Pola:

- `id`
- `reservationDateTime`
- `client`
- `barber`
- `hairService`

Rezerwacja laczy klienta, fryzjera i usluge relacjami `@ManyToOne`.

## Endpointy API

### Clients

Dodanie klienta:

```http
POST http://localhost:8080/clients
Content-Type: application/json

{
  "firstName": "Kamil",
  "lastName": "Kowalski",
  "email": "kamil@example.com",
  "phoneNumber": "123456789"
}
```

Pobranie wszystkich klientow:

```http
GET http://localhost:8080/clients
```

Pobranie klienta po ID:

```http
GET http://localhost:8080/clients/1
```

Usuniecie klienta:

```http
DELETE http://localhost:8080/clients/1
```

### Barbers

Dodanie fryzjera:

```http
POST http://localhost:8080/barbers
Content-Type: application/json

{
  "firstName": "Adam",
  "lastName": "Nowak",
  "phoneNumber": "500600700"
}
```

Pobranie wszystkich fryzjerow:

```http
GET http://localhost:8080/barbers
```

Pobranie fryzjera po ID:

```http
GET http://localhost:8080/barbers/1
```

Usuniecie fryzjera:

```http
DELETE http://localhost:8080/barbers/1
```

### Hair Services

Dodanie uslugi:

```http
POST http://localhost:8080/hair-services
Content-Type: application/json

{
  "name": "Strzyzenie meskie",
  "price": 50.00,
  "durationInMinutes": 30
}
```

Pobranie wszystkich uslug:

```http
GET http://localhost:8080/hair-services
```

Pobranie uslugi po ID:

```http
GET http://localhost:8080/hair-services/1
```

Usuniecie uslugi:

```http
DELETE http://localhost:8080/hair-services/1
```

### Reservations

Przed utworzeniem rezerwacji musza istniec:

- klient
- fryzjer
- usluga

Dodanie rezerwacji:

```http
POST http://localhost:8080/reservations
Content-Type: application/json

{
  "clientId": 1,
  "barberId": 1,
  "hairServiceId": 1,
  "reservationDateTime": "2026-08-10T14:30:00"
}
```

Pobranie wszystkich rezerwacji:

```http
GET http://localhost:8080/reservations
```

Pobranie rezerwacji po ID:

```http
GET http://localhost:8080/reservations/1
```

Usuniecie rezerwacji:

```http
DELETE http://localhost:8080/reservations/1
```

## Walidacja

Projekt uzywa Jakarta Validation.

Przyklady:

- `@NotBlank` - pole tekstowe nie moze byc puste
- `@Email` - pole musi miec format emaila
- `@NotNull` - pole nie moze byc `null`
- `@Positive` - liczba musi byc dodatnia
- `@Future` - data musi byc w przyszlosci

Przyklad blednego requesta:

```json
{
  "firstName": "",
  "lastName": "",
  "email": "abc",
  "phoneNumber": ""
}
```

Taki request powinien zwrocic blad `400 Bad Request`.

## Obsluga bledow

Projekt ma globalna obsluge bledow w:

```text
common/GlobalExceptionHandler.java
```

Obslugiwane sa m.in.:

- konflikt rezerwacji
- bledy walidacji requestow

Jesli fryzjer ma juz rezerwacje na dana godzine, aplikacja rzuca `ReservationsConflictException` i zwraca blad konfliktu.

## Regula biznesowa

Najwazniejsza zasada w projekcie:

```text
Jeden fryzjer nie moze miec dwoch rezerwacji na dokladnie ta sama date i godzine.
```

Sprawdza to metoda:

```java
existsByBarberIdAndReservationDateTime(...)
```

w `ReservationRepository`.

## Testy jednostkowe

Projekt zawiera testy:

- `ReservationSystemApplicationTests` - sprawdza, czy kontekst Spring Boot startuje
- `ReservationServiceTest` - testuje logike rezerwacji z Mockito

Przyklad testowanej sytuacji:

```text
Jesli fryzjer ma juz rezerwacje na dana godzine, system powinien rzucic ReservationsConflictException.
```

## Co mozna dodac dalej

- DTO request/response dla kazdego modulu
- aktualizacje danych przez `PUT` albo `PATCH`
- lepsze wyjatki dla "not found"
- statusy HTTP `404 Not Found`
- testy kontrolerow
- testy integracyjne z H2
- Spring Security i logowanie
- zmiane bazy H2 na PostgreSQL albo MySQL
