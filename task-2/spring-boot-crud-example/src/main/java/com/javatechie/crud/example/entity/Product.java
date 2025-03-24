package com.javatechie.crud.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data  // Generates Getters, Setters, `toString()`, and `equals()`
@AllArgsConstructor  // Generates a constructor with all fields
@NoArgsConstructor  // Generates a no-args constructor
@Document(collection = "products")  // MongoDB collection name
public class Product {
    @Id
    private String id;  // MongoDB uses String IDs instead of Long
    private String name;
    private Double price;
}
