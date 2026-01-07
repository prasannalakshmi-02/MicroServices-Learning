
# 📦 Synchronous Communication (Order & Product Service)

This module demonstrates **Synchronous Inter-Service Communication** using `RestTemplate` in Spring Boot. It simulates a real-world e-commerce scenario where an **Order Service** depends on a **Product Service** to fulfill requests.

## 🏗️ Architecture
This system consists of two isolated microservices:

| Service | Port | Role | Description |
| :--- | :--- | :--- | :--- |
| **Product-Service** | `8081` | Provider | Acts as the "Inventory System". Returns product details (Name, Price). |
| **Order-Service** | `8080` | Consumer | Acts as the "Storefront". Calls Product Service to verify items before confirming orders. |

---

## ✨ Key Features
* **Synchronous Communication:** Uses `RestTemplate` to perform blocking HTTP calls.
* **Data Decoupling (DTO Pattern):** Uses `ProductDTO` in the Order Service to map only necessary fields, keeping the services loosely coupled.
* **Fault Tolerance (Resilience):** Implements graceful error handling. If the Product Service is down, the Order Service catches the connection error and returns a friendly `503 Service Unavailable` message instead of crashing.

---

## 🛠️ Tech Stack
* **Java 17**
* **Spring Boot 3.x** (Web)
* **RestTemplate** (Client)

---

## 🚀 How to Run

### Step 1: Start the Provider
1. Open the `product-service` folder.
2. Run `ProductServiceApplication.java`.
3. Verify it started on **Port 8081**.

### Step 2: Start the Consumer
1. Open the `order-service` folder.
2. Run `OrderServiceApplication.java`.
3. Verify it started on **Port 8080**.

### Step 3: Test the "Happy Path"
Open your browser or Postman and visit:

```

http://localhost:8080/orders/1

```
**Expected Output:**
> "Order ID: 1 confirmed for Dell XPS Laptop"

---

## 🧪 Testing Resilience (Fault Tolerance)
This project handles failures gracefully. To test this:

1. **Stop** the `product-service` (Provider).
2. Keep the `order-service` (Consumer) **running**.
3. Refresh the browser URL: `http://localhost:8080/orders/1`

**Expected Output (Instead of a 500 Error):**
> "Sorry, the Product System is currently down. Please try again later."

*Check the Developer Tools (Network Tab) to verify the status code is **503 (Service Unavailable)**.*

---

## 📝 Learning Notes
* **Why RestTemplate?** While `RestClient` and `WebClient` are newer, `RestTemplate` is used here to understand the fundamental mechanics of blocking HTTP requests and thread management in Java.
* **Next Steps:** Future improvements will involve adding **Netflix Eureka** for Service Discovery to remove hardcoded URLs (`localhost:8081`).


```
