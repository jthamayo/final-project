# Final Project

## Description

A full-stack web application built with Java and Spring Boot on the backend, and React with TypeScript on the frontend.

## Features

- User authentication and authorization  
- CRUD operations for managing data  
- Responsive and modern UI using Tailwind CSS  
- Integration between frontend and backend via REST API  
- Unit and integration testing  
- Clean and modular project structure  

## Technologies Used

**Backend:**
- Java 17  
- Spring Boot  
- MySQL  
- Maven  
- JUnit  
- Mockito  

**Frontend:**
- React  
- TypeScript  
- Tailwind CSS


## Getting stated

These instructions will help you run the project locally for development and testing purposes.


## Installation

### Setup Spring boot Back end app

1. Clone the repository

```bash
 git clone https://github.com/jthamayo/final-project.git 
 ```

2. Navigate to the backend directory

```bash
 cd backend 
 ```

3. Install MySQL, Start MySQL Server and create a database

4. Configure `application.properties`

* Copy the `src/main/resources/application.properties.example` file


```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

* update it with your database credentials `<username>`, `<password>` and your `<databaseName>`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/<databaseName>
spring.datasource.username=<username>
spring.datasource.password=<password>
```
* add your own JWT secret Key `<secretKey>`:
```properties
app.jwtSecret=<secretKey>

```

4. Run the app

```bash
 mvn spring-boot:run 
 ```

The server will start on port `8080`

### Setup React Front end app

1. Move to the front-end directory

```bash
 cd frontend 
 ```

2. Run the following command to install the dependencies and start the application

```bash
npm install && npm start 
```

3. Open your browser and go to `http://localhost:5173`

## Testing

At the moment this project only includes **unit tests** in the services layer to verify business logic.

### Test Structure

All test classes are located in the `src/test/java` directory.

### Frameworks used

- JUnit 5 – Test framework  
- Spring Boot Test – Spring context support  
- Mockito – Mocking and stubbing

### To run all tests using maven

```bash
 mvn test 
 ```
![tests](https://res.cloudinary.com/ddguqr8l8/image/upload/v1749163191/Captura_de_pantalla_2025-06-06_003730_ck0vcv.png)
