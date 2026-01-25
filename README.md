
# 🚀 Microservices Learning Journey

Welcome to my central repository for mastering Microservices architecture! 

This repository documents my progression from building Monolithic applications to designing resilient Distributed Systems using **Spring Boot**. I am building these projects step-by-step, following industry standards and best practices learned from the "Concept and Coding" channel.

---

## 📂 Repository Structure

This is a Mono-repo organized by architectural concepts. Each folder represents a specific milestone in my learning journey.

### ✅ Completed Modules

* **`01-Rest-Template-Basics/`**
    * **Focus:** Synchronous Communication & Fault Tolerance.
    * **Overview:** A foundational project establishing communication between an `Order-Service` and a `Product-Service`.
    * **Key Learnings:** * Implementing HTTP communication using `RestTemplate`.
        * Handling service downtime gracefully (Resilience) by catching connection errors and returning user-friendly `503` responses.
     
* **`02-Rest-Client-Migration/`**
  * **Focus:** Modernization & Professional Error Handling.
  * **Key Changes:**
    * Migrated from `RestTemplate` to the modern **`RestClient`** (Spring Boot 3.2).
    * Implemented **Global Exception Handling** using `@RestControllerAdvice` to return clean JSON error responses.
    * Added **Timeouts** (3 seconds) to prevent the system from hanging if a service is slow.
   
* **`03-OpenFeign-Client/`**
    * **Focus:** Declarative Communication (Spring Cloud OpenFeign).
    * **Overview:** Replaced imperative HTTP code with declarative interfaces (`@FeignClient`) to simplify service-to-service communication.
    * **Key Learnings:**
        * Abstracting HTTP calls using Interfaces and Dynamic Proxies.
        * Handling Feign-specific exceptions (`RetryableException`) globally.
        * Understanding Point-to-Point communication (without Service Discovery).
     
* **`04-Service-Discovery-Eureka/`**
    * **Focus:** Service Discovery & Client-Side Load Balancing.
    * **Overview:** Implemented **Netflix Eureka** to eliminate hardcoded URLs and enable dynamic scaling.
    * **Key Learnings:**
        * **Registry Pattern:** Built a Eureka Server ("Phonebook") where services automatically register themselves.
        * **Dynamic Scaling:** Ran multiple instances of `Product-Service` on different ports (`8081`, `8082`).
        * **Client-Side Load Balancing:** Configured Feign Client to automatically distribute traffic (Round Robin) across available service instances.


---

## 🛠️ Tech Stack
* **Language:** Java 17
* **Framework:** Spring Boot 3.x
* **Tools:** Maven, Postman, IntelliJ IDEA

---

## 🔮 Future Roadmap
I am actively adding new modules to this repository. Upcoming implementations include:
- [ ] **Modern Clients:** Migrating to `RestClient` (Spring Boot 3.2+).
- [ ] **Service Discovery:** Implementing Netflix Eureka to handle dynamic IP addresses.
- [ ] **Load Balancing:** Client-side load balancing.
- [ ] **API Gateway:** Centralized routing and security.
- [ ] **Asynchronous Communication:** Event-driven architecture using Kafka/RabbitMQ.

---

### 👤 Author
**Prasanna Lakshmi Motati** *Aspiring Backend Engineer | Java & Spring Boot Enthusiast*

```
