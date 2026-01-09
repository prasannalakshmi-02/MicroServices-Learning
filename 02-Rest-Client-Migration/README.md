
# 02 - Microservices Communication: RestClient Migration 🚀

> **Status:** Completed ✅
> **Tech Stack:** Spring Boot 3.2, Java 17, RestClient, Global Exception Handling

## 📖 Overview
This module represents the **modernization** of synchronous microservices communication. I refactored the legacy `RestTemplate` implementation to use the new **`RestClient`** (introduced in Spring Boot 3.2). 

Beyond syntax changes, this project focuses on **Production Stability** by implementing timeouts, global error handling, and structured JSON responses.

---

## 🏗️ Key Architectual Changes

| Feature | Old Approach (Module 01) | New Approach (Module 02) |
| :--- | :--- | :--- |
| **HTTP Client** | `RestTemplate` (Maintenance Mode) | **`RestClient`** (Fluent API, Modern Standard) |
| **Error Handling** | `try-catch` inside every Controller | **Global `@RestControllerAdvice`** (Centralized Guard) |
| **Stability** | No Timeouts (Infinite Waiting) | **3-Second Timeout** (Fail Fast Strategy) |
| **Response Format** | Raw String messages | **Structured JSON DTO** (Message, Status, Timestamp) |

---

## ⚙️ How It Works (Internal Flow)

1. **Client** (Browser/Postman) hits the `Order-Service`.
2. **Order-Service** uses `RestClient` to call `Product-Service`.
    * *Scenario A (Success):* Product Service returns data -> Order Service processes it.
    * *Scenario B (Server Down):* `RestClient` throws `ResourceAccessException` -> **GlobalExceptionHandler** catches it -> Returns clean JSON 503 error.
    * *Scenario C (Slow Server):* Request takes >3 seconds -> **Timeout** triggers -> Returns clean JSON 503 error.

---

## 🚀 How to Run

1. **Start Product-Service** (Port `8081`)
2. **Start Order-Service** (Port `8080`)

### 🔗 API Endpoints

#### 1. GET Request (Fetch Data)
* **URL:** `http://localhost:8080/orders/{id}`
* **Method:** `GET`
* **Description:** Fetches product details from Product Service.

#### 2. POST Request (Send Data)
* **URL:** `http://localhost:8080/orders/create-product`
* **Method:** `POST`
* **Body (JSON):**
    ```json
    {
      "name": "Nothing Phone 2",
      "price": 45000.0
    }
    ```
* **Description:** Sends a new product object to the Product Service inventory.

---

## 🧪 Testing Resilience (Proof of Work)

### Test Case 1: Service Down (Circuit Breaker Logic)
1. Stop the `product-service` application.
2. Hit the Order API (`GET` or `POST`).
3. **Expected Output:** You will receive a structured **503 Service Unavailable** JSON response instead of a crash.

```json
{
    "message": "Sorry, the Product System is currently down. Please try again later.",
    "statusCode": "503",
    "timestamp": "2026-01-09T13:10:00"
}

```

### Test Case 2: Timeout (Hanging Server)

1. In `AppConfig.java`, the timeout is set to **3 seconds**.
2. If the `Product-Service` takes 5 seconds to respond (simulated delay), the `Order-Service` will automatically cancel the request and return the 503 error.
3. *Benefit:* This prevents the Order Service from freezing/hanging indefinitely.

---

```

```
