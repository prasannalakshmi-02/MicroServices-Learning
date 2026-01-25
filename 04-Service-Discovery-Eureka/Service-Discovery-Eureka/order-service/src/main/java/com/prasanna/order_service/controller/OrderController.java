package com.prasanna.order_service.controller;

import com.prasanna.order_service.client.ProductClient;
import com.prasanna.order_service.dto.ProductDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private ProductClient productClient;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String id){

        ProductDTO product = productClient.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/create-product")
    public ResponseEntity<String> createOrder(@RequestBody ProductDTO product){
          ProductDTO createdProduct = productClient.createOrder(product);
          return  ResponseEntity.ok("Order created for product:" + createdProduct.getName());
    }


}
