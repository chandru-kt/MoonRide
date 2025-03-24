package com.javatechie.crud.example.service;

import com.javatechie.crud.example.entity.Product;
import com.javatechie.crud.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    // Save a single product
    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    // Save multiple products
    public List<Product> saveProducts(List<Product> products) {
        return repository.saveAll(products);
    }

    // Get all products
    public List<Product> getProducts() {
        return repository.findAll();
    }

    // Get product by ID
    public Product getProductById(String id) {
        return repository.findById(id).orElse(null);
    }

    // Get product by name (Fix: Use Optional to avoid null errors)
    public Product getProductByName(String name) {
        Optional<Product> product = repository.findByName(name);
        return product.orElse(null);  // Return product if found, otherwise return null
    }

    // Delete product by ID
    public String deleteProduct(String id) {
        repository.deleteById(id);
        return "Product removed: " + id;
    }

    // Update product
    public Product updateProduct(Product product) {
        Optional<Product> existingProduct = repository.findById(product.getId());

        if (existingProduct.isPresent()) {
            Product updatedProduct = existingProduct.get();
            updatedProduct.setName(product.getName());
            updatedProduct.setPrice(product.getPrice());
            return repository.save(updatedProduct);
        }
        return null;
    }
}
