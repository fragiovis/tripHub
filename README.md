# 🧭 TripHub — Travel Itineraries Community

*Web Information Systems course (Sistemi Informativi su Web) · Università degli Studi Roma Tre (2024)*

A web application where travellers share destinations and build itineraries. Registered users publish destinations together with places to visit, activities and local food. They then create multi-day itineraries for a destination, review other people's itineraries and save their favourites to their profile.

## Features
- Browse destinations and their itineraries (public)
- Sign up / log in (Spring Security, BCrypt-hashed passwords)
- Publish destinations, places, activities and local food, with image upload
- Create itineraries (name, description, number of days) for a destination
- Review itineraries with a comment and a rating; save itineraries to your profile
- Edit your profile data
- Server-side validation of users and destinations

Domain model: `Destinazione` (destination), `Itinerario` (itinerary), `Luogo` (place), `Attivita` (activity), `Cibo` (food), `Recensione` (review), `Utente` (user). See `modello_triphub.pptx` for the domain model.

## Tech stack
Java 17 · Spring Boot 3.2 · Spring MVC · Spring Data JPA / Hibernate · Spring Security · Thymeleaf · PostgreSQL · Maven

## Running it
1. Create a PostgreSQL database named `triphub`.
2. Set your database credentials in `src/main/resources/application.properties` (`spring.datasource.username` / `password`).
3. Start the app:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Open http://localhost:8083. Sample data is loaded from `src/main/resources/import.sql`.
