package application.controller;

import application.model.ShoppingCart;
import application.model.ShoppingCartItem;
import application.model.User;
import application.service.BookService;
import application.service.ShoppingCartItemService;
import application.service.ShoppingCartService;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartItemService shoppingCartItemService;

    @Autowired
    private BookService bookService;

    //addItem
    @CrossOrigin(origins="http://localhost:4200")
    @RequestMapping(value = "/addItem", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> addItem(@RequestParam(value = "username")String username,@RequestBody ShoppingCartItem item)
    {
        User user = userService.viewUser(username);
        shoppingCartItemService.addItem(item);

        if(shoppingCartService.addItem(user,item) == true)
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        else
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }

    //deleteItem
    @CrossOrigin(origins="http://localhost:4200")
    @RequestMapping(value = "/deleteItem", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> deleteItem(@RequestParam(value = "username")String username,@RequestBody ShoppingCartItem item)
    {
        User user = userService.viewUser(username);

        if(shoppingCartService.deleteItem(user,item) == true && shoppingCartItemService.deleteItem(item))
        {
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins="http://localhost:4200")
    @RequestMapping(value = "/getCart", method = RequestMethod.POST)
    public ResponseEntity<ShoppingCart> getCart(@RequestParam(value = "username")String username)
    {
        User user = userService.viewUser(username);
        ShoppingCart shoppingCart = shoppingCartService.findCart(user);
        if(shoppingCart != null)
            return new ResponseEntity<ShoppingCart>(shoppingCart,HttpStatus.OK);
        else
                return new ResponseEntity<ShoppingCart>(shoppingCart,HttpStatus.BAD_REQUEST);
    }
}
