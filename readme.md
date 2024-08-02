# Banking System

A Spring Boot application for managing banking operations such as account creation, transactions, balance inquiries, user authentication, loan management, and more.

## Features

- User authentication and role-based access control.
- Account management (create, delete, update).
- Deposit and withdrawal transactions.
- Fund transfers between accounts.
- Transaction history and statements.
- Loan management (apply, approve, repay).
- Notification service for sending alerts and updates.

## Prerequisites

- Java 11 or later
- Maven 3.6.0 or later
- MySQL 8.0 or later

## Setup

### Database Configuration

1. Ensure MySQL is installed and running.
2. Create a database named `banking_system`:

   ```sql
   CREATE DATABASE banking_system;
   ```

3. Update the database connection properties in `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/banking_system
   spring.datasource.username=root
   spring.datasource.password=password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

### Build and Run the Application

1. Clone the repository:

2. Build the project using Maven:

3. Run the application:

## Project Structure

```
Java banking/
│
├── account/
│   ├── Account.java
│   ├── AccountController.java
│   ├── AccountRepository.java
│   └── AccountService.java
│
├── main/
│   └── Main.java
│
├── transactions/
│   ├── Transaction.java
│   ├── TransactionController.java
│   ├── TransactionRepository.java
│   └── TransactionService.java
│
├── user/
│   ├── AuthController.java
│   ├── AuthService.java
│   ├── Role.java
│   ├── User.java
│   └── UserRepository.java
│
├── banking_system/
│   ├── NotificationService.java
│   ├── DatabaseConfig.java
│   ├── Utils.java
│   └── application.properties
│
├── loan/
│   ├── Loan.java
│   ├── LoanController.java
│   ├── LoanRepository.java
│   └── LoanService.java
│
└── pom.xml
```

### Explanation of Modules

- **Account**: Manages bank account operations including creation, deletion, and updates.
- **Transactions**: Handles deposit, withdrawal, and fund transfer transactions.
- **User**: Manages user authentication and authorization.
- **Banking System**: Contains utility classes, database configuration, and notification services.
- **Loan**: Manages loan applications, approvals, and repayments.
- **Main**: Entry point of the Spring Boot application.

## API Endpoints

### User Authentication

- **Login**: `POST /auth/login`
  - Request Body: `{"username": "user", "password": "password"}`

### Account Management

- **Create Account**: `POST /accounts/create`
  - Request Body: `{"ownerUsername": "user", "balance": 1000, "accountType": "SAVINGS"}`
- **Delete Account**: `DELETE /accounts/delete/{id}`
- **Get Account by ID**: `GET /accounts/{id}`
- **Get All Accounts**: `GET /accounts/all`
- **Update Account**: `PUT /accounts/update`
  - Request Body: `{"id": 1, "ownerUsername": "user", "balance": 1500, "accountType": "CHECKING"}`

### Transactions

- **Create Transaction**: `POST /transactions/create`
  - Request Body: `{"type": "DEPOSIT", "amount": 500, "accountId": 1}`

### Loans

- **Apply for Loan**: `POST /loans/apply`
  - Request Body: `{"accountId": 1, "amount": 10000, "interestRate": 5.0, "dueDate": "2024-12-31"}`
- **Approve Loan**: `POST /loans/approve/{id}`
- **Repay Loan**: `POST /loans/repay/{id}`
- **Get All Loans**: `GET /loans/all`

## Acknowledgments

- Spring Boot
- Hibernate
- MySQL
- Lombok

### Running the Application

1. Ensure MySQL is running and the database `banking_system` is created.
2. Configure the database connection in `application.properties`.
3. Run the application using `mvn spring-boot:run`.
