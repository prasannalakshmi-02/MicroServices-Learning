
# 03 - Service-to-Service Communication (Spring Cloud OpenFeign) 🌉

> **Status:** Completed ✅
> **Tech Stack:** Spring Boot 3.2, Spring Cloud OpenFeign, Java 17, REST API

## 📖 Overview
This module demonstrates **Declarative HTTP Communication** between microservices.
Instead of using the older, imperative `RestTemplate` (which requires writing boilerplate code for connections and serialization), this project uses **Spring Cloud OpenFeign**.

By simply defining an interface (`ProductClient`), Spring Boot automatically generates the implementation at runtime to handle the HTTP connection, JSON parsing, and error handling.

**Note:** This module uses **Direct Point-to-Point** communication (Hardcoded URLs). It serves as the foundation before implementing Service Discovery (Eureka) in the next module.

---

## 🏗️ Architecture

| Service | Role | Port | Description |
| :--- | :--- | :--- | :--- |
| **Product Service** | Provider | `8081` | Serves product data (`GET`, `POST`). Acts as the standard REST API. |
| **Order Service** | Consumer | `8080` | Consumes Product Service using a **Feign Client Interface**. |

**Communication Flow:**
`Client (Postman)` -> `OrderController` -> `ProductClient (Interface)` -> `[HTTP Request]` -> `Product Service`

---

## ⚙️ Key Features Implemented

### 1. Declarative Client (`@FeignClient`)
We removed all `RestTemplate` code. The connection is now handled by a simple interface:
```java
@FeignClient(name = "product-service", url = "http://localhost:8081")
public interface ProductClient {
    @GetMapping("/products/{id}")
    ProductDTO getProductById(@PathVariable("id") String id);
}

```

### 2. Global Exception Handling for Feign

Implemented a robust error handling mechanism to catch connection failures without crashing the application.

* **Exception:** `feign.RetryableException` (Connection Refused / Service Down)
* **Response:** Returns `503 Service Unavailable` with a clean JSON error message.

---

## 🚀 How to Run

### Step 1: Start the Provider

1. Navigate to `product-service`.
2. Run the application.
3. **Verify:** Ensure it is running on **Port 8081**.

### Step 2: Start the Consumer

1. Navigate to `order-service`.
2. Run the application.
3. **Verify:** Ensure it is running on **Port 8080**.

### Step 3: Test the Endpoints

**1. Create a Product (via Order Service):**

* **Method:** `POST`
* **URL:** `http://localhost:8080/orders/create-order`
* **Body:**
```json
{
  "name": "iPhone 15",
  "price": 75000
}

```



**2. Get a Product (via Order Service):**

* **Method:** `GET`
* **URL:** `http://localhost:8080/orders/1`
* **Response:** You should see the product details (or a success message depending on your controller logic).

**3. Test Fault Tolerance:**

* Stop the `product-service`.
* Hit the API again.
* **Result:** You will receive a **503 Service Unavailable** JSON response (handled by `GlobalExceptionHandler`).

---

## 🧠 Key Learnings

* **Declarative vs Imperative:** Learned how OpenFeign abstracts away the low-level HTTP details.
* **Proxies:** Understood how Spring creates dynamic proxies for interfaces at runtime.
* **Error Handling:** Learned that Feign throws `RetryableException` instead of `ResourceAccessException`.

```

```
