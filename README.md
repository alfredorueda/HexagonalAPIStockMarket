# 🏗️ Hexagonal Architecture with Spring Boot

A progressive educational project for learning and applying Hexagonal Architecture (Ports & Adapters) using clean, modern Java practices. This project is designed for Software Engineering students and backend developers looking to understand and implement a professional, well-structured backend architecture.

## 🎯 Project Overview

This repository serves as a hands-on learning resource that demonstrates how to implement Hexagonal Architecture in a real Spring Boot application. The project progressively builds complexity through sequential exercises, allowing learners to understand each layer of the architecture and how they interact.

Each exercise documentation is available in the `doc/` folder.

## 🧩 Architecture Explanation

This project strictly follows the principles of Hexagonal Architecture (also known as Ports & Adapters), with a clear separation between:

- **Domain Layer (`model/`)**: Contains rich domain entities and value objects that encapsulate business rules and logic
- **Application Layer (`application/`)**: Contains use cases, port interfaces, and service implementations that orchestrate business logic
- **Adapters Layer (`adapter/`)**: Contains implementations of the interfaces defined in the application layer:
  - Primary adapters (`adapter/in/web/`): REST controllers that expose the application's API
  - Secondary adapters:
    - `adapter/out/finhub/`: Real HTTP client to fetch stock data from the FinHub API
    - `adapter/out/mock/`: Mock implementation for testing and demos

```
src/main/java/com/example/stockhexagonal/
│
├── application/                # Application Layer
│   ├── port/
│   │   ├── in/                 # Input ports (use case interfaces)
│   │   └── out/                # Output ports (secondary adapter interfaces)
│   ├── GetStockPriceService    # Use case implementations
│   ├── GetUserInvestmentsService
│   └── ...                     # DTOs and other application components
│
├── model/                      # Domain Layer
│   ├── StockPrice              # Domain entities and value objects
│   ├── User
│   ├── Money
│   ├── Symbol
│   └── ...
│
└── adapter/                    # Adapters Layer
    ├── in/
    │   └── web/                # REST controllers (primary adapters)
    └── out/
        ├── finhub/             # HTTP client implementation
        └── mock/               # Mock implementation
```

The key advantage of this architecture is that the domain and application layers have no dependencies on external 
frameworks or technologies, making them easy to test and maintain.

## 📚 Learning Structure and Exercises

The project is structured in incremental learning exercises, each building on the previous one:

| Exercise | Branch        | Description |
|----------|---------------|-------------|
| 1        | `exercise-01` | Implements a REST API to retrieve stock prices using both a real HTTP adapter (FinHub) and a mock adapter via Spring profiles. |
| 2        | `exercise-02` | Adds a `User` entity with a list of stock symbols and a use case to retrieve investment data using an in-memory repository and business logic composition. |

⚠️ Only Exercises 1 and 2 are currently available. More will be added in future branches.

For detailed instructions on each exercise, please refer to the corresponding documentation in the `doc/` folder.

## 🚀 How to Run the Project

### Prerequisites
- Java 21 or higher
- Maven

### Running with Maven

```bash
# Clone the repository
git clone <repository-url>
cd HexagonalAPI

# Run with mock adapter (for testing without external dependencies)
mvn spring-boot:run -Dspring.profiles.active=mock

# Run with real HTTP FinHub adapter
mvn spring-boot:run -Dspring.profiles.active=finhub
```

⚠️ When using the `finhub` profile, make sure to configure your FinHub API key in `application-finhub.properties`.

### API Endpoints

Once the application is running, you can access:

- Swagger UI: http://localhost:8080/swagger-ui.html
- API Docs: http://localhost:8080/api-docs
- Stock Price: http://localhost:8080/api/stocks/{symbol}
- User Investments: http://localhost:8080/api/users/{id}/investments

## 🎓 Educational Goals

By studying and extending this project, you will learn:

- How to apply Hexagonal Architecture in a real-world backend system
- How to decouple business logic from technical concerns using input/output ports
- How to write testable, modular, clean Java code
- How to grow a project through evolutionary design, from mock implementations to real-world logic
- How to implement and switch between different adapters using Spring profiles

## 🛠️ Technologies Used

- Java 21
- Spring Boot 3
- Maven
- OpenAPI / Swagger
- JUnit 5
- WebFlux for HTTP client
- Hexagonal Architecture principles

## 👨‍💻 Contributing

Contributions to improve the educational value of this project are welcome. Please feel free to submit issues or pull requests.

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## ✍️ Author and Maintainer

Developed by Alfredo Rueda Unsain, professor of Software Engineering at Tecnocampus (Universitat Pompeu Fabra).

---

Happy coding and learning! 🚀