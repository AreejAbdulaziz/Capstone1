package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {
    @NotEmpty(message = "Category id cannot be empty")
    private String id;
    @Size(min = 4,message = "Category name have to be more than 3 length long")
    @NotEmpty(message = "Category name cannot be empty")
    private String name;
}
