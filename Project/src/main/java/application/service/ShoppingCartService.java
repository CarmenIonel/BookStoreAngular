package application.service;

import application.model.ShoppingCart;
import application.model.ShoppingCartItem;
import application.model.User;
import application.repository.ShoppingCartItemRepo;
import application.repository.ShoppingCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepo shoppingCartRepo;

    @Autowired
    private ShoppingCartItemRepo shoppingCartItemRepo;

    //addCart ->cand creez un user
    public boolean addCart(ShoppingCart shoppingCart)
    {
        try {
            shoppingCartRepo.save(shoppingCart);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //deleteCart -> cand sterg un user
    public boolean deleteCart(ShoppingCart shoppingCart)
    {
        try {
            shoppingCartRepo.delete(shoppingCart);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //addItem
    public boolean addItem(User user, ShoppingCartItem shoppingCartItem)
    {
        ShoppingCart cart = null;
        try{
            cart = shoppingCartRepo.findByUser(user);
            cart.addItem(shoppingCartItem);
            shoppingCartRepo.save(cart);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //remove item
    public boolean deleteItem(User user, ShoppingCartItem shoppingCartItem)
    {
        ShoppingCart cart = null;
        try{
            cart = shoppingCartRepo.findByUser(user);
            cart.removeItem(shoppingCartItem);
            shoppingCartRepo.save(cart);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //empty cart ->cand se depune o comanda
    public boolean emptyShoppingCart(User user)
    {
        ShoppingCart cart = null;
        try{
            cart = shoppingCartRepo.findByUser(user);
            cart.setItems(new ArrayList<ShoppingCartItem>());
            shoppingCartRepo.save(cart);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ShoppingCart findCart(User user)
    {
        ShoppingCart cart = null;
        try{
            cart = shoppingCartRepo.findByUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cart;
    }
}
