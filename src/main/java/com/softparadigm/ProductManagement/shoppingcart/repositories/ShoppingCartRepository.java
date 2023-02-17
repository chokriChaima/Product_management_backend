package com.softparadigm.ProductManagement.shoppingcart.repositories;

import com.softparadigm.ProductManagement.shoppingcart.entities.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCart,String> {
    @Query("{ 'paid' : ?0 }")
    List<ShoppingCart> findPaidShoppingCart(boolean paid);

}
