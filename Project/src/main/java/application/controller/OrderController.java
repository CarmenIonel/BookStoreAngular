package application.controller;


import application.misc.ConvertCartToOrder;
import application.model.Order;
import application.model.ShoppingCart;
import application.model.ShoppingCartItem;
import application.model.User;
import application.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private MailService mailService;

    @Autowired
    private ShoppingCartItemService shoppingCartItemService;

    @Autowired
    private ConvertCartToOrder convertCartToOrder;

    //find all orders of the logged user
    @CrossOrigin(origins="http://localhost:4200")
    @RequestMapping(value = "/allOrders", method = RequestMethod.POST)
    public ResponseEntity<List<Order>> allOrders(@RequestParam(value = "username")String username)
    {
        List<Order> orders = orderService.allOrders(username);
        if(orders !=null)
            return new ResponseEntity<List<Order>>(orders,HttpStatus.OK);
        else
            return new ResponseEntity<List<Order>>(orders,HttpStatus.BAD_REQUEST);
    }

    //add order
    @CrossOrigin(origins="http://localhost:4200")
    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> addOrder(@RequestParam(value = "username")String username)
    {
        User user = userService.viewUser(username);

        ShoppingCart shoppingCart = shoppingCartService.findCart(user);

        List<ShoppingCartItem> aux = new ArrayList<ShoppingCartItem>();
        for(int i=0; i<shoppingCart.getItems().size(); i++)
            aux.add(shoppingCart.getItems().get(i));;

        Date today = new Date();
        SimpleDateFormat formattedDate = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 3);  // number of days to add
        String deliveryDate = (String)(formattedDate.format(c.getTime()));

        Order order=convertCartToOrder.convert(shoppingCart, deliveryDate);
        if(user != null)
        {
            try {
                orderService.addOrder(order);
                shoppingCartService.emptyShoppingCart(user);
                for(int i = 0; i<aux.size(); i++)
                {
                    shoppingCartItemService.deleteItem(aux.get(i));
                }
                mailService.sendRecomandation(order);

            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
            }

        }
        else
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

}
