package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    @NotEmpty(message = "Product id cannot be empty")
    private String id;
    @NotEmpty(message = "Product name cannot be empty")
    @Size(min = 4,message = "Product name have to be more than 3 length long")
    private String name;
    @NotNull(message = "Product price cannot be null")
    @Positive(message = "enter correct price!")
    private double price;
    @NotEmpty(message = "category id must not be empty")
    private String categoryID;
}
