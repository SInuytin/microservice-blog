# Blog Platform — Educational Java Project

This is an educational blog platform project written in Java using Spring Boot. The main goals are:

- Clean monolithic architecture design with potential for microservice migration;
- By now it's a simple api for blog-related sire
- Applying algorithms and data structures in real-world tasks.

---

## How to run

1. Install PostgreSQL and Docker
2. Run:
```bash
./gradlew build
docker-compose up -d
```
3. Build and start the application:

```bash
./gradlew bootRun
```

---

## Tech Stack

- Java 21
- Spring Boot 3.x
- Spring Data JPA
- Spring Security
- Lombok
- PostgreSQL
- Gradle
- JUnit 5

---

## Architecture

The project is structured as a monolith with clear modular boundaries, making it easy to extract microservices in the future.

### Project Structure:
```java
src/main/java
└─ main
   ├─ auth              # Security, JWT
   ├─ users             # UserService, DTO, and UserDetailsService
   ├─ posts             # Posts managing
   ├─ friends           # FriendService
   ├─ common            # CurrentUser, ExceptionHandler  etc.
   └─ config            # all config for Spring (WebConfig, SecurityConfig)

src/main/resources
├─ application.yml     # config for DB and network
```

Example structure of the `posts` module:

```java
    posts
    |_controller   // handles HTTP requests, RESTful
    |_dto          // Data Transfer Objects
    |_exceptions   // module-specific exceptions
    |_model        // database and domain models
    |_repository   // interface to interact with the database
    |_service      // business logic
```

---

## Implemented Features

### Authorization
- Registration
- Loging
- Authorites
- JWT token system

### Users:
- CRUD
- Role managing,
- Admin functions

### Posts:
- Full CRUD operations
- Search posts by title or name

### Friends:
- Sub/Friend system
- Safe transactions
- All-need functions to build-up friend graph

---

## Future plans

1. add some algorithms to recommend posts and friends
2. add comments and rating
3. add simple frontend 
4. add tests (they fall apart because of auth system)
