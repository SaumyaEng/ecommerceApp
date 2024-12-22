# ecommerceApp# E-Commerce Application Documentation

## Overview
This document provides details about the E-Commerce Application, including setup instructions, API documentation, and WebSocket functionality.

---

## Setup Instructions

### Prerequisites
- Java 17+
- Maven 3.8+
- IDE (e.g., IntelliJ IDEA, Eclipse) for local development
- Git for version control

### Steps to Set Up the Application

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/SaumyaEng/ecommerceApp.git
   cd <repository-folder>
   ```

2. **Configure Database:**
   - Update `application.properties` or `application.yml` file in the `src/main/resources` directory with your database credentials:
     ```properties
    spring:
  datasource:
    url: jdbc:h2:mem:ecommerce
    driver-class-name: org.h2.Driver
    username: sa
    password: password
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    show-sql: true
    hibernate:
      ddl-auto: update

     ```

3. **Install Dependencies:**
   ```bash
   mvn clean install
   ```

4. **Run the Application:**
   ```bash
   mvn spring-boot:run
   ```
   The application will start on `http://localhost:8080/` by default.

5. **Access WebSocket Endpoint:**
   - WebSocket Endpoint: `http://localhost:8080/ws`
   - Use tools like Postman or any WebSocket client to test WebSocket features.

---

## API Documentation

### Authentication APIs

#### **1. User Registration**
- **Endpoint:** `/api/auth/signup`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "name": "John Doe",
    "email": "johndoe@example.com",
    "password": "securepassword"
  }
  ```
- **Response:**
  ```json
  {
    "message": "User registered successfully"
  }
  ```

#### **2. User Login**
- **Endpoint:** `/api/auth/signin`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "email": "johndoe@example.com",
    "password": "securepassword"
  }
  ```
- **Response:**
  ```json
  {
    "access_token": "<JWT_ACCESS_TOKEN>",
    "refresh_token": "<JWT_REFRESH_TOKEN>"
  }
  ```

#### **3. Refresh Access Token**
- **Endpoint:** `/api/auth/refresh`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "refresh_token": "<JWT_REFRESH_TOKEN>"
  }
  ```
- **Response:**
  ```json
  {
    "access_token": "<NEW_JWT_ACCESS_TOKEN>"
  }
  ```

---

### Product APIs

#### **1. Create Product**
- **Endpoint:** `/api/products`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "name": "Product Name",
    "price": 99.99,
    "description": "Product Description"
  }
  ```
- **Response:**
  ```json
  {
    "id": 1,
    "name": "Product Name",
    "price": 99.99,
    "description": "Product Description"
  }
  ```

#### **2. List All Products**
- **Endpoint:** `/api/products`
- **Method:** `GET`
- **Response:**
  ```json
  [
    {
      "id": 1,
      "name": "Product Name",
      "price": 99.99,
      "description": "Product Description"
    }
  ]
  ```

---

### Purchase API

#### **Buy a Product**
- **Endpoint:** `/api/buy/{productId}`
- **Method:** `POST`
- **Response:**
  ```json
  {
    "message": "Purchase successful",
    "productId": 1
  }
  ```

---

## WebSocket Functionality

### WebSocket Endpoint
- **URL:** `/ws`
- **Protocol:** STOMP over WebSocket

### Real-Time Product Price Updates

#### Subscribe to Product Price Updates
- **Topic:** `/topic/product-price/{productId}`

#### Example Message from Server
```json
{
  "productId": 1,
  "realTimePrice": 45.99
}
```

---

## Testing

1. Use Postman for REST API testing.
2. Use WebSocket client tools (e.g., Postman, WebSocket King) to test WebSocket subscriptions.

---

