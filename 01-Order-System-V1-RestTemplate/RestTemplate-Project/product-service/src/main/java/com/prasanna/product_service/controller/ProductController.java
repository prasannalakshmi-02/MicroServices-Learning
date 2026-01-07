package com.prasanna.product_service.controller;


import com.prasanna.product_service.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id){
        return new Product(id, "DELL XS lAPTOP", 50000);
    }

}
