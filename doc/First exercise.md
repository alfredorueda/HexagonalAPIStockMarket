# First Exercise: Hexagonal Architecture Implementation

Create a fully functional Maven project in Java 21 using Spring Boot, following the hexagonal architecture pattern (ports and adapters) with a clear package separation between the Application Layer and the Domain Layer (model).

## Requirements

- Use Java 21 and Spring Boot.
- Use Maven as the build system.
- Follow clean hexagonal architecture with the following package structure (not modules):

### Layer separation:

- `application` package:
  - Contains:
    - Use case interfaces (input ports).
    - Use case implementations (application services).
    - Output ports (interfaces only).
- `model` package:
  - Contains:
    - Rich domain entities. 
    - Value objects and domain-level exceptions.

- `adapters` package:

  - Primary adapter:
    - A Spring REST controller that exposes an endpoint to query stock prices.
  - Secondary adapters:
    - `FinhubStockPriceAdapter` → fetches real data from the FinHub API using HTTP.
    - `MockStockPriceAdapter` → returns dummy values for testing/demo purposes.
  - Adapters should implement the appropriate interfaces defined in the `application.port.out` package.

### Behavior:

- Define a use case: `GetStockPriceUseCase`
  - Input: `String symbol`
  - Output: a DTO with symbol, price, currency (optional), and timestamp.
- The application service must implement the use case interface and use the `StockPriceProviderPort` (output port) to fetch the data.
- The controller exposes the endpoint: `GET /api/stocks/{symbol}`

### Spring Profile Switching:

- Use Spring profiles `finhub` and `mock` to switch between the two adapter implementations.

### Additional requirements:

- No database or persistence required.
- No Lombok: use standard Java for constructors, getters, etc.
- Add a Swagger/OpenAPI configuration to document the endpoint.
- Include a test for the use case using the mock adapter.
- The project must build and run out of the box with `mvn spring-boot:run`.

## Output

- A complete Maven `pom.xml` file.
- Source code following this structure:

```
src/main/java/com/example/stockhexagonal/
│
├── application/
│   ├── port/
│   │   ├── in/        # Use case interfaces
│   │   └── out/       # Output port interfaces
│   └── service/       # Use case implementations
│
├── model/             # Domain entities and value objects
│
├── adapter/
│   ├── in/web/        # REST controller
│   └── out/
│       ├── finhub/    # Real HTTP client implementation
│       └── mock/      # Mock implementation
```

- One unit test in:
```
src/test/java/com/example/stockhexagonal/application/service/GetStockPriceServiceTest.java
```

- Clear JavaDoc-style comments in English for all public types and methods.
- The project must be easy to extend with new use cases in the same layered structure.
