package application.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="shoppingCart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private double totalPrice;

    @OneToMany
    private List<ShoppingCartItem> items;

    @OneToOne
    private User user;

    public ShoppingCart() {
        this.totalPrice = 0.0;
        this.items = new ArrayList<ShoppingCartItem>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<ShoppingCartItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCartItem> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addItem(ShoppingCartItem item)
    {
        if(item != null)
        {
            items.add(item);
            totalPrice+= item.getBook().getPrice() * item.getBooksOrdered();
        }
    }

    public void removeItem(ShoppingCartItem item)
    {
        if(item != null)
        {
            for(int i=0; i<items.size(); i++)
                if(items.get(i).getId() == item.getId())
                {
                    items.remove(i);
                    totalPrice-= item.getBook().getPrice() * item.getBooksOrdered();
                }
        }
    }

    public void empty()
    {
        for(int i= 0; i<items.size(); i++)
            items.remove(i);
        totalPrice = 0.0;
    }
}
