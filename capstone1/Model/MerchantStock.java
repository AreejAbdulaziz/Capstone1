package com.example.capstone1.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {
    @NotEmpty(message = "Merchant stock id cannot be empty")
    private String id;
    @NotEmpty(message = "product id cannot be empty")
    private String productId;
    @NotEmpty(message = "merchant id cannot be empty")
    private String merchantId;
    @NotNull(message = "stock cannot be empty")
    @Min(value = 10,message = "stock have to be more than 10 at start")
    private int stock;
}
