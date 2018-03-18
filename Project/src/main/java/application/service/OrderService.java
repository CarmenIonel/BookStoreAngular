package application.service;

import application.model.Order;
import application.model.User;
import application.repository.OrderRepo;
import application.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserRepo userRepo;

    //addOrder
    public boolean addOrder(Order order)
    {
        try{
            orderRepo.save(order);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //find all orders of a user
    public List<Order> allOrders(String username)
    {
        List<Order> orders = null;
        User user = null;
        try{
            user = userRepo.findByUsername(username);
            orders = orderRepo.findByUser(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return orders;
    }
}
