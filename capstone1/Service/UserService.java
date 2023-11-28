package com.example.capstone1.Service;

import com.example.capstone1.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {
    private final MerchantStockService merchantStockService;
    private final ProductService productService;
    ArrayList<User>users=new ArrayList<>();
    public ArrayList<User>getUsers(){
        return users;
    }
    public void addUser(User user){
        users.add(user);
    }
    public boolean updateUser(String id,User user){
        for(int i=0;i<users.size();i++){
            if(users.get(i).getId().equals(id)){
                users.set(i,user);
                return true;
            }
        } return false;
    }
    public boolean deleteUser(String id){
        for(int i=0;i<users.size();i++){
            if(users.get(i).getId().equals(id)){
                users.remove(i);
                return true;
            }
        } return false;
    }
    public int buyProduct(String id,String productId,String merchantId,int amount){
        for(int i=0;i<users.size();i++) {
            if (users.get(i).getId().equals(id)) {
                for (int j = 0; j < merchantStockService.merchantStocks.size(); j++) {
                    if (merchantStockService.merchantStocks.get(j).getProductId().equals(productId)) {
                        for (int p = 0; p < merchantStockService.merchantStocks.size(); p++) {
                            if (merchantStockService.merchantStocks.get(p).getMerchantId().equals(merchantId)) {
                                    if(merchantStockService.merchantStocks.get(p).getStock()<amount)//الستوك حق تاجر محدد
                                    {
                                        return 2; // stock<amount
                                    }
                                        for (int a = 0; a < productService.products.size(); a++)
                                        {
                                            if (productService.products.get(a).getId().equals(productId)) {
                                                if(productService.products.get(a).getPrice()*amount > users.get(i).getBalance()){
                                                    return 1; //price > balance
                                                }
                                                merchantStockService.merchantStocks.get(p).setStock(merchantStockService.merchantStocks.get(p).getStock() - amount);
                                                users.get(i).setBalance(users.get(i).getBalance() - productService.products.get(j).getPrice()*amount);
                                                return 0;
                                            }
                                        }
                            }
                        } return 3;//wrong merchant id
                    }
                } return 4; //wrong product id
            }
        } return 5;//wrong user id
    }
}





