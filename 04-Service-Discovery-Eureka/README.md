# 04 - Service Discovery & Load Balancing (Netflix Eureka) 🧭

> **Status:** Completed ✅
> **Tech Stack:** Spring Cloud Netflix Eureka, Spring Cloud LoadBalancer, OpenFeign

## 📖 Overview
In the previous module (`03-OpenFeign`), we used hardcoded URLs (`localhost:8081`) to connect services. This approach is brittle and fails in dynamic cloud environments where IP addresses change frequently.

This module introduces **Service Discovery** to solve that problem. We implemented a **Eureka Server** (Service Registry) that acts as a dynamic "phonebook" for all microservices.

## 🏗️ Architecture

| Service | Role | Port | Description |
| :--- | :--- | :--- | :--- |
| **Service Registry** | Eureka Server | `8761` | The central server where all services register themselves. |
| **Product Service** | Eureka Client | `8081`, `8082` | Registers itself with Eureka. Running **two instances** to demonstrate scaling. |
| **Order Service** | Eureka Client | `8080` | Discovers Product Service via Eureka and uses **Client-Side Load Balancing**. |

---

## ⚙️ Key Features Implemented

### 1. Dynamic Service Registration
* **Product Service** automatically registers with Eureka on startup.
* No hardcoded IPs in `Order Service`. We now refer to services by name:
  ```java
  @FeignClient(name = "product-service") // No URL!
  public interface ProductClient { ... }
2. Client-Side Load Balancing
Order Service uses Spring Cloud LoadBalancer internally.

It fetches the list of available product-service instances from Eureka.

It distributes HTTP requests across available instances (Round Robin) to prevent overloading a single server.

🚀 How to Run
Step 1: Start the Infrastructure
Run Service Registry (8761).

Verify Dashboard at http://localhost:8761.

Step 2: Start the Provider (Cluster)
Run Product Service (Instance 1) on Port 8081.

Run Product Service (Instance 2) on Port 8082 (using -Dserver.port=8082).

Verify: Check Eureka Dashboard. You should see PRODUCT-SERVICE with 2 availability zones.

Step 3: Start the Consumer
Run Order Service (8080).

Step 4: Test Load Balancing
Hit the API: GET http://localhost:8080/orders/1 multiple times.

Check the logs of both Product Service instances.

Result: You will see requests alternating between 8081 and 8082.

🧠 Key Learnings
Registry Pattern: Centralizing service locations eliminates configuration nightmares.

Client-Side vs Server-Side LB: Learned how Feign handles load balancing internally without needing an external NGINX server.

Eventual Consistency: Understood that Eureka Clients cache registry data (30-second delay), so updates aren't always instant.
