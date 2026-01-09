package com.prasanna.order_service.controller;

import com.prasanna.order_service.dto.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;


@RestController
@RequestMapping("/orders")
public class OrderController {

    private final RestClient restClient;

    public OrderController(RestClient restClient){
        this.restClient = restClient;
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<String> placeOrder(@PathVariable String orderId){
        String url = "http://localhost:8081/products/101";
            ProductDTO productResult = restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(ProductDTO.class);
            String resultMsg =  "Order ID :" + orderId + " confirmed for " + productResult.getName();
            return ResponseEntity.ok(resultMsg);
    }

    @PostMapping("/createProduct")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO){
         String url = "http://localhost:8081/products";

            ProductDTO response = restClient.post()
                    .uri(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(productDTO)
                    .retrieve()
                    .body(ProductDTO.class);

            return ResponseEntity.ok(response);
    }

}
