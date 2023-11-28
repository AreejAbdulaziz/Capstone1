package com.example.capstone1.Controller;

import com.example.capstone1.Model.Product;
import com.example.capstone1.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping("/get")
    public ResponseEntity getProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts());
    }
    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody@Valid Product product, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        boolean isAdded=productService.addProduct(product);
        if(isAdded){
            return ResponseEntity.status(HttpStatus.OK).body("product added");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong category id");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable String id,@RequestBody@Valid Product product,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        int status=productService.updateProduct(id, product);
        switch (status){
            case 1:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("category id is incorrect");
            case 2:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("product id is incorrect ");
            default:
                return ResponseEntity.status(HttpStatus.OK).body("product updated");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id){
        boolean isDeleted= productService.deleteProduct(id);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body("product deleted");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong id");
    }
    @GetMapping("/sort/{name}/{min}/{max}")
    public ResponseEntity sort(@PathVariable String name,@PathVariable int min,@PathVariable int max){
        ArrayList<Product>sortedProducts=productService.sort(name, min, max);
    return ResponseEntity.status(HttpStatus.OK).body(sortedProducts);
    }
}
