package application.controller;

import application.model.ShoppingCart;
import application.model.User;
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
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    //login
    @CrossOrigin(origins="http://localhost:4200")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<User> login(@RequestBody User user)
    {
        User aux = userService.viewUser(user.getUsername());
        if(aux.getPassword().equals(user.getPassword()))
            return new ResponseEntity<User>(aux,HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<User>(aux,HttpStatus.FORBIDDEN);
    }

    //addUser ->Register
    @CrossOrigin(origins="http://localhost:4200")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> addUser(@RequestBody User user)
    {
        ShoppingCart cart = new ShoppingCart();
        cart.setUser(user);
        user.setRole("USER");
        if(userService.addUser(user) == true && shoppingCartService.addCart(cart) ==true )
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        else
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }

    //deleteUser ->Manage my account
    @CrossOrigin(origins="http://localhost:4200")
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> deleteUser(@RequestParam(value = "username")String username)
    {
        ShoppingCart cart = shoppingCartService.findCart(userService.viewUser(username));
        if(shoppingCartService.deleteCart(cart) == true && userService.deleteUser(username) == true )
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        else
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }

    //updateUser ->Manage my account
    @CrossOrigin(origins="http://localhost:4200")
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user)
    {
        if(userService.updateUser(user.getUsername(), user) == true)
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        else
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }

    //viewUser ->Manage my account
    @CrossOrigin(origins="http://localhost:4200")
    @RequestMapping(value = "/viewUser", method = RequestMethod.POST)
    public ResponseEntity<User> viewUser(@RequestParam(value = "username")String username)
    {
        User user = null;
        user = userService.viewUser(username);
        if(user != null)
            return new ResponseEntity<User>(user,HttpStatus.OK);
        else
            return new ResponseEntity<User>(user,HttpStatus.BAD_REQUEST);
    }
}
