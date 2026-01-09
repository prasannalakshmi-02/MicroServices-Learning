package com.prasanna.product_service.controller;


import com.prasanna.product_service.model.Product;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id){

        return new Product(id, "DELL XS lAPTOP", 50000);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        System.out.println("Received Request to add :" + product.getName());
        return product;
    }

}
