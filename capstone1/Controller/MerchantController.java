package com.example.capstone1.Controller;

import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Service.MerchantService;
import com.example.capstone1.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {
    private final MerchantService merchantService;
    @GetMapping("/get")
    public ResponseEntity getMerchants(){
        return ResponseEntity.status(HttpStatus.OK).body(merchantService.getMerchants());
    }
    @PostMapping("/add")
    public ResponseEntity addMerchant(@RequestBody@Valid Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(HttpStatus.OK).body("merchant added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable String id,@RequestBody@Valid Merchant merchant,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        boolean isUpdated=merchantService.updateMerchant(id, merchant);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body("merchant updated");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong merchant id");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable String id){
        boolean isDeleted=merchantService.deleteMerchant(id);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body("merchant deleted");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong merchant id");
    }
}
