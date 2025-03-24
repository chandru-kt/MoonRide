package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.entity.Product;
import com.javatechie.crud.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")  // Added base path for better structure
public class ProductController {

    @Autowired
    private ProductService service;

    // Add a single product
    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) {
        return service.saveProduct(product);
    }

    // Add multiple products
    @PostMapping("/addProducts")
    public List<Product> addProducts(@RequestBody List<Product> products) {
        return service.saveProducts(products);
    }

    // Get all products
    @GetMapping("/products")
    public List<Product> findAllProducts() {
        return service.getProducts();
    }

    // Get product by ID (Fix: Change `int id` to `String id`)
    @GetMapping("/productById/{id}")
    public Product findProductById(@PathVariable String id) {
        return service.getProductById(id);
    }

    // Get product by name
    @GetMapping("/product/{name}")
    public Product findProductByName(@PathVariable String name) {
        return service.getProductByName(name);
    }

    // Update a product
    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product) {
        return service.updateProduct(product);
    }

    // Delete a product (Fix: Change `int id` to `String id`)
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        return service.deleteProduct(id);
    }
}
