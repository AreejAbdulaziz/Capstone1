package com.example.capstone1.Service;

import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.MerchantStock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService {
    private final ProductService productService;
    private final MerchantService merchantService;
    ArrayList<MerchantStock>merchantStocks=new ArrayList<>();
    public ArrayList<MerchantStock>getMerchantStocks(){
        return merchantStocks;
    }
    public int addMerchantStock(MerchantStock merchantStock){
        for(int i=0;i<productService.products.size();i++){
            if(productService.products.get(i).getId().equals(merchantStock.getProductId())){
                for(int j=0;j<merchantService.merchants.size();j++) {
                    if (merchantService.merchants.get(j).getId().equals(merchantStock.getMerchantId())) {
                        merchantStocks.add(merchantStock);
                        return 0;
                    }
                } return 1; //بعد الفور
            }
        } return 2;
    }
    public int updateMerchantStock(String id,MerchantStock merchantStock){
       for(int i=0;i<merchantStocks.size();i++){
            if(merchantStocks.get(i).getId().equals(id)){
                for(int j=0;j<productService.products.size();j++) {
                    if (productService.products.get(j).getId().equals(merchantStock.getProductId())) {
                        for (int p = 0; p < merchantService.merchants.size(); p++) {
                            if (merchantService.merchants.get(p).getId().equals(merchantStock.getMerchantId())) {
                                merchantStocks.set(p, merchantStock);
                                return 0;
                            }
                        }
                        return 1;
                    }
                } return 2;
            }
        } return 3;
    }
    public boolean deleteMerchantStock(String id){
        for(int i=0;i<merchantStocks.size();i++){
            if(merchantStocks.get(i).getId().equals(id)){
                merchantStocks.remove(i);
                return true;
            }
        } return false;
    }
    public int addStock(String productId,String merchantId,int amount){
        for(int i =0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getMerchantId().equals(merchantId)) {
                for (int j = 0; j < merchantStocks.size(); j++) {
                    if (merchantStocks.get(j).getProductId().equals(productId)) {
                        {
                            merchantStocks.get(i).setStock(merchantStocks.get(i).getStock() + amount);
                            return 0;
                        }
                    }
                } return 1;
            }
        } return 2;
    }
    public Merchant suggestMerchant(String productId, String merchantId) {
        for (int j = 0; j < merchantStocks.size(); j++) {
            if (merchantStocks.get(j).getProductId().equals(productId)) {
                for (int i = 0; i < merchantStocks.size(); i++) {
                    if (!merchantStocks.get(i).getMerchantId().equals(merchantId)) {
                        for (int s = 0; s < merchantService.merchants.size(); s++) {
                            if(merchantService.merchants.get(s).getId().equals(merchantStocks.get(i).getMerchantId()))
                            return merchantService.merchants.get(s);
                        }
                      }

                   }
                }

            }
        return null;
        }
    }

