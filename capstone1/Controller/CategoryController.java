package com.example.capstone1.Controller;

import com.example.capstone1.Model.Category;
import com.example.capstone1.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/get")
    public ResponseEntity getCategories(){
        ArrayList<Category>categories=categoryService.getCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }
    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody@Valid Category category, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body("category added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable String id,@RequestBody@Valid Category category,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        boolean isUpdated=categoryService.updateCategory(id, category);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body("category updated");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong id");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable String id){
        boolean isDeleted=categoryService.deleteCategory(id);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body("category deleted");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong id");
    }
}
