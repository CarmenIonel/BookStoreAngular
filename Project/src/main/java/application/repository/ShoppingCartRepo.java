package application.repository;

import application.model.ShoppingCart;
import application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, Integer> {

    ShoppingCart findByUser(User user);
}
