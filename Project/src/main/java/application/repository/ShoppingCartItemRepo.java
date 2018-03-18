package application.repository;

import application.model.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartItemRepo extends JpaRepository<ShoppingCartItem, Integer> {
}
