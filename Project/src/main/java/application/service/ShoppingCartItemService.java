package application.service;

import application.model.ShoppingCartItem;
import application.repository.ShoppingCartItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartItemService {

    @Autowired
    private ShoppingCartItemRepo shoppingCartItemRepo;

    //add item
    public boolean addItem(ShoppingCartItem shoppingCartItem)
    {
        try{
            shoppingCartItemRepo.save(shoppingCartItem);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //delete item
    public boolean deleteItem(ShoppingCartItem shoppingCartItem)
    {
        try{
            shoppingCartItem.setBook(null);
            shoppingCartItemRepo.delete(shoppingCartItem);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
