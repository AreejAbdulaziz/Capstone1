package com.example.capstone1.Service;

import com.example.capstone1.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final CategoryService categoryService;
    ArrayList<Product>products=new ArrayList<>();
    public ArrayList<Product> getProducts(){
        return products;
    }
    public boolean addProduct(Product product){
        for(int i=0;i<categoryService.categories.size();i++){
            if(product.getCategoryID().equals(categoryService.categories.get(i).getId())){
                products.add(product);
                return true;
            }
        } return false;
    }
    public int updateProduct(String id,Product product){
        for(int i=0;i<products.size();i++){
            if(products.get(i).getId().equals(id)){
                for(int j=0;j<categoryService.categories.size();j++){
                    if(categoryService.categories.get(j).getId().equals(product.getCategoryID())){
                        products.set(i,product);
                        return 0;
                    }
                } return 1;
            }
        } return 2;
    }
    public boolean deleteProduct(String id){
        for(int i=0;i<products.size();i++){
            if(products.get(i).getId().equals(id)){
                products.remove(i);
                return true;
            }
        }
        return false;
    }
    public ArrayList<Product> sort(String name,int min,int max){
        ArrayList<Product>sortedProducts=new ArrayList<>();
         for(int i=0;i<products.size();i++){
                 if(products.get(i).getName().equals(name)&&products.get(i).getPrice()>=min&&products.get(i).getPrice()<=max){
                     sortedProducts.add(products.get(i));
                 }
             } return sortedProducts;
         }
    }

