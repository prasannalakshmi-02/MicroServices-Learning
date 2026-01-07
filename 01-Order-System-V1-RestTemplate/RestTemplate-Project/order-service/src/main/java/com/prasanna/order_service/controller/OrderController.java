package com.prasanna.order_service.controller;

import com.prasanna.order_service.dto.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final RestTemplate restTemplate;

    public OrderController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<String> placeOrder(@PathVariable String orderId){
        String url = "http://localhost:8081/products/101";
        try{
            ProductDTO productResult = restTemplate.getForObject(url, ProductDTO.class);
            String resultMsg =  "Order ID :" + orderId + " confirmed for " + productResult.getName();
            return ResponseEntity.ok(resultMsg);
        }catch(ResourceAccessException ex){
            System.out.println("Alert! Product-Service is down");
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Sorry, the Product System is currently down. Please try again later.");
        }

    }
}
