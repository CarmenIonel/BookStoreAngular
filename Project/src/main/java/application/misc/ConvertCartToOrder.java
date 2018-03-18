package application.misc;

import application.model.Order;
import application.model.OrderItem;
import application.model.ShoppingCart;
import application.repository.OrderItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ConvertCartToOrder {

    @Autowired
    private OrderItemRepo orderItemRepo;

    public Order convert(ShoppingCart cart, String delivery)
    {
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setDeliveryDate(delivery);
        order.setPrice(cart.getTotalPrice());

        List<OrderItem> items = new ArrayList<OrderItem>();

        for(int i = 0; i< cart.getItems().size(); i++)
        {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cart.getItems().get(i).getBook());
            orderItem.setBooksOrdered(cart.getItems().get(i).getBooksOrdered());
            items.add(orderItem);
            orderItemRepo.save(orderItem);
        }

        order.setItems(items);

        return order;
    }
}
