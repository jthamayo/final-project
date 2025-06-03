# final-project

## Description
## Features

## Technologies

- Java 17
- Spring Boot
- MySQL
- React
- Maven

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