# Second Exercise: Extending Hexagonal Architecture

Extend the existing Java 21 + Spring Boot Maven project that uses hexagonal architecture to add a new use case.
This project separates the Domain Layer (for entities only) from the Application Layer (for use cases and ports).

## Goal

Implement a new use case that retrieves the current stock prices of all companies associated with a given user. This simulates an investment portfolio. It's a simplified, educational scenario to gradually build up real business logic.

## Requirements

### 1. Domain Layer (Entities only)
- Create a `User` entity with the following fields:
  - `String id`
  - `String name`
  - `List<String> companySymbols` — official stock ticker symbols (e.g., "AAPL", "GOOGL").
- Ensure proper encapsulation (constructors, accessors).

### 2. Application Layer (Use cases and ports)
- Define a use case interface: `GetUserInvestmentsUseCase`.
  - Method: `UserInvestmentsDto getUserInvestments(String userId)`
- Create the following ports:
  - `LoadUserPort`: `Optional<User> loadUserById(String id)`
  - Reuse `StockPriceProviderPort` (defined previously) to obtain stock prices.
- Implement `GetUserInvestmentsService` as the use case, wired with both ports.
- Define DTOs:
  - `InvestmentDto`: contains `symbol`, `currentPrice`, `currency` (optional), `fetchedAt`.
  - `UserInvestmentsDto`: contains `userId`, `userName`, and a list of `InvestmentDto`.

### 3. Secondary Adapter: InMemoryUserRepository
- Implements `LoadUserPort`.
- Internally uses a `Map<String, User>` preloaded with 2–3 demo users:
 

### 4. Primary Adapter: REST Controller
- Create a new controller:
  - `GET /api/users/{userId}/investments`
  - Calls the `GetUserInvestmentsUseCase` and returns the `UserInvestmentsDto` as JSON.

### 5. Business Flow (Application Layer logic)
- The use case loads the user by ID via `LoadUserPort`.
- If not found, throw an exception (e.g., `UserNotFoundException`).
- For each symbol, call the `StockPriceProviderPort`.
- Collect the results and return the full `UserInvestmentsDto`.

### 6. Architecture and Clean Code Guidelines
- Use only Spring Boot, no Lombok.
- Respect strict separation: REST and repositories are adapters, business logic use cases stays in application layer, and domain is clean.
- Use Spring profiles (`mock`, `finhub`) to switch implementations of `StockPriceProviderPort`.

### 7. Testing
- Add a unit test for `GetUserInvestmentsService` using the in-memory user repository and a fake price provider.

### 8. Bonus
- Add Swagger/OpenAPI documentation for the new endpoint.

## Output

- Updated source tree:
```
model/User.java
application/port/in/GetUserInvestmentsUseCase.java
application/port/out/LoadUserPort.java, StockPriceProviderPort.java
application/GetUserInvestmentsService.java
adapter/in/web/UserInvestmentsController.java
adapter/out/memory/InMemoryUserRepository.java
```
- Everything should build and run with `mvn spring-boot:run`.

This exercise combines software engineering architecture best practices with a gradual learning curve, ideal for teaching advanced Java and Spring Boot with hexagonal architecture.
